package com.simplesys.SmartClient.Drawing.props

import com.simplesys.SmartClient.Drawing.gradient.Gradient
import com.simplesys.SmartClient.Drawing.{DrawItem, DrawPane}
import com.simplesys.SmartClient.Foundation.props.CanvasProps
import com.simplesys.SmartClient.System.isc
import com.simplesys.System.{JSUndefined, jSUndefined}
import com.simplesys.System.Types.Cursor
import com.simplesys.System.Types.Cursor.Cursor
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.option.ScOption._
import com.simplesys.function._

import scala.scalajs.js.ThisFunction1

class DrawPaneProps extends CanvasProps {

    import com.simplesys.SmartClient.Drawing.DrawPaneDefaults

    type classHandler <: DrawPane

    var drawingHeight: ScOption[Double] = ScNone
    var drawingWidth: ScOption[Double] = ScNone
    var drawItems: ScOption[Seq[DrawItem]] = ScNone
    var gradients: ScOption[Seq[Gradient]] = ScNone
    var rotation: ScOption[Double] = ScNone
    var translate: ScOption[Seq[Double]] = ScNone
    var zoomLevel: ScOption[Double] = ScNone
    var defaults: ScOption[DrawPaneDefaults] = ScNone
    var `type`: ScOption[String] = "DrawPane".opt

    var _updateCursor: ScOption[ThisFunction1[classHandler, JSUndefined[DrawItem], _]] = {
        (thiz: classHandler, drawItem: JSUndefined[DrawItem]) =>
            var currentCursor: JSUndefined[String] = jSUndefined
            drawItem.foreach {
                drawItem =>
                    //isc debugTrap currentCursor
                    currentCursor = drawItem.getCurrentCursor()
                    //isc debugTrap currentCursor
            }

            if (currentCursor.isEmpty) {
                //isc debugTrap currentCursor
                currentCursor = if (thiz.canDragScroll) Cursor.POINTER.toString else thiz.getCurrentCursor()
            }

            currentCursor foreach thiz._applyCursor

    }.toThisFunc.opt
}
