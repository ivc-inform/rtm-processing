package com.simplesys.js.components.drawing.drawItems.props

import com.simplesys.SmartClient.Drawing.drawItem.props.DrawRectProps
import com.simplesys.js.components.drawing.drawItems.Variable
import com.simplesys.option.ScOption
import com.simplesys.option.ScOption._

class VariableProps extends DrawRectProps {
    type classHandler <: Variable

    override val `type`: ScOption[String] = "Variable".opt
}
