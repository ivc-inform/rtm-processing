package com.simplesys.SmartClient.System

import com.simplesys.System.JSObject
import com.simplesys.System.Types.RelativeDateRangePosition.RelativeDateRangePosition
import com.simplesys.System.Types.RelativeDateShortcut.RelativeDateShortcut
import com.simplesys.System.Types.RelativeDateString

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
object RelativeDate extends JSObject{
  type RelativeDate = RelativeDate.type

  var rangePosition:RelativeDateRangePosition = js.native
  var value: RelativeDateString | RelativeDateShortcut = js.native
}
