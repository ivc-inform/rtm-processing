package com.simplesys.SmartClient.Drawing.drawItem

import com.simplesys.SmartClient.Drawing.{DrawItem, DrawKnob}
import com.simplesys.SmartClient.System.Point
import com.simplesys.System.Types.{AutoChild, void}

import scala.scalajs.js

@js.native
trait DrawCurve extends DrawItem {
    val c1Knob: DrawKnob with AutoChild
    val c2Knob: DrawKnob with AutoChild
    var controlPoint1: Point
    var controlPoint2: Point
    var endPoint: Point
    def moveStartPointTo (x: Int, y: Int): void
    def setControlPoint1 (left: Int, top: Int): void
    def setControlPoint2 (left: Int, top: Int): void
    def setEndPoint (left: Int, top: Int): void
    def setStartPoint (left: Int, top: Int): void
    var startPoint: Point
}


