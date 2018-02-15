package com.simplesys.SmartClient.Drawing

import com.simplesys.SmartClient.Drawing.drawItem.DrawLine
import com.simplesys.SmartClient.Drawing.props.DrawKnobProps
import com.simplesys.SmartClient.System.{IscArray, isc, _}
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.ScOption
import com.simplesys.option.ScOption._

import scala.scalajs.js.ThisFunction0

trait DrawItemCommons {
    def removeListRefItemsItem(drawItem: DrawItem): Unit = {
        drawItem.outConnectedItems.foreach {
            _.foreach {
                item =>
                    item.sourceConnect = jSUndefined
                    item.canDrag = true
            }
        }
        drawItem.inConnectedItems.foreach {
            _.foreach {
                item =>
                    item.targetConnect = jSUndefined
                    item.canDrag = true
            }
        }
    }

    def removeOutDrawItem(drawItem: DrawItem): Unit = {
        removeListRefItemsItem(drawItem)

        drawItem.sourceConnect foreach {
            item =>
                item.outConnectedItems.foreach {
                    sourceArray =>
                        if (sourceArray.remove(obj = drawItem, comparator = {
                            (item1: DrawItem, item2: DrawItem) =>
                                item1.getID() == item2.getID()
                        })) {
                            //println(s"Unlink drawItem: ${drawItem.getID()} to ${drawItem.source.get.getID()}")
                            drawItem.sourceConnect = jSUndefined
                            drawItem.startLeft2CentrLeft = jSUndefined
                            drawItem.startTop2CentrTop = jSUndefined
                            if (drawItem.targetConnect.isEmpty)
                                drawItem.canDrag = true
                        }
                }
        }
    }

    protected def changeZ(line: DrawItem, figArea: DrawItem): Unit = {
        line.drawPane.foreach {
            drawPane =>
                val indexOfLine = drawPane.drawItems.indexOf(obj = line, comparator = (item1: DrawItem, item2: DrawItem) => item1.ID == item2.ID)
                val indexOfFigArea = drawPane.drawItems.indexOf(obj = figArea, comparator = (item1: DrawItem, item2: DrawItem) => item1.ID == item2.ID)

                if (indexOfFigArea > indexOfLine) {
                    val lineItem = drawPane.drawItems(indexOfLine)
                    drawPane.drawItems(indexOfLine) = drawPane.drawItems(indexOfFigArea)
                    drawPane.drawItems(indexOfFigArea) = lineItem
                    drawPane.refreshNow()
                }
        }
    }

    def changeZ(line: DrawItem): Unit = {
        line.sourceConnect.foreach(changeZ(line, _))
        line.targetConnect.foreach(changeZ(line, _))
    }

    def removeInDrawItem(drawItem: DrawItem): Unit = {
        removeListRefItemsItem(drawItem)
        drawItem.targetConnect foreach {
            item =>
                item.inConnectedItems.foreach {
                    targetArray =>
                        if (targetArray.remove(obj = drawItem, comparator = {
                            (item1: DrawItem, item2: DrawItem) =>
                                item1.getID() == item2.getID()
                        })) {
                            //println(s"Unlink drawItem: ${drawItem.getID()} to ${drawItem.target.get.getID()}")
                            drawItem.targetConnect = jSUndefined
                            drawItem.endLeft2CentrLeft = jSUndefined
                            drawItem.endTop2CentrTop = jSUndefined
                            if (drawItem.sourceConnect.isEmpty)
                                drawItem.canDrag = true
                        }
                }
        }
    }
}

sealed trait KindOfRefs
case object IncomingRefs extends KindOfRefs
case object OutcomingRefs extends KindOfRefs

trait DrawLineCommons extends DrawItemCommons {
    self =>

    private def updatePoint(thiz: DrawKnob, x: Double, y: Double, dx: Double, dy: Double, state: String, creator: Class, kindOfRefs: KindOfRefs) {
        var _x = x
        var _y = y

        val startState = state == "start"
        val stopState = state == "stop"
        val drawItem = creator.asInstanceOf[DrawLine]

        val fixedPoint: Point =
            if (startState) {
                val res = kindOfRefs match {
                    case OutcomingRefs => drawItem.startPoint.duplicate()
                    case IncomingRefs => drawItem.endPoint.duplicate()
                }
                drawItem._dragFixedPoint = res
                drawItem._normalize(res.getX().get, res.getY().get, "local", "global")
            } else
                drawItem._dragFixedPoint.get

        if (drawItem.keepInParentRect) {
            val box = drawItem._getParentRect()
            val point = drawItem._intersectLineSegmentBox(fixedPoint, IscArray(x, y), box)

            if (point == null) {
                if (stopState)
                    drawItem._dragFixedPoint = jSUndefined
            } else {
                _x = point.getX().get
                _y = point.getY().get
            }
        } else if (stopState)
            drawItem._dragFixedPoint = jSUndefined

        val v = drawItem._normalize(_x, _y, "global", "local")

        _x = isc.DrawItem._makeCoordinate(v.getX().get)
        _y = isc.DrawItem._makeCoordinate(v.getY().get)

        val connectItems = drawItem.drawPane.map(_.drawItems.filter(item => item.enable4Connect.getOrElse(false)))
        connectItems.foreach {
            _.foreach {
                item =>
                    val center = item.getCenter()
                    val centerX = center.getX().get
                    val centerY = center.getY().get

                    kindOfRefs match {
                        case OutcomingRefs =>
                            if (item.isInBounds(_x, _y) && drawItem.sourceConnect.isEmpty && !isc.isA.StopState(item)) {
                                changeZ(drawItem, item)
                                isc.ask(s"Привязать ${drawItem.title} к ${item.title} ?", {
                                    (res: Boolean) =>
                                        if (res) {

                                            drawItem.startLeft2CentrLeft = centerX - drawItem.startLeft
                                            drawItem.startTop2CentrTop = centerY - drawItem.startTop

                                            drawItem.sourceConnect = item
                                            drawItem.canDrag = false
                                            if (item.outConnectedItems.isEmpty)
                                                item.outConnectedItems = IscArray[DrawItem]()
                                            item.outConnectedItems.get add drawItem
                                            //println(s"Link drawItem: ${item.getID()} to ${item.getID()}")
                                        }
                                })


                            } else if (drawItem.sourceConnect.isDefined && !drawItem.sourceConnect.get.isInBounds(_x, _y)) {
                                isc.ask(s"Удалить привязку ${drawItem.title} к ${drawItem.sourceConnect.get.title} ?", {
                                    (res: Boolean) =>
                                        if (res)
                                            removeOutDrawItem(drawItem)
                                        else if (!drawItem.sourceConnect.get.isInBounds(drawItem.startLeft, drawItem.startTop))
                                            drawItem.setStartPoint(fixedPoint.getX().get, fixedPoint.getY().get)
                                })
                            } else {
                                drawItem.sourceConnect.foreach {
                                    sourceConnect =>
                                        val center = sourceConnect.getCenter()
                                        val centerX = center.getX().get
                                        val centerY = center.getY().get

                                        drawItem.startLeft2CentrLeft = centerX - drawItem.startLeft
                                        drawItem.startTop2CentrTop = centerY - drawItem.startTop
                                }

                            }

                        case IncomingRefs =>
                            if (item.isInBounds(_x, _y) && drawItem.targetConnect.isEmpty) {
                                isc.ask(s"Привязать ${drawItem.title} к ${item.title} ?", {
                                    changeZ(drawItem, item)
                                    (res: Boolean) =>
                                        if (res) {
                                            drawItem.endLeft2CentrLeft = centerX - drawItem.endLeft
                                            drawItem.endTop2CentrTop = centerY - drawItem.endTop

                                            drawItem.targetConnect = item
                                            drawItem.canDrag = false
                                            if (item.inConnectedItems.isEmpty)
                                                item.inConnectedItems = IscArray[DrawItem]()
                                            item.inConnectedItems.get add drawItem
                                            //println(s"Link drawItem: ${item.getID()} to ${item.getID()}")
                                        }
                                })


                            } else if (drawItem.targetConnect.isDefined && !drawItem.targetConnect.get.isInBounds(_x, _y)) {
                                isc.ask(s"Удалить привязку ${drawItem.title} к ${drawItem.targetConnect.get.title} ?", {
                                    (res: Boolean) =>
                                        if (res)
                                            removeInDrawItem(drawItem)
                                        else if (!drawItem.targetConnect.get.isInBounds(drawItem.endLeft, drawItem.endTop))
                                            drawItem.setEndPoint(fixedPoint.getX().get, fixedPoint.getY().get)
                                })
                            } else {
                                drawItem.targetConnect.foreach {
                                    targetConnect =>
                                        val center = targetConnect.getCenter()
                                        val centerX = center.getX().get
                                        val centerY = center.getY().get

                                        drawItem.endLeft2CentrLeft = centerX - drawItem.endLeft
                                        drawItem.endTop2CentrTop = centerY - drawItem.endTop
                                }
                            }
                    }
            }
        }
        kindOfRefs match {
            case OutcomingRefs =>
                drawItem.setStartPoint(_x, _y)
            case IncomingRefs =>
                drawItem.setEndPoint(_x, _y)
        }
        //println(s"setStartPoint(${_x}, ${_y})")
    }

    var showStartPointKnobs: ScOption[ThisFunction0[DrawLine, _]] = {
        (thiz: DrawLine) =>
            if (thiz._startKnob.isEmpty || thiz._startKnob.get.destroyed.getOrElse(false)) {
                import com.simplesys.SmartClient.Drawing.props.DrawKnobProps

                val v = thiz._normalize(thiz.startLeft, thiz.startTop, "local", "global")
                thiz._startKnob = thiz.createAutoChild(
                    "startKnob",
                    DrawKnob(
                        new DrawKnobProps {
                            x = v.getX().get.opt
                            y = v.getY().get.opt
                            drawPane = thiz.drawPane.get.opt

                            resetKnobPosition = {
                                (thiz: DrawKnob) =>
                                    val drawItem = thiz.creator.asInstanceOf[DrawLine]
                                    val v = drawItem._normalize(drawItem.startLeft, drawItem.startTop, "local", "global")
                                    thiz.setCenterPoint(v.getX().get, v.getY().get)
                            }.toThisFunc.opt

                            updatePoints = {
                                (thiz: DrawKnob, x: Double, y: Double, dx: Double, dy: Double, state: String) =>
                                    self.updatePoint(thiz, x, y, dx, dy, state, thiz.creator, OutcomingRefs)
                            }.toThisFunc.opt
                        }
                    )
                )
            }
    }.toThisFunc.opt

    var hideStartPointKnobs: ScOption[ThisFunction0[DrawLine, _]] = {
        (thiz: DrawLine) =>
            thiz._startKnob.foreach {
                knob =>
                    /*if (thiz.sourceConnect.isEmpty) {
                        knob.markForDestroy()
                        thiz._startKnob = jSUndefined
                    }*/

                    knob.markForDestroy()
                    thiz._startKnob = jSUndefined

            }
    }.toThisFunc.opt


    var showEndPointKnobs: ScOption[ThisFunction0[DrawLine, _]] = {
        (thiz: DrawLine) =>
            if (thiz._endKnob.isEmpty || thiz._endKnob.get.destroyed.getOrElse(false)) {

                val v = thiz._normalize(thiz.endLeft, thiz.endTop, "local", "global")
                thiz._endKnob = thiz.createAutoChild(
                    "endKnob",
                    DrawKnob(
                        new DrawKnobProps {
                            x = v.getX().get.opt
                            y = v.getY().get.opt
                            drawPane = thiz.drawPane.get.opt

                            resetKnobPosition = {
                                (thiz: DrawKnob) =>
                                    val drawItem = thiz.creator.asInstanceOf[DrawLine]
                                    val v = drawItem._normalize(drawItem.endLeft, drawItem.endTop, "local", "global")
                                    thiz.setCenterPoint(v.getX().get, v.getY().get)
                            }.toThisFunc.opt

                            updatePoints = {
                                (thiz: DrawKnob, x: Double, y: Double, dx: Double, dy: Double, state: String) =>
                                    self.updatePoint(thiz, x, y, dx, dy, state, thiz.creator, IncomingRefs)
                            }.toThisFunc.opt
                        }
                    )
                )
            }
    }.toThisFunc.opt

    var hideEndPointKnobs: ScOption[ThisFunction0[DrawLine, _]] = {
        (thiz: DrawLine) =>
            thiz._endKnob.foreach {
                knob =>
//                    if (thiz.targetConnect.isEmpty) {
//                        knob.markForDestroy()
//                        thiz._endKnob = jSUndefined
//                    }

                    knob.markForDestroy()
                    thiz._endKnob = jSUndefined
            }
    }.toThisFunc.opt
}
