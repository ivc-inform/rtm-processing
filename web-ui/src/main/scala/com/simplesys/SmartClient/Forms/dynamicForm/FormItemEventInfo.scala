package com.simplesys.SmartClient.Forms.dynamicForm

import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.System.JSObject

import scala.scalajs.js

@js.native
trait FormItemEventInfo extends JSObject {
    val icon: String
    val item: FormItem
    val overItem: Boolean
    val overTitle: Boolean
}


