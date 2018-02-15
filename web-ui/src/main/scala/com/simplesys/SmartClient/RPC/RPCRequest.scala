package com.simplesys.SmartClient.RPC

import com.simplesys.SmartClient.System.{AbstractClassCompanion, Class, IscArray}
import com.simplesys.System.Types.PromptStyle.PromptStyle
import com.simplesys.System.Types.RPCTransport.RPCTransport
import com.simplesys.System.Types.{ID, URL, void}
import com.simplesys.System._

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSName, ScalaJSDefined}
import scala.scalajs.js.|

@ScalaJSDefined
trait ClientContext extends JSObject {
    val fetchID: ID
}

@js.native
trait RPCRequest extends Class {
    var actionURL: URL
    val allowIE9Leak: Boolean
    var bypassCache: Boolean
    var callback: js.Function3[RPCResponse, JSAny, RPCRequest, _]
    var callbackParam: String
    var clientContext: JSUndefined[ClientContext]
    var containsCredentials: Boolean
    var contentType: String
    var data: JSObject | IscArray[JSObject]
    var downloadResult: Boolean
    var downloadToNewWindow: Boolean
    var evalResult: Boolean
    var evalVars: JSObject
    var httpHeaders: JSObject
    var httpMethod: String
    val httpProxyURL: String
    var ignoreTimeout: Boolean
    var omitNullMapValuesInResponse: Boolean
    var params: JSObject
    var prompt: JSUndefined[String]
    var promptCursor: String
    var promptDelay: Int
    var promptStyle: PromptStyle
    var sendNoQueue: Boolean
    var serverOutputAsString: Boolean
    var showPrompt: Boolean
    var suppressAutoDraw: Boolean
    var timeout: Int
    var transport: RPCTransport
    var useHttpProxy: Boolean
    var useSimpleHttp: Boolean
    var useXmlHttpRequest: Boolean
    var willHandleError: Boolean
    var withCredentials: Boolean
}

@js.native
abstract trait AbstractRPCRequestCompanion extends AbstractClassCompanion {
    def create(): void = js.native
    var useCursorTracker: Boolean = js.native
}

@js.native
@JSName("RPCRequest")
object RPCRequestStatic extends AbstractRPCRequestCompanion


