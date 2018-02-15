package com.simplesys.SmartClient.DataBinding.props.dataSource

import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

class DSRequestModifierProps extends AbstractClassProps {
    var end: ScOption[String] = ScNone
    var fieldName: ScOption[String] = ScNone
    var operator: ScOption[String] = ScNone
    var start: ScOption[String] = ScNone
    var value: ScOption[String] = ScNone
}
