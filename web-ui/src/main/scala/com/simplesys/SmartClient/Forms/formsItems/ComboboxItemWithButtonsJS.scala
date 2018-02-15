package com.simplesys.SmartClient.Forms.formsItems

import com.simplesys.System.Types.FormItemComponentType._
import com.simplesys.System.Types._
import com.simplesys.System._

import scala.scalajs.js

@js.native
trait ComboboxItemWithButtonsJS extends FormItemWithButtons {
    var comboBoxItemName: String
    var specialValues: JSUndefined[ValueMap]
    var separateSpecialValues: JSUndefined[Boolean]
    var constructor: JSUndefined[FormItemComponentType]
    var comboBoxValue : JSUndefined[JSAny]
}

@js.native
abstract trait AbstractComboboxItemWithButtonsJSCompanion extends AbstractFormItemWithButtonsCompanion {
}

