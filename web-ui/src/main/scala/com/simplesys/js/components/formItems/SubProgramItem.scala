package com.simplesys.js.components.formItems

import com.simplesys.SmartClient.Forms.formsItems.{AbstractFormItemWithButtonsCompanion, FormItemWithButtons}
import com.simplesys.System.JSUndefined
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

@js.native
trait SubProgramItem extends FormItemWithButtons {
    var returnType: JSUndefined[String]
    var optionType: JSUndefined[Boolean]
    var loggedVariable: JSUndefined[Boolean]
    var routineCode: JSUndefined[String]
    var transition2Data: JSUndefined[Boolean]
    var scopeVisivlity: JSUndefined[String]
}

@js.native
abstract trait AbstractSubProgramItemCompanion extends AbstractFormItemWithButtonsCompanion {
}

