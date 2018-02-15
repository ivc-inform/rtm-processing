
public class CoariAdvisaOutMessageReceiver implements CoariAdvisaOutMessageConsumer {

	private static Category cat = Category.getInstance(CoariAdvisaOutMessageReceiver.class.getName());
	
    private CoariAdvisaOutMessageSource advisaOutMessageCoariSource;
	private CoariAviRouterAdvisaOutMessageService coariAviRouterAdvisaOutMessageService;
	
	private StoredMap checkUpMap;
	
	private ThreadPool monitorStateThread;
	
	public void init(CoariAdvisaOutMessageReceiverConf conf,
			AviRouterRegistryAccessor registryAccessor,
			AviRouterMonitorAccessor monitorAccessor,
			AviRouterDatabaseAccessor databaseAccessor,
			CoariAdvisaOutMessageSource advisaOutMessageCoariSource) throws CoariAdvisaOutMessageReceiverException {
		this.advisaOutMessageCoariSource = advisaOutMessageCoariSource;
		
		try {
			this.checkUpMap = new StoredMap(cat, 
					conf.getCheckUpMapDatabaseHomeDirectory(), 
					conf.getCheckUpMapDatabaseCatalog(), 
					conf.getCheckUpMapDatabaseConfigParams(), 
					ExternalizableMock.class);
		} catch (Exception e) {
			cat.error("Can not init check up map: "+e.getMessage(), e);
			throw new CoariAdvisaOutMessageReceiverException("Can not init check up map: "+e.getMessage(), e);
		}
		
		String monitorStateThreadName = conf.getMonitorStateThreadName();
		long monitorStateThreadSleepMillis = conf.getMonitorStateThreadSleepMillis();
		ThreadJob monitorStateJob = new MonitorStateJob();
		this.monitorStateThread = new ThreadPool(cat, 
				1, 
				monitorStateThreadName, 
				monitorStateThreadSleepMillis, 
				-1, 
				-1, 
				monitorStateJob);

		RegAviRouter regAviRouter;
		try {
			cat.info("Getting registry avi router...");
			regAviRouter = registryAccessor.getRegAviRouter();
		} catch(RegistryAccessorException e) {
			cat.error("Can not get registry avi router: "+e.getMessage(), e);
			throw new CoariAdvisaOutMessageReceiverException("Can not get registry avi router: "+e.getMessage(), e);
		}
		
		if(regAviRouter == null) {
			cat.error("Can not find registry avi router");
			throw new CoariAdvisaOutMessageReceiverException("Can not find registry avi router");
		} else {
			cat.info("Registry avi router code <"+regAviRouter.getCode()+"> found");
		}
		
		cat.info("Initing coari avi router aom service...");
		this.coariAviRouterAdvisaOutMessageService = new CoariAviRouterAdvisaOutMessageServiceImpl(this,
				regAviRouter.getRmiHost(),
				regAviRouter.getRmiPort(),
				"CoariAviRouterAdvisaOutMessageService");
		cat.info("Coari avi router aom service inited");
		
	}
	
	public void start() throws CoariAdvisaOutMessageReceiverException {
		try {
			checkUpMap.open();
			checkUpMap.clear();
		} catch (Exception e) {
			cat.error("Can not open check up map: "+e.getMessage(), e);
			throw new CoariAdvisaOutMessageReceiverException("Can not open check up map: "+e.getMessage(), e);
		}
		
		cat.info("Starting coari avi router aom service...");
		try {
			((CoariAviRouterAdvisaOutMessageServiceImpl)coariAviRouterAdvisaOutMessageService).open();
			cat.info("Coari avi router aom service started");
		} catch(CoariAviRouterOpenException e) {
		    cat.error("Can not start coari avi router aom service: "+e.getMessage(), e);
		    throw new CoariAdvisaOutMessageReceiverException("Can not start coari avi router aom service: "+e.getMessage(), e);
		}
		
		monitorStateThread.start();
	}
	
	public void stop() throws CoariAdvisaOutMessageReceiverException {
		cat.info("Stopping coari avi router aom service...");
		try {
			((CoariAviRouterAdvisaOutMessageServiceImpl)coariAviRouterAdvisaOutMessageService).close();
			cat.info("Coari avi router aom service stopped");
		} catch(CoariAviRouterCloseException e) {
		    cat.error("Can not stop coari avi router aom service: "+e.getMessage(), e);
		}
		
		checkUpMap.close();
		
		monitorStateThread.stop();
	}
	
	public void close() throws CoariAdvisaOutMessageReceiverException {
	}

	public void consumeCoariAdvisaOutMessage(CoariAdvisaOutMessage coariAdvisaOutMessage) throws CoariAdvisaOutMessageConsumerException {
        cat.info("R-ed coari aom");
        AdvisaOutMessage advisaOutMessage = convertCoariAdvisaOutMessage(coariAdvisaOutMessage);
        if (advisaOutMessage != null) {
        	try {
				advisaOutMessageCoariSource.consumeAdvisaOutMessage(advisaOutMessage);
			} catch (AdvisaOutMessageSourceException e) {
				cat.error("Can not consume aom: "+e.getMessage(), e);
				throw new CoariAdvisaOutMessageConsumerException("Can not consume aom: "+e.getMessage(), e);
			}
        }
	}
	
	public void consumeCoariAdvisaOutMessageList(List coariAdvisaOutMessageList) throws CoariAdvisaOutMessageConsumerException {
        cat.info("R-ed coari aom list size "+coariAdvisaOutMessageList.size());
        Date now = new Date();
        List advisaOutMessageList = new ArrayList(coariAdvisaOutMessageList.size());
		for(Iterator i = coariAdvisaOutMessageList.iterator(); i.hasNext();) {
			CoariAdvisaOutMessage coariAdvisaOutMessage = (CoariAdvisaOutMessage)i.next();
			AdvisaOutMessage advisaOutMessage = convertCoariAdvisaOutMessage(coariAdvisaOutMessage);
			if (advisaOutMessage != null) {
				advisaOutMessageList.add(advisaOutMessage);
			}
		}
		
		try {
			advisaOutMessageCoariSource.consumeAdvisaOutMessageList(advisaOutMessageList);
		} catch(AdvisaOutMessageProcessorException e) {
			cat.error("Can not consume aom list: "+e.getMessage(), e);
			throw new CoariAdvisaOutMessageConsumerException("Can not consume aom list: "+e.getMessage(), e);
		}
	}
	
	public boolean isUp() throws CoariAdvisaOutMessageConsumerException {
		long time = System.currentTimeMillis();
		synchronized (checkUpMap) {
			checkUpMap.put(Long.valueOf(time), new ExternalizableMock());
			return checkUpMap.remove(Long.valueOf(time)) != null;
		}
	}
	
	private AdvisaOutMessage convertCoariAdvisaOutMessage(CoariAdvisaOutMessage coariAdvisaOutMessage) {
		int dlvStatus = convertCoariAdvisaOutMessageDlvStatus(coariAdvisaOutMessage.getDlvStatus());
		if(dlvStatus == -1) {
		    cat.error("Unknown coari aom dlv status <"+coariAdvisaOutMessage.getDlvStatus()+">, ignoring aom id <"+coariAdvisaOutMessage.getAdvisaOutMessageId()+">");
		    return null;
		}
		
		AdvisaOutMessage message = new AdvisaOutMessage();

		message.setAdvisaOutMessageId(coariAdvisaOutMessage.getAdvisaOutMessageId());
		message.setGtimestamp(coariAdvisaOutMessage.getGtimestamp());
		message.setTimestamp(coariAdvisaOutMessage.getTimestamp());
		message.setConnectorId(coariAdvisaOutMessage.getConnectorId());
		message.setSubject(coariAdvisaOutMessage.getSubject());
		message.setOperatorUnitRegionId(coariAdvisaOutMessage.getOperatorUnitRegionId());
		message.setAddress(coariAdvisaOutMessage.getAddress());
		message.setPriority(coariAdvisaOutMessage.getPriority());
		message.setText(coariAdvisaOutMessage.getText());
		message.setComment(coariAdvisaOutMessage.getComment());
		message.setTot(coariAdvisaOutMessage.getTot());
		message.setOpt(coariAdvisaOutMessage.getOpt());
		message.setType(coariAdvisaOutMessage.getType());
		message.setProcessedAt(coariAdvisaOutMessage.getProcessedAt());

		message.setDlvStatus(dlvStatus);
		message.setDlvStatusAt(coariAdvisaOutMessage.getDlvStatusAt());
		message.setDlvError(coariAdvisaOutMessage.getDlvError());
        message.setSentAt(coariAdvisaOutMessage.getSentAt());
        message.setOperationType(coariAdvisaOutMessage.getOperationType());

		return message;
	}
	
	private int convertCoariAdvisaOutMessageDlvStatus(int dlvStatus) {
	    switch(dlvStatus) {
	    case CoariAdvisaOutMessage.DLV_STATUS_ENQUEUED:
	    	return AdvisaOutMessage.DLV_STATUS_ENQUEUED;
	    case CoariAdvisaOutMessage.DLV_STATUS_SENT:
	    	return AdvisaOutMessage.DLV_STATUS_SENT;
	    case CoariAdvisaOutMessage.DLV_STATUS_DELIVERED:
	    	return AdvisaOutMessage.DLV_STATUS_DELIVERED;
	    case CoariAdvisaOutMessage.DLV_STATUS_APPROVED:
	    	return AdvisaOutMessage.DLV_STATUS_APPROVED;
	    case CoariAdvisaOutMessage.DLV_STATUS_FAILED:
	    	return AdvisaOutMessage.DLV_STATUS_FAILED;
	    case CoariAdvisaOutMessage.DLV_STATUS_CANCELLED:
	    	return AdvisaOutMessage.DLV_STATUS_CANCELLED;
	    default:
	    	return -1;
	    }
	}
	
	private class MonitorStateJob implements ThreadJob {
		public boolean perform() throws Exception {
			cat.info("Current state: " +
			    	"monitorStateThread <"+monitorStateThread.getState()+">");
			return true;
		}
		public boolean isExitAllowed() throws Exception {
			return true;
		}
	}	
	
	public static class ExternalizableMock implements Externalizable {

		private static final long serialVersionUID = 1L;
		
		public ExternalizableMock() {
			super();
		}

		public void writeExternal(ObjectOutput out) throws IOException {
			out.writeByte(0);
		}

		public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
			in.readByte();
		}
		
	}

}

