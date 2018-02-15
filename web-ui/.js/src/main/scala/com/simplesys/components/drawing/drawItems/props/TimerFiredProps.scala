package com.simplesys.components.drawing.drawItems.props

import com.simplesys.SmartClient.Drawing.drawItem.props.DrawLinePathSSProps
import com.simplesys.components.drawing.drawItems.TimerFired
import com.simplesys.option.ScOption
import com.simplesys.option.ScOption._

class TimerFiredProps extends DrawLinePathSSProps {
    type classHandler <: TimerFired

    override val `type`: ScOption[String] = "TimerFired".opt
}
