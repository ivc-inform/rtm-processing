package com.simplesys.SmartClient.Drawing.props

import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

import scala.scalajs.js._

class PointProps extends AbstractClassProps {
    val x: ScOption[Int] = ScNone
    val y: ScOption[Int] = ScNone
}
