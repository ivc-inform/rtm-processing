package com.simplesys.SmartClient.Drawing.gradient.props

import scala.scalajs.js._
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

class RadialGradientProps extends GradientProps {
    var cx: ScOption[String] = ScNone
    var cy: ScOption[String] = ScNone
    var fx: ScOption[String] = ScNone
    var fy: ScOption[String] = ScNone
    var r: ScOption[String] = ScNone
}
