package com.simplesys.SmartClient.Drawing.drawItem.props

import com.simplesys.SmartClient.Drawing.drawItem.drawShape.DrawShapeCommand
import com.simplesys.SmartClient.Drawing.props.DrawItemProps
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.option.ScOption._

class DrawShapeProps extends DrawItemProps {
    var commands: ScOption[Seq[DrawShapeCommand]] = ScNone
    override val `type`: ScOption[String]  = "DrawShape".opt
}
