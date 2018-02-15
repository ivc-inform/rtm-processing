package com.simplesys.js.components.formItems

import com.simplesys.SmartClient.Forms.formsItems.{AbstractFormItemWithButtonsCompanion, FormItemWithButtons}
import com.simplesys.System._

import scala.scalajs.js

@js.native
trait VariableItem extends FormItemWithButtons {
    var returnType: JSUndefined[String]
    var routineCode: JSUndefined[String]
    var transition2Data: JSUndefined[Boolean]
    var scopeVisivlity: JSUndefined[String]
}

@js.native
abstract trait AbstractVarriableCompanion extends AbstractFormItemWithButtonsCompanion {
}

