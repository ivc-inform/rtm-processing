if not isc.module_RealtimeMessaging?
	isc.module_RealtimeMessaging = 1
	isc.defineClass("MessagingSS").addClassProperties
		fireCallback            : isc.Class.fireCallback
		_maxRecentIDLength      : 20
		_subscribeReconnectDelay: 100
		eventStream             : ->
			res = not not window.EventSource
			if not res? then isc.error "Ваш браузер не поддерживает технологию SSE, что делает невозможным автоматическое получение сообщений от сервера. (Данная задача находится в доработке.)"
			res
		_channels               : {}
		_recentIDList           : []
		_handleEventSourceError : (e) -> undefined
		getSubscribedChannels   : -> isc.getKeys(@_channels)
		subscribe               : (channels, callback, subscribeCallback, target, event) ->
			if channels?
				if isc.isA.Function(subscribeCallback) is false and isc.isA.Object(subscribeCallback) is true and not target?
					target = subscribeCallback

				if isc.isA.Array(channels) is false
					channels = [channels]

				executed = false

				channels.forEach (channel) =>
					@_channels[channel] =
							isChannel: true
							target   : target
							callback : callback
							event : event

					executed = true
					return

				if executed is true
					@_reconnect subscribeCallback, event
			return

		_emptyChannelsObject: (object) ->
			if isc.isAn.Object(object) is false
				return false
			for own value of object
				if object.isChannel is true
					return false
			return true

		unsubscribe: (channels, unSubscribeCallback, event) ->
			if channels?
				if isc.isA.Array(channels) is false
					channels = [channels]

				channels.forEach (channel) =>
					if @_channels[channel]?
						delete @_channels[channel]
						return

				if @_emptyChannelsObject(@_channels) is true
					@disconnect event

				if unSubscribeCallback?
					isc.MessagingSS.fireCallback(unSubscribeCallback)
			return

		connected: ->
			isc.getKeys(@._channels?).length > 0

		_event: "message"

		_cleanup : (event) ->
			if not event?
				event = @_event

			if @_es?
				@_es.removeEventListener event, @_message, false

				@_es.close()
				delete @_es
			return

		disconnect: (event)-> @_cleanup(event); return

		_reconnect: (callback, event) ->
			if isc.Page.isLoaded() isnt true
				if not @_setLoadEventHandler?
					_reconnect = -> isc.MessagingSS._reconnect callback

					isc.Page.setEvent("load", _reconnect)
					@_setLoadEventHandler = true

			if not @_subscribeReconnectTimer?
				_connect = -> isc.MessagingSS._connect(callback, event);return
				@_subscribeReconnectTimer =
					isc.Timer.setTimeout(
						                    _connect,
						                    @_subscribeReconnectDelay,
						                    isc.Timer.MSEC
					                    )
			return

			
		checkSimpleSysContextPath: () ->
			if not @simpleSysContextPath? and not simpleSyS.simpleSysContextPath?
				Log.logError "simpleSysContextPath undefined"
				return
				
		messagingSubscribeURL: ->
			@checkSimpleSysContextPath()
			"#{if @simpleSysContextPath? then @simpleSysContextPath else simpleSyS.simpleSysContextPath}Message/Subscribe"
			
		messagingSendURL : ->
			@checkSimpleSysContextPath()
			"#{if @simpleSysContextPath? then @simpleSysContextPath else simpleSyS.simpleSysContextPath}Message/Send"

		_connect: (callback, event) ->
			if not event?
				event = @_event

			isc.Timer.clear @_subscribeReconnectTimer
			@_subscribeReconnectTimer = null

			@_cleanup()

			if @getSubscribedChannels().length is 0
				if callback?
					isc.MessagingSS.fireCallback callback
					return

			_url = @messagingSubscribeURL()
			
			uriBuilder = isc.URIBuilder.create isc.Page.getURL _url
			
			data = subscribedChannels: isc.JSONSS.encode @_channels

			for own fieldName, value of data
				uriBuilder.setQueryParam fieldName, String data[fieldName]

			uriBuilder.setQueryParam "eventStream", "true"

			if uriBuilder.uri.length > 2000
				Log.logError "Слишком много каналов, uriBuilder.uri > 2000 символов"
				return

			##todo В случае необходимости подключения большого кол-ва каналов, необходимо разбиение их на несколько EventSource

			###console.log "URI: #{uriBuilder.uri}"###
			@_es = new EventSource uriBuilder.uri

			@_es.onerror = @_handleEventSourceError

			@_es.addEventListener event, @_message, false
			if callback?
				MessagingSS.fireCallback callback
			return

		_message: (message) ->
			data = isc.JSONSS.decode message.data
			channels = data.channels

			if isc.isA.Array(channels) is false
				channels = [channels]

			id = message.lastEventId

			if id? and id isnt strEmpty
				if isc.MessagingSS._recentIDList.contains(id) is true
					return undefined

				isc.MessagingSS._recentIDList.push id
				if isc.MessagingSS._recentIDList.length > isc.MessagingSS._maxRecentIDLength
					isc.MessagingSS._recentIDList.shift()

			channels.forEach (channel) =>
				if isc.MessagingSS._channels[channel]?
					channel = isc.MessagingSS._channels[channel]
					callback = channel.callback
					if callback?
						isc.MessagingSS.fireCallback callback, "data", [data], channel
			return

		send: (channels, data, callback) ->
			if isc.isA.Array(channels) is false
				channels = [channels]

			isc.RPCManagerSS.sendRequest
				 actionURL: @messagingSendURL()
				 data     :
					 data    : data
					 channels: channels
				 callback : callback
			return
else
	if isc.isA.Function(isc?.Log?.logError) is true
		isc.Log.logError "Duplicate load of module 'RealtimeMessaging'."
