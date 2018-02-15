package com.simplesys.SmartClient.Drawing.drawItem

import com.simplesys.SmartClient.Drawing.{AbstractDrawItemCompanion, DrawKnob}
import com.simplesys.SmartClient.System.{IscArray, Point}
import com.simplesys.System.Types.ConnectorOrientation.ConnectorOrientation
import com.simplesys.System.Types.ConnectorStyle._
import com.simplesys.System.Types._
import com.simplesys.System._

import scala.scalajs.js

@js.native
trait DrawLinePathSS extends DrawPath {
    def setControlPoint(index: Int, left: Double, top: Double, fireMovedAndResized: Boolean = js.native, cx0: Double = js.native, cy0: Double = js.native): void

    def _getSegmentPoints(): IscArray[Point]
    def getCenter(p1: JSUndefined[Point], p2: JSUndefined[Point]): Point
    def updateStartPointKnob(): void
    def updateEndPointKnob(): void
    def updateControlPointsKnob(): void
    def showControlPointsKnobs(): void
    def hideControlPointsKnobs(): void
    def refresh(): void
    def removeControlPointKnob(lnob: DrawKnob): void
    def insertControlPointKnob(): void

    var cKnobs: IscArray[JSUndefined[DrawKnob]]
    var controlPoints: IscArray[JSUndefined[Point]]

    val connectorOrientation: JSUndefined[ConnectorOrientation]
    val connectorStyle: JSUndefined[ConnectorStyle]

    var endPoint: Point
    var startPoint: Point

    var endLeft: Double
    var endTop: Double

    var startLeft: Double
    var startTop: Double

    def moveStartPointTo(left: Double, top: Double): void
    def moveEndPointTo(left: Double, top: Double): void
    def setEndPoint(left: Double, top: Double): void
    def setStartPoint(left: Double, top: Double): void
}

@js.native
abstract trait AbstractDrawLinePathSSCompanion extends AbstractDrawPathCompanion
