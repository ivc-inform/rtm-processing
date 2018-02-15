package com.simplesys.SmartClient.Foundation.props

import com.simplesys.SmartClient.DataBinding.props.DataBoundComponentProps
import com.simplesys.SmartClient.Foundation.HTMLFlow
import com.simplesys.SmartClient.RPC.{RPCRequest, RPCResponse}
import com.simplesys.System.JSObject
import com.simplesys.System.Types.ContentsType.ContentsType
import com.simplesys.System.Types.SendMethod.SendMethod
import com.simplesys.System.Types.{HTMLString, URL}
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.option.ScOption._
import com.simplesys.function._
import org.scalajs.dom.raw.HTMLElement

import scala.scalajs.js
import scala.scalajs.js.ThisFunction0

class HTMLFlowProps extends CanvasProps with DataBoundComponentProps {
    type classHandler <: HTMLFlow

    var allowCaching: ScOption[Boolean] = ScNone
    var captureSCComponents: ScOption[Boolean] = ScNone
    var contentLoaded: ScOption[ThisFunction0[classHandler, _]] = ScNone
    var contentsType: ScOption[ContentsType] = ScNone
    var contentsURL: ScOption[URL] = ScNone
    var contentsURLParams: ScOption[JSObject] = ScNone
    var evalScriptBlocks: ScOption[Boolean] = ScNone
    var handleError: ScOption[js.Function2[RPCRequest, RPCResponse, Boolean]] = ScNone
    var httpMethod: ScOption[SendMethod] = ScNone
    var loadingMessage: ScOption[HTMLString] = ScNone
    var selectContentOnSelectAll: ScOption[Boolean] = ScNone
    var transformHTML: ScOption[js.Function1[HTMLElement, _]] = ScNone
    var clean: ScOption[ThisFunction0[classHandler, _]] = {
        (thiz: classHandler) =>
            thiz setContents "&nbsp;"
    }.toThisFunc.opt
}
