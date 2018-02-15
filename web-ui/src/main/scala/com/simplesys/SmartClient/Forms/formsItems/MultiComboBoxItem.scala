package com.simplesys.SmartClient.Forms.formsItems

import com.simplesys.SmartClient.Control.IButton
import com.simplesys.SmartClient.Forms.DynamicForm
import com.simplesys.SmartClient.Layout.Layout
import com.simplesys.System.Types.MultiComboBoxLayoutStyle.MultiComboBoxLayoutStyle
import com.simplesys.System.Types.{void, _}

import scala.scalajs.js

@js.native
trait MultiComboBoxItem extends CanvasItem {
    var addUnknownValues: Boolean
    var alwaysExitOnTab: Boolean
    var autoFetchData: Boolean
    var autoFitButtons: Boolean
    var button: IButton with MultiAutoChild
    var comboBox: ComboBoxItem with AutoChild
    var comboBoxWidth: Int
    var comboForm: DynamicForm with AutoChild
    val deselectedButtonStyle: CSSStyleName
    var layoutStyle: MultiComboBoxLayoutStyle
    val pendingButtonStyle: CSSStyleName
    def setAutoFitButtons(autoFitButtons: Boolean): void
    def setLayoutStyle(layoutStyle: MultiComboBoxLayoutStyle): void
    val useInsertionOrder: Boolean
    val valueLayout: Layout with MultiAutoChild
}


