package com.simplesys.SmartClient.Drawing.drawItem

import com.simplesys.SmartClient.Drawing.{AbstractDrawItemCompanion, DrawItem, DrawKnob}
import com.simplesys.SmartClient.System.{AbstractClassCompanion, IscArray, Point}
import com.simplesys.System.JSUndefined
import com.simplesys.System.Types.void

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

@js.native
trait DrawLine extends DrawItem {
    val endLeft: Double
    var endPoint: Point
    val endTop: Double
    def moveStartPointTo (left: Double, top: Double): void
    def setEndPoint (left: Double, top: Double): void
    def setStartPoint (left: Double, top: Double): void
    val startLeft: Double
    var startPoint: Point
    val startTop: Double
    var _dragEndPointFixedPoint: JSUndefined[Point]
    var _dragFixedPoint: JSUndefined[Point]
    def _getParentRect():IscArray[Double]
    def _intersectLineSegmentBox(fixedPoint: Point, point: Point, box: IscArray[Double]): Point
}

@js.native
abstract trait AbstractDrawLineCompanion extends AbstractDrawItemCompanion {
}

@js.native
@JSName("DrawLine")
object DrawLineStatic extends AbstractDrawLineCompanion

