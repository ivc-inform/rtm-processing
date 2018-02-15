package com.simplesys.SmartClient.RPC.props

import com.simplesys.SmartClient.DataBinding.Callbacks.RPCCallback
import com.simplesys.SmartClient.RPC.RequestParams
import com.simplesys.SmartClient.System.props.ClassProps
import com.simplesys.System.Types.PromptStyle.PromptStyle
import com.simplesys.System.Types.RPCTransport.RPCTransport
import com.simplesys.System.Types._
import com.simplesys.System._
import com.simplesys.option.{ScNone, ScOption, ScSome}

import scala.scalajs.js

class RequestCommon[C <: js.Function] extends ClassProps {
    var callback: ScOption[C] = ScNone

    var actionURL: ScOption[URL] = ScNone
    var allowIE9Leak: ScOption[Boolean] = ScNone
    var bypassCache: ScOption[Boolean] = ScNone
    var callbackParam: ScOption[String] = ScNone
    var clientContext: ScOption[JSObject] = ScNone
    var containsCredentials: ScOption[Boolean] = ScNone
    var contentType: ScOption[String] = ScNone
    var downloadResult: ScOption[Boolean] = ScNone
    var downloadToNewWindow: ScOption[Boolean] = ScNone
    var evalResult: ScOption[Boolean] = ScNone
    var evalVars: ScOption[JSObject] = ScNone
    var httpHeaders: ScOption[JSObject] = ScNone
    var httpMethod: ScOption[String] = ScNone
    var httpProxyURL: ScOption[String] = ScNone
    var ignoreTimeout: ScOption[Boolean] = ScNone
    var omitNullMapValuesInResponse: ScOption[Boolean] = ScNone
    var params: ScOption[RequestParams] = ScNone
    var prompt: ScOption[String] = ScNone
    var promptCursor: ScOption[String] = ScNone
    var promptDelay: ScOption[Int] = ScNone
    var promptStyle: ScOption[PromptStyle] = ScNone
    var sendNoQueue: ScOption[Boolean] = ScNone
    var serverOutputAsString: ScOption[Boolean] = ScNone
    var showPrompt: ScOption[Boolean] = ScNone
    var suppressAutoDraw: ScOption[Boolean] = ScNone
    var timeout: ScOption[Int] = ScNone
    var transport: ScOption[RPCTransport] = ScNone
    var useHttpProxy: ScOption[Boolean] = ScNone
    var useSimpleHttp: ScOption[Boolean] = ScNone
    var useXmlHttpRequest: ScOption[Boolean] = ScNone
    var willHandleError: ScOption[Boolean] = ScNone
    var withCredentials: ScOption[Boolean] = ScNone
}

class RPCRequestProps extends RequestCommon[RPCCallback] {
    var data: ScOption[JSAny] = ScNone
}
