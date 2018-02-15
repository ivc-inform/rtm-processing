package com.simplesys.SmartClient.DataBinding

import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.{JSObject, JSUndefined}
import com.simplesys.System.Types.OperatorId.OperatorId

import scala.scalajs.js

@js.native
trait Criterion extends JSObject {
    var criteria: JSUndefined[IscArray[Criterion]]
    var end: JSObject
    var fieldName: JSUndefined[String]
    var operator: OperatorId
    var start: JSUndefined[JSObject]
    var value: JSUndefined[JSObject]
    var _constructor: String
}
