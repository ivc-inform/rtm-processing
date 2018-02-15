package com.simplesys.SmartClient.Grids.tileGrid

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.System.JSObject

import scala.scalajs.js

@js.native
trait TileRecord extends JSObject {
    var tileConstructor: String
    var tileProperties: Canvas
}


