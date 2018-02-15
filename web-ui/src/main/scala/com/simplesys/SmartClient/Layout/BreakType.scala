package com.simplesys.SmartClient.Layout

import scala.language.implicitConversions

object BreakType extends Enumeration {
    type BreakType = Value
    val notBreak, withSaved, withOutSaved = Value
}
