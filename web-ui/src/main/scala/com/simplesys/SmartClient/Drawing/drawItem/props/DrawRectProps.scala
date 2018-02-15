package com.simplesys.SmartClient.Drawing.drawItem.props

import com.simplesys.SmartClient.Drawing.DrawRectDefaults
import com.simplesys.SmartClient.Drawing.drawItem.DrawRect
import com.simplesys.SmartClient.Drawing.props.DrawItemProps
import com.simplesys.SmartClient.System.{IscArray, isc}
import com.simplesys.SmartClient.math.AffineTransform
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js.ThisFunction7



class DrawRectProps extends DrawItemProps {
    type classHandler <: DrawRect

    var height: ScOption[Int] = ScNone
    var left: ScOption[Int] = ScNone
    var rounding: ScOption[Double] = ScNone
    var top: ScOption[Int] = ScNone
    var width: ScOption[Int] = ScNone
    var defaults: ScOption[DrawRectDefaults] = ScNone

    var _updateLocalTransform: ScOption[ThisFunction7[classHandler, AffineTransform, Double, Double, JSUndefined[DrawRect], Boolean, Boolean, IscArray[JSAny], _]] = {
        (thiz: classHandler, transform: AffineTransform, cx: Double, cy: Double, initialShape: JSUndefined[DrawRect], fireReshaped: Boolean, viaLocalTransformOnly: Boolean, arguments: IscArray[JSAny]) =>
            var _initialShape = initialShape

            if (initialShape.isEmpty)
                _initialShape = thiz

            var superMentodExecuted = false
            if (!viaLocalTransformOnly) {
                val dd: Double = if (thiz._hasStroke()) thiz.lineWidth / 2 else 0
                val info = isc.DrawItem._fitBestRect(transform, cx, cy, _initialShape.get.left, _initialShape.get.top, _initialShape.get.width, _initialShape.get.height, dd)

                if (info.success) {
                    thiz.Super("_updateLocalTransform", IscArray(transform, cx, cy, _initialShape, false, true), arguments)
                    superMentodExecuted = true
                    thiz.setRect(
                        isc.DrawItem._makeCoordinate(info.left),
                        isc.DrawItem._makeCoordinate(info.top),
                        isc.DrawItem._makeCoordinate(info.width),
                        isc.DrawItem._makeCoordinate(info.height),
                        isc.DrawItem._makeCoordinate(cx),
                        isc.DrawItem._makeCoordinate(cy)
                    )
                }
            }

            if (!superMentodExecuted)
                thiz.Super("_updateLocalTransform", IscArray(transform, cx, cy, _initialShape, fireReshaped, true), arguments)


    }.toThisFunc.opt

    override val `type`: ScOption[String] = "DrawRect".opt
}
