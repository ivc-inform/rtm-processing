package com.simplesys.SmartClient.Drawing.drawItem

import com.simplesys.SmartClient.Drawing.{AbstractDrawItemCompanion, DrawItem}
import com.simplesys.SmartClient.System.{IscArray, Point}
import com.simplesys.System.JSUndefined
import com.simplesys.System.Types.void

import scala.scalajs.js

@js.native
trait DrawPath extends DrawItem {
    var points: IscArray[Point]
    def moveFirstPointTo(left: Int, top: Int): void
    def setPoints(points: IscArray[Point], cx0: JSUndefined[Double] = js.native, cy0: JSUndefined[Double] = js.native): void
}

@js.native
abstract trait AbstractDrawPathCompanion extends AbstractDrawItemCompanion


