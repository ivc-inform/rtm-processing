package com.simplesys.js.components.drawing.drawItems.props

import com.simplesys.SmartClient.Drawing.drawItem.props.DrawRectProps
import com.simplesys.js.components.drawing.drawItems.Group
import com.simplesys.option.ScOption
import com.simplesys.option.ScOption._

class GroupProps extends DrawRectProps {
    type classHandler <: Group

    override val `type`: ScOption[String] = Group.typeName.opt
}
