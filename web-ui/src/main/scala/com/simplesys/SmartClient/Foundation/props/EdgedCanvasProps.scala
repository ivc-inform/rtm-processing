package com.simplesys.SmartClient.Foundation.props

import com.simplesys.System.Types.{CSSColor, CSSStyleName}
import com.simplesys.option.{ScNone, ScOption}

class EdgedCanvasProps extends CanvasProps {
    var addEdgeStyleSuffix: ScOption[Boolean] = ScNone
    var centerBackgroundColor: ScOption[CSSColor] = ScNone
    var edgeBottom: ScOption[Int] = ScNone
    var edgeColor: ScOption[CSSColor] = ScNone
    var edgeLeft: ScOption[Int] = ScNone
    var edgeOffsetBottom: ScOption[Int] = ScNone
    var edgeOffsetLeft: ScOption[Int] = ScNone
    var edgeOffsetRight: ScOption[Int] = ScNone
    var edgeOffsetTop: ScOption[Int] = ScNone
    var edgeRight: ScOption[Int] = ScNone
    var edgeStyleName: ScOption[CSSStyleName] = ScNone
    var edgeTop: ScOption[Int] = ScNone
    var showCenter: ScOption[Int] = ScNone
}
