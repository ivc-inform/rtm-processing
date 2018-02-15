package com.simplesys.SmartClient.DataBinding.props

import com.simplesys.SmartClient.DataBinding.Criterion
import com.simplesys.System.Types.OperatorId._
import com.simplesys.System._
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

class CriterionProps extends AbstractClassProps {
    var criteria: ScOption[Seq[Criterion]] = ScNone
    var end: ScOption[JSObject] = ScNone
    var fieldName: ScOption[String] = ScNone
    var operator: ScOption[OperatorId] = ScNone
    var start: ScOption[JSObject] = ScNone
    var value: ScOption[JSAny] = ScNone
    var _constructor: ScOption[String] = ScNone
}
