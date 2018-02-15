package com.simplesys.SmartClient.Forms.formsItems

import com.simplesys.System.Types.ColorPickerMode.ColorPickerMode

import scala.scalajs.js

@js.native
trait ColorItem extends TextItem {
    var allowComplexMode: Boolean
    var defaultPickerMode: ColorPickerMode
    //var pickerColorSelected: js.ThisFunction1[String, Int, _]
    var supportsTransparency: Boolean
}



