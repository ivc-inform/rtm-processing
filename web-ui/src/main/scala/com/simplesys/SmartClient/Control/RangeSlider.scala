package com.simplesys.SmartClient.Control

import com.simplesys.SmartClient.Foundation.{Scrollbar, Canvas, AbstractCanvasCompanion}
import com.simplesys.SmartClient.Layout.Snapbar
import com.simplesys.System.Types.AutoChild

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait RangeSlider extends Canvas {
    var changed: js.Function3[Double, Double, Boolean, _]
    val endThumb: Snapbar with AutoChild
    var endValue: Double
    var maxValue: Double
    var minValue: Double
    val scrollbar: Scrollbar with AutoChild
    val startThumb: Snapbar with AutoChild
    val startValue: Double
    val track: Canvas with AutoChild
    val vertical: Boolean
}



