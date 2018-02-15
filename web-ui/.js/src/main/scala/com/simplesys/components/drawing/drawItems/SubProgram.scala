package com.simplesys.components.drawing.drawItems

import com.simplesys.SmartClient.Drawing.drawItem.DrawRect
import com.simplesys.SmartClient.Forms.formsItems.AbstractFormItemWithButtonsCompanion
import com.simplesys.System._

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

object SubProgram extends BaseCompanionDrawItem

trait SubProgramValue extends JSObject {
    val returnType: JSUndefined[String]
    val optionType: JSUndefined[Boolean]
    val loggedVariable: JSUndefined[Boolean]
    val routineCode: JSUndefined[String]
    val transition2Data: JSUndefined[Boolean]
    val scopeVisivlity: JSUndefined[String]
}

@js.native
trait SubProgram extends DrawRect {

}

@js.native
abstract trait AbstractSubProgramCompanion extends AbstractFormItemWithButtonsCompanion {
}


