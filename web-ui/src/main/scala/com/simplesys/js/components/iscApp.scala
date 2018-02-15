package com.simplesys.js.components

import com.simplesys.System.JSObject
import com.simplesys.js.components.formItems.AbstractFormItemListCompanion

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("isc")
object iscApp extends JSObject{
  val FormItemList: AbstractFormItemListCompanion = js.native
}
