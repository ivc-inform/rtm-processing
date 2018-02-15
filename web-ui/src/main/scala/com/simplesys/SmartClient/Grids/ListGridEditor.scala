package com.simplesys.SmartClient.Grids

import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.DataBinding.Callbacks._
import com.simplesys.SmartClient.DataBinding.{DSRequest, DataSource}
import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.SmartClient.Grids.listGrid.{ListGridField, ListGridRecord, MasterDetailMapping}
import com.simplesys.SmartClient.Layout.{AbstractVLayoutSSCompanion, VLayoutSS}
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.AutoFitWidthApproach.AutoFitWidthApproach
import com.simplesys.System.Types.DateDisplayFormat.DateDisplayFormat
import com.simplesys.System.Types.DragDataAction.DragDataAction
import com.simplesys.System.Types.DragTrackerMode.DragTrackerMode
import com.simplesys.System.Types.ListGridEditEvent.ListGridEditEvent
import com.simplesys.System.Types.SelectionAppearance.SelectionAppearance
import com.simplesys.System.Types.SelectionStyle.SelectionStyle
import com.simplesys.System.Types.TextMatchStyle.TextMatchStyle
import com.simplesys.System.Types._
import com.simplesys.System._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName
import scala.scalajs.js.{UndefOr, |}

@js.native
trait GridEditor[T <: ListGridField, R <: JSAny, S <: JSAny] extends VLayoutSS {
    var canDragSelectText: Boolean
    var canAcceptDroppedRecords: JSUndefined[Boolean]
    var autoFitFieldWidth: Boolean
    var canResizeFields: Boolean
    def findByKey(keyValue: JSAny): R
    def removeData(removeRecord: R, callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    def saveAllEdits(): void
    def selectAllRecords(visibleNodesOnly: Boolean): void
    val showAllRecords: Boolean
    def selectSingleRecordByKey(keyValue: JSAny, newStyle: Boolean = js.native, callback: Callback = js.native): R
    def fetchData(criteria: Criteria = js.native, callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    def exportData(criteria: Criteria = js.native, requestProperties: DSRequest = js.native, callback: DSCallback = js.native): void
    def hasChanges(): Boolean
    def getDataLength(): Int
    def applyRecordData(recordData: IscArray[Record]): void
    def invalidateRecordComponents(): void
    def refreshRecordComponent(rowNum:Int, colNum:Int = js.native): void
    var showRecordComponentsByCell: Boolean
    var showRecordComponents: Boolean
    var filterOnKeypress: Boolean
    var drawAheadRatio: Double
    var autoSaveEdits: Boolean
    var selectionType: JSUndefined[SelectionStyle]
    var canEdit: Boolean
    var showRollOver: Boolean
    var autoFetchData: Boolean
    var showRowNumbers: Boolean
    var autoSaveConfig: Boolean
    var showAdvancedFilter: Boolean
    def selectSingleRecord(record: R): void
    def selectFirstRecord(): void
    def hasErrors(): Boolean
    def getFieldByName(name: String): T
    def removeSelectedData(callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    def restoreViewState(callback: Callback): void
    def getCriteria(): Criteria
    def setValueMap(fieldID: ID, map: ValueMap): void
    def setDataSource(dataSource: DataSource): void
    def setSelectedState(selectedState: S): void
    def cancelEditing(): void
    var emptyMessage: String
    def updateData(updatedRecord: JSObject, callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    var wrapCells: Boolean
    val autoFetchTextMatchStyle: TextMatchStyle
    def getSelection(excludePartialSelections: Boolean = js.native): IscArray[R]
    var fixedRecordHeights: Boolean
    val dragTrackerMode: DragTrackerMode
    def setData(newData: IscArray[R]): void
    var autoFitWidthApproach: AutoFitWidthApproach
    def startEditing(rowNum: Int = js.native, colNum: Int = js.native, suppressFocus: Boolean = js.native): Boolean
    def addData(newRecord: Record, callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    var modalEditing: Boolean
    var canSelectCells: Boolean
    var useClientFilteringSorting: Boolean
    var selectionAppearance: SelectionAppearance
    def selectRecords(records: IscArray[R], newState: Boolean = js.native): void
    def setFields(newFields: IscArray[T] = js.native): void
    def selectRecord(record: R, newState: Boolean = js.native): void
    def selectRecordByKey(keyValue: JSAny, newState: Boolean = js.native): void
    def fullRefresh(criteria: Criteria = js.native, callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    def invalidateCache(): void
    def saveViewState(callback: Callback): void
    def getViewState(): ListGridViewState
    var cancelEditingConfirmationMessage: String
    def getAllRows(): IscArray[R]
    var fetchDelay: Int
    def startEditingNew(newValues: JSObject | R = js.native, suppressFocus: Boolean = js.native): void
    var editEvent: ListGridEditEvent
    def getEditRecord(): R
    def getSelectedRecord(): R
    def getSelectedRecords(excludePartialSelections: Boolean = js.native): IscArray[R]
    def isSelected(): Boolean
    var canSelectTextExpandedField: Boolean
    var showFilterEditor: Boolean
    def discardAllEdits(): void
    def getSelectedState(): S
    def getRecord(data: JSAny): R
    var dataPageSize: Int
    def getEditorType(field: T, values: JSObject): String
    def deselectRecord(record: R | Int): void
    def deselectRecords(records: IscArray[R]): void
    def deselectAllRecords(): void
    def selectRecordsByKey(keyValues: JSAny, newState: Boolean = js.native): R
    def getRowNum(record: R): Int
    var dataSource: DataSource
    def setContextMenu(menu: MenuSS): void
    def getContextMenu(): MenuSS
    def setFuncMenu(menu: MenuSS): void
    var saveItems: IscArray[String | MenuSSItem]
    def setSelectionChanged(func: js.Function2[R, Boolean, _]): void
    def setSelectionUpdated(func: js.Function2[R, IscArray[R], _]): void
    def unsetSelectionChanged(func: js.Function2[R, Boolean, _]): void
    def unsetSelectionUpdated(func: js.Function2[R, IscArray[R], _]): void
    def setMasterGrid(grid: ListGrid | TreeGrid | ListGridEditor | TreeGridEditor, pkFieldNames: IscArray[MasterDetailMapping] | MasterDetailMapping = js.native): void
    def setForignFieldFields(grid: ListGrid | TreeGrid, masterGrid: UndefOr[ListGrid] | UndefOr[TreeGrid]): void
    var masterGrid: JSUndefined[ListGrid | ListGridEditor | TreeGrid | TreeGridEditor]
    var canDragRecordsOut: Boolean
    var canReorderRecords: Boolean
    var dragDataAction: DragDataAction
    def startEditingNewInForm(obj: JSObject = js.native, fields: IscArray[FormItem] = js.native, callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    //var startEditingNewInForm: JSUndefined[Function4[JSObject, IscArray[FormItem], DSCallback, DSRequest, _]]
    def startEditingInForm(obj: JSObject = js.native, fields: IscArray[FormItem] = js.native, callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    //var startEditingInForm: JSUndefined[Function4[JSObject, IscArray[FormItem], DSCallback, DSRequest, _]]
    var newRequestProperties: JSUndefined[js.Function0[DSRequest]]
    var editRequestProperties: JSUndefined[js.Function0[DSRequest]]
    var editingFields: JSUndefined[IscArray[FormItem]]
    def getFieldName(colNum: Int | ID): String
    var selectFirstRecordAfterFetch: Boolean
    var fields: JSUndefined[IscArray[ListGridField]]
    val replacingFields: JSUndefined[IscArray[ListGridField]]
    def setSelectionAppearance(selectionAppearance: SelectionAppearance): void
    def setSelectionType(selectionType: SelectionStyle): void
    var simpleTable: JSUndefined[Boolean]
    def getEditFormItem(field: String | Int): FormItem
    @JSName("grid")
    var listGrid: ListGrid
    @JSName("grid")
    var treeGrid: TreeGrid
    val datetimeFormatter: DateDisplayFormat
    val dateFormatter: DateDisplayFormat
    def refreshData(callBack: DSCallback = js.native): void
}

@js.native
trait ListGridEditor extends VLayoutSS with GridEditor[ListGridField, ListGridRecord, ListGridSelectedState] {
}

@js.native
abstract trait AbstractListGridEditorCompanion extends AbstractVLayoutSSCompanion {
}
