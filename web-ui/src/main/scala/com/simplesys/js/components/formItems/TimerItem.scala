package com.simplesys.js.components.formItems

import com.simplesys.SmartClient.Forms.formsItems.{AbstractFormItemWithButtonsCompanion, DurationValue, FormItemWithButtons}
import com.simplesys.System.JSUndefined

import scala.scalajs.js

@js.native
trait TimerItem extends FormItemWithButtons {
    var startedAt: JSUndefined[String]
    var duration: JSUndefined[DurationValue]
    var testDuration: JSUndefined[DurationValue]
    var offset: JSUndefined[DurationValue]
    var testOffset: JSUndefined[DurationValue]
}

@js.native
abstract class AbstractTimerItemCompanion extends AbstractFormItemWithButtonsCompanion

