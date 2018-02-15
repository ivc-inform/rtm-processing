package com.simplesys.js.components.drawing.drawItems

import com.simplesys.SmartClient.Drawing.drawItem.DrawRect
import com.simplesys.SmartClient.Forms.formsItems.AbstractFormItemWithButtonsCompanion
import com.simplesys.System._

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
trait VariableValue extends JSObject {
    val returnType: JSUndefined[String]
    val routineCode: JSUndefined[String]
    val transition2Data: JSUndefined[Boolean]
    val scopeVisivlity: JSUndefined[String]
}

object Variable extends BaseCompanionDrawItem

@js.native
trait Variable extends DrawRect {
}

@js.native
abstract trait AbstractVariableItemCompanion extends AbstractFormItemWithButtonsCompanion {
}

