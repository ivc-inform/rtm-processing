package com.simplesys.SmartClient.Grids.listGrid

import com.simplesys.System.{JSObject, JSDictionaryAny}

import scala.scalajs.js

@js.native
trait UserFormula extends JSObject {
    val formulaVars: JSDictionaryAny
    var text: String
}


