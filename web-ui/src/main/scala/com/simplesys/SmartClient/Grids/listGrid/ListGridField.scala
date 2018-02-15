package com.simplesys.SmartClient.Grids.listGrid

import com.simplesys.SmartClient.DataBinding.DSRequest
import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.SmartClient.Forms.formsItems.formItem.FormItemIcon
import com.simplesys.SmartClient.Forms.{DynamicForm, Validator}
import com.simplesys.SmartClient.Grids.Grid
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.Alignment.Alignment
import com.simplesys.System.Types.AutoComplete.AutoComplete
import com.simplesys.System.Types.AutoFitWidthApproach.AutoFitWidthApproach
import com.simplesys.System.Types.DateDisplayFormat.DateDisplayFormat
import com.simplesys.System.Types.EditCompletionEvent.EditCompletionEvent
import com.simplesys.System.Types.EnterKeyEditAction.EnterKeyEditAction
import com.simplesys.System.Types.EscapeKeyEditAction.EscapeKeyEditAction
import com.simplesys.System.Types.HiliteIconPosition.HiliteIconPosition
import com.simplesys.System.Types.ListGridFieldType.ListGridFieldType
import com.simplesys.System.Types.OperatorId.OperatorId
import com.simplesys.System.Types.RecordSummaryFunction.RecordSummaryFunction
import com.simplesys.System.Types.SortDirection.SortDirection
import com.simplesys.System.Types.SummaryFunction.SummaryFunction
import com.simplesys.System.Types.TextMatchStyle.TextMatchStyle
import com.simplesys.System.Types.TimeDisplayFormat.TimeDisplayFormat
import com.simplesys.System.Types._
import com.simplesys.System.{JSAny, JSObject, JSUndefined, NameStrong}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName
import scala.scalajs.js.|

@js.native
trait ListGridField extends JSObject {
    var align: Alignment
    var applyAfterSummary: Boolean
    var autoComplete: AutoComplete
    var autoFetchDisplayMap: Boolean
    var autoFitWidth: Boolean
    var autoFitWidthApproach: AutoFitWidthApproach
    var autoFreeze: Boolean
    var baseStyle: CSSStyleName
    var canAutoFitWidth: Boolean
    var canDragResize: Boolean
    var canEdit: Boolean
    var canEditFormula: Boolean
    var canEditSummary: Boolean
    var canExport: Boolean
    var canFilter: Boolean
    var canFreeze: Boolean
    var canGroupBy: Boolean
    var canHide: Boolean
    var canHilite: Boolean
    var canReorder: Boolean
    var canSort: Boolean
    var canSortClientOnly: Boolean
    var canToggle: Boolean
    var cellAlign: Alignment
    var cellChanged: js.Function6[ListGridRecord, JSAny, JSAny, Int, Int, Grid[ListGridField, ListGridRecord], void]
    var cellIcon: SCImgURL
    var change: js.Function4[DynamicForm, FormItem, JSAny, JSAny, Boolean]
    var changed: js.Function3[DynamicForm, FormItem, JSAny, void]
    var dataPath: String
    var dateFormatter: DateDisplayFormat
    var decimalPad: Int
    var decimalPrecision: Int
    def defaultDynamicValue(item: FormItem, form: DynamicForm, values: JSObject): void
    var defaultFilterValue: JSAny
    val defaultGroupingMode: String
    val defaultIconSrc: String
    var defaultValue: JSAny
    var defaultWidth: Int
    var displayField: String
    var displayValueFromRecord: Boolean
    var editorEnter: js.Function5[ListGridRecord, JSAny, Int, Int, Grid[ListGridField, ListGridRecord], void]
    var editorExit: js.Function6[EditCompletionEvent, ListGridRecord, JSAny, Int, Int, Grid[ListGridField, ListGridRecord], void]
    val editorIconHeight: Int
    val editorIconWidth: Int
    var editorImageURLPrefix: String
    var editorImageURLSuffix: String
    var editorProperties: JSUndefined[FormItem]
    var editorType: FormItemClassName
    var editorValueIconHeight: Int
    var editorValueIcons: JSObject
    var editorValueIconWidth: Int
    var editorValueMap: JSObject
    var emptyCellValue: HTMLString
    var enterKeyEditAction: EnterKeyEditAction
    var escapeHTML: Boolean
    var escapeKeyEditAction: EscapeKeyEditAction
    var exportFieldWidth: Boolean
    var exportFormat: FormatString
    val exportRawValues: Boolean
    var filterEditorProperties: JSUndefined[FormItem]
    var filterEditorType: FormItemClassName
    var filterEditorValueMap: JSObject
    var filterOnKeypress: Boolean
    var filterOperator: OperatorId
    val format: FormatString
    def formatCellValue(value: JSAny, record: ListGridRecord, rowNum: Int, colNum: Int, grid: Grid[ListGridField, ListGridRecord]): HTMLString
    def formatEditorValue(value: JSAny, record: ListGridRecord, rowNum: Int, colNum: Int, grid: Grid[ListGridField, ListGridRecord]): JSAny
    var formatGridSummary: String
    var formatGroupSummary: String
    var frozen: Boolean
    def getAutoFreezePosition(): Int
    def getEditorValueMap(values: JSObject, field: ListGridField, grid: Grid[ListGridField, ListGridRecord]): ValueMap
    def getFieldTitle(viewer: Grid[ListGridField, ListGridRecord], fieldNum: Int): String
    def getGridSummary(records: IscArray[ListGridRecord], field: ListGridField, groupSummaries: IscArray[JSObject] = js.native): JSAny
    def getGroupSummary(records: IscArray[ListGridRecord], field: ListGridField, groupNode: JSObject = js.native): JSAny
    def getGroupTitle(groupValue: JSAny, groupNode: groupNode, field: JSObject, fieldName: String, grid: Grid[ListGridField, ListGridRecord]): JSAny
    def getGroupValue(value: JSAny, record: ListGridRecord, field: JSObject, fieldName: String, grid: Grid[ListGridField, ListGridRecord]): JSAny
    def getRecordSummary(record: ListGridRecord, field: ListGridField, grid: Grid[ListGridField, ListGridRecord]): JSAny
    def getSummaryTitle(viewer: Grid[ListGridField, ListGridRecord], field: ListGridField): void
    val groupGranularity: Int
    val groupingMode: String
    val groupingModes: ValueMap
    val groupPrecision: Int
    val headerBaseStyle: CSSStyleName
    var headerHoverDelay: Int
    val headerTitle: String
    var headerTitleStyle: CSSStyleName
    var hidden: Boolean
    val hiliteHTMLAfterFormat: Boolean
    val hiliteIconHeight: Int
    val hiliteIconLeftPadding: Int
    var hiliteIconPosition: HiliteIconPosition
    var hiliteIconRightPadding: Int
    var hiliteIconSize: Int
    var hiliteIconWidth: Int
    var hoverDelay: Int
    var hoverHTML: js.Function5[ListGridRecord, JSAny, Int, Int, Grid[ListGridField, ListGridRecord], HTMLString]
    var hoverWidth: Int
    var hoverWrap: Boolean
    var icon: SCImgURL
    var iconHeight: Int
    var iconOrientation: String
    var icons: IscArray[FormItemIcon]
    var iconSize: Int
    var iconSpacing: Int
    var iconVAlign: String
    var iconWidth: Int
    var ignoreKeyboardClicks: Boolean
    var imageHeight: Int
    var imageSize: Int
    var imageURLPrefix: String
    var imageURLSuffix: String
    var imageWidth: Int
    var includeFrom: String
    var includeInRecordSummary: Boolean
    val includeInRecordSummaryFields: IscArray[String]
    val initialValue: JSAny
    var inputFormat: DateInputFormat
    val isRemoveField: Boolean
    val leaveHeaderMenuButtonSpace: Boolean
    var linkText: String
    var linkTextProperty: String
    var linkURLPrefix: String
    var linkURLSuffix: String
    var multiple: Boolean
    @deprecated(message = "Use nameStrong instead.", since = "")
    var name: JSUndefined[String]
    @JSName("name")
    var _name: String
    var nameStrong: JSUndefined[NameStrong]
    var optionCriteria: Criteria
    var optionDataSource: String
    var optionFilterContext: DSRequest
    var optionOperationId: String
    var optionTextMatchStyle: TextMatchStyle
    def parseEditorValue(value: JSAny, record: JSObject, rowNum: Int, colNum: Int, grid: Grid[ListGridField, ListGridRecord]): JSAny
    var partialSummary: Boolean
    var prompt: HTMLString
    var recordClick: js.Function7[Grid[ListGridField, ListGridRecord], ListGridRecord, Int, ListGridField, Int, JSAny, JSAny, Boolean]
    var recordDoubleClick: js.Function7[Grid[ListGridField, ListGridRecord], ListGridRecord, Int, ListGridField, Int, JSAny, JSAny, Boolean]
    var recordSummaryFunction: RecordSummaryFunction
    var required: Boolean
    var shouldPrint: Boolean
    var showAlternateStyle: Boolean
    var showDefaultContextMenu: Boolean
    var showDisabledIcon: Boolean
    var showDownIcon: Boolean
    var showFileInline: Boolean
    var showFocusedIcon: Boolean
    var showGridSummary: Boolean
    var showGroupSummary: Boolean
    var showHover: Boolean
    var showIf: js.Function3[Grid[ListGridField, ListGridRecord], ListGridField, Int, Boolean]
    var showRollOverIcon: Boolean
    var showSelectedIcon: Boolean
    var showTitle: Boolean
    var showValueIconOnly: Boolean
    var sortByDisplayField: Boolean
    var sortByMappedValue: Boolean
    var sortDirection: SortDirection
    def sortNormalizer(recordObject: JSObject, fieldName: String, context: Grid[ListGridField, ListGridRecord]): JSAny
    var summaryFunction: SummaryFunction | IscArray[SummaryFunction]
    var summaryTitle: String
    var summaryValue: HTMLString
    val summaryValueTitle: String
    var suppressValueIcon: Boolean
    var target: String
    var timeFormatter: TimeDisplayFormat
    var title: String
    var `type`: ListGridFieldType
    var userFormula: UserFormula
    var userSummary: UserSummary
    var validateOnChange: Boolean
    var validators: IscArray[Validator]
    var valueField: String
    var valueIconClick: js.Function6[Grid[ListGridField, ListGridRecord], ListGridRecord, Int, ListGridField, JSAny, FormItem, Boolean]
    var valueIconHeight: Int
    var valueIconLeftPadding: Int
    var valueIconOrientation: String
    var valueIconRightPadding: Int
    var valueIcons: JSObject
    var valueIconSize: Int
    var valueIconWidth: Int
    var valueMap: JSObject | IscArray[String]
    var width: Int | String
    var wrap: Boolean
}
