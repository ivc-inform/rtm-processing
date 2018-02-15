package com.simplesys.SmartClient.RPC.props

import com.simplesys.SmartClient.System.props.ClassProps
import com.simplesys.System.{JSObject, JSAny}
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class ResponseCommon[D <: JSAny] extends ClassProps {
    val data: ScOption[D] = ScNone
    var clientContext: ScOption[JSObject] = ScNone
    var httpHeaders: ScOption[JSObject] = ScNone
    var httpResponseCode: ScOption[Int] = ScNone
    var httpResponseText: ScOption[String] = ScNone
    var status: ScOption[Int] = ScNone
    var transactionNum: ScOption[Int] = ScNone
}

class RPCResponseProps extends ResponseCommon[JSAny] {
}
