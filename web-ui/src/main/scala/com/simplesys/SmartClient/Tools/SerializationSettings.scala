package com.simplesys.SmartClient.Tools

import com.simplesys.System.JSObject

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait SerializationSettings extends JSObject {
    var indent: Boolean
    var outputComponentsIndividually: Boolean
    var serverless: Boolean
}


