package com.simplesys.SmartClient.Drawing

import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.DataBinding.{AdvancedCriteria, DataSource}
import com.simplesys.SmartClient.Drawing.drawItem.{DrawGroup, DrawLabel, DrawRect}
import com.simplesys.SmartClient.Drawing.gradient.Gradient
import com.simplesys.SmartClient.System.{AbstractClassCompanion, Class, IscArray, Point, Rect1}
import com.simplesys.SmartClient.Tools.{EditContext, EditNode, EditProxy}
import com.simplesys.SmartClient.math.AffineTransform
import com.simplesys.System.Types.ArrowStyle.ArrowStyle
import com.simplesys.System.Types.Cursor.Cursor
import com.simplesys.System.Types.KnobType.KnobType
import com.simplesys.System.Types.LineCap.LineCap
import com.simplesys.System.Types.LinePattern.LinePattern
import com.simplesys.System.Types.MoveKnobPoint.MoveKnobPoint
import com.simplesys.System.Types.ProportionalResizeMode.ProportionalResizeMode
import com.simplesys.System.Types.ResizeKnobPoint.ResizeKnobPoint
import com.simplesys.System.Types.TitleRotationMode.TitleRotationMode
import com.simplesys.System.Types._
import com.simplesys.System._

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSName, ScalaJSDefined}
import scala.scalajs.js.|

@ScalaJSDefined
trait RotationCenter extends JSObject {
    val cx: Double
    val cy: Double
}

@ScalaJSDefined
trait CalculateTitleLabelPositionInfo extends JSObject {
    // in drawing coordinates
    val topLeftPoint: Point

    // in drawing coordinates
    val centerPoint: Point

    val dims: DrawLabel
}

@ScalaJSDefined
trait LiveObject extends JSObject {
    var canDrag: Boolean
    val fieldDataSource: JSUndefined[DataSource]
}

@js.native
trait DrawItem extends Class {

    def getCurrentCursor(): JSUndefined[String]
    val liveObject: LiveObject
    var editingOn: JSUndefined[Boolean]
    val _addOrder: Int
    val _radPerDeg: Double
    def addPeer[T <: DrawItem](newPeer: T, name: String, autoDraw: Boolean = js.native): T
    def _adjustBoundingBox(forStroke: Boolean, forHitTolerance: Boolean, bbox: IscArray[Double]): IscArray[Double]
    def _getRotationCenter(): RotationCenter
    def _movePointToPoint(left: Double, top: Double, startLeft: Double, startTop: Double): void
    def _updateRotationCenter(cx: Double, cy: Double, centerCx: Double, centerCy: Double): void
    def _calculateTitleLabelPositionInfo(title: JSUndefined[String], drawLabel: DrawLabel, drawPane: DrawPane): CalculateTitleLabelPositionInfo
    val updateEditNode: js.Function2[EditContext, EditNode, _]
    var editContext: JSUndefined[EditContext]
    var editNode: JSUndefined[EditNode]
    var canDrag: JSUndefined[Boolean]
    var ID: ID
    val titleLabelProperties: JSUndefined[DrawLabel]
    var _segmentPoints: IscArray[Point]
    var canHover: Boolean
    var click: js.ThisFunction0[classHandler, Boolean]
    var clickedPoint: JSUndefined[Point]
    def computeAngle(px1: Double, py1: Double, px2: Double, py2: Double): Double
    var _constructor: JSUndefined[String]
    var contextMenu: JSUndefined[MenuSS]
    var cornerResizeKnob: DrawKnob with MultiAutoChild
    val criteria: JSUndefined[AdvancedCriteria]
    var cursor: Cursor
    val defaults: DrawItemDefaults
    def deleteGlueItems(): void
    val destroyed: JSUndefined[Boolean]
    val destroying: Boolean
    var dragMove: js.ThisFunction0[classHandler, Boolean]
    var dragResizeMove: js.Function5[String, Double, Double, Double, Double, _]
    var dragStart: js.Function0[Boolean]
    var dragStartDistance: Double
    var dragStop: js.Function0[Boolean]
    def draw(): void
    val drawGroup: DrawGroup
    var drawPane: JSUndefined[DrawPane]
    val drawingBitmap: JSUndefined[String]
    val editProxyConstructor: SCClassName
    val editProxyProperties: EditProxy
    val exemptFromGlobalTransform: Boolean
    var editProxy: JSUndefined[EditProxy]
    var endArrow: ArrowStyle
    var _endKnob: JSUndefined[DrawKnob]
    def erase(): void
    var fillColor: CSSColor
    var fillGradient: String | Gradient
    var fillOpacity: Double
    def getBoundingBox(): IscArray[Double]
    def getCenter(): Point
    def getHoverHTML(): HTMLString
    def getPageLeft(): Double
    def getPageTop(): Double
    def getResizeBoundingBox(): IscArray[Double]
    def getShapeData(): JSObject
    def getSvgString(): String
    def hide(): void
    def hideAllKnobs(): void
    def hideKnobs(knobType: KnobType | IscArray[KnobType])
    def hover(): Boolean
    def isInBounds(x: Double, y: Double): Boolean
    def isPointInPath(x: Double, y: Double): Boolean
    var keepInParentRect: Boolean
    val knobs: IscArray[KnobType]
    var lineCap: LineCap
    var lineColor: JSUndefined[CSSColor]
    var lineOpacity: Double
    //var linePattern: LinePattern //!!!!! Не убирать
    var lineWidth: Double
    var mouseDown: js.Function0[Boolean]
    var mouseMove: js.Function0[Boolean]
    var mouseOut: js.Function0[Boolean]
    var mouseOver: js.Function0[Boolean]
    var mouseUp: js.Function0[Boolean]
    def moveBy(dX: Double, dY: Double): void
    var moved: js.Function2[Double, Double, _]
    def _moved(dx: Double, dy: Double): void
    def _moved1(dx: Double, dy: Double): void
    def _resized1(newLeft: Double, newTop: Double, newRight: Double, newBottom: Double): void
    def _moved4Glue(newLeft: Double, newTop: Double, newRight: Double, newBottom: Double): void
    def resized1(newLeft: Double, newTop: Double, newRight: Double, newBottom: Double): void
    def resized(): Boolean
    def _resized(): Boolean
    val moveKnob: DrawKnob
    var moveKnobOffset: IscArray[Double]
    val moveKnobPoint: MoveKnobPoint
    def moveTo(left: Double, top: Double): void
    def _moveTo(left: Double, top: Double): void
    def _normalize(x: Double, y: Double, inputCoordinateSystem: String = js.native, outputCoordinateSystem: String = js.native): Point
    def _getNormalizeTransform(inputCoordinateSystem: String = js.native, outputCoordinateSystem: String = js.native): AffineTransform
    var palette: JSUndefined[_ <: JSObject]
    var prompt: HTMLString
    val proportionalResizeModifiers: IscArray[KeyName]
    val proportionalResizing: ProportionalResizeMode
    def resizeBy(dX: Double, dY: Double): void
    val resizeKnobPoints: IscArray[ResizeKnobPoint]
    val resizeOutline: DrawRect
    def resizeTo(width: Double, height: Double): void
    val resizeViaLocalTransformOnly: Boolean
    def rotateBy(degrees: Double): void
    def rotateTo(degrees: Double): void
    val rotation: JSUndefined[Double]
    val scale: IscArray[Double]
    def scaleBy(x: Double, y: Double): void
    def scaleTo(x: Double, y: Double): void
    def setCanDrag(canDrag: Boolean): void
    def setCenterPoint(left: Double, top: Double): void
    def setCursor(newCursor: Cursor): void
    def setDrawPane(drawPane: DrawPane): void
    def setEndArrow(arrowStyle: ArrowStyle): void
    def setFillColor(color: Color): void
    def setFillGradient(gradient: Gradient): void
    def setFillOpacity(opacity: Double): void
    def setLineCap(cap: LineCap): void
    def setLineColor(color: CSSColor): void
    def setLineOpacity(opacity: Double): void
    def setLinePattern(pattern: LinePattern): void
    def setLineWidth(width: Double): void
    def setMoveKnobOffset(newMoveKnobOffset: IscArray[Double] = js.native): void
    def setPropertyValue(propertyName: String, newValue: JSAny): void
    def setShadow(shadow: Shadow): void
    def setStartArrow(arrowStyle: ArrowStyle): void
    def setTitle(newTitle: String = js.native): void
    var shadow: JSUndefined[Shadow]
    val shapeData: JSObject
    def show(): void
    def showAllKnobs(): void
    def showContextMenu(): void
    var showHover: Boolean
    def showKnobs(knobType: KnobType | IscArray[KnobType]): void
    var showResizeOutline: Boolean
    val showTitleLabelBackground: Boolean
    val sideResizeKnob: DrawKnob with MultiAutoChild
    var sourceConnect: JSUndefined[DrawItem]
    var sourceGlue: JSUndefined[DrawItem]
    var targetConnect: JSUndefined[DrawItem]
    var targetGlue: JSUndefined[DrawItem]
    var inConnectedItems: JSUndefined[IscArray[DrawItem]]
    var outConnectedItems: JSUndefined[IscArray[DrawItem]]
    def setGluedDrawItem(): void
    var startArrow: ArrowStyle
    var _startKnob: JSUndefined[DrawKnob]
    def supportsEndArrow(): Boolean
    def supportsStartArrow(): Boolean
    var enable4Connect: JSUndefined[Boolean]
    //Возможность приклеивания к другому
    var enable2Glue: JSUndefined[Boolean]
    //Возможность приклеивания другого
    var enable4Glue: JSUndefined[Boolean]
    var title: JSUndefined[String]
    var titleLabel: JSUndefined[DrawLabel]
    val titleLabelDefaults: DrawLabel
    val titleLabelBackground: JSUndefined[DrawRect]
    val titleLabelPadding: Double
    var titleRotationMode: JSUndefined[TitleRotationMode]
    val translate: IscArray[Double]
    var _transform: JSUndefined[AffineTransform]
    def _updateLocalTransform(transform: AffineTransform, cx: Double, cy: Double, initialShape: JSObject, fireReshaped: Boolean, viaLocalTransformOnly: Boolean): void
    def _hasStroke(): Boolean
    def updateControlKnobs(): void
    def _updateTitleLabelAndBackground(): void
    def _updateQuadTreeItem(): void
    def saveCoordinates(): void
    val xShearFactor: Double
    val yShearFactor: Double
    val `type`: JSUndefined[String]
    var editModeOriginalValues: JSUndefined[JSObject]
    def editablePropertiesUpdated(properties: JSObject): void

    var endLeft2CentrLeft: JSUndefined[Double]
    var endTop2CentrTop: JSUndefined[Double]

    var startLeft2CentrLeft: JSUndefined[Double]
    var startTop2CentrTop: JSUndefined[Double]

    val fieldDataSource: JSUndefined[DataSource]
    var variableProps: JSUndefined[JSObject]
    var subProgramProps: JSUndefined[JSObject]
    var multiElementsProps: JSUndefined[JSObject]
    val listRefs: JSUndefined[JSObject]
    val timerProps: JSUndefined[JSObject]
    val groupProps: JSUndefined[JSObject]
}

@js.native
abstract trait AbstractDrawItemCompanion extends AbstractClassCompanion {
    def computeAngle(px1: Double, py1: Double, px2: Double, py2: Double): Double = js.native
    def _makeCoordinate(x: Double): Double = js.native
    def _fitBestRect(transform: AffineTransform, cx: Double, cy: Double, left: Double, top: Double, width: Double, height: Double, halfLineWidthAndHitTolerance: JSUndefined[Double] | Double): Rect1 = js.native
    val _instancePrototype: DrawItem = js.native
}

@js.native
@JSName("DrawItem")
object DrawItemStatic extends AbstractDrawItemCompanion
