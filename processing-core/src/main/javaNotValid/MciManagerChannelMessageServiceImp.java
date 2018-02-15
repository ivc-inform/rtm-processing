MciManagerChannelMessageServiceImpl mciManagerMessageService;

	public static void main(String argv[]) throws Exception {
		System.setSecurityManager(new RMISecurityManager());
		Properties props = new Properties();
		props.put("log4j.rootLogger", "DEBUG,dummy");
		props.put("log4j.appender.dummy", "org.apache.log4j.ConsoleAppender");
		props.put("log4j.appender.dummy.layout", "org.apache.log4j.PatternLayout");

		PropertyConfigurator.configure(props);

		String host = argv[0];
		int port = Integer.parseInt(argv[1]);

		cat.info("Starting on "+host+":"+port);
		MciManagerDummy dummy = new MciManagerDummy();
		dummy.start(host, port);
	}

	public void start(String host, int port) {
		try {
			//LocateRegistry.createRegistry(port);

			mciManagerMessageService =
				new MciManagerChannelMessageServiceImpl(cat,
						host,
						port,
						"MciChannelChannelMessageService",
						1000,
						-1);

			cat.info("Starting services");
			mciManagerMessageService.open();
			cat.info("Services started");

			cat.info("Sending messages");
			for(int i = 0; i < 10000; i ++) {
				MciChannelMessage msg = new MciChannelMessage();
				msg.setChannelMessageId(i);
			    cat.info("Sending message: "+msg);
				mciManagerMessageService.processMciChannelMessage(msg);
				Thread.sleep(1000);
			}
			cat.info("Messages sent");

		} catch(Throwable e) {
			e.printStackTrace();
		}

	}