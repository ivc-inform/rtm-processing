package com.simplesys.SmartClient.Layout

import com.simplesys.SmartClient.Forms.DynamicForm
import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.System.JSObject
import com.simplesys.System.Types.Alignment.Alignment
import com.simplesys.System.Types.VerticalAlignment.VerticalAlignment

import scala.scalajs.js

@js.native
trait ToolStrip extends Layout {
    def addFormItem(formItem: FormItem, formProperties: DynamicForm = js.native, position: Int = js.native): DynamicForm
    val formWrapper: DynamicForm
    val formWrapperConstructor: String
    val formWrapperDefaults: JSObject
    val formWrapperProperties: JSObject
    val groupTitleAlign: Alignment
    val groupTitleOrientation: VerticalAlignment
    val separatorClass: String
    val separatorSize: Int
    val showGroupTitle: Boolean
    val verticalStyleName: String
}




