package com.simplesys.SmartClient.Drawing.props

import com.simplesys.SmartClient.Drawing.{DrawItem, DrawKnob, DrawPane}
import com.simplesys.SmartClient.Foundation.props.CanvasProps
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js.{ThisFunction0, ThisFunction5}

class DrawKnobProps extends CanvasProps {
    type classHandler <: DrawKnob

    var drawPane: ScOption[DrawPane] = ScNone
    var knobShape: ScOption[DrawItem] = ScNone
    var knobShapeDefaults: ScOption[DrawItem] = ScNone
    var resetKnobPosition: ScOption[ThisFunction0[classHandler, _]] = ScNone
    var updatePoints: ScOption[ThisFunction5[classHandler, Double, Double, Double, Double, String, _]] = ScNone
    var x: ScOption[Double] = ScNone
    var y: ScOption[Double] = ScNone

    _constructor = "DrawKnob".opt
}
