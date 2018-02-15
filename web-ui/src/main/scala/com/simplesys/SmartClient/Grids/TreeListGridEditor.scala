package com.simplesys.SmartClient.Grids

import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.DataBinding.Callbacks.DSCallback
import com.simplesys.SmartClient.DataBinding.{DSRequest, DataSource}
import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Foundation.canvas.ImgProperties
import com.simplesys.SmartClient.Grids.listGrid.{ListGridField, ListGridRecord}
import com.simplesys.SmartClient.Grids.treeGrid.TreeGridField
import com.simplesys.SmartClient.Layout.{AbstractHLayoutSSCompanion, HLayoutSS, TabSetSS}
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.RecordComponentPoolingMode._
import com.simplesys.System.Types.SelectionAppearance.SelectionAppearance
import com.simplesys.System.Types.SelectionStyle.SelectionStyle
import com.simplesys.System.Types.TextMatchStyle.TextMatchStyle
import com.simplesys.System.Types._
import com.simplesys.System._

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait TreeListGridEditor extends HLayoutSS {

    def getTreeSelectedRecord(): ListGridRecord
    def getListSelectedRecord(): ListGridRecord
    def findByKeyList(keyValue: JSAny): ListGridRecord
    def findByKeyTree(keyValue: JSAny): ListGridRecord
    def discardListAllEdits(): void
    def discardTreeAllEdits(): void
    def updateListData(updatedRecord: ListGridRecord, callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    def updateTreeData(updatedRecord: ListGridRecord, callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    def removeListSelectedData(): void
    def removeTreeSelectedData(): void
    var widthTree: Int | String
    var widthList: Int | String
    def fetchTreeData(criteria: Criteria | js.Dictionary[_] = js.native, callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    def fetchListData(criteria: Criteria | js.Dictionary[_] = js.native, callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    var fetchTreeDelay: Int
    var fetchListDelay: Int
    var showTreeRecordComponents: Boolean
    var showListRecordComponents: Boolean
    def selectListSingleRecordByKey(keyValue: JSAny, newStyle: Boolean = js.native, callback: Callback = js.native): ListGridRecord
    def selectTreeSingleRecordByKey(keyValue: JSAny, newStyle: Boolean = js.native, callback: Callback = js.native): ListGridRecord
    var editByCellTree: Boolean
    var editByCellList: Boolean
    var enableChangeSelectionTree: Boolean
    var showAllChild: Boolean
    var selectionTypeTree: SelectionStyle
    var selectionTypeList: SelectionStyle
    def getTreeSelection: IscArray[ListGridRecord]
    var showTreeFilterEditor: Boolean
    def cancelTreeEditing(): void
    def cancelListEditing(): void
    var canAcceptDroppedRecordsTree: Boolean
    var canAcceptDroppedRecordsList: Boolean
    var autoFetchData: Boolean
    def startListEditing(): void
    def startTreeEditing(): void
    def setTreeFields(fields: IscArray[TreeGridField])
    def setListFields(fields: IscArray[ListGridField])
    var showTreeAdvancedFilter: Boolean
    def startTreeEditingNew(): void
    def saveListAllEdits(rows: IscArray[Int] = js.native, saveCallback: Callback = js.native): void
    def saveTreeAllEdits(rows: IscArray[Int] = js.native, saveCallback: Callback = js.native): void
    var cancelEditingConfirmationMessageList: String
    var cancelEditingConfirmationMessageTree: String
    var showListAdvancedFilte: Boolean
    var canAcceptDropTree: Boolean
    var drawAheadRatioList: Double
    def setListSelectedState(selectedState: ListGridSelectedState): void
    def getListSelectedState(): ListGridSelectedState
    var drawAheadRatioTree: Double
    def startListEditingNew
    def startListEditingNew(newValues: JSObject | ListGridRecord = js.native, suppressFocus: Boolean = js.native): void
    def setFuncMenu(menu: MenuSS): void
    var showResizeBarList: Boolean
    def fullTreeRefresh(): void
    def fullListRefresh(): void
    def getListEditRecord(): ListGridRecord
    var heightTree: Int | String
    var heightList: Int | String
    def setTreeSelectedState(selectedState: TreeGridSelectedState): void
    def getTreeSelectedState(): TreeGridSelectedState
    var canSelectCellsLis: Boolean
    var showTreeRecordComponentsByCell: Boolean
    var showListRecordComponentsByCell: Boolean
    var canSelectCellsTree: Boolean
    var emptyMessageTree: String
    var emptyMessageList: String
    var showResizeBarTree: Boolean
    var canReparentNodesTree: Boolean
    var showListFilterEditor: Boolean
    var filterTreeOnKeypress: Boolean
    var filterListOnKeypress: Boolean
    var showOpenIconsTree: Boolean
    var loadDataOnDemandTree: Boolean
    def setTreeDataSource(dataSource: DataSource): void
    def setListDataSource(dataSource: DataSource): void
    var dataPageSizeTree: Int
    var dataPageSizeList: Int
    var autoFetchTextMatchStyleTree: TextMatchStyle
    var autoFetchTextMatchStyleList: TextMatchStyle
    var canEditTree: Boolean
    var canEditList: Boolean
    def setContextMenuTreeGridEditor(menu: MenuSS): void
    def setContextMenuListGridEditor(menu: MenuSS): void
    def setTreeCanReparentNodes(value: Boolean): void
    def getListCriteria(): Criteria
    def getTreeCriteria(): Criteria
    var folderDropImageTree: SCImgURL
    var folderIconTree: SCImgURL
    var nodeIconTree: SCImgURL
    var canSelectCellsList: Boolean
    var autoSaveListEdits: Boolean
    var autoSaveTreeEdits: Boolean
    var dataSourceList: DataSource
    var dataSourceTree: DataSource
    var wrapListCells: Boolean
    var wrapTreeCells: Boolean
    def getListGrid(): ListGridEditor
    def getTreeGrid(): TreeGridEditor
    var fieldsTree: JSUndefined[IscArray[ListGridField]]
    var replacingFieldsTree: JSUndefined[IscArray[ListGridField]]
    var defaultFieldsTree: JSUndefined[IscArray[TreeGridField]]
    var fieldsList: JSUndefined[IscArray[ListGridField]]
    var replacingFieldsList: JSUndefined[IscArray[ListGridField]]
    var defaultFieldsList: JSUndefined[IscArray[ListGridField]]
    val treeGrid: TreeGridEditor
    val listGrid: ListGridEditor
    val contextMenuListGridEditor: MenuSS
    val contextMenuTreeGridEditor: MenuSS
    def getViewState(): void
    def setTabSet(tabSet: TabSetSS): TabSetSS
    var newTreeRequestProperties: JSUndefined[DSRequest]
    var editTreeRequestProperties: JSUndefined[DSRequest]
    var newListRequestProperties: JSUndefined[DSRequest]
    var editListRequestProperties: JSUndefined[DSRequest]
    var editingTreeFields: JSUndefined[IscArray[FormItem]]
    var editingListFields: JSUndefined[IscArray[FormItem]]
    var canDragRecordsOutList: JSUndefined[Boolean]
    var canDragRecordsOutTree: JSUndefined[Boolean]
    var trackerImageList: JSUndefined[ImgProperties]
    var trackerImageTree: JSUndefined[ImgProperties]
    var selectionAppearanceList: JSUndefined[SelectionAppearance]
    var selectionAppearanceTree: JSUndefined[SelectionAppearance]
    var selectFirstRecordAfterFetchList: JSUndefined[Boolean]
    var selectFirstRecordAfterFetchTree: JSUndefined[Boolean]
    var dataArrivedList: js.ThisFunction2[classHandler, Int, Int, void]
    var dataArrivedTree: js.ThisFunction2[classHandler, Int, Int, void]

    def createListRecordComponent(record: ListGridRecord, colNum: Int): JSUndefined[Canvas]
    def createTreeRecordComponent(record: ListGridRecord, colNum: Int): JSUndefined[Canvas]

    def updateListRecordComponent(record: ListGridRecord, colNum: Int, component: Canvas, recordChanged: Boolean): JSUndefined[Canvas]
    def updateTreeRecordComponent(record: ListGridRecord, colNum: Int, component: Canvas, recordChanged: Boolean): JSUndefined[Canvas]

    val recordListComponentPoolingMode: RecordComponentPoolingMode
    val recordTreeComponentPoolingMode: RecordComponentPoolingMode

    def refreshDataList(callBack: DSCallback = js.native): void
    def refreshDataTree(callBack: DSCallback = js.native): void

    def refreshRecordComponentList(rowNum:Int, colNum:Int = js.native): void
    def refreshRecordComponentTree(rowNum:Int, colNum:Int = js.native): void
}

@js.native
abstract trait AbstractTreeListGridEditorCompanion extends AbstractHLayoutSSCompanion {
}
