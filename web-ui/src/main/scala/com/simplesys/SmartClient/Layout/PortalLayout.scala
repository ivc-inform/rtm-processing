package com.simplesys.SmartClient.Layout

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Layout.portalLayout.{PortalPosition, Portlet}
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.Overflow.Overflow
import com.simplesys.System.Types.{MultiAutoChild, void}

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait PortalLayout extends Layout {
    def addColumn(index: Int): void
    def addPortlet(portlet: Portlet, colNum: Int = js.native, rowWithinCol: Int = js.native, positionInExistingRow: Int = js.native): void
    var canResizeColumns: Boolean
    var canResizePortlets: Boolean
    var canShrinkColumnWidths: Boolean
    var canStretchColumnWidths: Boolean
    var column: Layout with MultiAutoChild
    var columnBorder: String
    var columnOverflow: Overflow
    def getColumnWidth(colNumber: Int): Int
    def getDropPortlet(dragTarget: Canvas, colNum: Int, rowNum: Int, dropPosition: Int = js.native): Canvas
    def getNumColumns(): Int
    def getPortalPosition(portlet: Portlet): PortalPosition
    def getPortletArray(): IscArray[IscArray[IscArray[Portlet]]]
    def getPortlets(): IscArray[Portlet]
    val numColumns: Int
    var portletDropTypes: IscArray[String]
    def portletMaximized(portlet: Portlet): void
    def portletMinimized(portlet: Portlet): void
    def portletRestored(portlet: Portlet): void
    val portlets: IscArray[Portlet] | Portlet
    var portletsChanged: js.ThisFunction0[classHandler, void]
    var portletsResized: js.ThisFunction0[classHandler, void]
    var preventColumnUnderflow: Boolean
    var preventRowUnderflow: Boolean
    var preventUnderflow: Boolean
    def removeColumn(index: Int): void
    def removePortlet(portlet: Portlet): void
    val row: Layout with MultiAutoChild
    val rowLayout: Layout with MultiAutoChild
    def setCanResizeColumns(canResize: Boolean): void
    def setCanResizePortlets(canResize: Boolean): void
    def setCanShrinkColumnWidths(canShrink: Boolean): void
    def setCanStretchColumnWidths(canStretch: Boolean): void
    def setColumnBorder(columnBorder: String): void
    def setColumnOverflow(overflow: Overflow): void
    def setColumnWidth(colNumber: Int, width: String | Int): void
    def setPortletDropTypes(portletDropTypes: IscArray[String]): void
    def setPreventRowUnderflow(preventRowUnderflow: Boolean): void
    def setPreventUnderflow(preventUnderflow: Boolean): void
    def setShowColumnMenus(showMenus: Boolean): void
    def setStretchColumnWidthsProportionally(stretchProportionally: Boolean): void
    var showColumnMenus: Boolean
    var stretchColumnWidthsProportionally: Boolean
    def willAcceptPortletDrop(dragTarget: Int, colNum: Int, rowNum: Int, dropPosition: Int = js.native): Boolean
    var willMaximizePortlet: js.ThisFunction1[classHandler, Portlet, Boolean]
    var willMinimizePortlet: js.ThisFunction1[classHandler, Portlet, Boolean]
    var willRestorePortlet: js.ThisFunction1[classHandler, Portlet, Boolean]
}


