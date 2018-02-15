package com.simplesys.SmartClient.Foundation.richTextEditor

import com.simplesys.System.JSObject
import com.simplesys.System.Types.SCImgURL

import scala.scalajs.js

@js.native
trait ListProperties extends JSObject {
    val image:SCImgURL
    val startNumber:Int
    val style : ListProperties
}


