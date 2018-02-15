package com.simplesys.SmartClient.Drawing.props

import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.DataBinding.DataSource
import com.simplesys.SmartClient.Drawing._
import com.simplesys.SmartClient.Drawing.drawItem._
import com.simplesys.SmartClient.Drawing.gradient.Gradient
import com.simplesys.SmartClient.System.props.ClassProps
import com.simplesys.SmartClient.System.{DrawRect, IscArray, Point, isc}
import com.simplesys.SmartClient.Tools.{EditContextSS, EditNode, EditProxy}
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
import com.simplesys.function._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js
import scala.scalajs.js._

class DrawItemProps extends ClassProps {
    type classHandler <: DrawItem

    def emptyFunc(thiz: classHandler) {}

    var canDrag: ScOption[Boolean] = ScNone
    var canHover: ScOption[Boolean] = ScNone

    var _updateTitleLabelAndBackground: ScOption[ThisFunction0[classHandler, _]] = {
        (thiz: classHandler) =>

            var title = thiz.title
            //isc debugTrap title

            var titleLabel = thiz.titleLabel
            //thiz.updateFont()

            val showTitleLabelBackground: Boolean = thiz.showTitleLabelBackground
            var titleLabelBackground = thiz.titleLabelBackground
            val drawPane = thiz.drawPane
            var initialRotation: JSUndefined[Double] = jSUndefined

            // If the title is null or thiz DrawItem is not in a DrawPane, then the titleLabel should
            // be hidden.

            if (title.isEmpty || drawPane.isEmpty) {
                if (titleLabelBackground.isDefined && !titleLabelBackground.get.destroyed.getOrElse(false) && titleLabelBackground.get.drawPane.isDefined)
                    titleLabelBackground.get.drawPane.foreach(_.removeDrawItem(titleLabelBackground.get))

                if (titleLabel.isDefined && !titleLabel.get.destroyed.getOrElse(false) && titleLabel.get.drawPane.isDefined) {
                    titleLabel.get.drawPane.foreach(_.removeDrawItem(titleLabel.get))
                    titleLabel.get setContents null // clear the contents to potentially free up some memory
                }

                // Otherwise, the titleLabel should be shown.
            } else {
                // Create/add the titleLabelBackground first so that it is below the titleLabel in
                // stacking order.
                if (showTitleLabelBackground) {
                    if (titleLabelBackground.isEmpty || titleLabelBackground.get.destroyed.getOrElse(false)) {
                        titleLabelBackground = thiz.addPeer[DrawRect](
                            thiz.createAutoChild[DrawRect](
                                "titleLabelBackground",
                                js.Dynamic.literal(
                                    "autoDraw" -> false,
                                    "eventProxy" -> thiz,
                                    "knobs" -> null,
                                    "rotation" -> initialRotation
                                )
                            ), "titleLabelBackground"
                        )

                        //isc debugTrap titleLabelBackground

                    } else
                        titleLabelBackground.foreach {
                            titleLabelBackground =>
                                titleLabelBackground.drawPane.foreach {
                                    _drawPane =>
                                        drawPane.foreach {
                                            __drawPane =>
                                                if (_drawPane != __drawPane)
                                                    _drawPane addDrawItem titleLabelBackground
                                        }
                                }
                        }

                } else if (titleLabelBackground.isDefined && !titleLabelBackground.get.destroyed.getOrElse(false) && titleLabelBackground.get.drawPane.isDefined)
                    titleLabelBackground.get.drawPane.foreach(_.removeDrawItem(titleLabelBackground.get))

                val titleRotationMode = thiz.titleRotationMode.getOrElse(isc.DrawItem._instancePrototype.titleRotationMode).toString
                var finalRotation: JSUndefined[Double] = jSUndefined
                val isADrawLine = isc.isA.DrawLine(thiz)
                val isADrawLinePath = isc.isA.DrawLinePath(thiz)
                val isADrawLinePathSS = isc.isA.DrawLinePathSS(thiz)
                var flipped = false

                initialRotation = thiz.rotation
                if (initialRotation.isEmpty) initialRotation = 0.0

                if (titleRotationMode != TitleRotationMode.neverRotate.toString) {

                    if ((isADrawLine || isADrawLinePath || isADrawLinePathSS) && (titleRotationMode == TitleRotationMode.withLine.toString || titleRotationMode == TitleRotationMode.withLineAlwaysUp.toString)) {
                        var startLeft: JSUndefined[Double] = jSUndefined
                        var startTop: JSUndefined[Double] = jSUndefined
                        var endLeft: JSUndefined[Double] = jSUndefined
                        var endTop: JSUndefined[Double] = jSUndefined

                        if (isADrawLinePathSS) {
                            val segmentPoints = thiz._segmentPoints
                            val _thiz: DrawLinePathSS = thiz.asInstanceOf[DrawLinePathSS]
                            if (segmentPoints.length <= 2) {
                                startLeft = _thiz.startLeft
                                startTop = _thiz.startTop
                                endLeft = _thiz.endLeft
                                endTop = _thiz.endTop
                            } else {
                                val index = if (segmentPoints.length == 2) 0 else 1

                                val centerSegmentStartPoint = segmentPoints(index)
                                val centerSegmentEndPoint = segmentPoints(index + 1)

                                startLeft = centerSegmentStartPoint.getX().get
                                startTop = centerSegmentStartPoint.getY().get
                                endLeft = centerSegmentEndPoint.getX().get
                                endTop = centerSegmentEndPoint.getY().get
                            }
                        } else if (isADrawLinePath) {
                            val segmentPoints = thiz._segmentPoints

                            val centerSegmentStartPoint = segmentPoints(1)
                            val centerSegmentEndPoint = segmentPoints(2)

                            startLeft = centerSegmentStartPoint.getX().get
                            startTop = centerSegmentStartPoint.getY().get
                            endLeft = centerSegmentEndPoint.getX().get
                            endTop = centerSegmentEndPoint.getY().get
                        } else {

                            val _thiz: DrawLine = thiz.asInstanceOf[DrawLine]
                            startLeft = _thiz.startLeft
                            startTop = _thiz.startTop
                            endLeft = _thiz.endLeft
                            endTop = _thiz.endTop
                        }

                        // finalRotation is the angle that the titleLabel should be rotated (in degrees) such
                        // that it matches the line direction.
                        //
                        // To get the direction of the line, use atan2().
                        // In drawing coordinates, the positive Y direction points downward, whereas atan2()
                        // works in a coordinate system where the positive Y direction points upward.
                        // Also, atan2() returns the angle where positive is counter-clockwise (drawing
                        // treats the clockwise direction as positive).

                        finalRotation = -math.atan2(-(endTop.get - startTop.get), endLeft.get - startLeft.get) / thiz._radPerDeg + initialRotation.get
                        finalRotation = finalRotation.get % 360

                    } else
                        finalRotation = initialRotation


                    if (finalRotation.get < -180)
                        finalRotation = finalRotation.get + 360
                    else if (finalRotation.get > 180)
                        finalRotation = finalRotation.get - 360

                    if (titleRotationMode == TitleRotationMode.withItemAlwaysUp.toString || titleRotationMode == TitleRotationMode.withLineAlwaysUp.toString) {
                        if (finalRotation.get > 90) {
                            finalRotation = finalRotation.get - 180
                            flipped = true
                        } else if (finalRotation.get < -90) {
                            finalRotation = finalRotation.get + 180
                            flipped = true
                        }
                    }
                }

                val titleLabelPadding = thiz.titleLabelPadding
                val twiceTitleLabelPadding = 2 * titleLabelPadding

                //isc debugTrap titleLabel
                // If the titleLabel hasn't been created yet or was destroyed, (re-)create it now.
                if (titleLabel.isEmpty || titleLabel.get.destroyed.getOrElse(false)) {
                    if (isc.isA.DrawPane(thiz.drawPane)) {

                        //isc debugTrap(finalRotation, initialRotation)

                        val rt: Double = finalRotation getOrElse initialRotation.get

                        val titleLabelDynamicProps = js.Dynamic.literal(
                            "drawPane" -> drawPane,
                            "autoDraw" -> false,
                            "eventProxy" -> thiz,
                            "contents" -> title,
                            "knobs" -> null,
                            "left" -> null,
                            "top" -> null,
                            "rotation" -> rt
                        )

                        val pi = thiz._calculateTitleLabelPositionInfo(
                            title,
                            isc.addProperties(
                                js.Object(),
                                thiz.titleLabelDefaults,
                                thiz.titleLabelProperties,
                                titleLabelDynamicProps
                            ),
                            drawPane.get
                        )

                        titleLabelDynamicProps.left = pi.topLeftPoint.getX().get + (if (thiz.drawingBitmap.isDefined) 1 else 0)
                        titleLabelDynamicProps.top = pi.topLeftPoint.getY().get
                        titleLabel = thiz.addPeer(thiz.createAutoChild("titleLabel", titleLabelDynamicProps), "titleLabel")

                        //isc debugTrap titleLabel

                        if (showTitleLabelBackground) {
                            val dims = pi.dims

                            titleLabelBackground.foreach {
                                _.setRect(
                                    isc.DrawItem._makeCoordinate(pi.topLeftPoint.getX().get) - titleLabelPadding,
                                    isc.DrawItem._makeCoordinate(pi.topLeftPoint.getY().get) - titleLabelPadding,
                                    dims.width + twiceTitleLabelPadding,
                                    dims.height + twiceTitleLabelPadding
                                )
                            }

                            if (finalRotation.isDefined)
                                titleLabelBackground.foreach(_.rotateTo(finalRotation.get))
                        }
                    }

                    // Otherwise, the titleLabel has been created.
                } else {
                    // If the titleLabel is in a different DrawPane, then move it to thiz DrawItem's
                    // DrawPane.
                    //isc debugTrap titleLabel

                    if (titleLabel.get.drawPane != drawPane) {
                        drawPane.foreach(_.addDrawItem(titleLabel.get))

                        // If the titleLabel is stacked below the titleLabelBackground, then re-add the titleLabel.
                    } else if (showTitleLabelBackground && titleLabel.get._addOrder < titleLabelBackground.get._addOrder) {
                        drawPane.foreach(_.addDrawItem(titleLabel.get))
                    }

                    // Update the position of the titleLabel and titleLabelBackground.
                    val pi = thiz._calculateTitleLabelPositionInfo(title.getOrElse(null), titleLabel.get, drawPane.get)

                    val p: Point = if (initialRotation.isDefined) {
                        titleLabel.foreach(_.rotateTo(initialRotation.get))
                        var _p: JSUndefined[Point] = jSUndefined
                        titleLabel foreach {
                            titleLabel =>
                                _p = titleLabel._normalize(pi.topLeftPoint.getX().get, pi.topLeftPoint.getY().get, "drawing", "local")
                        }
                        _p.get
                    } else
                        pi.topLeftPoint

                    titleLabel foreach (_._moveTo(isc.DrawItem._makeCoordinate(p.getX().get) + (if (thiz.drawingBitmap.isDefined) 1 else 0), isc.DrawItem._makeCoordinate(p.getY().get)))

                    titleLabel foreach (_.setContents(title.getOrElse(null), true))

                    if (finalRotation.isDefined)
                        titleLabel foreach (_.rotateTo(finalRotation.get))

                    if (thiz.showTitleLabelBackground) {
                        val titleLabelBackground = thiz.titleLabelBackground
                        val dims = pi.dims

                        val p: Point = if (initialRotation.isDefined) {
                            titleLabelBackground foreach (_ rotateTo initialRotation.get)
                            var _p: JSUndefined[Point] = jSUndefined
                            titleLabelBackground foreach {
                                titleLabelBackground =>
                                    _p = titleLabelBackground._normalize(pi.topLeftPoint.getX().get, pi.topLeftPoint.getY().get, "drawing", "local")
                            }
                            _p.get
                        } else
                            pi.topLeftPoint

                        titleLabelBackground.foreach {
                            titleLabelBackground =>
                                titleLabelBackground.setRect(
                                    isc.DrawItem._makeCoordinate(p.getX().get) - titleLabelPadding,
                                    isc.DrawItem._makeCoordinate(p.getY().get) - titleLabelPadding,
                                    dims.width + twiceTitleLabelPadding,
                                    dims.height + twiceTitleLabelPadding)

                                if (finalRotation.isDefined)
                                    titleLabelBackground.rotateTo(finalRotation.get)
                        }
                    }
                }
            }

    }.toThisFunc.opt

    var _calculateTitleLabelPositionInfo: ScOption[ThisFunction3[classHandler, String, DrawLabel, DrawPane, CalculateTitleLabelPositionInfo]] = {
        (thiz: classHandler, title: String, titleLabelProps: DrawLabel, drawPane: DrawPane) =>
            val titleRotationMode = thiz.titleRotationMode.getOrElse(isc.DrawItem._instancePrototype.titleRotationMode).toString
            val _dims: DrawLabel = drawPane.measureLabel(title, titleLabelProps)
            val p = Point(0, 0)

            val localToDrawingTransform = thiz._getNormalizeTransform("local", "drawing")

            val center: Point =
                if (isc.isA.DrawLinePathSS(thiz) && (titleRotationMode == TitleRotationMode.withLine.toString || titleRotationMode == TitleRotationMode.withLineAlwaysUp.toString)) {

                    val segmentPoints = thiz._segmentPoints

                    if (segmentPoints.length <= 2)
                        thiz.getCenter()
                    else {
                        val index = if (segmentPoints.length == 2) 0 else 1

                        val centerSegmentStartPoint = segmentPoints(index)
                        val centerSegmentEndPoint = segmentPoints(index + 1)

                        Point((centerSegmentStartPoint.getX().get + centerSegmentEndPoint.getX().get) / 2, (centerSegmentStartPoint.getY().get + centerSegmentEndPoint.getY().get) / 2)
                    }

                } else if (isc.isA.DrawLinePath(thiz) && (titleRotationMode == TitleRotationMode.withLine.toString || titleRotationMode == TitleRotationMode.withLineAlwaysUp.toString)) {
                    val segmentPoints = thiz._segmentPoints

                    val centerSegmentStartPoint = segmentPoints(1)
                    val centerSegmentEndPoint = segmentPoints(2)

                    Point((centerSegmentStartPoint.getX().get + centerSegmentEndPoint.getX().get) / 2, (centerSegmentStartPoint.getY().get + centerSegmentEndPoint.getY().get) / 2)
                } else {
                    thiz.getCenter()
                }

            if (titleRotationMode != TitleRotationMode.neverRotate.toString) {

                val halfWidth = _dims.width / 2
                val halfHeight = _dims.height / 2
                localToDrawingTransform.transform(center.getX().get - halfWidth, center.getY().get - halfHeight, p)
                localToDrawingTransform.transform(center.getX().get, center.getY().get, center)

                var scale = thiz.scale
                // If thiz draw item is scaled, we need to compensate for the scaling to compute the
                // top left point of the titleLabel because the titleLabel is not scaled.

                if (scale != null && scale.length >= 2) {
                    p.setX(p.getX().get - (1 - scale(0)) * halfWidth)
                    p.setY(p.getY().get - (1 - scale(1)) * halfWidth)
                }
            } else {
                localToDrawingTransform.transform(center.getX().get, center.getY().get, center)
                p.setX(center.getX().get - _dims.width / 2)
                p.setY(center.getY().get - _dims.height / 2)
            }

            p setX isc.DrawItem._makeCoordinate(p.getX().get)
            p setY isc.DrawItem._makeCoordinate(p.getY().get)

            new CalculateTitleLabelPositionInfo {
                override val topLeftPoint: Point = p
                override val centerPoint: Point = center
                override val dims: DrawLabel = _dims
            }

    }.toThisFunc.opt
    var click: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
    var contextMenu: ScOption[MenuSS] = ScNone
    var cornerResizeKnob: ScOption[DrawKnob with MultiAutoChild] = ScNone
    var cursor: ScOption[Cursor] = ScNone
    var destroyed: ScOption[Boolean] = ScNone
    var destroying: ScOption[Boolean] = ScNone
    var dragMove: ScOption[js.Function0[Boolean]] = ScNone

    def move(parentDrawItem: DrawItem, drawItems: IscArray[DrawItem], kindOfRefs: KindOfRefs): Unit = {

        val center = parentDrawItem.getCenter()
        val centerX = center.getX().getOrElse(0.0)
        val centerY = center.getY().getOrElse(0.0)

        drawItems foreach {
            item =>
                if (isc.isA.DrawLinePathSS(item)) {
                    val drawItem = item.asInstanceOf[DrawLinePathSS]

                    kindOfRefs match {
                        case OutcomingRefs =>
                            if (drawItem.startLeft2CentrLeft.isEmpty)
                                drawItem.startLeft2CentrLeft = centerX - drawItem.startLeft
                            if (drawItem.startTop2CentrTop.isEmpty)
                                drawItem.startTop2CentrTop = centerY - drawItem.startTop

                            drawItem.setStartPoint(centerX - drawItem.startLeft2CentrLeft.getOrElse(0.0), centerY - drawItem.startTop2CentrTop.getOrElse(0.0))

                        case IncomingRefs =>
                            if (drawItem.endLeft2CentrLeft.isEmpty)
                                drawItem.endLeft2CentrLeft = centerX - drawItem.endLeft
                            if (drawItem.endTop2CentrTop.isEmpty)
                                drawItem.endTop2CentrTop = centerY - drawItem.endTop

                            drawItem.setEndPoint(centerX - drawItem.endLeft2CentrLeft.getOrElse(0.0), centerY - drawItem.endTop2CentrTop.getOrElse(0.0))
                    }
                }
        }
    }

    def resize(parentDrawItem: DrawItem, drawItems: IscArray[DrawItem], kindOfRefs: KindOfRefs, newLeft: Double, newTop: Double, newRight: Double, newBottom: Double): Unit = {
        val center = parentDrawItem.getCenter()
        val centerX = center.getX().getOrElse(0.0)
        val centerY = center.getY().getOrElse(0.0)

        drawItems.foreach {
            item =>
                if (isc.isA.DrawLinePathSS(item)) {
                    val drawItem = item.asInstanceOf[DrawLinePathSS]
                    kindOfRefs match {
                        case OutcomingRefs =>

                            if (!parentDrawItem.isInBounds(drawItem.startLeft, drawItem.startTop)) {

                                val box = parentDrawItem.getBoundingBox()
                                //println(s"box: $box")

                                {
                                    val x = box(0)
                                    if (drawItem.startLeft < x) {
                                        drawItem.startLeft = x
                                        drawItem.startLeft2CentrLeft = centerX - x
                                    }
                                }

                                {
                                    val width = box(2)
                                    if (drawItem.startLeft > width) {
                                        drawItem.startLeft = width
                                        drawItem.startLeft2CentrLeft = centerX - width
                                    }
                                }

                                {
                                    val y = box(1)
                                    if (drawItem.startTop < y) {
                                        drawItem.startTop = y
                                        drawItem.startTop2CentrTop = centerY - y
                                    }
                                }

                                {
                                    val height = box(3)
                                    if (drawItem.startTop > height) {
                                        drawItem.startTop = height
                                        drawItem.startTop2CentrTop = centerY - height
                                    }
                                }
                            }

                            drawItem.setStartPoint(centerX - drawItem.startLeft2CentrLeft.getOrElse(0.0), centerY - drawItem.startTop2CentrTop.getOrElse(0.0))

                        case IncomingRefs =>
                            val x = centerX - drawItem.endLeft2CentrLeft.getOrElse(0.0)
                            val y = centerY - drawItem.endTop2CentrTop.getOrElse(0.0)

                            if (x < newLeft) {
                                drawItem.setEndPoint(newLeft, y)
                                drawItem.endLeft2CentrLeft = centerX - newLeft
                            }
                            else if (x > newRight) {
                                drawItem.setEndPoint(newRight, y)
                                drawItem.endLeft2CentrLeft = centerX - newRight
                            }
                            else if (y < newTop) {
                                drawItem.setEndPoint(x, newTop)
                                drawItem.endTop2CentrTop = centerX - newTop
                            }
                            else if (y > newBottom) {
                                drawItem.setEndPoint(x, newBottom)
                                drawItem.endTop2CentrTop = centerX - newBottom
                            }
                    }
                }
        }
    }
    var dragResizeMove: ScOption[ThisFunction6[classHandler, String, Double, Double, Double, Double, String, _]] = ScNone
    var dragStart: ScOption[js.Function0[Boolean]] = ScNone
    var dragStartDistance: ScOption[Double] = ScNone
    var dragStop: ScOption[js.Function0[Boolean]] = ScNone
    var onDragStop: ScOption[Function2[Double, Double, _]] = ScNone
    var drawGroup: ScOption[DrawGroup] = ScNone
    var drawPane: ScOption[DrawPane] = ScNone
    var editProxyConstructor: ScOption[SCClassName] = ScNone
    var editProxyProperties: ScOption[EditProxy] = ScNone
    var endArrow: ScOption[ArrowStyle] = ScNone
    var endKnob: ScOption[DrawKnob] = ScNone
    var fieldDataSource: ScOption[DataSource] = ScNone
    var fillColor: ScOption[CSSColor] = ScNone
    var fillGradient: ScOption[Gradient] = ScNone
    var fillOpacity: ScOption[Double] = ScNone
    var keepInParentRect: ScOption[Boolean] = ScNone
    var palette: ScOption[JSObject] = ScNone
    var knobs: ScOption[Seq[KnobType]] = ScNone
    var lineCap: ScOption[LineCap] = ScNone
    var lineColor: ScOption[CSSColor] = ScNone
    var lineOpacity: ScOption[Double] = ScNone
    var linePattern: ScOption[LinePattern] = ScNone
    var lineWidth: ScOption[Double] = ScNone
    var mouseDown: ScOption[js.Function0[Boolean]] = ScNone
    var mouseMove: ScOption[js.Function0[Boolean]] = ScNone
    var mouseOut: ScOption[js.Function0[Boolean]] = ScNone
    var mouseOver: ScOption[js.Function0[Boolean]] = ScNone
    var mouseUp: ScOption[js.Function0[Boolean]] = ScNone
    var moved: ScOption[ThisFunction2[classHandler, Double, Double, _]] = {
        (thiz: classHandler, dx: Double, dy: Double) =>

            //isc debugTrap thiz

            thiz.outConnectedItems foreach (move(thiz, _, OutcomingRefs))

            thiz.inConnectedItems foreach (move(thiz, _, IncomingRefs))

            thiz.targetGlue.foreach(_.setGluedDrawItem())

            def moveGluedItems(targetGlueDrawItem: JSUndefined[DrawItem]): Unit = {
                targetGlueDrawItem.foreach {
                    drawItem ⇒
                        drawItem.targetGlue.foreach {
                            drawItem ⇒
                                drawItem.outConnectedItems foreach (move(drawItem, _, OutcomingRefs))
                                drawItem.inConnectedItems foreach (move(drawItem, _, IncomingRefs))
                        }
                        moveGluedItems(drawItem.targetGlue)
                }
            }

            moveGluedItems(thiz)

            true
    }.toThisFunc.opt

    def intercect2Rectangle(rect1: IscArray[Double], rect2: IscArray[Double]): Boolean = {

        val __rect1 = Rectangle(rect1)
        val __rect2 = Rectangle(rect2)

        val rect1Box = __rect1.getRect()
        val rect2Box = __rect2.getRect()

        val drawRect1 = DrawRect.create()
        val drawRect2 = DrawRect.create()

        drawRect1.setRect(rect1Box.left, rect1Box.top, rect1Box.width, rect1Box.height)

        //        val _rect1 = drawRect1.getBoundingBox()
        //        println(s"box1: rect1(0): ${rect1(0)}, rect1(1): ${rect1(1)}, rect1(2): ${rect1(2)}, rect1(3):${rect1(3)}")
        //        println(s"box1: _rect1(0): ${_rect1(0)}, rect_1(1): ${_rect1(1)}, _rect1(2): ${_rect1(2)}, _rect1(3):${_rect1(3)}")

        drawRect2.setRect(rect2Box.left, rect2Box.top, rect2Box.width, rect2Box.height)

        //        val _rect2 = drawRect2.getBoundingBox()
        //        println(s"box2: rect1(0): ${rect2(0)}, rect2(1): ${rect2(1)}, rect2(2): ${rect2(2)}, rect2(3):${rect2(3)}")
        //        println(s"box2: _rect2(0): ${_rect2(0)}, rect_1(1): ${_rect2(1)}, _rect2(2): ${_rect2(2)}, _rect2(3):${_rect2(3)}")

        val appex1 = Rectangle(rect1).getAppex()
        val appex2 = Rectangle(rect2).getAppex()

        val res1 = appex1.exists(item => drawRect2.isInBounds(item.get.getX().get, item.get.getY().get))
        val res2 = appex2.exists(item => drawRect1.isInBounds(item.get.getX().get, item.get.getY().get))

        res1 || res2
    }

    var deleteGlueItems: ScOption[ThisFunction0[classHandler, _]] = {
        (thiz: classHandler) =>

            if (thiz.sourceGlue.isDefined) {
                if (thiz.targetGlue.isDefined) {
                    thiz.targetGlue.get.sourceGlue = thiz.sourceGlue
                    thiz.sourceGlue.get.targetGlue = thiz.targetGlue

                    thiz.sourceGlue = jSUndefined
                    thiz.targetGlue = jSUndefined
                } else {
                    thiz.sourceGlue.get.targetGlue = jSUndefined
                    thiz.sourceGlue = jSUndefined
                }
            }
    }.toThisFunc.opt

    var setGluedDrawItem: ScOption[ThisFunction0[classHandler, _]] = {
        (thiz: classHandler) =>

            thiz.sourceGlue.foreach {
                sourceGlue =>
                    if (thiz.GetClass().isMethodSupported("setRect") && sourceGlue.GetClass().isMethodSupported("setRect")) {

                        val sourceRect = Rectangle(sourceGlue.getBoundingBox()).getRect()
                        val rect = Rectangle(thiz.getBoundingBox()).getRect()

                        val drawRect = thiz.asInstanceOf[DrawRect]

                        drawRect setLeft (sourceRect.left + sourceRect.width / 2) - rect.width / 2
                        drawRect setTop sourceRect.top + sourceRect.height
                        drawRect setWidth rect.width
                        drawRect setHeight rect.height

                        thiz.targetGlue.foreach(_.setGluedDrawItem())
                    }
            }
    }.toThisFunc.opt

    var _moved4Glue: ScOption[ThisFunction4[classHandler, Double, Double, Double, Double, _]] = {
        (thiz: classHandler, newLeft: Double, newTop: Double, newRight: Double, newBottom: Double) =>
            if (thiz.enable2Glue.getOrElse(false)) {

                val connectItems = thiz.drawPane.map(_.drawItems.filter(item => item.enable4Glue.getOrElse(false) && item.getID() != thiz.getID() && (item.sourceGlue.isEmpty || item.sourceGlue.get.getID() != thiz.getID())))

                connectItems.foreach {
                    _.foreach {
                        item =>

                            if (intercect2Rectangle(item.getBoundingBox(), thiz.getBoundingBox())) {
                                if (item.targetGlue.isEmpty) {
                                    isc.ask(s"Привязать ${thiz.title} к ${item.title} ?", {
                                        (res: Boolean) =>
                                            if (res) {
                                                thiz.sourceGlue = item
                                                item.targetGlue = thiz
                                                thiz.setGluedDrawItem()
                                            }
                                    })
                                }
                            } else {
                                if (item.targetGlue.isDefined && item.targetGlue.get.getID() == thiz.getID()) {
                                    isc.ask(s"Удалить привязку ${thiz.title} к ${item.title} ?", {
                                        (res: Boolean) =>
                                            if (res) {
                                                thiz.sourceGlue = jSUndefined
                                                item.targetGlue = jSUndefined
                                            } else
                                                thiz.setGluedDrawItem()
                                    })
                                }
                            }
                    }
                }
            }
    }
      .toThisFunc.opt
    var onDragMove: ScOption[ThisFunction2[classHandler, Double, Double, Boolean]] = ScNone
    var onDragSrart: ScOption[ThisFunction2[classHandler, Double, Double, Boolean]] = ScNone
    var moveKnob: ScOption[DrawKnob] = ScNone
    var moveKnobOffset: ScOption[Seq[Double]] = ScNone
    var moveKnobPoint: ScOption[MoveKnobPoint] = ScNone
    var prompt: ScOption[HTMLString] = ScNone
    var proportionalResizeModifiers: ScOption[Seq[KeyName]] = ScNone
    var proportionalResizing: ScOption[ProportionalResizeMode] = ScNone
    var resized: ScOption[js.Function0[_]] = ScNone

    var resized1: ScOption[ThisFunction4[classHandler, Double, Double, Double, Double, _]] = {
        (thiz: classHandler, newLeft: Double, newTop: Double, newRight: Double, newBottom: Double) =>

            thiz.outConnectedItems foreach (resize(thiz, _, OutcomingRefs, newLeft, newTop, newRight, newBottom))

            thiz.inConnectedItems foreach (resize(thiz, _, IncomingRefs, newLeft, newTop, newRight, newBottom))

            thiz.setGluedDrawItem()
            thiz.targetGlue.foreach(_.setGluedDrawItem())
    }.toThisFunc.opt

    var _resized1: ScOption[ThisFunction4[classHandler, Double, Double, Double, Double, _]] = {
        (thiz: classHandler, newLeft: Double, newTop: Double, newRight: Double, newBottom: Double) =>
            thiz._transform = jSUndefined
            thiz.updateControlKnobs()
            thiz._updateTitleLabelAndBackground()
            thiz._updateQuadTreeItem()
            thiz.saveCoordinates()
            thiz.resized1(newLeft, newTop, newRight, newBottom)
    }.toThisFunc.opt

    var resizeKnobPoints: ScOption[Seq[ResizeKnobPoint]] = ScNone
    var resizeOutline: ScOption[DrawRect] = ScNone
    var resizeViaLocalTransformOnly: ScOption[Boolean] = ScNone
    var rotation: ScOption[Double] = ScNone
    var scale: ScOption[Seq[Double]] = ScNone

    var setEditMode: ScOption[ThisFunction3[classHandler, JSUndefined[Boolean], JSUndefined[EditContextSS], JSUndefined[DrawItem], _]] = {
        (thiz: classHandler, editingOn: JSUndefined[Boolean], editContext: JSUndefined[EditContextSS], editNode: JSUndefined[DrawItem]) =>
            //isc debugTrap(0)
            var _editingOn = editingOn
            var _editContext = editContext
            if (editingOn.isEmpty) _editingOn = true

            if (thiz.editingOn != _editingOn) {
                thiz.editingOn = _editingOn
                //isc debugTrap thiz.editingOn

                if (thiz.editingOn.getOrElse(false)) {
                    // If an EditTree (or similar) component is passed which contains
                    // an EditContext rather than being one, grab the actual EditContext.

                    val isContext = isc.isA.EditContext(_editContext.get)
                    val isContextSS = isc.isA.EditContextSS(_editContext.get)

                    //isc debugTrap (isContext, isContextSS)

                    if (_editContext.isDefined && !isContext && _editContext.get.getEditContext.isDefined) {
                        _editContext = _editContext.get.getEditContext.get()
                        //isc debugTrap _editingOn
                    }
                    thiz.editContext = _editContext
                }

                editNode foreach (editNode => thiz.editNode = editNode.asInstanceOf[EditNode])
                if (thiz.editingOn.isDefined && thiz.editProxy.isEmpty) {

                    val defaults = isc.Canvas._getEditProxyPassThruProperties(thiz.editContext.get)

                    if (thiz.editNode.isDefined && thiz.editNode.get.editProxyProperties.isDefined) {
                        //isc debugTrap defaults
                        isc.addProperties(defaults, thiz.editNode.get.editProxyProperties)
                        //isc debugTrap defaults
                    }

                    thiz.editProxy = thiz.createAutoChild("editProxy", defaults)
                }

                // Allow edit proxy to perform custom operations on edit mode change
                if (thiz.editProxy.isDefined) {
                    //isc debugTrap editNode
                    editNode.foreach {
                        editNode =>
                            val canDrag = editNode.defaults.canDrag //Добавил 25/05/2016

                            thiz.editProxy.get.setEditMode(editingOn.get)

                            //Добавил 25/05/2016
                            canDrag.foreach {
                                canDrag =>
                                    editNode.liveObject.canDrag = canDrag
                            }
                        ///////////////////////
                    }
                    //isc debugTrap editNode
                }
            }
    }.toThisFunc.opt

    var setEditableProperties: ScOption[ThisFunction1[classHandler, JSObject, _]] = {
        (thiz: classHandler, properties: JSObject) =>
            if (thiz.editModeOriginalValues.isEmpty) thiz.editModeOriginalValues = js.Object()

            js.Object.keys(properties).foreach {
                key =>
                    if (isc.getPropValue(thiz.editModeOriginalValues.get, key).isEmpty) {
                        thiz.logInfo(s"Field $key - value is going to live values editModeOriginalValues")
                        isc.getPropValue[JSObject](properties, key).foreach(thiz.setPropertyValue(key, _))
                    } else {
                        thiz.logInfo(s"Field $key - value is going to original values editModeOriginalValues")
                        isc.setPropValue(thiz.editModeOriginalValues.get, key, isc.getPropValue(properties, key))
                        isc.getPropValue[JSObject](properties, key).foreach(thiz.setPropertyValue(key, _)) // ?? Добавил не знаю на чем отразится 25/05/2016
                    }
            }

            thiz.editablePropertiesUpdated(properties)
    }.toThisFunc.opt

    var _setResizeBoundingBox: ScOption[ThisFunction12[classHandler, Double, Double, Double, Double, Double, Double, JSObject, com.simplesys.SmartClient.math.AffineTransform, Double, Double, Double, Double, _]] = {
        (thiz: classHandler, oldLeft: Double, oldTop: Double, oldRight: Double, oldBottom: Double, oldCenterX: Double, oldCenterY: Double, oldShape: JSObject, oldLocalTransform: com.simplesys.SmartClient.math.AffineTransform, newLeft: Double, newTop: Double, newRight: Double, newBottom: Double) =>

            var _newLeft = newLeft
            var _newRight = newRight
            var _newTop = newTop
            var _newBottom = newBottom

            var swap: JSUndefined[Double] = jSUndefined

            if (oldLeft != oldRight && oldTop != oldBottom && _newLeft != _newRight && _newTop != _newBottom) {
                if (_newLeft > _newRight) {
                    swap = _newLeft
                    _newLeft = _newRight
                    _newRight = swap.get
                }
                if (_newTop > _newBottom) {
                    swap = _newTop
                    _newTop = _newBottom
                    _newBottom = swap.get
                }

                val sx = (_newRight - _newLeft) / (oldRight - oldLeft)
                val sy = (_newBottom - _newTop) / (oldBottom - oldTop)
                val dx = _newLeft - sx * oldLeft
                val dy = _newTop - sy * oldTop

                val transform: AffineTransform = isc.AffineTransform.create(sx, 0, dx, 0, sy, dy)

                if (thiz.exemptFromGlobalTransform) {
                    thiz.drawPane.foreach {
                        drawPane =>
                            val scrollLeft = drawPane.scrollLeft
                            val scrollTop = drawPane.scrollTop
                            transform.preTranslate(-scrollLeft, -scrollTop)
                            transform.translate(scrollLeft, scrollTop)
                    }

                } else if (thiz.drawPane.isDefined) {

                    thiz.drawPane.foreach {
                        drawPane =>
                            val g = drawPane._getGlobalTransform()
                            val gInv = drawPane._getInverseGlobalTransform()

                            transform leftMultiply gInv
                            transform rightMultiply g
                    }
                }

                // Combine the transform with the old local transform.
                transform rightMultiply oldLocalTransform

                thiz._updateLocalTransform(transform, oldCenterX, oldCenterY, oldShape, true, thiz.resizeViaLocalTransformOnly)

                if (
                    (isc.DrawItem._makeCoordinate(oldLeft) != isc.DrawItem._makeCoordinate(_newLeft) && isc.DrawItem._makeCoordinate(oldRight - oldLeft) == isc.DrawItem._makeCoordinate(_newRight - _newLeft)) ||
                      (isc.DrawItem._makeCoordinate(oldTop) != isc.DrawItem._makeCoordinate(_newTop) && isc.DrawItem._makeCoordinate(oldBottom - oldTop) == isc.DrawItem._makeCoordinate(_newBottom - _newTop))
                ) {
                    thiz._moved(isc.DrawItem._makeCoordinate(_newLeft - oldLeft), isc.DrawItem._makeCoordinate(_newTop - oldTop))
                    thiz._moved4Glue(isc.DrawItem._makeCoordinate(_newLeft), isc.DrawItem._makeCoordinate(_newTop), isc.DrawItem._makeCoordinate(_newRight), isc.DrawItem._makeCoordinate(_newBottom))
                }

                if (isc.DrawItem._makeCoordinate(oldRight - oldLeft) != isc.DrawItem._makeCoordinate(_newRight - _newLeft) || isc.DrawItem._makeCoordinate(oldBottom - oldTop) != isc.DrawItem._makeCoordinate(_newBottom - _newTop)) {
                    thiz._resized()
                    thiz._resized1(isc.DrawItem._makeCoordinate(_newLeft), isc.DrawItem._makeCoordinate(_newTop), isc.DrawItem._makeCoordinate(_newRight), isc.DrawItem._makeCoordinate(_newBottom))
                }
            }
    }.toThisFunc.opt
    var shadow: ScOption[Shadow] = ScNone
    var shapeData: ScOption[JSObject] = ScNone
    var showHover: ScOption[Boolean] = ScNone
    var showResizeOutline: ScOption[Boolean] = ScNone
    var showTitleLabelBackground: ScOption[Boolean] = ScNone
    var sideResizeKnob: ScOption[DrawKnob] = ScNone
    var startArrow: ScOption[ArrowStyle] = ScNone
    var startKnob: ScOption[DrawKnob] = ScNone
    var title: ScOption[String] = ScNone
    var titleLabel: ScOption[DrawLabel] = ScNone
    var titleLabelBackground: ScOption[DrawLabel] = ScNone
    var titleLabelPadding: ScOption[Double] = ScNone
    var titleRotationMode: ScOption[TitleRotationMode] = ScNone
    var translate: ScOption[Seq[Double]] = ScNone
    var xShearFactor: ScOption[Double] = ScNone
    var yShearFactor: ScOption[Double] = ScNone
    val `type`: ScOption[String] = "DrawItem".opt
}
