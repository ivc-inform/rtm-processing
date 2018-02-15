package com.simplesys.SmartClient.Control.props

import com.simplesys.SmartClient.Foundation.props.CanvasProps
import com.simplesys.SmartClient.Foundation.{Canvas, Scrollbar}
import com.simplesys.SmartClient.Layout.Snapbar
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class RangeSliderProps extends CanvasProps {
    var changed: ScOption[js.Function3[Double, Double, Boolean, _]] = ScNone
    var endThumb: ScOption[Snapbar] = ScNone
    var endValue: ScOption[Double] = ScNone
    var maxValue: ScOption[Double] = ScNone
    var minValue: ScOption[Double] = ScNone
    var scrollbar: ScOption[Scrollbar] = ScNone
    var startThumb: ScOption[Snapbar] = ScNone
    var startValue: ScOption[Double] = ScNone
    var track: ScOption[Canvas] = ScNone
    var vertical: ScOption[Boolean] = ScNone
}
