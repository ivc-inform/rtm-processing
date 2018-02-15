package com.simplesys.SmartClient.Layout

import com.simplesys.SmartClient.Foundation.{Canvas, Label}
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.Alignment.Alignment
import com.simplesys.System.Types.VerticalAlignment.VerticalAlignment
import com.simplesys.System.Types.{AutoChild, SCClassName, void}

import scala.scalajs.js

@js.native
trait ToolStripGroup extends VLayout {
    def addControl(control: Canvas, index: Int = js.native): void
    def addControls(controls: IscArray[Canvas], index: Int = js.native): void
    val autoSizeToTitle: Boolean
    val body: HLayout with AutoChild
    val bodyConstructor: String
    val controls: IscArray[Canvas]
    val label: HLayout with AutoChild
    val labelConstructor: String
    var numRows: Int
    def removeControl(control: Canvas): void
    var rowHeight: Int
    def setControlColumn(control: Canvas): void
    def setControls(controls: IscArray[Canvas]): void
    def setShowTitle(showTitle: Boolean): void
    def setTitle(title: String): void
    def setTitleAlign(align: Alignment): void
    def setTitleOrientation(orientation: VerticalAlignment): void
    val titleAlign: Alignment
    val titleHeight: Int
    val titleOrientation: VerticalAlignment
    val titleProperties: Label with AutoChild
    var titleStyle: SCClassName
}


