package com.simplesys.SmartClient.Foundation.props

import com.simplesys.option.{ScOption, ScNone}
import com.simplesys.props.AbstractClassProps

import scala.scalajs.js
import scala.scalajs.js._

class PrintPropertiesProps extends AbstractClassProps {
    var includeControls: ScOption[Seq[String]] = ScNone
    var omitControls: ScOption[Seq[String]] = ScNone
    var printForExport: ScOption[Boolean] = ScNone
}
