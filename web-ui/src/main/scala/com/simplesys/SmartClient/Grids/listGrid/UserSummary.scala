package com.simplesys.SmartClient.Grids.listGrid

import com.simplesys.System.{JSObject, JSDictionaryAny}

import scala.scalajs.js

@js.native
trait UserSummary extends JSObject {
    val summaryVars: JSDictionaryAny
    var text: String
}

