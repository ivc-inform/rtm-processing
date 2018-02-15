package com.simplesys.SmartClient.Drawing.gradient.props

import scala.scalajs.js._
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

class LinearGradientProps extends GradientProps {
    var x1: ScOption[String] = ScNone
    var x2: ScOption[String] = ScNone
    var y1: ScOption[String] = ScNone
    var y2: ScOption[String] = ScNone
}
