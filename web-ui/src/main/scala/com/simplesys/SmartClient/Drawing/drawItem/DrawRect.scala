package com.simplesys.SmartClient.Drawing.drawItem

import com.simplesys.SmartClient.Drawing.{AbstractDrawItemCompanion, DrawItem}
import com.simplesys.System.Types.void

import scala.scalajs.js

@js.native
trait DrawRect extends DrawItem {
    var height: Double
    var left: Double
    val rounding: Double
    def setCenter(left: Double, top: Double): void
    def setHeight(height: Double): void
    def setLeft(left: Double): void
    def setRect(left: Double, top: Double, width: Double, height: Double, cx:Double = js.native, cy:Double = js.native): void
    def setRounding(rounding: Boolean): void
    def setTop(top: Double): void
    def setWidth(width: Double): void
    var top: Double
    var width: Double
}

@js.native
abstract trait AbstractDrawRectCompanion extends AbstractDrawItemCompanion {
}

