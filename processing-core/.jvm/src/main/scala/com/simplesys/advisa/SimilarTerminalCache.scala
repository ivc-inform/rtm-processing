package com.simplesys.advisa

import java.util.concurrent.{ForkJoinPool, RecursiveTask}

import com.simplesys.log.Logging
import com.simplesys.misc.Helper._

class SimilarTerminalCache extends Logging {

    import com.simplesys.actors.AppConfig._
    import doobie.imports._

    val forkJoinPool = new ForkJoinPool(forkJoinThreadCount)

    //import com.simplesys.doobie._ //Это дожно быть для маппинга полей //Это дожно быть для маппинга полей
    import com.simplesys.doobie._ //Это дожно быть для маппинга полей //Это дожно быть для маппинга полей

    private val sqlText =
        "SELECT STERMINAL, SCATEGORYCODE, SCATEGORYNAME, MERCHANT_NAME FROM TERMINALS_WCATS4MFMD_V"
    //private val sqlText = "SELECT STERMINAL, SCATEGORYCODE, SCATEGORYNAME, MERCHANT_NAME FROM TERMINALS_WCATS4MFMD_VTST"
    
    private val proc = HC.process[Terminal](sqlText, HPS.setFetchSize(dsAdvisa.settings.fetchSize), dsAdvisa.settings.fetchSize)

    val _listTerminal: IndexedSeq[TerminalParams] = proc
      .transact(dsAdvisaTransactor)
      .runLog
      .run
      .map(item => TerminalParams(item))

    //private val stringMetric: CosineSimilarity[String] = new  CosineSimilarity[String]()

    private val terminalMap: Map[String, TerminalParams] =
        _listTerminal.map(tp => (tp.sTerminal, tp))(collection.breakOut)

    def getTerminalByName(
                           merchantName: String): Option[SimilarTerminalFindResult] = {
        val searchStr = merchantName.toUpperCase

        terminalMap
          .get(searchStr)
          .map(
              tp =>
                  // we are disabling cousine similarity metric as it's highly unefficient
                  SimilarTerminalFindResult(tp.sTerminal,
                      tp.sCategoryCode,
                      tp.sCategoryName,
                      tp.merchantName,
                      1)) //orElse findSimilarTerminal(searchStr)

    }

    def cousineSimilarity[T](_a: Set[T], _b: Set[T]): Double = {
        var a = _a
        var b = _b

        val ret =
            if (a.isEmpty && b.isEmpty) 1.0d
            else if (a.isEmpty || b.isEmpty) 0.0d
            else {
                //val fullSet = a union b
                //val fullSetSize = fullSet.size
                val intersect = a intersect b
                val intersectSize = intersect.size
                val aSize = a.size
                val bSize = b.size
                val dotProduct: Double = intersectSize
                val magnitudeA: Double = aSize
                val magnitudeB: Double = bSize
                // Lager set first for performance improvement.
                // See: MultisetUnionSize benchmark
                //            if (a.size < b.size) {
                //                val swap: Set[T] = a
                //                a = b
                //                b = swap
                //            }
                //            for (entry <- a.union(b)) {
                //                val aCount: Double = if (a.contains(entry)) 1 else 0
                //                val bCount: Double = if (b.contains(entry)) 1 else 0
                //                dotProduct += aCount * bCount
                //                magnitudeA += aCount * aCount
                //                magnitudeB += bCount * bCount
                //            }
                //  a·b / (||a|| * ||b||)
                import scala.math._
                dotProduct / (sqrt(magnitudeA) * sqrt(magnitudeB))
            }
        ret
    }

    def findSimilarTerminalSingleThreaded(merchantName: String): Option[SimilarTerminalFindResult] = {

        if (isNotNul(merchantName) && merchantName.length >= minimumTerminalNameLength && !merchantName.isWeirdNameForTerminal) {
            val countSlashes: Int = merchantName countMatches "/"
            val tokenSet = merchantName.simplify.tokenize

            var merchantUnitMaxValue = minimumSimilarityMerchantUnitMetricValue
            var categoryMaxValue = minimumSimilarityCategoryMetricValue

            var merchantUnitDescriptor: Option[TerminalParams] = None
            var categoryDescriptor: Option[TerminalParams] = None

            var break = false

            val iter = _listTerminal.iterator
            while (iter.hasNext && !break) {
                val terminalParam = iter.next()
                //            _listTerminal.foreach {terminalParam =>
                if (!break) {

                    //var value = stringMetric.compare(tokenSet.toMultiset, terminalParam.terminalNameTokens.toMultiset)
                    var value =
                        cousineSimilarity(tokenSet, terminalParam.terminalNameTokens)

                    if (value >= merchantUnitMaxValue && countSlashes > 0 && countSlashes == terminalParam.countSlashes) {
                        merchantUnitMaxValue = value
                        merchantUnitDescriptor = Some(terminalParam)

                        if (value >= 1.0)
                            break = true
                    } else if (value >= categoryMaxValue) {
                        categoryMaxValue = value
                        categoryDescriptor = Some(terminalParam)
                    } else if (terminalParam.merchantNameTokens.nonEmpty) {
                        //value = stringMetric.compare(tokenSet.toMultiset, terminalParam.merchantNameTokens.toMultiset)
                        value =
                          cousineSimilarity(tokenSet, terminalParam.merchantNameTokens)
                        if (value >= categoryMaxValue) {
                            categoryMaxValue = value
                            categoryDescriptor = Some(terminalParam)
                        }
                    }
                }
            }

            merchantUnitDescriptor match {
                case Some(merchUnitDescriptor) =>
                    if (countSlashes > 0 && countSlashes == merchUnitDescriptor.countSlashes)
                        Some(
                            SimilarTerminalFindResult(merchUnitDescriptor.sTerminal,
                                merchUnitDescriptor.sCategoryCode,
                                merchUnitDescriptor.sCategoryName,
                                merchUnitDescriptor.merchantName,
                                merchantUnitMaxValue))
                    else
                        None
                case None =>
                    categoryDescriptor match {
                        case Some(catDescriptor) =>
                            Some(
                                SimilarTerminalFindResult(catDescriptor.sTerminal,
                                    catDescriptor.sCategoryCode,
                                    catDescriptor.sCategoryName,
                                    catDescriptor.merchantName,
                                    categoryMaxValue))
                        case None => None
                    }
            }

        } else
            None

    }

    def findSimilarTerminal(
                             merchantName: String): Option[SimilarTerminalFindResult] = {

        if (isNotNul(merchantName) && merchantName.length >= minimumTerminalNameLength && !merchantName.isWeirdNameForTerminal) {
            val countSlashes: Int = merchantName countMatches "/"
            val tokens = merchantName.simplify.tokenize

            val resultTask = forkJoinPool.submit(
                new SimilarTerminalFindResultTask(merchantName, countSlashes, tokens))
            try {
                resultTask.get()
            } catch {
                case e: Exception =>
                    None
            }

        } else
            None
    }

    private def findSimilarTerminalInternal(
                                             merchantName: String,
                                             start: Int,
                                             end: Int,
                                             countSlashes: Int,
                                             tokenSet: Set[String]): Option[SimilarTerminalFindResult] = {
        var merchantUnitMaxValue = minimumSimilarityMerchantUnitMetricValue
        var categoryMaxValue = minimumSimilarityCategoryMetricValue

        var merchantUnitDescriptor: Option[TerminalParams] = None
        var categoryDescriptor: Option[TerminalParams] = None

        var break = false

        _listTerminal.slice(start, end).foreach { terminalParam =>
            if (!break) {

                //var value = stringMetric.compare(tokenSet.toMultiset, terminalParam.terminalNameTokens.toMultiset)
                var value =
                    cousineSimilarity(tokenSet, terminalParam.terminalNameTokens)

                if (value >= merchantUnitMaxValue && countSlashes > 0 && countSlashes == terminalParam.countSlashes) {
                    merchantUnitMaxValue = value
                    merchantUnitDescriptor = Some(terminalParam)

                    if (value >= 1.0)
                        break = true
                } else if (value >= categoryMaxValue) {
                    categoryMaxValue = value
                    categoryDescriptor = Some(terminalParam)
                } else if (terminalParam.merchantNameTokens.nonEmpty) {
                    //value = stringMetric.compare(tokenSet.toMultiset, terminalParam.merchantNameTokens.toMultiset)
                    value = cousineSimilarity(tokenSet, terminalParam.merchantNameTokens)
                    if (value >= categoryMaxValue) {
                        categoryMaxValue = value
                        categoryDescriptor = Some(terminalParam)
                    }
                }
            }
        }

        merchantUnitDescriptor match {
            case Some(merchUnitDescriptor) =>
                if (countSlashes > 0 && countSlashes == merchUnitDescriptor.countSlashes)
                    Some(
                        SimilarTerminalFindResult(merchUnitDescriptor.sTerminal,
                            merchUnitDescriptor.sCategoryCode,
                            merchUnitDescriptor.sCategoryName,
                            merchUnitDescriptor.merchantName,
                            merchantUnitMaxValue))
                else
                    None
            case None =>
                categoryDescriptor match {
                    case Some(catDescriptor) =>
                        Some(
                            SimilarTerminalFindResult(catDescriptor.sTerminal,
                                catDescriptor.sCategoryCode,
                                catDescriptor.sCategoryName,
                                catDescriptor.merchantName,
                                categoryMaxValue))
                    case None => None
                }
        }
    }

    class SimilarTerminalFindResultTask(val start: Int,
                                        val end: Int,
                                        val merchantName: String,
                                        val countSlashes: Int,
                                        val tokens: Set[String])
      extends RecursiveTask[Option[SimilarTerminalFindResult]] {
        def this(merchantName: String, countSlashes: Int, tokens: Set[String]) =
            this(0, _listTerminal.size, merchantName, countSlashes, tokens)

        override def compute(): Option[SimilarTerminalFindResult] =
            try {
                val length: Int = end - start

                if (_listTerminal.isEmpty || length < _listTerminal.size / forkJoinThreadCount || forkJoinPool.getQueuedSubmissionCount > 100) {
                    findSimilarTerminalInternal(merchantName,
                        start,
                        end,
                        countSlashes,
                        tokens)
                } else {
                    val split: Int = length / 2

                    val left = new SimilarTerminalFindResultTask(start,
                        start + split,
                        merchantName,
                        countSlashes,
                        tokens)
                    val leftTask = left.fork
                    val leftResult = leftTask.get

                    val right = new SimilarTerminalFindResultTask(start + split,
                        end,
                        merchantName,
                        countSlashes,
                        tokens)
                    val rightResult = right.compute()

                    rightResult match {
                        case None =>
                            leftResult match {
                                case None => None
                                case _ => leftResult
                            }
                        case Some(rResult) =>
                            leftResult match {
                                case None => Some(rResult)
                                case Some(lResult) =>
                                    if (rResult.similarity > lResult.similarity)
                                        Some(rResult)
                                    else
                                        Some(lResult)
                            }
                    }
                }
            } catch {
                case e: Exception =>
                    logger error e
                    None
            }
    }

}
