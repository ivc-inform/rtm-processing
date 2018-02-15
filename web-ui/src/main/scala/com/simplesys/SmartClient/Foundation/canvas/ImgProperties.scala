package com.simplesys.SmartClient.Foundation.canvas

import com.simplesys.System.JSObject
import com.simplesys.System.Types.URL

import scala.scalajs.js

@js.native
trait ImgProperties extends JSObject {
    var extraStuff: String
    var height: Int
    var imgDir: URL
    var name: String
    var src: URL
    var width: Int
}

