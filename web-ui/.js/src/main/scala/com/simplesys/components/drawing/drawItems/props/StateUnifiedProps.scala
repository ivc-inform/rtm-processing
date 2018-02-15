package com.simplesys.components.drawing.drawItems.props

import com.simplesys.SmartClient.Drawing.drawItem.props.DrawOvalProps
import com.simplesys.components.drawing.drawItems.StateUnified
import com.simplesys.option.ScOption
import com.simplesys.option.ScOption._

class StateUnifiedProps extends DrawOvalProps {
    type classHandler <: StateUnified

    override val `type`: ScOption[String] = "StateUnified".opt
}
