package com.simplesys.js.components.drawing.drawItems.props

import com.simplesys.js.components.drawing.drawItems.CalcGroup
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

class CalcGroupProps extends VariableProps {
    type classHandler <: CalcGroup

    val scaleLine: ScOption[String] = ScNone

    override val `type`: ScOption[String] = "CalcGroup".opt
}
