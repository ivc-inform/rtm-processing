package com.simplesys.SmartClient.System

import com.simplesys.System.JSObject
import com.simplesys.System.Types.void

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait Timer extends Class {
}

@js.native
abstract trait AbstractTimerCompanion extends AbstractClassCompanion {
    def clear(timerEvent: JSObject): void = js.native
    def setTimeout(action: js.Function, delay: Int = js.native): JSObject = js.native
}

@js.native
object Timer extends AbstractTimerCompanion


