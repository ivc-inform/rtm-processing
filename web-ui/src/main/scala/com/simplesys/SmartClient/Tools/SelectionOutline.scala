package com.simplesys.SmartClient.Tools

import com.simplesys.System.JSObject
import com.simplesys.System.Types.{CSSClassName, CSSColor, void}

import scala.scalajs.js

@js.native
trait SelectionOutline extends JSObject {
    var border: String
    def getBorder(): CSSClassName
    def getLabelBackgroundColor(): CSSColor
    var labelBackgroundColor: CSSColor
    def setBorder(border: CSSClassName): void
    def setLabelBackgroundColor(color: CSSColor): void
}


