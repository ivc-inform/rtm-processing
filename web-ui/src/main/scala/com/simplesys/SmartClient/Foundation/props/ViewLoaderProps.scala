package com.simplesys.SmartClient.Foundation.props

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.RPC.{RPCRequest, RPCResponse}
import com.simplesys.System.JSObject
import com.simplesys.System.Types.{HTMLString, URL}
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class ViewLoaderProps extends LabelProps {
    var allowCaching: ScOption[Boolean] = ScNone
    var handleError: ScOption[js.Function2[RPCRequest, RPCResponse, Boolean]] = ScNone
    var httpMethod: ScOption[String] = ScNone
    var loadingMessage: ScOption[HTMLString] = ScNone
    var viewLoaded: ScOption[js.Function1[Canvas, _]] = ScNone
    var viewRPCProperties: ScOption[RPCRequest] = ScNone
    var viewURL: ScOption[URL] = ScNone
    var viewURLParams: ScOption[JSObject] = ScNone
}
