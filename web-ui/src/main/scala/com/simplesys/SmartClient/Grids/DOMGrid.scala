package com.simplesys.SmartClient.Grids

import com.simplesys.System.Types.void
import org.scalajs.dom.html._

import scala.scalajs.js

@js.native
trait DOMGrid extends TreeGrid {
    var rootElement: Element
    def setRootElement(rootElement: Element): void
}



