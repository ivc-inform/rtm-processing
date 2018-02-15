package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.System.Class
import com.simplesys.System.Types.FormItemComponentType
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.System.{JSAny, JSDictionary, JSDictionaryAny}

import scala.scalajs.js
import scala.scalajs.js._

class SliderItemProps extends CanvasItemProps {
    var changeOnDrag: ScOption[Boolean] = ScNone
    var maxValue: ScOption[Double] = ScNone
    var minValue: ScOption[Double] = ScNone
    var numValues: ScOption[Int] = ScNone
    var roundPrecision: ScOption[Int] = ScNone
    var roundValues: ScOption[Boolean] = ScNone
    var slider: ScOption[Canvas ] = ScNone
    var sliderConstructor: ScOption[Class] = ScNone
    var sliderProperties: ScOption[JSDictionaryAny] = ScNone
    var vertical: ScOption[Boolean] = ScNone
    `type` = FormItemComponentType.SliderItem
}
