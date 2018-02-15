package com.simplesys.SmartClient.Drawing.drawItem

import com.simplesys.SmartClient.Drawing.{AbstractDrawItemCompanion, DrawItem}
import com.simplesys.SmartClient.System.Point
import com.simplesys.System.Types.void

import scala.scalajs.js

@js.native
trait DrawOval extends DrawItem {
    var centerPoint: Point
    def getRadiusX(): Int
    def getRadiusY(): Int
    var height: Int
    var left: Int
    val radius: Int
    def setHeight(height: Int): void
    def setLeft(left: Int): void
    def setOval(cx: Int, cy: Int, rx: Int, ry: Int): void
    def setRadii(rx: Int, ry: Int): void
    def setRadius(radius: Int): void
    def setRect(left: Int, top: Int, width: Int, height: Int): void
    def setTop(top: Int): void
    def setWidth(width: Int): void
    var top: Int
    var width: Int
}

@js.native
abstract trait AbstractDrawOvalCompanion extends AbstractDrawItemCompanion

