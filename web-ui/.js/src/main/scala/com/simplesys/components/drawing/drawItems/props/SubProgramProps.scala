package com.simplesys.components.drawing.drawItems.props

import com.simplesys.SmartClient.Drawing.drawItem.props.DrawRectProps
import com.simplesys.components.drawing.drawItems.SubProgram
import com.simplesys.option.ScOption
import com.simplesys.option.ScOption._

class SubProgramProps extends DrawRectProps {
    type classHandler <: SubProgram

    override val `type`: ScOption[String] = "SubProgram".opt
}
