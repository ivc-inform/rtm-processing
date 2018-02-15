package com.simplesys.SmartClient.Tools.props

import com.simplesys.System.Types._

import scala.scalajs.js._
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

class SelectionOutlineProps extends AbstractClassProps {
    var border: ScOption[String] = ScNone
    var labelBackgroundColor: ScOption[CSSColor] = ScNone
}
