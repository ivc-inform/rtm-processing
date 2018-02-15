package com.simplesys.SmartClient.DataBinding

import com.simplesys.SmartClient.DataBinding.Callbacks.DSCallback
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.listGrid.{Hilite, HiliteEditor, ListGridField}
import com.simplesys.SmartClient.Layout.Window
import com.simplesys.SmartClient.System.{AbstractClassCompanion, Class, IscArray}
import com.simplesys.System.{JSAny, JSObject, JSUndefined}
import com.simplesys.System.Types.Alignment.Alignment
import com.simplesys.System.Types.DragDataAction.DragDataAction
import com.simplesys.System.Types.FetchMode.FetchMode
import com.simplesys.System.Types.FieldNamingStrategy.FieldNamingStrategy
import com.simplesys.System.Types.HiliteIconPosition.HiliteIconPosition
import com.simplesys.System.Types.RecategorizeMode.RecategorizeMode
import com.simplesys.System.Types.TextMatchStyle.TextMatchStyle
import com.simplesys.System.Types._

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait DataBoundComponent extends Class {
    var addDropValues: Boolean
    def addFormulaField(): void
    var addFormulaFieldText: String
    var addOperation: String
    def addSummaryField(): void
    var addSummaryFieldText: String
    def anySelected(): Boolean
    def askForSort(): void
    val autoFetchData: Boolean
    var badFormulaResultValue: String
    var canAddFormulaFields: Boolean
    var canAddSummaryFields: Boolean
    val canEditFieldAttribute: String
    var canEditHilites: Boolean
    var dataArity: String
    var dataFetchMode: JSUndefined[FetchMode]
    val dataField: String
    var dataPageSize: Int
    var dataSource: JSUndefined[DataSource]
    var deepCloneOnEdit: Boolean
    var descriptionField: String
    def deselectAllRecords(): void
    def deselectRange(startRow: Int, endRow: Int): void
    def deselectRecord(record: Record | Int): void
    def deselectRecords(records: IscArray[Record]): void
    def disableHilite(hiliteID: String): void
    def disableHiliting(): void
    def dragComplete(): void
    var dragDataAction: DragDataAction
    var dragRecategorize: RecategorizeMode
    var dragTrackerStyle: CSSStyleName
    def dropComplete(transferredRecords: IscArray[Record]): void
    var dropValues: JSObject
    val duplicateDragMessage: String
    def editFields(): void
    def editFormulaField(field: Field): void
    var editFormulaFieldText: String
    def editHilites(): void
    var editHilitesDialogTitle: String
    var editHilitesText: String
    def editSummaryField(field: Field): void
    var editSummaryFieldText: String
    var emptyExportMessage: String
    def enableHilite(hiliteID: String, enable: Boolean = js.native): void
    def enableHiliting(enable: Boolean = js.native): void
    var exportAll: Boolean
    def exportData(requestProperties: DSRequest = js.native, callback: DSCallback = js.native)
    var exportFields: IscArray[String]
    var exportIncludeSummaries: Boolean
    var fetchOperation: String
    val fieldEditorWindow: Window
    var fieldEditorWindowTitle: HTMLString
    var fieldNamingStrategy: FieldNamingStrategy
    def find(advancedCriteria: AdvancedCriteria): JSObject
    def findAll(advancedCriteria: AdvancedCriteria): IscArray[JSObject]
    def findByKey(keyValue: JSAny): Record
    def findIndex(advancedCriteria: AdvancedCriteria): Int
    def findNextIndex(startIndex: Int, advancedCriteria: AdvancedCriteria, endIndex: Int = js.native): Int
    def getDataPathField(dataPath: DataPath): Field
    def getDragData(source: DataBoundComponent): IscArray[Record]
    def getDropValues(record: Record, sourceDS: DataSource, targetRecord: Record, index: Int, sourceWidget: Canvas): JSObject
    def getField(fieldID: String | Int): Field
    def getFieldAlignments(): IscArray[Alignment]
    def getFieldNum(fieldID: String | Int): Int
    def getFormulaFieldValue(field: Field, record: Record): Double | String
    def getHilites(): IscArray[Hilite]
    def getHiliteState(): String
    def getRecordHiliteCSSText(record: Record, cssText: String, field: Field): String
    def getSelectionLength(): Int
    def getSort(): IscArray[SortSpecifier]
    def getSummaryFieldValue(field: Field, record: Record): String
    def getTitleField(): String
    def getTitleFieldValue(record: Record): String
    val hiliteEditor: HiliteEditor
    var hiliteIconHeight: Int
    var hiliteIconLeftPadding: Int
    val hiliteIconPosition: HiliteIconPosition
    var hiliteIconRightPadding: Int
    val hiliteIcons: IscArray[String]
    var hiliteIconSize: Int
    var hiliteIconWidth: Int
    var hiliteProperty: String
    var hilites: IscArray[Hilite]
    var hiliteState: String
    val iconField: String
    var includeHilitesInSummaryFields: Boolean
    val infoField: String
    val initialCriteria: Criteria
    def isOffline(): Boolean
    var missingSummaryFieldValue: String
    var offlineMessage: String
    var offlineSaveMessage: String
    var preventDuplicates: Boolean
    var progressiveLoading: Boolean
    var removeFormulaFieldText: String
    var removeOperation: String
    var removeSummaryFieldText: String
    def selectAllRecords(): void
    def selectionUpdated(record: Record, recordList: IscArray[Record]): void
    def selectRange(startRow: Int, endRow: Int, newState: Boolean = js.native): void
    def setHilites(hilites: Hilite): void
    def setHiliteState(hiliteState: String): void
    def setSort(sortSpecifiers: SortSpecifier): void
    def shouldIncludeHiliteInSummaryField(summaryFieldName: String, usedFieldName: String): Boolean
    var showComplexFields: Boolean
    var showDetailFields: Boolean
    var showHiddenFields: Boolean
    var showOfflineMessage: Boolean
    val titleField: String
    def transferDragData(): IscArray[JSObject]
    def transferRecords(dropRecords: IscArray[Record], targetRecord: Record, index: Int, sourceWidget: Canvas, callback: Callback = js.native): void
    def transferSelectedData(source: DataBoundComponent, index: Int, callback: Callback = js.native): void
    var unknownErrorMessage: HTMLString
    var updateOperation: String
    var useAllDataSourceFields: Boolean
    val useFlatFields: Boolean
    def userAddedField(field: ListGridField): Boolean
    def willFetchData(newCriteria: Criteria, textMatchStyle: TextMatchStyle = js.native): Boolean
}

@js.native
abstract trait AbstractDataBoundComponentCompanion extends AbstractClassCompanion


