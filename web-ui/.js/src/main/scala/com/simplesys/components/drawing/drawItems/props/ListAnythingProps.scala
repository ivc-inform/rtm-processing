package com.simplesys.components.drawing.drawItems.props

import com.simplesys.SmartClient.Drawing.drawItem.props.DrawRectProps
import com.simplesys.components.drawing.drawItems.ListAnything
import com.simplesys.option.ScOption
import com.simplesys.option.ScOption._

class ListAnythingProps extends DrawRectProps {
    type classHandler <: ListAnything

    override val `type`: ScOption[String] = ListAnything.typeName.opt
}
