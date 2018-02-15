package com.simplesys.SmartClient.Forms.formsItems

import com.simplesys.SmartClient.Control.Button
import com.simplesys.SmartClient.Forms.DynamicFormSS
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.void
import com.simplesys.System._

import scala.scalajs.js

@js.native
trait FormItemWithButtons extends CanvasItem {
    var buttons: JSUndefined[IscArray[Button]]
    var testInnerForm: JSUndefined[DynamicFormSS]
    var buttonsVertical: Boolean
    var cancelButtonName: JSUndefined[String]
    var cancelButton: JSUndefined[JSArray[Button]]
    def showCancelButton(): void
    def hideCancelButton(): void
    def updateCancelButton(value: JSUndefined[JSAny]): void
}

@js.native
abstract class AbstractFormItemWithButtonsCompanion extends AbstractCanvasItemCompanion

