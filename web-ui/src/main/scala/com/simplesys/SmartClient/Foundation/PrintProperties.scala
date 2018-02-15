package com.simplesys.SmartClient.Foundation

import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.JSObject

import scala.scalajs.js

@js.native
trait PrintProperties extends JSObject {
    val includeControls: IscArray[String]
    val omitControls: IscArray[String]
    val printForExport: Boolean
}
