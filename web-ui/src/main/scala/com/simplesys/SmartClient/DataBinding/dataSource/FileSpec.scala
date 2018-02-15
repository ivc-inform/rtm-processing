package com.simplesys.SmartClient.DataBinding.dataSource

import com.simplesys.System.JSObject

import scala.scalajs.js

@js.native
trait FileSpec extends JSObject {
    val fileFormat: String
    val fileName: String
    val fileType: String
}


