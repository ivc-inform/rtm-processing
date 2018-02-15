package com.simplesys.SmartClient.Foundation

import com.simplesys.SmartClient.Grids.listGrid.ListGridRecord
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.JSObject
import com.simplesys.System.Types.State.State
import com.simplesys.System.Types.{CSSStyleName, HTMLString, void}
import org.scalajs.dom

import scala.scalajs.js

@js.native
trait GridRenderer extends Canvas {
    var alternateColumnFrequency: Int
    var alternateColumnStyles: Boolean
    var alternateRowFrequency: Int
    val alternateRowStyles: Boolean
    var autoFit: Boolean
    val baseStyle: CSSStyleName
    var canSelectOnRightMouse: Boolean
    var cellClick: js.Function3[ListGridRecord, Int, Int, Boolean]
    var cellContextClick: js.Function3[ListGridRecord, Int, Int, Boolean]
    var cellDoubleClick: js.Function3[ListGridRecord, Int, Int, Boolean]
    var cellHeight: Int
    var cellHover: js.Function3[ListGridRecord, Int, Int, Boolean]
    var cellHoverHTML: js.Function3[ListGridRecord, Int, Int, HTMLString]
    var cellIsEnabled: js.Function2[Int, Int, Boolean]
    var cellMouseDown: js.Function3[ListGridRecord, Int, Int, Boolean]
    var cellMouseUp: js.Function3[ListGridRecord, Int, Int, Boolean]
    var cellOut: js.Function3[ListGridRecord, Int, Int, Boolean]
    var cellOver: js.Function3[ListGridRecord, Int, Int, Boolean]
    var cellPadding: Int
    var cellSelectionChanged: js.Function1[IscArray[JSObject], Boolean]
    var cellValueHover: js.Function3[ListGridRecord, Int, Int, Boolean]
    var cellValueHoverHTML: js.Function3[ListGridRecord, Int, Int, HTMLString]
    var cellValueIsClipped: js.Function2[Int, Int, Boolean]
    var drawAheadRatio: Double
    var drawAllMaxCells: Int
    var emptyCellValue: String
    var emptyMessage: String
    var emptyMessageStyle: CSSStyleName
    var emptyMessageTableStyle: CSSStyleName
    var fastCellUpdates: Boolean
    var findColNum: js.Function1[ListGridRecord, Int]
    var findRowNum: js.Function1[ListGridRecord, Int]
    var fixedColumnWidths: Boolean
    var fixedRowHeights: Boolean
    var getBaseStyle: js.Function3[ListGridRecord, Int, Int, Boolean]
    var getCellCSSText: js.Function3[ListGridRecord, Int, Int, Boolean]
    def getCellFromDomElement(element: dom.Element): IscArray[Int]
    var getCellHoverComponent: js.Function3[ListGridRecord, Int, Int, Boolean]
    def getCellPageRect(rowNum: Int, colNum: Int): IscArray[Int]
    var getCellRecord: js.Function2[Int, Int, JSObject]
    def getCellRowSpan(rowNum: Int, colNum: Int): Int
    def getCellStartRow(rowNum: Int, colNum: Int): Int
    var getCellStyle: js.Function3[ListGridRecord, Int, Int, CSSStyleName]
    var getCellValue: js.Function3[ListGridRecord, Int, Int, String]
    def getColumnLeft(colNum: Int): Int
    def getColumnPageLeft(colNum: Int): Int
    def getColumnWidth(colNum: Int): Int
    def getDrawnRows(): IscArray[Int]
    def getEventColumn(x: Int = js.native): Int
    def getEventRow(y: Int = js.native): Int
    def getNearestColToEvent(): Int
    def getNearestRowToEvent(): Int
    var getRowHeight: js.Function2[ListGridRecord, Int, Int]
    def getRowPageTop(rowNum: Int): Int
    var getRowSpan: js.Function3[ListGridRecord, Int, Int, Int]
    def getRowTop(rowNum: Int): Int
    def getTotalRows(): Int
    def getVisibleRows(): Int
    var instantScrollTrackRedraw: Boolean
    var offlineMessage: String
    var offlineMessageStyle: CSSStyleName
    var quickDrawAheadRatio: Double
    var recordCustomStyleProperty: String
    def refreshCell(rowNum: Int, colNum: Int): void
    def refreshCellStyle(rowNum: Int, colNum: Int): void
    def refreshRow(rowNum: Int): void
    var rowClick: js.Function3[ListGridRecord, Int, Int, Boolean]
    var rowContextClick: js.Function3[ListGridRecord, Int, Int, Boolean]
    var rowDoubleClick: js.Function3[ListGridRecord, Int, Int, Boolean]
    var rowHover: js.Function3[ListGridRecord, Int, Int, Boolean]
    var rowMouseDown: js.Function3[ListGridRecord, Int, Int, Boolean]
    var rowMouseUp: js.Function3[ListGridRecord, Int, Int, Boolean]
    var rowOut: js.Function3[ListGridRecord, Int, Int, Boolean]
    var rowOver: js.Function3[ListGridRecord, Int, Int, Boolean]
    var scrollRedrawDelay: Int
    var scrollWheelRedrawDelay: Int
    var selectionChanged: js.Function2[ListGridRecord, State, _]
    def setColumnWidth(colNum: Int, newWidth: Int): void
    def setColumnWidths(newWidths: IscArray[Int]): void
    def setFastCellUpdates(fastCellUpdates: Boolean): void
    val showAllColumns: Boolean
    val showAllRows: Boolean
    val showClippedValuesOnHover: Boolean
    val showEmptyMessage: Boolean
    val showOfflineMessage: Boolean
    val snapInsideBorder: Boolean
    var totalRows: Int
    var touchScrollRedrawDelay: Int
    val virtualScrolling: Boolean
    val wrapCells: Boolean
}


