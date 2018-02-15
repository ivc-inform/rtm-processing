package com.simplesys.SmartClient.Drawing.drawItem

import com.simplesys.SmartClient.Drawing.DrawItem
import com.simplesys.SmartClient.System.Point
import com.simplesys.System.JSUndefined
import com.simplesys.System.Types.ConnectorOrientation.ConnectorOrientation
import com.simplesys.System.Types.ConnectorStyle.ConnectorStyle
import com.simplesys.System.Types.{ID, void}

import scala.scalajs.js

@js.native
trait DrawLinePath extends DrawPath {
    val connectorOrientation: ConnectorOrientation
    val connectorStyle: ConnectorStyle
    var controlPoint1: JSUndefined[Point]
    var controlPoint2: JSUndefined[Point]
    var endLeft: JSUndefined[Int]
    var endPoint: Point
    var endTop: JSUndefined[Int]
    def moveStartPointTo(left: Int, top: Int): void
    def moveEndPointTo(left: Int, top: Int): void
    def setControlPoint1(left: Int, top: Int, fireMovedAndResized: Boolean = js.native, cx0: Int = js.native, cy0: Int = js.native): void
    def setControlPoint2(left: Int, top: Int, fireMovedAndResized: Boolean = js.native, cx0: Int = js.native, cy0: Int = js.native): void
    def setEndPoint(left: Int, top: Int): void
    def setStartPoint(left: Int, top: Int): void
    var startLeft: JSUndefined[Int]
    var startPoint: Point
    var startTop: JSUndefined[Int]
    val tailSize: Int
}
