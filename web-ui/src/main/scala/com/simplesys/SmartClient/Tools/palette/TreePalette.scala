package com.simplesys.SmartClient.Tools.palette

import com.simplesys.SmartClient.Grids.TreeGrid
import com.simplesys.SmartClient.System.Class
import com.simplesys.SmartClient.Tools.Palette
import com.simplesys.System.JSObject

import scala.scalajs.js

@js.native
trait TreePalette extends TreeGrid with Palette {
    val componentDefaults: JSObject
}


