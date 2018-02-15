package com.simplesys.SmartClient.Grids

import com.simplesys.SmartClient.DataBinding.Callbacks._
import com.simplesys.SmartClient.DataBinding._
import com.simplesys.SmartClient.DataBinding.fieldPicker.FieldPickerWindow
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.detailViewer.{DetailViewerField, DetailViewerRecord}
import com.simplesys.SmartClient.Grids.listGrid.{ListGridField, ListGridRecord}
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.Alignment.Alignment
import com.simplesys.System.Types.DateDisplayFormat.DateDisplayFormat
import com.simplesys.System.Types.TimeDisplayFormat.TimeDisplayFormat
import com.simplesys.System.Types.{void, _}
import com.simplesys.System.{JSAny, JSObject}

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait DetailViewer extends Canvas with DataBoundComponent {
    val blockSeparator: HTMLString
    var blockStyle: CSSStyleName
    val canPickFields: Boolean
    val cellStyle: CSSStyleName
    val configureFieldsText: String
    var data: IscArray[DetailViewerRecord]
    var dateFormatter: DateDisplayFormat
    var datetimeFormatter: DateDisplayFormat
    var emptyCellValue: HTMLString
    var emptyMessage: HTMLString
    def emptyMessageHTML(): String
    var emptyMessageStyle: CSSStyleName
    def exportClientData(requestProperties: DSRequest = js.native, callback: RPCCallback = js.native): void
    def fetchRelatedData(record: ListGridRecord, schema: Canvas | DataSource | String, callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    var fieldIdProperty: String
    def fieldIsVisible(field: String | DetailViewerField): Boolean
    val fieldPickerFieldProperties: IscArray[String]
    val fieldPickerWindow: FieldPickerWindow
    var fields: IscArray[DetailViewerField]
    var formatCellValue: js.Function3[String, DetailViewerRecord, DetailViewerField, void]
    var getCellCSSText: js.Function4[JSAny, DetailViewerField, DetailViewerRecord, DetailViewer, CSSText]
    var getCellStyle: js.Function4[JSAny, DetailViewerField, DetailViewerRecord, DetailViewer, CSSStyleName]
    def getRecordIndex(record: DetailViewerRecord): Int
    def getViewState(): DetailViewerViewState
    var headerStyle: CSSStyleName
    var labelPrefix: HTMLString
    var labelStyle: CSSStyleName
    var labelSuffix: HTMLString
    var linkTextProperty: String
    var loadingMessage: HTMLString
    var loadingMessageStyle: CSSStyleName
    var printCellStyle: CSSStyleName
    var printHeaderStyle: CSSStyleName
    var printLabelStyle: CSSStyleName
    var recordsPerBlock: Int
    var separatorStyle: SCClassName
    def setData(newData: IscArray[JSObject]): void
    def setViewState(viewState: DetailViewerViewState): void
    val showEmptyField: Boolean
    val showEmptyMessage: Boolean
    val timeFormatter: TimeDisplayFormat
    var valueAlign: Alignment
    def viewSelectedData(selectionComponent: Grid[ListGridField, ListGridRecord] | String): void
    var wrapLabel: Boolean
    var wrapValues: Boolean
}


