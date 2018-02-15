package com.simplesys.SmartClient.Foundation

import com.simplesys.SmartClient.DataBinding.Callbacks.DSCallback
import com.simplesys.SmartClient.DataBinding.{AbstractDataBoundComponentCompanion, DSRequest, DataBoundComponent, DataSource}
import com.simplesys.SmartClient.Grids.listGrid.ListGridRecord
import com.simplesys.SmartClient.RPC.{RPCRequest, RPCResponse}
import com.simplesys.System.JSObject
import com.simplesys.System.Types.ContentsType.ContentsType
import com.simplesys.System.Types.SendMethod.SendMethod
import com.simplesys.System.Types._
import org.scalajs.dom.raw.HTMLElement

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait HTMLFlow extends Canvas with DataBoundComponent {

    val allowCaching: Boolean
    val captureSCComponents: Boolean
    var contentLoaded: js.ThisFunction0[classHandler , _]
    var contentsType: ContentsType
    var contentsURL: URL
    var contentsURLParams: JSObject
    var evalScriptBlocks: Boolean
    def fetchRelatedData(record: ListGridRecord, schema: Canvas | DataSource | ID, callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    var handleError: js.Function2[RPCRequest, RPCResponse, Boolean]
    var httpMethod: SendMethod
    def clean():void
    def loadingContent(): Boolean
    val loadingMessage: HTMLString
    var selectContentOnSelectAll: Boolean
    def setContentsURL(url: URL = js.native, params: JSObject = js.native): void
    var transformHTML: js.Function1[HTMLElement, _]
}

@js.native
abstract trait AbstractHTMLFlowCompanion extends AbstractDataBoundComponentCompanion


