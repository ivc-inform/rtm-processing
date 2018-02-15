package com.simplesys.components.drawing.drawItems

import com.simplesys.SmartClient.Drawing.drawItem.{AbstractDrawOvalCompanion, DrawOval}

import scala.scalajs.js

object StartState extends BaseCompanionDrawItem

@js.native
trait StartState extends DrawOval {
}

@js.native
abstract trait AbstractStartStateCompanion extends AbstractDrawOvalCompanion {
}

