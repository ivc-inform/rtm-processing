package com.simplesys.SmartClient.Forms.formsItems

import com.simplesys.SmartClient.Control.IButtonSS
import com.simplesys.SmartClient.System.IscArray

import scala.scalajs.js

@js.native
trait ComboboxItemWithButtons extends CanvasItem {
    val buttonsProperties:IscArray[IButtonSS]
}

@js.native
abstract class AbstractComboboxItemWithButtonsCompanion extends AbstractCanvasItemCompanion

