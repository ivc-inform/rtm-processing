package com.simplesys.SmartClient.Drawing.drawItem.props

import com.simplesys.SmartClient.Drawing.DrawLineCommons
import com.simplesys.SmartClient.Drawing.drawItem.DrawLine
import com.simplesys.SmartClient.Drawing.props.DrawItemProps
import com.simplesys.SmartClient.System.Point
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

class DrawLineProps extends DrawItemProps with DrawLineCommons{

    import com.simplesys.SmartClient.Drawing.DrawLineDefaults

    type classHandler <: DrawLine

    var endLeft: ScOption[Int] = ScNone
    var endPoint: ScOption[Point] = ScNone
    var endTop: ScOption[Int] = ScNone
    var startLeft: ScOption[Int] = ScNone
    var startPoint: ScOption[Point] = ScNone
    var startTop: ScOption[Int] = ScNone
    var defaults: ScOption[DrawLineDefaults] = ScNone

    override val `type`: ScOption[String] = "DrawLine".opt
}
