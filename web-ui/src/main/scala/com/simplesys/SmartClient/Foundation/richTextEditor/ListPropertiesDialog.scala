package com.simplesys.SmartClient.Foundation.richTextEditor

import com.simplesys.SmartClient.Control.IButton
import com.simplesys.SmartClient.Layout.Window
import com.simplesys.System.Types.AutoChild

import scala.scalajs.js

@js.native
trait ListPropertiesDialog extends Window {
    val applyButton: IButton with AutoChild
    val applyButtonTitle: String
    var applyClick: js.Function1[ListProperties, _]
    val cancelButton: IButton with AutoChild
    val cancelButtonTitle: String
    var cancelClick: js.Function1[ListProperties, _]
    val listPropertiesPane: ListPropertiesPane with AutoChild
}



