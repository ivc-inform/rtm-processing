package com.simplesys.SmartClient.System.date.props

import com.simplesys.SmartClient.System.date.Date
import com.simplesys.SmartClient.System.isc
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.ScOption
import com.simplesys.option.ScOption._
import com.simplesys.props.AbstractClassProps

import scala.scalajs.js.ThisFunction1

class DateProps extends AbstractClassProps {
    type classHandler <: Date

    var toEuropeanDatetime: ScOption[ThisFunction1[classHandler, JSUndefined[Boolean], String]] = {
        (thiz: Date, useCustomTimezone: JSUndefined[Boolean]) =>
            s"${thiz.toEuropeanShortDate()} ${isc.Time.format(thiz, "toPadded24HourTime", true, useCustomTimezone.getOrElse(false))}"
    }.toThisFunc.opt

}
