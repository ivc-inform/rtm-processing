isc.Canvas.addProperties
	setBlinkBorder: (newBorder, intervalBlink, timeoutBlink) ->
		if not intervalBlink?
			intervalBlink = 500

		if not timeoutBlink
			timeoutBlink = 5000

		border = @border
		step = 0

		intervalID = window.setInterval ->
			                                if step is 0
				                                @setBorder newBorder
				                                step = 1
			                                else
				                                @setBorder border
				                                step = 0
			                                return
		                                ,
			                                intervalBlink

		timeOutID = window.setTimeout ->
											  clearInterval intervalID
											  clearTimeout timeOutID
											  @setBorder border
											  return
										,
			                              timeoutBlink
		return

	setBlinkBorder1: (newBorder, intervalBlink) ->

		intervalBlink ?= 500

		border = @border
		step = 0

		intervalID = window.setInterval =>
			                                if step is 0
				                                @setBorder newBorder
				                                step = 1
			                                else
				                                @setBorder border
				                                step = 0
			                                return
		                                ,
			                                intervalBlink

		intervalID: intervalID
		border    : border
		canvas    : @
		return

	getRootCanvas: ->
		if not @getParentCanvas()? then return @
		res = @getParentCanvas()

		while res
			result = res
			res = res.getParentCanvas()

		result

	initWidget: ->
		isc.Class.addGlobalSS @getIdentifier(), @
		return

	setShowResizeBar1: (value) -> @setShowResizeBar(value); @
	hide1 : -> @hide(); @

	###destroy: ->
		@beforeDestroy? @
		@Super "destroy", arguments
		return###
