package com.simplesys.SmartClient.Drawing.drawItem

import com.simplesys.SmartClient.Drawing.{AbstractDrawItemCompanion, DrawItem}
import com.simplesys.SmartClient.System.Point

import scala.scalajs.js

@js.native
trait DrawSector extends DrawItem {
    var centerPoint: Point
    val endAngle: Double
    def getArcMidpoint(): Point
    val radius: Int
    val startAngle: Double
}

@js.native
abstract trait AbstractDrawSectorCompanion extends AbstractDrawItemCompanion {
    def getArcMidpoint(centerX: Double, centerY: Double, startAngle: Double, endAngle: Double, radius: Double): Point = js.native
}

@js.native
object DrawSector extends AbstractDrawSectorCompanion
