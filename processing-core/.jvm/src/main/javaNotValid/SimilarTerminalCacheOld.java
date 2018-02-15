tokenizeimport java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.regex.Pattern;

import com.google.common.base.MoreObjects;
import com.google.common.base.Stopwatch;
import com.mfms.advisa.domain.merchant.Terminal;
import com.mfms.pushserver.cache.AbstractDataCache;
import com.mfms.pushserver.cache.DataCacheUpdateException;
import com.mfms.pushserver.monitor.MonitorAvgCounter.TimeUnit;
import com.mfms.pushserver.monitor.MonitorAvgSpeedCounter;
import com.mfms.pushserver.utils.ProcessorException;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.simmetrics.Metric;
import org.simmetrics.metrics.CosineSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedMetric;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

import static java.util.Arrays.asList;
import static java.util.Collections.emptySet;
import static org.apache.commons.lang.StringUtils.countMatches;
import static org.apache.commons.lang.StringUtils.substringAfterLast;
import static org.apache.commons.lang.StringUtils.upperCase;


@Service
@ManagedResource("MFMS:type=AdvisaWeb,kind=Cache,name=SimilarTerminalCache")

public class SimilarTerminalCacheOld extends AbstractDataCache {

	private final SessionFactory sessionFactory;

	private final Pattern replaceToWhitespaceRegexp;

	private final Pattern tokenizePattern = Pattern.compile("\\s+");

	private final float minimumSimilarityCategoryMetricValue;

	private final float minimumSimilarityMerchantUnitMetricValue;

	private final int minimumTerminalNameLength;

	private final int forkJoinThreadCount;

	private List<TerminalParams> terminalParamList = Collections.emptyList();

	protected final MonitorAvgSpeedCounter avgFindSpeedCounter = new MonitorAvgSpeedCounter(TimeUnit.second);

	private Metric<Set<String>> stringMetric;

	private volatile long missCount;

	private volatile long hitCount;

	private final ForkJoinPool forkJoinPool;


	@Autowired
	public SimilarTerminalCache(SessionFactory sessionFactory,
	                            @Value("${similarTerminalCache.replaceToWhitespaceRegexp}") Pattern replaceToWhitespaceRegexp,
	                            @Value("${similarTerminalCache.minimumSimilarityCategoryMetricValue}") float minimumSimilarityCategoryMetricValue,
	                            @Value("${similarTerminalCache.minimumSimilarityMerchantUnitMetricValue}") float minimumSimilarityMerchantUnitMetricValue,
	                            @Value("${similarTerminalCache.minimumTerminalNameLength}") int minimumTerminalNameLength,
	                            @Value("${similarTerminalCache.forkJoinThreadCount}") int forkJoinThreadCount
	) {
		this.sessionFactory = sessionFactory;
		this.replaceToWhitespaceRegexp = replaceToWhitespaceRegexp;
		this.minimumSimilarityCategoryMetricValue = minimumSimilarityCategoryMetricValue;
		this.minimumSimilarityMerchantUnitMetricValue = minimumSimilarityMerchantUnitMetricValue;
		this.minimumTerminalNameLength = minimumTerminalNameLength;
		this.forkJoinThreadCount = forkJoinThreadCount;
		this.forkJoinPool = new ForkJoinPool(forkJoinThreadCount);
	}

	@Override
	protected String getMonitorString() {
		return "cacheMap.size=" + getCacheSize() + ",hits=" + getSuccessRate() + "% (" + getHitCount() + " hits, " + getMissCount() + " miss)";
	}

	@ManagedMetric
	public long getMissCount() {
		return missCount;
	}

	@ManagedMetric
	public long getAvgFindSpeed() {
		return avgFindSpeedCounter.stat();
	}

	@ManagedMetric
	public long getHitCount() {
		return hitCount;
	}

	@ManagedMetric
	public long getSuccessRate() {
		return missCount + hitCount == 0 ? 0 : hitCount * 100 / (missCount + hitCount);
	}

	@ManagedMetric
	public int getCacheSize() {
		return terminalParamList.size();
	}

	@Override
	@Value("${similarTerminalCache.cacheUpdateThreadWaitMillis}")
	public void setCacheUpdateThreadWaitMillis(long cacheUpdateThreadWaitMillis) {
		super.setCacheUpdateThreadWaitMillis(cacheUpdateThreadWaitMillis);
	}

	@Override
	protected void initInternal() throws ProcessorException {

		stringMetric = new CosineSimilarity<>();

		super.initInternal();
	}

	private String simplify(String input) {
		return replaceToWhitespaceRegexp.matcher(input).replaceAll("#").toUpperCase();
	}

	private Set<String> tokenize(String input) {
		if (input == null || input.isEmpty()) {
			return emptySet();
		}

		return new HashSet<>(asList(tokenizePattern.split(input)));
	}

	@Override
	public void updateCache() throws DataCacheUpdateException {

		Session session = sessionFactory.openSession();

		try {

			Stopwatch sw = Stopwatch.createStarted();

			//noinspection unchecked
			List<Terminal> terminalList = session.createQuery("select t from Terminal t where t.merchantName is not null and (t.transactionCategory is not null or t.merchantUnit is not null)")
					.setFetchSize(100)
					.list();

			List<TerminalParams> newTerminalParamsList = new ArrayList<>(20000);

			for (Terminal terminal : terminalList) {

				String merchantName = terminal.getMerchantName();
				if (isWeirdNameForTerminal(merchantName)) {
					log.debug("Skip using terminal [{}] for similarity due to it is weird name (empty components)", merchantName);
					continue;
				}

				newTerminalParamsList.add(new TerminalParams(terminal));
			}

			this.terminalParamList = new CopyOnWriteArrayList<>(newTerminalParamsList);

			log.debug("Loading [{}] terminals took {}ms", this.terminalParamList.size(), sw.elapsed(java.util.concurrent.TimeUnit.MILLISECONDS));

		} finally {
			session.close();
		}
	}

	public boolean isWeirdNameForTerminal(String merchantName) {
		merchantName = simplify(merchantName);
		return countMatches(merchantName, "/") > 1 && StringUtils.isBlank(substringAfterLast(merchantName, "/"));
	}

	public void addTerminal(Terminal terminal) {
		if ((terminal.getMerchantUnit() != null || terminal.getTransactionCategory() != null) && !isWeirdNameForTerminal(terminal.getMerchantName())) {
			this.terminalParamList.add(new TerminalParams(terminal));
		}
	}

	public SimilarTerminalFindResult findSimilarTerminal(String merchantName) {

		if (merchantName == null || merchantName.length() < minimumTerminalNameLength || isWeirdNameForTerminal(merchantName)) {
			return null;
		}

		Stopwatch stopwatch = Stopwatch.createStarted();

		int countSlashes = countMatches(merchantName, "/");
		Set<String> tokens = SimilarTerminalCache.this.tokenize(SimilarTerminalCache.this.simplify(merchantName));

		ForkJoinTask<SimilarTerminalFindResult> resultTask = forkJoinPool.submit(new SimilarTerminalFindResultTask(merchantName, countSlashes, tokens));

		try {
			SimilarTerminalFindResult result = resultTask.get();

			avgFindSpeedCounter.inc(1, stopwatch.elapsed(java.util.concurrent.TimeUnit.MILLISECONDS));

			return result;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return null;
	}

	private SimilarTerminalFindResult findSimilarTerminalInternal(String merchantName, int start, int end, int countSlashes, Set<String> tokenSet) {

		float merchantUnitMaxValue = minimumSimilarityMerchantUnitMetricValue;
		float categoryMaxValue = minimumSimilarityCategoryMetricValue;

		TerminalParams merchantUnitDescriptor = null;
		TerminalParams categoryDescriptor = null;

		for (TerminalParams terminalParam : this.terminalParamList.subList(start, end)) {

			float value = stringMetric.compare(tokenSet, terminalParam.terminalNameTokens);

			if (value >= merchantUnitMaxValue && countSlashes > 0 && countSlashes == terminalParam.countSlashes) {
				merchantUnitMaxValue = value;
				merchantUnitDescriptor = terminalParam;

				if (value >= 1.0) {
					break;
				}

			} else if (value >= categoryMaxValue) {

				categoryMaxValue = value;
				categoryDescriptor = terminalParam;

			} else if (!terminalParam.merchantNameTokens.isEmpty()) {

				value = stringMetric.compare(tokenSet, terminalParam.merchantNameTokens);

				if (value >= categoryMaxValue) {
					categoryMaxValue = value;
					categoryDescriptor = terminalParam;
				}
			}
		}

		SimilarTerminalFindResult result = null;

		if (merchantUnitDescriptor != null && countSlashes > 0 && countSlashes == merchantUnitDescriptor.countSlashes) {
			log.debug("Found similar terminals for [terminal:{}]: [merchantName:{};merchantUnitId:{};categoryId:{}]: {} ({})",
					merchantName,
					merchantUnitDescriptor.terminalName,
					merchantUnitDescriptor.merchantUnitId,
					merchantUnitDescriptor.categoryId,
					merchantUnitMaxValue,
					minimumSimilarityMerchantUnitMetricValue
			);
			result = new SimilarTerminalFindResult(merchantUnitDescriptor.merchantUnitId, merchantUnitDescriptor.categoryId, merchantUnitDescriptor.terminalId, merchantUnitMaxValue);
			hitCount++;
		} else if (categoryDescriptor != null) {
			log.debug("Found similar terminals for [terminal:{}]: [merchantName:{};categoryId:{}]: {} ({})",
					merchantName,
					categoryDescriptor.terminalName,
					categoryDescriptor.categoryId,
					categoryMaxValue,
					minimumSimilarityCategoryMetricValue
			);
			result = new SimilarTerminalFindResult(null, categoryDescriptor.categoryId, categoryDescriptor.terminalId, categoryMaxValue);
			hitCount++;
		} else {
			missCount++;
		}
		return result;
	}

	public class SimilarTerminalFindResultTask extends RecursiveTask<SimilarTerminalFindResult> {

		private static final long serialVersionUID = 3031290715595851156L;

		private final int countSlashes;
		private final int start;
		private final int end;
		private final String merchantName;
		private final Set<String> tokens;

		public SimilarTerminalFindResultTask(int start, int end, String merchantName, int countSlashes, Set<String> tokens) {
			this.start = start;
			this.end = end;
			this.merchantName = merchantName;
			this.countSlashes = countSlashes;
			this.tokens = tokens;
		}

		public SimilarTerminalFindResultTask(String merchantName, int countSlashes, Set<String> tokens) {
			this(0, terminalParamList.size(), merchantName, countSlashes, tokens);
		}

		@Override
		protected SimilarTerminalFindResult compute() {

			try {
				final int length = end - start;

				if (terminalParamList.size() == 0 ||
						length < terminalParamList.size() / forkJoinThreadCount ||
						forkJoinPool.getQueuedSubmissionCount() > 100) {
					return findSimilarTerminalInternal(merchantName, start, end, countSlashes, tokens);
				}

				final int split = length / 2;

				final SimilarTerminalFindResultTask left = new SimilarTerminalFindResultTask(start, start + split, merchantName, countSlashes, tokens);
				ForkJoinTask<SimilarTerminalFindResult> leftTask = left.fork();

				final SimilarTerminalFindResultTask right = new SimilarTerminalFindResultTask(start + split, end, merchantName, countSlashes, tokens);

				SimilarTerminalFindResult rightResult = right.compute();
				SimilarTerminalFindResult leftResult = leftTask.get();

				if (rightResult == null) {

					return leftResult;

				} else if (leftResult == null) {

					return rightResult;

				} else if (rightResult.similarity > leftResult.similarity) {

					return rightResult;

				} else {
					return leftResult;
				}

			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return null;
			}
		}
	}

	public class SimilarTerminalFindResult {
		public final String merchantUnitId;
		public final long categoryId;
		public final long terminalId;
		public final float similarity;

		public SimilarTerminalFindResult(String merchantUnitId, long categoryId, long terminalId, float similarity) {
			this.merchantUnitId = merchantUnitId;
			this.categoryId = categoryId;
			this.terminalId = terminalId;
			this.similarity = similarity;
		}

		@Override
		public String toString() {
			return MoreObjects.toStringHelper(this)
					.omitNullValues()
					.add("merchantUnitId", merchantUnitId)
					.add("categoryId", categoryId)
					.add("terminalId", terminalId)
					.add("similarity", similarity)
					.toString();
		}
	}

	public class TerminalParams {
		public final String terminalName;
		public final String merchantName;
		public final String merchantUnitId;
		public final long categoryId;
		public final long terminalId;
		public final Set<String> terminalNameTokens;
		public final Set<String> merchantNameTokens;
		public final int countSlashes;

		public TerminalParams(Terminal terminal) {

			this.terminalId = terminal.getId();
			this.terminalName = upperCase(terminal.getMerchantName());
			this.countSlashes = countMatches(this.terminalName, "/");

			String merchantName = substringAfterLast(this.terminalName, "/");

			if (merchantName != null && merchantName.length() > minimumTerminalNameLength) {
				this.merchantName = merchantName;
				this.merchantNameTokens = tokenize(simplify(this.merchantName));
			} else {
				this.merchantName = null;
				this.merchantNameTokens = Collections.emptySet();
			}

			this.terminalNameTokens = this.terminalName != null && this.terminalName.length() > minimumTerminalNameLength
					? tokenize(simplify(this.terminalName))
					: Collections.emptySet();

			this.merchantUnitId = terminal.getMerchantUnit() != null && this.terminalName != null
					? terminal.getMerchantUnit().getId()
					: null;

			this.categoryId = terminal.getTransactionCategory() != null
					? terminal.getTransactionCategory().getId()
					: -1;
		}
	}

}


