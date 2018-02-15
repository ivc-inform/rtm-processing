package com.simplesys.components.drawing.drawItems

import com.simplesys.SmartClient.Drawing.drawItem.{AbstractDrawLinePathSSCompanion, DrawLinePathSS}

import scala.scalajs.js

object TimerFired extends BaseCompanionDrawItem

@js.native
trait TimerFired extends DrawLinePathSS {
}

@js.native
abstract trait AbstractTimerFiredCompanion extends AbstractDrawLinePathSSCompanion {
}

