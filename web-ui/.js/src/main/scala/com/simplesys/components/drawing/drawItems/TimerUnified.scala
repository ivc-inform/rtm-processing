package com.simplesys.components.drawing.drawItems

import com.simplesys.SmartClient.Drawing.drawItem.{AbstractDrawRectCompanion, DrawRect}

import scala.scalajs.js

object TimerUnified extends BaseCompanionDrawItem

@js.native
trait TimerUnified extends DrawRect {
}

@js.native
abstract trait AbstractTimerUnifiedCompanion extends AbstractDrawRectCompanion {
}

