package com.simplesys.SmartClient.Drawing.drawItem.props.drawShape

import com.simplesys.System.Types.DrawShapeCommandType.DrawShapeCommandType
import com.simplesys.System._
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

class DrawShapeCommandProps extends AbstractClassProps {
    var args: ScOption[Seq[JSAny]] = ScNone
    var `type`: ScOption[DrawShapeCommandType] = ScNone
}
