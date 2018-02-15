package com.simplesys.SmartClient.Drawing.gauge

import com.simplesys.System.JSObject
import com.simplesys.System.Types.CSSColor

import scala.scalajs.js

@js.native
trait GaugeSector extends JSObject {
    val endAngle: Double
    val fillColor: CSSColor
    val startAngle: Double
    val value: Double
}

