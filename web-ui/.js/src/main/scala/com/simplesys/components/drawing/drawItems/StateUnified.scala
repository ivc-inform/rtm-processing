package com.simplesys.components.drawing.drawItems

import com.simplesys.SmartClient.Drawing.drawItem.{AbstractDrawOvalCompanion, DrawOval}

import scala.scalajs.js

object StateUnified extends BaseCompanionDrawItem

@js.native
trait StateUnified extends DrawOval {
}

@js.native
abstract trait AbstractStateUnifiedCompanion extends AbstractDrawOvalCompanion {
}

