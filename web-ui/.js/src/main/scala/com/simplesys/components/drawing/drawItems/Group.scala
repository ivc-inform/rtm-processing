package com.simplesys.components.drawing.drawItems

import com.simplesys.SmartClient.Drawing.drawItem.{AbstractDrawRectCompanion, DrawRect}

import scala.scalajs.js

object Group extends BaseCompanionDrawItem

@js.native
trait Group extends DrawRect {
}

@js.native
abstract trait AbstractGroupCompanion extends AbstractDrawRectCompanion {
}

