package com.simplesys.SmartClient.System

import com.simplesys.System.Types.EscapingMode.EscapingMode

import scala.scalajs.js

@js.native
trait TextSettings extends Class {
    var escapingMode: EscapingMode
    var fieldList: IscArray[String]
    var fieldSeparator: String
    var lineSeparator: String
}
