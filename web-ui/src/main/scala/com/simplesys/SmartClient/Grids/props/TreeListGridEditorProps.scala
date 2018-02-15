package com.simplesys.SmartClient.Grids.props

import com.simplesys.SmartClient.DataBinding.{DSRequest, DataSource}
import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Foundation.canvas.ImgProperties
import com.simplesys.SmartClient.Grids.{ListGridEditor, TreeGridEditor, TreeListGridEditor}
import com.simplesys.SmartClient.Grids.listGrid.ListGridRecord
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.Grids.props.treeGrid.TreeGridFieldProps
import com.simplesys.SmartClient.Grids.treeGrid.Tree
import com.simplesys.SmartClient.Layout.WindowSS
import com.simplesys.SmartClient.Layout.props.HLayoutSSProps
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.{JSObject, JSUndefined}
import com.simplesys.System.Types.FetchMode.FetchMode
import com.simplesys.System.Types.RecordComponentPoolingMode.RecordComponentPoolingMode
import com.simplesys.System.Types.SelectionAppearance._
import com.simplesys.System.Types.SelectionStyle.SelectionStyle
import com.simplesys.System.Types.TextMatchStyle.TextMatchStyle
import com.simplesys.System.Types._
import com.simplesys.option.{IntString, ScNone, ScOption}

import scala.scalajs.js
import scala.scalajs.js.{ThisFunction0, ThisFunction2, ThisFunction4}

class TreeListGridEditorProps extends HLayoutSSProps {
    type classHandler <: TreeListGridEditor

    var widthTree: ScOption[IntString[Int, String]] = ScNone
    var widthList: ScOption[IntString[Int, String]] = ScNone
    var fetchTreeDelay: ScOption[Int] = ScNone
    var fetchListDelay: ScOption[Int] = ScNone
    var showTreeRecordComponents: ScOption[Boolean] = ScNone
    var showListRecordComponents: ScOption[Boolean] = ScNone
    var editByCellTree: ScOption[Boolean] = ScNone
    var editByCellList: ScOption[Boolean] = ScNone
    var enableChangeSelectionTree: ScOption[Boolean] = ScNone
    var showAllChild: ScOption[Boolean] = ScNone
    var selectionTypeTree: ScOption[SelectionStyle] = ScNone
    var selectionTypeList: ScOption[SelectionStyle] = ScNone
    var getTreeSelection: ScOption[IscArray[ListGridRecord]] = ScNone
    var showTreeFilterEditor: ScOption[Boolean] = ScNone
    var canAcceptDroppedRecordsTree: ScOption[Boolean] = ScNone
    var canAcceptDroppedRecordsList: ScOption[Boolean] = ScNone
    var canDragSelectText: ScOption[Boolean] = ScNone
    var autoFetchData: ScOption[Boolean] = ScNone
    var showTreeAdvancedFilter: ScOption[Boolean] = ScNone
    var cancelEditingConfirmationMessageList: ScOption[String] = ScNone
    var cancelEditingConfirmationMessageTree: ScOption[String] = ScNone
    var showListAdvancedFilte: ScOption[Boolean] = ScNone
    var canAcceptDropTree: ScOption[Boolean] = ScNone
    var drawAheadRatioList: ScOption[Double] = ScNone
    var drawAheadRatioTree: ScOption[Double] = ScNone
    var showResizeBarList: ScOption[Boolean] = ScNone
    var heightTree: ScOption[IntString[Int, String]] = ScNone
    var heightList: ScOption[IntString[Int, String]] = ScNone
    var showTreeRecordComponentsByCell: ScOption[Boolean] = ScNone
    var showListRecordComponentsByCell: ScOption[Boolean] = ScNone
    var canSelectCellsTree: ScOption[Boolean] = ScNone
    var emptyMessageTree: ScOption[String] = ScNone
    var emptyMessageList: ScOption[String] = ScNone
    var showResizeBarTree: ScOption[Boolean] = ScNone
    var canReparentNodesTree: ScOption[Boolean] = ScNone
    var showListFilterEditor: ScOption[Boolean] = ScNone
    var filterTreeOnKeypress: ScOption[Boolean] = ScNone
    var filterListOnKeypress: ScOption[Boolean] = ScNone
    var showOpenIconsTree: ScOption[Boolean] = ScNone
    var loadDataOnDemandTree: ScOption[Boolean] = ScNone
    var dataPageSizeTree: ScOption[Int] = ScNone
    var dataPageSizeList: ScOption[Int] = ScNone
    var autoFetchTextMatchStyleTree: ScOption[TextMatchStyle] = ScNone
    var autoFetchTextMatchStyleList: ScOption[TextMatchStyle] = ScNone
    var canEditTree: ScOption[Boolean] = ScNone
    var canEditList: ScOption[Boolean] = ScNone
    var folderDropImageTree: ScOption[SCImgURL] = ScNone
    var folderIconTree: ScOption[SCImgURL] = ScNone
    var nodeIconTree: ScOption[SCImgURL] = ScNone
    var wrapTreeCells: ScOption[Boolean] = ScNone
    var canSelectCellsList: ScOption[Boolean] = ScNone
    var autoSaveListEdits: ScOption[Boolean] = ScNone
    var autoSaveTreeEdits: ScOption[Boolean] = ScNone
    var dataSourceList: ScOption[DataSource] = ScNone
    var dataSourceTree: ScOption[DataSource] = ScNone
    var wrapListCells: ScOption[Boolean] = ScNone
    var fieldsTree: ScOption[Seq[ListGridFieldProps]] = ScNone
    var defaultFieldsTree: ScOption[Seq[TreeGridFieldProps]] = ScNone
    var fieldsList: ScOption[Seq[ListGridFieldProps]] = ScNone
    var defaultFieldsList: ScOption[Seq[ListGridFieldProps]] = ScNone
    var newTreeRequestProperties: ScOption[ThisFunction0[classHandler, DSRequest]] = ScNone
    var editTreeRequestProperties: ScOption[ThisFunction0[classHandler, DSRequest]] = ScNone
    var newListRequestProperties: ScOption[ThisFunction0[classHandler, DSRequest]] = ScNone
    var editListRequestProperties: ScOption[ThisFunction0[classHandler, DSRequest]] = ScNone
    var editingListFields: ScOption[Seq[FormItem]] = ScNone
    var editingTreeFields: ScOption[Seq[FormItem]] = ScNone
    var saveByTreeCell: ScOption[Boolean] = ScNone
    var saveByListCell: ScOption[Boolean] = ScNone
    var dataFetchModeList: ScOption[FetchMode] = ScNone
    var dataFetchModeTree: ScOption[FetchMode] = ScNone
    var dataTree: ScOption[Tree] = ScNone
    var editWindowPropertiesTree: ScOption[WindowSS] = ScNone
    var editWindowPropertiesList: ScOption[WindowSS] = ScNone
    var canDragRecordsOutList: ScOption[Boolean] = ScNone
    var canDragRecordsOutTree: ScOption[Boolean] = ScNone
    var trackerImageList: ScOption[ImgProperties] = ScNone
    var trackerImageTree: ScOption[ImgProperties] = ScNone
    var selectionAppearanceList: ScOption[SelectionAppearance] = ScNone
    var selectionAppearanceTree: ScOption[SelectionAppearance] = ScNone
    var selectFirstRecordAfterFetchList: ScOption[Boolean] = ScNone
    var selectFirstRecordAfterFetchTree: ScOption[Boolean] = ScNone
    var dataArrivedList: ScOption[js.ThisFunction2[classHandler, Int, Int, _]] = ScNone
    var dataArrivedTree: ScOption[js.ThisFunction2[classHandler, Int, Int, _]] = ScNone

    var createListRecordComponent: ScOption[ThisFunction2[ListGridEditor, _ <: JSObject, Int, JSUndefined[Canvas]]] = ScNone
    var updateListRecordComponent: ScOption[ThisFunction4[ListGridEditor, _ <: JSObject, Int, Canvas, Boolean, JSUndefined[Canvas]]] = ScNone

    var createTreeRecordComponent: ScOption[ThisFunction2[TreeGridEditor, _ <: JSObject, Int, JSUndefined[Canvas]]] = ScNone
    var updateTreeRecordComponent: ScOption[ThisFunction4[TreeGridEditor, _ <: JSObject, Int, Canvas, Boolean, JSUndefined[Canvas]]] = ScNone

    var recordListComponentPoolingMode: ScOption[RecordComponentPoolingMode] = ScNone
    var recordTreeComponentPoolingMode: ScOption[RecordComponentPoolingMode] = ScNone
}
