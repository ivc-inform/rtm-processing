package com.simplesys.SmartClient.DataBinding.props.simpleType

import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

import scala.scalajs.js._

class SummaryConfigurationProps extends AbstractClassProps {
    var badFormulaResultValue: ScOption[String] = ScNone
    var invalidSummaryValue: ScOption[String] = ScNone
}
