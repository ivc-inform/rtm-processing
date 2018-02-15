package com.simplesys.SmartClient.System

import com.simplesys.SmartClient.System.RelativeDate.RelativeDate
import com.simplesys.SmartClient.System.date._
import com.simplesys.System.JSObject

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
object DateRange extends JSObject {
  type DateRange = DateRange.type
  var end: RelativeDate | Date = js.native
  var start: RelativeDate | Date = js.native
}
