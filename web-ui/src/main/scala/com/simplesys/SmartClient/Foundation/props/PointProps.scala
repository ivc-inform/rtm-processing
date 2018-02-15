package com.simplesys.SmartClient.Foundation.props

import com.simplesys.option.{ScOption, ScNone}
import com.simplesys.props.AbstractClassProps

import scala.scalajs.js._

class PointProps extends AbstractClassProps {
    var x: ScOption[Int] = ScNone
    var y: ScOption[Int] = ScNone
}
