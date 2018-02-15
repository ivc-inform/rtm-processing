package com.simplesys.SmartClient.Grids.listGrid

import com.simplesys.System.JSObject

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
trait MasterDetailMapping extends JSObject {
    val masterGridField: String
    val detailGridField: String
}


