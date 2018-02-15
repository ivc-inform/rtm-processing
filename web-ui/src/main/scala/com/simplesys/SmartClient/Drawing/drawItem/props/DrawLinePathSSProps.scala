package com.simplesys.SmartClient.Drawing.drawItem.props

import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Control.props.MenuSSProps
import com.simplesys.SmartClient.Control.props.menu.MenuSSItemProps
import com.simplesys.SmartClient.Drawing.drawItem.DrawLinePathSS
import com.simplesys.SmartClient.Drawing.props.DrawKnobProps
import com.simplesys.SmartClient.Drawing.{DrawKnob, DrawLineCommons, Point}
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.System._
import com.simplesys.SmartClient.math.AffineTransform
import com.simplesys.System.Types.ConnectorOrientation.ConnectorOrientation
import com.simplesys.System.Types.ConnectorStyle.ConnectorStyle
import com.simplesys.System.Types.{ArrowStyle, ConnectorOrientation, ConnectorStyle, TitleRotationMode}
import com.simplesys.System.{JSAny, JSObject, JSUndefined, jSUndefined}
import com.simplesys.function._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScOption, ScSome}

import scala.scalajs.js
import scala.scalajs.js._

class DrawLinePathSSProps extends DrawPathProps with DrawLineCommons {
    type classHandler <: DrawLinePathSS

    var connectorStyle: ScOption[ConnectorStyle] = ConnectorStyle.diagonal.opt

    var connectorOrientation: ScOption[ConnectorOrientation] = ConnectorOrientation.auto.opt
    var _editModeKnobs: ScOption[IscArray[String]] = ScSome(IscArray("startPoint", "endPoint", "controlPoints"))

    showTitleLabelBackground = true.opt

    var showResizeKnobs: ScOption[ThisFunction0[classHandler, _]] = ((emptyFunc _).toThisFunc).opt
    var hideResizeKnobs: ScOption[ThisFunction0[classHandler, _]] = ((emptyFunc _).toThisFunc).opt
    var showMoveKnobs: ScOption[ThisFunction0[classHandler, _]] = ((emptyFunc _).toThisFunc).opt
    var hideMoveKnobs: ScOption[ThisFunction0[classHandler, _]] = ((emptyFunc _).toThisFunc).opt

    var startPoint: ScOption[Point] = Point(0, 0).opt

    var endPoint: ScOption[Point] = Point(100, 100).opt

    endArrow = ArrowStyle.open.opt

    contextMenu = MenuSS.create(
        new MenuSSProps {
            unserialize = true.opt
            items = Seq(
                new MenuSSItemProps {
                    title = "Установить контрольную точку".ellipsis.opt
                    identifier = "controlPoint".opt
                    icon = Common.insert.opt
                    click = {
                        (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                            target.asInstanceOf[DrawLinePathSS].insertControlPointKnob()

                    }.toFunc.opt
                }
            ).opt
        }).opt

    init = {
        (thiz: classHandler, arguments: IscArray[JSAny]) =>
            //println("init")

            //thiz.startPoint = thiz.startPoint.duplicate()
            //thiz.endPoint = thiz.endPoint.duplicate()

            thiz.startLeft = thiz.startPoint.getX().get
            thiz.startTop = thiz.startPoint.getY().get

            thiz.endLeft = thiz.endPoint.getX().get
            thiz.endTop = thiz.endPoint.getY().get

            //isc debugTrap thiz.titleRotationMode
            //thiz.titleRotationMode = TitleRotationMode.withLineAlwaysUp

            thiz.cKnobs = IscArray()
            thiz.controlPoints = IscArray(thiz.points.slice(1, thiz.points.length - 1).map(item => js.UndefOr.any2undefOrA(item)): _*)
            //isc debugTrap thiz.controlPoints
            thiz.points = thiz._getSegmentPoints()

            thiz.Super("init", arguments)

            //isc debugTrap(thiz)

    }.toThisFunc.opt

    var _getSegmentPoints: ScOption[ThisFunction0[classHandler, IscArray[Point]]] = {
        (thiz: classHandler) =>


            val startPoint = Point(thiz.startLeft, thiz.startTop)
            val endPoint = Point(thiz.endLeft, thiz.endTop)

            thiz._segmentPoints = IscArray(startPoint)
            thiz._segmentPoints addArray thiz.controlPoints.filter(_.isDefined).map(_.get)
            thiz._segmentPoints add endPoint

            //println(s"class: ${thiz.getClassName()} _getSegmentPoints: ${thiz._segmentPoints}")
            thiz._segmentPoints

    }.toThisFunc.opt

    var getCenter: ScOption[ThisFunction2[classHandler, JSUndefined[Point], JSUndefined[Point], Point]] = {
        (thiz: classHandler, p1: JSUndefined[Point], p2: JSUndefined[Point]) =>
            //println("getCenter begin")

            val startPoint: Point = p1 getOrElse thiz.startPoint
            val endPoint: Point = p2 getOrElse thiz.endPoint

            val startPointX = startPoint.getX().get
            val endPointX = endPoint.getX().get
            val startPointY = startPoint.getY().get
            val endPointY = endPoint.getY().get

            val res = Point(startPointX + isc.DrawItem._makeCoordinate((endPointX - startPointX) / 2), startPointY + isc.DrawItem._makeCoordinate((endPointY - startPointY) / 2))
            res
    }.toThisFunc.opt

    var _drawLineStartArrow: ScOption[ThisFunction0[classHandler, Boolean]] = {
        (thiz: classHandler) =>
            //println("_drawLineStartArrow")
            false
    }.toThisFunc.opt

    var _drawLineEndArrow: ScOption[ThisFunction0[classHandler, Boolean]] = {
        (thiz: classHandler) =>
            //println("_drawLineEndArrow")
            false
    }.toThisFunc.opt

    var setStartPoint: ScOption[ThisFunction5[classHandler, Double, Double, JSUndefined[Boolean], JSUndefined[Double], JSUndefined[Double], _]] = {
        (thiz: classHandler, left: Double, top: Double, fireMovedAndResized: JSUndefined[Boolean], cx: JSUndefined[Double], cy: JSUndefined[Double]) =>
            //println("setStartPoint")

            thiz.startLeft = left
            thiz.startPoint setX left

            thiz.startTop = top
            thiz.startPoint setY top

            thiz.setPoints(thiz._getSegmentPoints(), cx, cy)

    }.toThisFunc.opt


    var setEndPoint: ScOption[ThisFunction5[classHandler, Double, Double, JSUndefined[Boolean], JSUndefined[Double], JSUndefined[Double], _]] = {
        (thiz: classHandler, left: Double, top: Double, fireMovedAndResized: JSUndefined[Boolean], cx: JSUndefined[Double], cy: JSUndefined[Double]) =>
            //println(s"setEndPoint X: $left, Y: $top")

            thiz.endLeft = left
            thiz.endPoint setX left

            thiz.endTop = top
            thiz.endPoint setY top

            thiz.setPoints(thiz._getSegmentPoints(), cx, cy)

    }.toThisFunc.opt

    var setControlPoint: ScOption[ThisFunction6[classHandler, Int, Double, Double, JSUndefined[Boolean], JSUndefined[Double], JSUndefined[Double], _]] = {
        (thiz: classHandler, index: Int, left: Double, top: Double, fireMovedAndResized: JSUndefined[Boolean], cx: JSUndefined[Double], cy: JSUndefined[Double]) =>

            thiz.controlPoints(index).foreach {
                cp =>
                    cp setX left
                    cp setY top

                //println(s"setControlPoint$index, left: $left, top: $top")
            }

            thiz.setPoints(thiz._getSegmentPoints(), cx, cy)

    }.toThisFunc.opt

    var getBoundingBox: ScOption[ThisFunction2[classHandler, Boolean, JSUndefined[IscArray[Double]], IscArray[Double]]] = {
        (thiz: classHandler, includeStroke: Boolean, outputBox: JSUndefined[IscArray[Double]]) =>
            //println("getBoundingBox")

            val x1 = thiz.startPoint.getX().get
            val y1 = thiz.startPoint.getY().get
            val x2 = thiz.endPoint.getX().get
            val y2 = thiz.endPoint.getY().get

            val bbox = outputBox.getOrElse(IscArray())

            bbox(0) = Math.min(x1, x2)
            bbox(1) = Math.min(y1, y2)
            bbox(2) = Math.max(x1, x2)
            bbox(3) = Math.max(y1, y2)

            if (includeStroke) bbox else thiz._adjustBoundingBox(true, false, bbox)
    }.toThisFunc.opt

    var refresh: ScOption[ThisFunction0[classHandler, _]] = {
        (thiz: classHandler) =>
            thiz._movePointToPoint(thiz.startLeft + 1, thiz.startTop + 1, thiz.startLeft, thiz.startTop)
            thiz._movePointToPoint(thiz.startLeft - 1, thiz.startTop - 1, thiz.startLeft, thiz.startTop)
    }.toThisFunc.opt

    var removeControlPointKnob: ScOption[ThisFunction1[classHandler, DrawKnob, _]] = {
        (thiz: classHandler, knob: DrawKnob) =>
            if (thiz.controlPoints.remove(obj = Point(knob.x, knob.y), comparator = {
                (item1: Point, item2: Point) =>
                    item1.getX().get == item2.getX().get && item1.getY().get == item2.getY().get
            })) {
                thiz.clickedPoint = jSUndefined
                thiz.setPoints(thiz._getSegmentPoints())
                thiz.hideControlPointsKnobs()
                thiz.refresh()
                thiz.showControlPointsKnobs()
            }
    }.toThisFunc.opt

    var insertControlPointKnob: ScOption[ThisFunction0[classHandler, _]] = {
        (thiz: classHandler) =>
            thiz.clickedPoint foreach {
                clickedPoint =>
                    var indexAt = 0
                    thiz.points.zipWithIndex.foreach {
                        case (item, index) if indexAt == 0 =>
                            if (index + 2 <= thiz.points.length) {
                                val line = DrawLine.create(
                                    new DrawLineProps {
                                        startPoint = item.opt
                                        endPoint = thiz.points(index + 1).opt
                                    }
                                )
                                if (line.isPointInPath(clickedPoint.getX().get, clickedPoint.getY().get))
                                    indexAt = index
                                line.destroy()
                            }

                        case _ =>
                    }

                    thiz.controlPoints addAt(clickedPoint, indexAt)
                    thiz.setPoints(thiz._getSegmentPoints())
                    thiz.showControlPointsKnobs()
            }
    }.toThisFunc.opt

    var showControlPointsKnobs: ScOption[ThisFunction0[classHandler, _]] = {
        (thiz: classHandler) =>
            val topThiz = thiz
            thiz.controlPoints.zipWithIndex.foreach {
                case (controlPoint, index) =>
                    if (thiz.cKnobs(index).isEmpty || thiz.cKnobs(index).get.destroyed.getOrElse(false)) {
                        controlPoint foreach {
                            controlPoint =>
                                val v = thiz._normalize(controlPoint.getX().get, controlPoint.getY().get, "local", "global")

                                thiz.drawPane.foreach {
                                    _drawPane =>
                                        thiz.cKnobs(index) = thiz.createAutoChild(
                                            s"c${index}Knob", DrawKnob(
                                                new DrawKnobProps {
                                                    x = v.getX().get.opt
                                                    y = v.getY().get.opt
                                                    click = {
                                                        (thiz: DrawKnob) =>
                                                            if (isc.EventHandler.shiftKeyDown())
                                                                topThiz removeControlPointKnob thiz
                                                            false
                                                    }.toThisFunc.opt
                                                    drawPane = _drawPane.opt
                                                    contextMenu = MenuSS.create(
                                                        new MenuSSProps {
                                                            unserialize = true.opt
                                                            items = Seq(
                                                                new MenuSSItemProps {
                                                                    title = "Удалить контрольную точку".ellipsis.opt
                                                                    identifier = "deleteControlPoint".opt
                                                                    icon = Common.delete_icon.opt
                                                                    click = {
                                                                        (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                                            thiz removeControlPointKnob target.asInstanceOf[DrawKnob]
                                                                    }.toFunc.opt
                                                                }
                                                            ).opt
                                                        }).opt

                                                    resetKnobPosition = {
                                                        (thiz: DrawKnob) =>
                                                            val drawItem = thiz.creator.asInstanceOf[DrawLinePathSS]
                                                            drawItem.controlPoints(index).foreach {
                                                                controlPoint =>

                                                                    val v = drawItem._normalize(controlPoint.getX().get, controlPoint.getY().get, "local", "global")
                                                                    thiz.setCenterPoint(v.getX().get, v.getY().get, false)
                                                            }

                                                    }.toThisFunc.opt

                                                    updatePoints = {
                                                        (thiz: DrawKnob, x: Double, y: Double, dx: Double, dy: Double, state: String) =>
                                                            val drawItem = thiz.creator.asInstanceOf[DrawLinePathSS]
                                                            val v = drawItem._normalize(x, y, "global", "local")

                                                            val center = drawItem._getRotationCenter()

                                                            drawItem.setControlPoint(index, isc.DrawItem _makeCoordinate v.getX().get, isc.DrawItem _makeCoordinate v.getY().get, false, center.cx, center.cy)

                                                    }.toThisFunc.opt
                                                }
                                            )
                                        )
                                }

                            //println(s"showControlPoint${index}Knobs")
                        }
                    }
            }
    }.toThisFunc.opt

    var hideControlPointsKnobs: ScOption[ThisFunction0[classHandler, _]] = {
        (thiz: classHandler) =>
            thiz.cKnobs.filter(_.isDefined).map(_.get).zipWithIndex.foreach {
                case (cKnob, index) =>
                    //println(s"hideControlPoint${index}Knobs")
                    cKnob.markForDestroy()
            }
            thiz.cKnobs = IscArray()
    }.toThisFunc.opt

    var updateStartPointKnob: ScOption[ThisFunction0[classHandler, _]] = {
        (thiz: classHandler) =>
            //println("updateStartPointKnob")
            thiz._startKnob.foreach {
                knob =>
                    val v = thiz._normalize(thiz.startLeft, thiz.startTop, "local", "global")
                    knob.setCenterPoint(v.getX().get, v.getY().get)
            }
    }.toThisFunc.opt

    var updateEndPointKnob: ScOption[ThisFunction0[classHandler, _]] = {
        (thiz: classHandler) =>
            thiz._endKnob.foreach {
                knob =>
                    //println("updateEndPointKnob")
                    val v = thiz._normalize(thiz.endLeft, thiz.endTop, "local", "global")
                    knob.setCenterPoint(v.getX().get, v.getY().get)
            }
    }.toThisFunc.opt

    var updateControlPointsKnob: ScOption[ThisFunction0[classHandler, _]] = {
        (thiz: classHandler) =>
            thiz.cKnobs.filter(_.isDefined).map(_.get).zipWithIndex.foreach {
                case (knob, index) =>
                    thiz.controlPoints(index).foreach {
                        controlPoint =>
                            //println(s"updateControlPoint${index}Knob")
                            val v = thiz._normalize(controlPoint.getX().get, controlPoint.getY().get, "local", "global")
                            knob.setCenterPoint(v.getX().get, v.getY().get)
                    }
            }
    }.toThisFunc.opt


    var updateControlKnobs: ScOption[ThisFunction1[classHandler, IscArray[JSAny], _]] = {
        (thiz: classHandler, arguments: IscArray[JSAny]) =>
            //println("updateControlKnobs")
            thiz.Super("updateControlKnobs", arguments)
            thiz.updateStartPointKnob()
            thiz.updateEndPointKnob()
            thiz.updateControlPointsKnob()
    }.toThisFunc.opt

    var moveStartPointTo: ScOption[ThisFunction2[classHandler, Double, Double, _]] = {
        (thiz: classHandler, left: Double, top: Double) =>
            //println(s"moveStartPointTo")
            thiz._movePointToPoint(left, top, thiz.startLeft, thiz.startTop)
    }.toThisFunc.opt

    var _updateLocalTransform: ScOption[ThisFunction7[classHandler, AffineTransform, Double, Double, JSObject, Boolean, Boolean, IscArray[JSAny], _]] = {
        (thiz: classHandler, transform: AffineTransform, cx: Double, cy: Double, initialShape: JSObject, fireReshaped: Boolean, viaLocalTransformOnly: Boolean, arguments: IscArray[JSAny]) =>

            //println(s"_updateLocalTransform")
            if (viaLocalTransformOnly)
                thiz.Super("_updateLocalTransform", IscArray(transform, cx, cy, initialShape, fireReshaped, true), arguments)
            else {
                val epsilon: Double = 1e-9

                val det = transform.getDeterminant()
                if (Math.abs(det) > epsilon) {

                    val dx = isc.DrawItem._makeCoordinate(transform.m02 * transform.m11 - transform.m12 * transform.m01)
                    val dy = isc.DrawItem._makeCoordinate(transform.m12 * transform.m00 - transform.m02 * transform.m10)

                    thiz.Super("_updateLocalTransform", IscArray(transform.duplicate().rightMultiply(isc.AffineTransform.getTranslateTransform(-dx, -dy)), cx, cy, initialShape, false, true), arguments)

                    thiz.startLeft = thiz.startLeft + dx
                    thiz.startPoint setX (thiz.startPoint.getX().get + dx)

                    thiz.startTop = thiz.startTop + dy
                    thiz.startPoint setY (thiz.startPoint.getY().get + dy)

                    thiz.endLeft = thiz.endLeft + dx
                    thiz.endPoint setX (thiz.endPoint.getX().get + dx)

                    thiz.endTop = thiz.endTop + dy
                    thiz.endPoint setY (thiz.endPoint.getY().get + dy)

                    thiz.controlPoints.foreach {
                        cp =>
                            cp.foreach {
                                cp =>
                                    cp setX (cp.getX().get + dx)
                                    cp setY (cp.getY().get + dy)
                            }
                    }

                    val center = thiz._getRotationCenter()
                    thiz._updateRotationCenter(cx, cy, center.cx, center.cy)

                    thiz.setPoints(thiz._getSegmentPoints())

                } else
                    thiz.Super("_updateLocalTransform", IscArray(transform, cx, cy, initialShape, fireReshaped, true), arguments)

            }
    }.toThisFunc.opt

    override val `type`: ScOption[String] = "DrawLinePathSS".opt
}
