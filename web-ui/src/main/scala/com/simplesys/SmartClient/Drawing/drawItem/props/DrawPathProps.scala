package com.simplesys.SmartClient.Drawing.drawItem.props

import com.simplesys.SmartClient.Drawing.DrawPathDefaults
import com.simplesys.SmartClient.Drawing.drawItem.DrawPath
import com.simplesys.SmartClient.Drawing.props.DrawItemProps
import com.simplesys.SmartClient.System.{IscArray, Point, isc}
import com.simplesys.System.JSAny
import com.simplesys.option.ScOption._
import com.simplesys.function._
import com.simplesys.option.{ScNone, ScOption}

class DrawPathProps extends DrawItemProps {
    type classHandler <: DrawPath

    var points: ScOption[Seq[Point]] = ScNone
    var defaults: ScOption[DrawPathDefaults] = ScNone
    override val `type`: ScOption[String]  = "DrawPath".opt
}
