package com.simplesys.SmartClient.System

import com.simplesys.System.JSObject
import com.simplesys.System.Types.KeyName

import scala.scalajs.js

@js.native
trait KeyIdentifier extends JSObject {
    val altKey: Boolean
    val ctrlKey: Boolean
    val keyName: KeyName
    val shiftKey: Boolean
}
