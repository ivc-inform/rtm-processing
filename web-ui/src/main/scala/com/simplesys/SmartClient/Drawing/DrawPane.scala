package com.simplesys.SmartClient.Drawing

import com.simplesys.SmartClient.DataBinding.Callbacks.DataURLCallback
import com.simplesys.SmartClient.Drawing.drawItem.DrawLabel
import com.simplesys.SmartClient.Drawing.gradient.{Gradient, LinearGradient, SimpleGradient}
import com.simplesys.SmartClient.Foundation.{AbstractCanvasCompanion, Canvas}
import com.simplesys.SmartClient.System.{IscArray, Point}
import com.simplesys.System.Types.Cursor._
import com.simplesys.System.Types.{ID, void}
import com.simplesys.System.{JSDictionaryAny, JSUndefined}
import org.scalajs.dom.svg.RadialGradient

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

@js.native
trait DrawPane extends Canvas {
    def _applyCursor(cursor: String): void
    def getCurrentCursor(): JSUndefined[String]
    val dragScrollCursor: Cursor
    def addDrawItem(item: DrawItem, autoDraw: Boolean = js.native): void
    def addGradient(gradient: Gradient): ID
    def createLinearGradient(id: ID, linearGradient: LinearGradient): ID
    def createRadialGradient(id: ID, radialGradient: RadialGradient): ID
    def createSimpleGradient(id: ID, simple: SimpleGradient): ID
    def destroyItems(): void
    val drawingHeight: Double
    val drawingWidth: Double
    var drawItems: IscArray[DrawItem]
    def erase(): void
    def getDataURL(callback: DataURLCallback = js.native): String
    def getDrawingPoint(): Point
    def getDrawingX(): Double
    def getDrawingY(): Double
    def getGradient(gradientID: ID): Gradient
    def getSvgString(): String
    val gradients: IscArray[Gradient]
    def refreshNow(): void
    def measureLabel(title: String, titleLabelProps: DrawLabel): DrawLabel
    def removeGradient(gradientID: ID): void
    def removeDrawItem(item: DrawItem): void
    def rotate(degrees: Double): void
    var rotation: Double
    def setRotation(degrees: Double): void
    def setZoomLevel(zoomLevel: Double): void
    var translate: IscArray[Double]
    def zoom(zoomLevel: Double): void
    var zoomLevel: Double
    var `type`: String
    val defaults: JSUndefined[JSDictionaryAny]
    def _pageToGlobal(x: Double, y: Double): Point
}

@js.native
abstract trait AbstractDrawPaneCompanion extends AbstractCanvasCompanion {
    def bezier(p1: Double, cp1: Double, cp2: Double, p2: Double, t: Double): Double = js.native
    def bezierExtrema(p1: Double, cp1: Double, cp2: Double, p2: Double): IscArray[Double] = js.native
    def getBezierBoundingBox(p1: Double, cp1: Double, cp2: Double, p2: Double): IscArray[Double] = js.native
    def getPolygonPoints(width: Double, height: Double, xc: Double, yc: Double, angles: IscArray[Double]): IscArray[Point] = js.native
    def getRegularPolygonPoints(n: Double, width: Double, height: Double, xc: Double, yc: Double, startAngle: Double): IscArray[Point] = js.native
    def scaleAndCenter(width: Double, height: Double, xc: Double, yc: Double, points: IscArray[Point]): void = js.native
    def scaleAndCenterBezier(width: Double, height: Double, xc: Double, yc: Double, startPoint: Point, endPoint: Point, controlPoint1: Point, controlPoint2: Point): void = js.native
}

@js.native
@JSName("DrawPane")
object DrawPaneSatic extends AbstractDrawPaneCompanion
