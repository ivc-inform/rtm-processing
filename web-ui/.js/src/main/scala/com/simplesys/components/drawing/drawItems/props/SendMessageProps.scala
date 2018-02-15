package com.simplesys.components.drawing.drawItems.props

import com.simplesys.components.drawing.drawItems.SendMessage
import com.simplesys.option.ScOption
import com.simplesys.option.ScOption._

class SendMessageProps extends VariableProps {
    type classHandler <: SendMessage

    override val `type`: ScOption[String] = "SendMessage".opt
}
