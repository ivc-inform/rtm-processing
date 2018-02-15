package com.simplesys.SmartClient.System

import com.simplesys.System.JSObject

import scala.scalajs.js

@js.native
trait PrintProperties extends JSObject {
    val includeControls: IscArray[String]
    val omitControls: IscArray[String]
    val printForExport: Boolean
}
