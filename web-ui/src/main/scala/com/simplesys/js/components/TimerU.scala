package com.simplesys.js.components

import com.simplesys.SmartClient.Forms.formsItems.DurationValue
import com.simplesys.SmartClient.System.AbstractClassCompanion
import com.simplesys.System._

import scala.scalajs.js

@js.native
trait TimerU extends com.simplesys.SmartClient.System.Class {
    var startedAt: JSUndefined[String]
    var duration: JSUndefined[DurationValue]
    var testDuration: JSUndefined[DurationValue]
    var offset: JSUndefined[DurationValue]
    var testOffset: JSUndefined[DurationValue]
}

@js.native
abstract trait AbstractTimerCompanion extends AbstractClassCompanion {
}

