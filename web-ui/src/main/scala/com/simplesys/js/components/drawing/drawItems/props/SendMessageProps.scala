package com.simplesys.js.components.drawing.drawItems.props

import com.simplesys.js.components.drawing.drawItems.SendMessage
import com.simplesys.option.ScOption
import com.simplesys.option.ScOption._

class SendMessageProps extends VariableProps {
    type classHandler <: SendMessage

    override val `type`: ScOption[String] = "SendMessage".opt
}
