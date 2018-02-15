package com.simplesys.SmartClient.Drawing.gradient.props

import com.simplesys.System.Types._
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

import scala.scalajs.js._

class ColorStopProps extends AbstractClassProps {
    val color: ScOption[CSSColor] = ScNone
    val offset: ScOption[Double] = ScNone
    val opacity: ScOption[Double] = ScNone
}
