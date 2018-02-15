package com.simplesys.components.drawing.drawItems

import com.simplesys.SmartClient.Drawing.drawItem.{AbstractDrawLinePathSSCompanion, DrawLinePathSS}

import scala.scalajs.js

object Transition extends BaseCompanionDrawItem

@js.native
trait Transition extends DrawLinePathSS {
}

@js.native
abstract trait AbstractTransitionCompanion extends AbstractDrawLinePathSSCompanion {
}

