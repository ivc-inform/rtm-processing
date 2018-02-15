package com.simplesys.SmartClient.Drawing.drawItem.props

import com.simplesys.SmartClient.Drawing.props.DrawItemProps
import com.simplesys.option.ScOption
import com.simplesys.option.ScOption._

class DrawPolygonProps extends DrawItemProps {

    override val `type`: ScOption[String] = "DrawPolygon".opt
}
