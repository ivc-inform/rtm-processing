package com.simplesys.components.drawing.drawItems.props

import com.simplesys.SmartClient.Drawing.drawItem.props.DrawOvalProps
import com.simplesys.components.drawing.drawItems.StartState
import com.simplesys.option.ScOption
import com.simplesys.option.ScOption._

class StartStateProps extends DrawOvalProps {
    type classHandler <: StartState

    override val `type`: ScOption[String] = "StartState".opt
}
