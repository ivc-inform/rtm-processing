package com.simplesys.SmartClient.Forms

import com.simplesys.SmartClient.Control.IButton
import com.simplesys.SmartClient.Forms.formsItems.DateRangeItem
import com.simplesys.SmartClient.Layout.Window
import com.simplesys.System.Types.AutoChild

import scala.scalajs.js

@js.native
trait DateRangeDialog extends Window {
    val cancelButton: IButton with AutoChild
    val cancelButtonTitle: String
    val clearButton: IButton with AutoChild
    val clearButtonTitle: String
    var headerTitle: String
    val okButton: IButton with AutoChild
    val okButtonTitle: String
    val rangeItem: DateRangeItem with AutoChild
}


