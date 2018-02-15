package com.simplesys.components.props

import com.simplesys.SmartClient.Forms.formsItems.DurationValue
import com.simplesys.SmartClient.System.props.ClassProps
import com.simplesys.components.TimerU
import com.simplesys.option.{ScNone, ScOption}

class TimerUProps extends ClassProps {
    override type classHandler <: TimerU

    var startedAt: ScOption[String] = ScNone
    var duration: ScOption[DurationValue] = ScNone
    var testDuration: ScOption[DurationValue] = ScNone
    var offset: ScOption[DurationValue] = ScNone
    var testOffset: ScOption[DurationValue] = ScNone
}
