package com.simplesys.SmartClient.Foundation.props

import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

class AnimateShowEffectProps extends AbstractClassProps {
    var effect: ScOption[String] = ScNone
    var endsAt: ScOption[String] = ScNone
    var startFrom: ScOption[String] = ScNone
}
