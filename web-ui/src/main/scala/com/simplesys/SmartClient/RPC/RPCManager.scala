package com.simplesys.SmartClient.RPC

import com.simplesys.SmartClient.DataBinding.DSRequest
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.System.{AbstractClassCompanion, Class, IscArray}
import com.simplesys.System.Types.PromptStyle.PromptStyle
import com.simplesys.System.Types.RPCTransport.RPCTransport
import com.simplesys.System.Types.{Callback, HTMLString, URL, void}
import com.simplesys.System.{JSAny, JSUndefined}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName
import scala.scalajs.js.|

@js.native
trait RPCManager extends Class {
}

@js.native
abstract trait AbstractRPCManagerCompanion extends AbstractClassCompanion {

    import com.simplesys.SmartClient.DataBinding.Callbacks.RPCQueueCallback

    var actionURL: URL = js.native
    val ALL_GLOBALS: String = js.native
    var allowCrossDomainCalls: Boolean = js.native
    var allowIE9Leak: Boolean = js.native
    def cacheScreens(screenName: IscArray[String], callback: Callback, locale: String = js.native, requestProperties: RPCRequest = js.native): void = js.native
    def cancelQueue(transactionNum: Int = js.native): void = js.native
    def clearTransaction(transactionNum: Int): void = js.native
    def createScreen(screenName: String, globals: IscArray[String] = js.native): Canvas = js.native
    var credentialsURL: String = js.native
    var defaultPrompt: HTMLString = js.native
    var defaultTimeout: Int = js.native
    var defaultTransport: RPCTransport = js.native
    def exportContent(canvas: Canvas | IscArray[Canvas] | String, requestProperties: RPCRequest = js.native): void = js.native
    def exportImage(svgString: String, requestProperties: RPCRequest = js.native, callback: Callback = js.native): void = js.native
    var fetchDataPrompt: String = js.native
    def getCurrentTransactionId(): JSUndefined[Int] = js.native
    def getQueueTransactionId(): JSUndefined[Int] = js.native
    def handleError(response: RPCResponse, request: RPCRequest): void = js.native
    def handleTransportError(transactionNum: Int, status: Int, httpResponseCode: Int, httpResponseText: String): void = js.native
    def hasCurrentTransactionQueued(): Boolean = js.native
    val httpProxyURL: String = js.native
    def loadScreen(screenName: String, callback: Callback, globals: IscArray[String] = js.native, locale: String = js.native, requestProperties: RPCRequest = js.native): void = js.native
    var loginRequired: js.Function3[Int, RPCRequest, RPCResponse, _] = js.native
    @JSName("loginRequired")
    var loginRequiredMarker: String = js.native
    var loginStatusCodeMarker: String = js.native
    var loginSuccessMarker: String = js.native
    var maxLoginAttemptsExceededMarker: String = js.native
    var promptCursor: String = js.native
    var promptDelay: Int = js.native
    var promptStyle: PromptStyle = js.native
    def queueSent(requests: IscArray[RPCRequest]): void = js.native
    var removeDataPrompt: HTMLString = js.native
    def requestsArePending(): Boolean = js.native
    def resendTransaction(transactionNum: Int): void = js.native
    var saveDataPrompt: HTMLString = js.native
    var screenLoaderURL: URL = js.native
    def send(data: JSAny, callback: Callback, requestParams: RPCRequest): void = js.native
    def sendProxied(request: RPCRequest): void = js.native
    def sendQueue(callback: RPCQueueCallback = js.native, prompt: String = js.native, URL: URL = js.native, delay: Int = js.native): void = js.native
    def sendRequest(rpcRequest: RPCRequest | DSRequest, evalResult: Boolean = js.native): void = js.native
    var showPrompt: Boolean = js.native
    def startQueue(shouldQueue: Boolean = js.native): Boolean = js.native
    def suspendTransaction(transaction: Int): void = js.native
    var timeoutErrorMessage: String = js.native
    var useCursorTracker: Boolean = js.native
    val useHttpProxy: Boolean = js.native
    var useXmlHttpRequest: Boolean = js.native
    var validateDataPrompt: HTMLString = js.native
    def xmlHttpRequestAvailable(): Boolean = js.native
}

@js.native
object RPCManager extends AbstractRPCManagerCompanion

