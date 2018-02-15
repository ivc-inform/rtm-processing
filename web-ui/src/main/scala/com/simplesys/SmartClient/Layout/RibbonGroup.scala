package com.simplesys.SmartClient.Layout

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.System.Class
import com.simplesys.System.Types.{AutoChild, void}

import scala.scalajs.js

@js.native
trait RibbonGroup extends ToolStripGroup {
    def createControl(properties: Canvas, position: Int = js.native): void
    val newControlConstructor: Class
    val newControlDefaults: IconButton with AutoChild
}



