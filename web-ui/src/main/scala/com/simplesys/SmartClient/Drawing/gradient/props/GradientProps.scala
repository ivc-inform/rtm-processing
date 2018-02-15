package com.simplesys.SmartClient.Drawing.gradient.props

import com.simplesys.props.AbstractClassProps

class GradientProps extends AbstractClassProps {

    import com.simplesys.System.Types.CSSColor
    import com.simplesys.option.{ScNone, ScOption}

    var colorStops: ScOption[Seq[ColorStopProps]] = ScNone
    var endColor: ScOption[CSSColor] = ScNone
    var startColor: ScOption[CSSColor] = ScNone
}
