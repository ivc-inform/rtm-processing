package com.simplesys.js.components.drawing.drawItems.props

import com.simplesys.SmartClient.Drawing.drawItem.props.DrawRectProps
import com.simplesys.js.components.drawing.drawItems.TimerUnified
import com.simplesys.option.ScOption
import com.simplesys.option.ScOption._

class TimerUnifiedProps extends DrawRectProps {
    type classHandler <: TimerUnified

    override val `type`: ScOption[String] = "TimerUnified".opt
}
