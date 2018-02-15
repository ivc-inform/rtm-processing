package com.simplesys.SmartClient.Drawing.props

import com.simplesys.SmartClient.System.Point
import com.simplesys.System.JSObject
import com.simplesys.System.Types._
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

class ShadowProps extends AbstractClassProps {
    var blur: ScOption[Int] = ScNone
    var color: ScOption[CSSColor] = ScNone
    var offset: ScOption[Point] = ScNone
}
