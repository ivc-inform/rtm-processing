package com.simplesys.SmartClient.Foundation.props

import com.simplesys.SmartClient.Grids.listGrid.ListGridRecord
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.JSObject
import com.simplesys.System.Types.State.State
import com.simplesys.System.Types.{CSSStyleName, HTMLString}
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class GridRendererProps extends CanvasProps {
    var alternateColumnFrequency: ScOption[Int] = ScNone
    var alternateColumnStyles: ScOption[Boolean] = ScNone
    var alternateRowFrequency: ScOption[Int] = ScNone
    var alternateRowStyles: ScOption[Boolean] = ScNone
    var autoFit: ScOption[Boolean] = ScNone
    var baseStyle: ScOption[CSSStyleName] = ScNone
    var canSelectOnRightMouse: ScOption[Boolean] = ScNone
    var cellClick: ScOption[js.Function3[ListGridRecord, Int, Int, Boolean]] = ScNone
    var cellContextClick: ScOption[js.Function3[ListGridRecord, Int, Int, Boolean]] = ScNone
    var cellDoubleClick: ScOption[js.Function3[ListGridRecord, Int, Int, Boolean]] = ScNone
    var cellHeight: ScOption[Int] = ScNone
    var cellHover: ScOption[js.Function3[ListGridRecord, Int, Int, Boolean]] = ScNone
    var cellHoverHTML: ScOption[js.Function3[ListGridRecord, Int, Int, HTMLString]] = ScNone
    var cellIsEnabled: ScOption[js.Function2[Int, Int, Boolean]] = ScNone
    var cellMouseDown: ScOption[js.Function3[ListGridRecord, Int, Int, Boolean]] = ScNone
    var cellMouseUp: ScOption[js.Function3[ListGridRecord, Int, Int, Boolean]] = ScNone
    var cellOut: ScOption[js.Function3[ListGridRecord, Int, Int, Boolean]] = ScNone
    var cellOver: ScOption[js.Function3[ListGridRecord, Int, Int, Boolean]] = ScNone
    var cellPadding: ScOption[Int] = ScNone
    var cellSelectionChanged: ScOption[js.Function1[IscArray[JSObject], Boolean]] = ScNone
    var cellValueHover: ScOption[js.Function3[ListGridRecord, Int, Int, Boolean]] = ScNone
    var cellValueHoverHTML: ScOption[js.Function3[ListGridRecord, Int, Int, HTMLString]] = ScNone
    var cellValueIsClipped: ScOption[js.Function2[Int, Int, Boolean]] = ScNone
    var drawAheadRatio: ScOption[Double] = ScNone
    var drawAllMaxCells: ScOption[Int] = ScNone
    var emptyCellValue: ScOption[String] = ScNone
    var emptyMessage: ScOption[String] = ScNone
    var emptyMessageStyle: ScOption[CSSStyleName] = ScNone
    var emptyMessageTableStyle: ScOption[CSSStyleName] = ScNone
    var fastCellUpdates: ScOption[Boolean] = ScNone
    var findColNum: ScOption[js.Function1[ListGridRecord, Int]] = ScNone
    var findRowNum: ScOption[js.Function1[ListGridRecord, Int]] = ScNone
    var fixedColumnWidths: ScOption[Boolean] = ScNone
    var fixedRowHeights: ScOption[Boolean] = ScNone
    var getBaseStyle: ScOption[js.Function3[ListGridRecord, Int, Int, Boolean]] = ScNone
    var getCellCSSText: ScOption[js.Function3[ListGridRecord, Int, Int, Boolean]] = ScNone
    var getCellHoverComponent: ScOption[js.Function3[ListGridRecord, Int, Int, Boolean]] = ScNone
    var getCellRecord: ScOption[js.Function2[Int, Int, JSObject]] = ScNone
    var getCellStyle: ScOption[js.Function3[ListGridRecord, Int, Int, CSSStyleName]] = ScNone
    var getCellValue: ScOption[js.Function3[ListGridRecord, Int, Int, String]] = ScNone
    var getRowHeight: ScOption[js.Function2[ListGridRecord, Int, Int]] = ScNone
    var getRowSpan: ScOption[js.Function3[ListGridRecord, Int, Int, Int]] = ScNone
    var instantScrollTrackRedraw: ScOption[Boolean] = ScNone
    var offlineMessage: ScOption[String] = ScNone
    var offlineMessageStyle: ScOption[CSSStyleName] = ScNone
    var quickDrawAheadRatio: ScOption[Double] = ScNone
    var recordCustomStyleProperty: ScOption[String] = ScNone
    var rowClick: ScOption[js.Function3[ListGridRecord, Int, Int, Boolean]] = ScNone
    var rowContextClick: ScOption[js.Function3[ListGridRecord, Int, Int, Boolean]] = ScNone
    var rowDoubleClick: ScOption[js.Function3[ListGridRecord, Int, Int, Boolean]] = ScNone
    var rowHover: ScOption[js.Function3[ListGridRecord, Int, Int, Boolean]] = ScNone
    var rowMouseDown: ScOption[js.Function3[ListGridRecord, Int, Int, Boolean]] = ScNone
    var rowMouseUp: ScOption[js.Function3[ListGridRecord, Int, Int, Boolean]] = ScNone
    var rowOut: ScOption[js.Function3[ListGridRecord, Int, Int, Boolean]] = ScNone
    var rowOver: ScOption[js.Function3[ListGridRecord, Int, Int, Boolean]] = ScNone
    var scrollRedrawDelay: ScOption[Int] = ScNone
    var scrollWheelRedrawDelay: ScOption[Int] = ScNone
    var selectionChanged: ScOption[js.Function2[ListGridRecord, State, _]] = ScNone
    var showAllColumns: ScOption[Boolean] = ScNone
    var showAllRows: ScOption[Boolean] = ScNone
    var showClippedValuesOnHover: ScOption[Boolean] = ScNone
    var showEmptyMessage: ScOption[Boolean] = ScNone
    var showOfflineMessage: ScOption[Boolean] = ScNone
    var snapInsideBorder: ScOption[Boolean] = ScNone
    var totalRows: ScOption[Int] = ScNone
    var touchScrollRedrawDelay: ScOption[Int] = ScNone
    var virtualScrolling: ScOption[Boolean] = ScNone
    var wrapCells: ScOption[Boolean] = ScNone
}
