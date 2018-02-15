package com.simplesys.SmartClient.DataBinding.dataSource

import com.simplesys.System.JSObject

import scala.scalajs.js

@js.native
trait DSRequestModifier extends JSObject {
    val end: String
    val fieldName: String
    val operator: String
    val start: String
    val value: String
}


