package com.simplesys.SmartClient.Drawing.gradient

import com.simplesys.System.JSObject
import com.simplesys.System.Types.CSSColor

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait ColorStop extends JSObject {
    val color: CSSColor
    val offset: Double
    val opacity: Double
}


