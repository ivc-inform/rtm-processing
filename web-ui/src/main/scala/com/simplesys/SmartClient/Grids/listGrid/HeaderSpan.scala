package com.simplesys.SmartClient.Grids.listGrid

import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.JSObject
import com.simplesys.System.Types.Alignment.Alignment
import com.simplesys.System.Types.VerticalAlignment.VerticalAlignment
import com.simplesys.System.Types._

import scala.scalajs.js

@js.native
trait HeaderSpan extends JSObject {
    val align: Alignment
    val fields: IscArray[String]
    var headerBaseStyle: SCClassName
    val headerTitle: String
    var headerTitleStyle: SCClassName
    val height: Int
    val name: String
    val spans: IscArray[HeaderSpan]
    val title: String
    val valign: VerticalAlignment
}
