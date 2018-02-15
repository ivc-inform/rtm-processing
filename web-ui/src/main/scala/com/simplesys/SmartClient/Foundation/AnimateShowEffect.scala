package com.simplesys.SmartClient.Foundation

import com.simplesys.System.JSObject

import scala.scalajs.js

@js.native
trait AnimateShowEffect extends JSObject {
    val effect: String
    val endsAt: String
    val startFrom: String
}
