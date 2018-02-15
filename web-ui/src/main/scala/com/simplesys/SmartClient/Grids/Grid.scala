package com.simplesys.SmartClient.Grids

import com.simplesys.SmartClient.Charts.{Chart, FacetChart}
import com.simplesys.SmartClient.Control.menu.MenuItem
import com.simplesys.SmartClient.Control.{Button, IButton}
import com.simplesys.SmartClient.DataBinding.Callbacks._
import com.simplesys.SmartClient.DataBinding._
import com.simplesys.SmartClient.Forms.DynamicForm
import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.SmartClient.Foundation.canvas.ImgProperties
import com.simplesys.SmartClient.Foundation.{Canvas, GridRenderer, HTMLFlow, StatefulCanvas}
import com.simplesys.SmartClient.Grids.listGrid._
import com.simplesys.SmartClient.Grids.treeGrid.Tree
import com.simplesys.SmartClient.Layout._
import com.simplesys.SmartClient.System.selection.CellSelection
import com.simplesys.SmartClient.System.{Class, IscArray, Selection}
import com.simplesys.System.Types.Alignment.Alignment
import com.simplesys.System.Types.AnimationAcceleration.AnimationAcceleration
import com.simplesys.System.Types.AutoComplete.AutoComplete
import com.simplesys.System.Types.AutoFitEvent.AutoFitEvent
import com.simplesys.System.Types.AutoFitIconFieldType.AutoFitIconFieldType
import com.simplesys.System.Types.AutoFitWidthApproach.AutoFitWidthApproach
import com.simplesys.System.Types.Autofit.Autofit
import com.simplesys.System.Types.ChartType.ChartType
import com.simplesys.System.Types.DateDisplayFormat.DateDisplayFormat
import com.simplesys.System.Types.DragTrackerMode.DragTrackerMode
import com.simplesys.System.Types.EditCompletionEvent.EditCompletionEvent
import com.simplesys.System.Types.EmbeddedPosition.EmbeddedPosition
import com.simplesys.System.Types.EnterKeyEditAction.EnterKeyEditAction
import com.simplesys.System.Types.EscapeKeyEditAction.EscapeKeyEditAction
import com.simplesys.System.Types.ExpansionComponentPoolingMode.ExpansionComponentPoolingMode
import com.simplesys.System.Types.ExpansionMode.ExpansionMode
import com.simplesys.System.Types.GroupStartOpen.GroupStartOpen
import com.simplesys.System.Types.HoverMode.HoverMode
import com.simplesys.System.Types.ListGridComponent.ListGridComponent
import com.simplesys.System.Types.ListGridEditEvent.ListGridEditEvent
import com.simplesys.System.Types.Overflow.Overflow
import com.simplesys.System.Types.RecordComponentPoolingMode.RecordComponentPoolingMode
import com.simplesys.System.Types.RecordDropAppearance.RecordDropAppearance
import com.simplesys.System.Types.RecordDropPosition.RecordDropPosition
import com.simplesys.System.Types.ReorderPosition.ReorderPosition
import com.simplesys.System.Types.RowEndEditAction.RowEndEditAction
import com.simplesys.System.Types.RowSpanEditMode.RowSpanEditMode
import com.simplesys.System.Types.RowSpanSelectionMode.RowSpanSelectionMode
import com.simplesys.System.Types.SelectionAppearance.SelectionAppearance
import com.simplesys.System.Types.SelectionNotificationType.SelectionNotificationType
import com.simplesys.System.Types.SelectionStyle.SelectionStyle
import com.simplesys.System.Types.SortArrow.SortArrow
import com.simplesys.System.Types.SortDirection.SortDirection
import com.simplesys.System.Types.State.State
import com.simplesys.System.Types.SummaryFunction.SummaryFunction
import com.simplesys.System.Types.TextMatchStyle.TextMatchStyle
import com.simplesys.System.Types.TimeDisplayFormat.TimeDisplayFormat
import com.simplesys.System.Types.VerticalAlignment.VerticalAlignment
import com.simplesys.System.Types._
import com.simplesys.System._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName
import scala.scalajs.js.{UndefOr, |}

@js.native
trait Grid[T <: ListGridField, R <: JSAny] extends VLayout with DataBoundComponent {
    def addData(newRecord: Record, callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    def addEmbeddedComponent(component: Canvas, record: Record, rowNum: Int = js.native, colNum: Int = js.native, position: Int = js.native): void
    def addSort(sortSpecifier: SortSpecifier): void
    val advancedFieldPickerThreshold: Int
    val allowFilterExpressions: Boolean
    val allowRowSpanning: Boolean
    var alternateBodyStyleName: CSSStyleName
    var alternateFieldFrequency: Int
    var alternateFieldStyles: Boolean
    var alternateRecordFrequency: Int
    var alternateRecordStyles: Boolean
    val alwaysShowEditors: Boolean
    var animateFolderEffect: AnimationAcceleration
    var animateFolderMaxRows: Int
    var animateFolders: Boolean
    var animateFolderSpeed: Int
    var animateFolderTime: Int
    var animateRemoveRecord: Boolean
    var animateRemoveSpeed: Int
    var animateRemoveTime: Int
    var animateRollOver: Boolean
    var animateRollUnder: Boolean
    var animateSelection: Boolean
    var animateSelectionUnder: Boolean
    def applyCellData(cellData: IscArray[Record]): void
    var applyFormulaAfterSummary: Boolean
    def applyRecordData(recordData: IscArray[Record]): void
    var applyRowNumberStyle: Boolean
    def applySortToData(sortSpecifiers: IscArray[SortSpecifier]): void
    var arrowKeyAction: String
    val asynchGroupingPrompt: HTMLString
    var autoComplete: AutoComplete
    var autoConfirmSaveEdits: Boolean
    var autoFetchDisplayMap: Boolean
    val autoFetchTextMatchStyle: TextMatchStyle
    var autoFitAllText: String
    val autoFitClipFields: IscArray[String]
    var autoFitData: Autofit
    val autoFitExpandField: String
    var autoFitExtraRecords: Int
    def autoFitField(fieldName: String): Int
    def autoFitFields(fields: IscArray[T] = js.native): void
    val autoFitFieldsFillViewport: Boolean
    var autoFitFieldText: String
    val autoFitFieldWidths: Boolean
    val autoFitHeaderHeights: Boolean
    val autoFitIconFields: AutoFitIconFieldType
    var autoFitMaxColumns: Int
    var autoFitMaxHeight: Int
    var autoFitMaxRecords: Int
    var autoFitMaxWidth: Int
    var autoFitWidthApproach: AutoFitWidthApproach
    var autoSaveEdits: Boolean
    val autoSizeHeaderSpans: Boolean
    val backgroundComponent: Canvas with MultiAutoChild
    val baseStyle: CSSStyleName
    val body: GridRenderer with MultiAutoChild
    var bodyBackgroundColor: String
    var bodyKeyPress: js.ThisFunction0[classHandler, Boolean]
    var bodyOverflow: Overflow
    var bodyStyleName: CSSStyleName
    val booleanBaseStyle: CSSStyleName
    var booleanFalseImage: SCImgURL
    var booleanImageHeight: Int
    var booleanImageWidth: Int
    var booleanPartialImage: SCImgURL
    var booleanTrueImage: SCImgURL
    var canAcceptDroppedRecords: Boolean
    var canAutoFitFields: Boolean
    def cancelEditing(): void
    var cancelEditingConfirmationMessage: String
    val canCollapseGroup: Boolean
    var canDragRecordsOut: Boolean
    var canDragSelect: Boolean
    var canDragSelectText: Boolean
    var canDropInEmptyArea: Boolean
    var canEdit: Boolean
    def canEditCell(rowNum: Int, colNum: Int): Boolean
    var canEditTitles: Boolean
    var canExpandMultipleRecords: Boolean
    def canExpandRecord(record: R, rowNum: Int): Boolean
    val canExpandRecordProperty: String
    var canExpandRecords: Boolean
    val canFocusInEmptyGrid: Boolean
    var canFreezeFields: Boolean
    var canGroupBy: Boolean
    var canMultiGroup: Boolean
    var canMultiSort: Boolean
    var canPickFields: Boolean
    val canPickOmittedFields: Boolean
    val canRemoveRecords: Boolean
    var canReorderFields: Boolean
    var canReorderRecords: Boolean
    var canResizeFields: Boolean
    var canSelectAll: Boolean
    def canSelectCell(rowNum: Int, colNum: Int): Boolean
    val canSelectCells: Boolean
    var canSelectGroups: Boolean
    def canSelectRecord(record: R): Boolean
    var canSort: Boolean
    val canTabToHeader: Boolean
    val cellChanged: js.Function5[R, JSAny, Int, Int, Grid[T, R], void]
    val cellClick: js.Function3[R, Int, Int, Boolean]
    val cellContextClick: js.Function3[R, Int, Int, Boolean]
    val cellContextMenu: Layout with AutoChild
    val cellDoubleClick: js.Function3[R, Int, Int, Boolean]
    val cellErrorIconHover: js.Function3[R, Int, Int, Boolean]
    def cellHasChanges(rowNum: Int, colNum: Int): Boolean
    def cellHasErrors(rowNum: Int, fieldID: Int | String): Boolean
    var cellHeight: Int
    val cellHover: js.Function3[R, Int, Int, Boolean]
    val cellHoverHTML: js.Function3[R, Int, Int, HTMLString]
    val cellMouseDown: js.Function3[R, Int, Int, Boolean]
    val cellMouseUp: js.Function3[R, Int, Int, Boolean]
    val cellOut: js.Function3[R, Int, Int, Boolean]
    val cellOver: js.Function3[R, Int, Int, Boolean]
    var cellPadding: Int
    val cellSelectionChanged: js.Function1[IscArray[JSAny], Boolean]
    val cellValueHover: js.Function3[R, Int, Int, Boolean]
    val cellValueHoverHTML: js.Function3[R, Int, Int, HTMLString]
    def cellValueIsClipped(rowNum: Int, colNum: Int): Boolean
    def chartColumn(dataColumn: String, labelColumn: String, chartProperties: Chart): Chart
    val chartConstructor: String
    def chartData(labelField: String, dataFields: IscArray[String] = js.native, dataRows: IscArray[R] = js.native, chartProperties: FacetChart = js.native, labelFieldFirst: Boolean = js.native): FacetChart
    def chartRow(rowNum: Int, dataFields: IscArray[String], chartProperties: Chart = js.native): Chart
    val chartType: ChartType
    val checkboxField: T with AutoChild
    var checkboxFieldFalseImage: SCImgURL
    val checkboxFieldHSpace: Int
    val checkboxFieldImageHeight: Int
    val checkboxFieldImageWidth: Int
    var checkboxFieldPartialImage: SCImgURL
    var checkboxFieldTrueImage: SCImgURL
    var childExpansionMode: ExpansionMode
    var clearAllSortingText: String
    def clearCriteria(callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    def clearEditValue(editValuesID: String | JSObject, colNum: Int | String): void
    def clearFieldError(rowNum: Int, fieldName: Int | String): void
    var clearFilterText: String
    def clearRowErrors(rowNum: Int): void
    def clearSort(): void
    var clearSortFieldText: String
    val clipHeaderTitles: Boolean
    def closeGroup(record: Record): Boolean
    val collapseGroupOnRowClick: Boolean
    def collapseRecord(record: R): void
    def collapseRecords(records: IscArray[R]): void
    def configureGrouping(): void
    var configureGroupingText: String
    var configureSortText: String
    var confirmCancelEditing: Boolean
    var confirmDiscardEdits: Boolean
    var confirmDiscardEditsMessage: String
    def createRecordComponent(record: R, colNum: Int): JSUndefined[Canvas]
    var dataArrived: js.ThisFunction2[classHandler, Int, Int, void]
    var dataProperties: ResultSet
    var dateFormatter: DateDisplayFormat
    var dateInputFormat: DateInputFormat
    var datetimeFormatter: DateDisplayFormat
    val defaultFields: IscArray[T]
    val deferRemoval: Boolean
    var detailDS: String
    var detailField: String
    def discardAllEdits(rows: IscArray[Int] = js.native, dontHideEditor: Boolean = js.native): void
    def discardEdits(rowNum: Int, colNum: Int, dontHideEditor: Boolean = js.native): void
    var discardEditsOnHideField: Boolean
    var discardEditsSaveButtonTitle: String
    def displayHeaderContextMenu(target: Canvas, position: IscArray[Int] = js.native): void
    def displaySort(sortSpecifiers: IscArray[SortSpecifier]): void
    val dragTrackerMode: DragTrackerMode
    var drawAheadRatio: Double
    var drawAllMaxCells: Int
    var drawAreaChanged: js.ThisFunction4[classHandler, Int, Int, Int, Int, void]
    var editByCell: Boolean
    var editComplete: js.ThisFunction6[classHandler, Int, Int, Record, Record, EditCompletionEvent, DSResponse, void]
    var editEvent: ListGridEditEvent
    var editFailed: js.ThisFunction6[classHandler, Int, Int, Record, Record, EditCompletionEvent, DSResponse, void]
    var editFailedBaseStyle: CSSStyleName
    var editFailedCSSText: String
    var editOnF2Keypress: Boolean
    var editOnFocus: Boolean
    var editorEnter: js.ThisFunction4[classHandler, R, JSAny, Int, Int, void]
    var editorExit: js.ThisFunction5[classHandler, EditCompletionEvent, R, JSAny, Int, Int, Boolean]
    val editPendingBaseStyle: CSSStyleName
    var editPendingCSSText: String
    var emptyCellValue: HTMLString
    var emptyMessage: HTMLString
    var emptyMessageStyle: CSSStyleName
    def endEditing(): void
    var enforceVClipping: Boolean
    var enterKeyEditAction: EnterKeyEditAction
    val enumCriteriaAsInitialValues: Boolean
    var errorIconHeight: Int
    var errorIconSrc: SCImgURL
    var errorIconWidth: Int
    var escapeKeyEditAction: EscapeKeyEditAction
    def expandRecord(record: R): void
    def expandRecords(record: IscArray[R]): void
    var expansionCanEdit: Boolean
    var expansionComponentPoolingMode: ExpansionComponentPoolingMode
    val expansionDetailField: HTMLFlow with MultiAutoChild
    val expansionDetailRelated: HLayout with MultiAutoChild
    val expansionDetails: DetailViewer with MultiAutoChild
    val expansionEditor: DynamicForm with MultiAutoChild
    var expansionEditorCollapseOnSave: Boolean
    val expansionEditorSaveButton: IButton with MultiAutoChild
    var expansionEditorSaveButtonTitle: String
    val expansionEditorSaveDialogPrompt: String
    val expansionEditorShowSaveDialog: Boolean
    var expansionField: Grid[T, R] with AutoChild
    var expansionFieldFalseImage: SCImgURL
    val expansionFieldImageHeight: Int
    val expansionFieldImageWidth: Int
    var expansionFieldTrueImage: SCImgURL
    val expansionLayout: VLayout with MultiAutoChild
    var expansionMode: ExpansionMode
    val expansionRelated: Grid[T, R] with MultiAutoChild
    val exportAlternateRowBGColor: CSSColor
    def exportClientData(requestProperties: DSRequest = js.native, callback: RPCCallback): void
    val exportDefaultBGColor: CSSColor
    var exportFieldAlignments: Boolean
    var exportFieldWidths: Boolean
    var exportHeaderHeights: Boolean
    val exportRawValues: Boolean
    var exportWidthScale: Double
    var exportWrapHeaderTitles: Boolean
    val fastCellUpdates: Boolean
    def fetchData(criteria: Criteria = js.native, callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    var fetchDelay: Int
    def fetchRelatedData(record: R, schema: Canvas | DataSource | String, callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    def fieldIsEditable(field: T | Int | String): Boolean
    def fieldIsVisible(field: T | String): Boolean
    val fieldPickerFieldProperties: IscArray[String]
    val fieldPickerShowSampleValues: String
    var fields: IscArray[T]
    var fieldState: String
    var fieldStateChanged: js.ThisFunction0[void, void]
    var fieldVisibilitySubmenuTitle: String
    val filterButtonPrompt: String
    val filterButtonProperties: IscArray[Button]
    var filterByCell: Boolean
    def filterByEditor(): void
    def filterData(criteria: Criteria = js.native, callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    val filterEditor: RecordEditor with AutoChild
    var filterEditorHeight: Int
    val filterEditorProperties: RecordEditor
    var filterEditorSubmit: js.ThisFunction1[classHandler, Criteria, Boolean]
    val filterLocalData: Boolean
    var filterOnKeypress: Boolean
    def findNextEditCell(rowNum: Int, colNum: Int, direction: Int, stepThroughFields: Boolean, checkStartingCell: Boolean = js.native): IscArray[Int]
    var fixedFieldWidths: Boolean
    var fixedRecordHeights: Boolean
    def focusInCell(row: Int, col: Int): void
    def focusInFilterEditor(fieldName: String): void
    def focusInRow(row: Int): void
    var formatCellValue: js.ThisFunction3[classHandler, R, Int, Int, HTMLString]
    var formatEditorValue: js.ThisFunction3[classHandler, R, Int, Int, JSAny]
    var formulaBuilderSpanTitleSeparator: String
    def formulaUpdated(field: T, formula: UserFormula): void
    var freezeFieldText: HTMLString
    var freezeOnLeftText: String
    var freezeOnRightText: String
    var frozenBaseStyle: String
    val frozenHeaderBaseStyle: CSSStyleName
    val frozenHeaderTitleStyle: CSSStyleName
    var generateClickOnEnter: Boolean
    var generateClickOnSpace: Boolean
    var generateDoubleClickOnEnter: Boolean
    var generateDoubleClickOnSpace: Boolean
    def getAllEditRows(): IscArray[Int]
    def getAllFields(): IscArray[T]
    var getBaseStyle: js.ThisFunction3[classHandler, R, Int, Int, CSSStyleName]
    def getBody(): GridRenderer
    def getCellAlign(record: R, rowNum: Int, colNum: Int): Alignment
    def getCellCSSText(record: R, rowNum: Int, colNum: Int): String
    def getCellErrors(rowNum: Int, fieldName: String | Int): IscArray[String]
    var getCellHoverComponent: js.ThisFunction3[classHandler, R, Int, Int, Canvas]
    def getCellPageRect(record: R, rowNum: Int, colNum: Int): IscArray[Int]
    var getCellRecord: js.ThisFunction1[classHandler, Int, R]
    def getCellRowSpan(record: R, rowNum: Int, colNum: Int): Int
    def getCellSelection(): CellSelection
    def getCellStartRow(record: R, rowNum: Int, colNum: Int): Int
    var getCellStyle: js.ThisFunction3[classHandler, R, Int, Int, CSSStyleName]
    def getCellVAlign(record: R, rowNum: Int, colNum: Int): VerticalAlignment
    var getCellValue: js.ThisFunction3[classHandler, R, Int, Int, String]
    def getCheckboxField(): T
    def getColumnLeft(colNum: Int): Int
    def getColumnPageLeft(colNum: Int): Int
    def getColumnWidth(colNum: Int): Int
    def getCriteria(): Criteria
    def getCurrentExpansionComponent(record: R | Int): Canvas
    def getCurrentFieldWidths(): IscArray[Int]
    def getDefaultFieldWidth(field: T): Int
    def getDefaultFormattedFieldValue(record: R, field: T, rowNum: Int = js.native, colNum: Int = js.native): String
    def getDefaultFormattedValue(record: R, rowNum: Int, colNum: Int): String
    def getDisplayValue(fieldID: String | Int | T, valueFieldValue: JSAny): String
    var getDragData: js.Function1[DataBoundComponent, IscArray[R]]
    def getDragTrackerIcon(records: IscArray[R]): String
    def getDragTrackerTitle(record: R, rowNum: Int): String
    def getDrawArea(): IscArray[Int]
    def getDrawnRowHeight(rowNum: Int): Int
    def getDrawnRows(): IscArray[Int]
    def getDropIndex(recordNum: Int = js.native, reorderPosition: ReorderPosition = js.native): Int
    def getEditCol(): Int
    def getEditedCell(record: R, field: Int | String): JSAny
    def getEditedRecord(valuesID: Int | JSObject): JSObject
    def getEditField(): JSObject
    def getEditFormItem(field: String | Int): FormItem
    var getEditorProperties: js.Function3[T, R, Int, JSObject]
    def getEditorType(field: T, values: JSObject): String
    def getEditorValueIcons(field: T, values: JSObject): JSObject
    def getEditorValueMap(field: T, values: JSObject): ValueMap
    def getEditRow(): Int
    def getEditValue(rowNum: Int, colNum: Int): JSAny
    def getEditValues(valuesID: JSObject | Int): JSObject
    def getEventColumn(x: Int): Int
    def getEventRow(y: Int): Int
    def getExpandedRecords(): IscArray[R]
    def getExpansionComponent(record: R): Canvas
    def getExpansionField(): T
    def getExportBGColor(rowNum: Int, colNum: Int, record: R): CSSColor
    def getExportColumnBGColor(colNum: Int): CSSColor
    def getExportRowBGColor(rowNum: Int, record: R): CSSColor
    def getField(colNum: Int): T
    def getFieldByName(fieldName: String): T
    def getFieldContentWidth(field: T): Int
    def getFieldName(colNum: Int): String
    def getFieldState(): State
    def getFieldTitle(fieldId: String | Int): String
    def getFieldWidth(fieldNum: Int | String): Int
    def getFilterEditorCriteria(omitHiddenFields: Boolean = js.native): Criteria | AdvancedCriteria
    def getFilterEditorType(field: T): String
    def getFilterEditorValueMap(field: T): ValueMap
    def getFocusRow(): Int
    def getFormulaFieldValue(field: T, record: R): Double | String
    def getGridSummary(field: T): JSAny
    def getGridSummaryData(): IscArray[R]
    def getGridSummaryFunction(field: T): SummaryFunction
    def getGroupByFields(): IscArray[String]
    def getGroupByText(field: T): HTMLString
    def getGroupNodeHTML(node: JSObject): void
    def getGroupState(): ListGridGroupState
    def getGroupSummaryData(records: IscArray[R], groupNode: R): IscArray[R]
    def getGroupTreeSelection(excludePartialSelection: Boolean = js.native, groupNodesOnly: Boolean = js.native): IscArray[R]
    def getHeaderContextMenuItems(fieldNum: Int): IscArray[MenuItem]
    def getHeaderSpanContextMenuItems(headerSpan: HeaderSpan): IscArray[MenuItem]
    def getOriginalData(): JSObject
    def getRecord(recordNum: Int): R
    def getRecordComponent(rowNum: Int, colNum: Int = js.native): Canvas
    def getRecordDropPosition(): RecordDropPosition
    def getRecordIndex(record: R): Int
    def getRecordSummary(record: R, field: T): JSAny
    def getRelatedDataSource(record: R): DataSource
    def getRequiredFieldMessage(field: T, record: R): String
    def getRollOverCanvas(rowNum: Int, colNum: Int): Canvas
    def getRollUnderCanvas(rowNum: Int, colNum: Int): Canvas
    def getRowErrors(rowNum: Int): JSObject
    var getRowHeight: js.Function2[Int, Int, Int]
    def getRowNum(record: R): Int
    def getRowPageTop(rowNum: Int): Int
    var getRowSpan: js.Function3[R, Int, Int, Int]
    def getRowTop(rowNum: Int): Int
    def getSelectedCellData(): IscArray[R]
    def getSelectedRecord(): R
    def getSelectedRecords(excludePartialSelections: Boolean = js.native): IscArray[R]
    def getSelectedState(): ListGridSelectedState
    def getSelection(excludePartialSelections: Boolean = js.native): IscArray[R]
    def getSortByGroupFirst(): void
    def getSortField(): String
    def getSortFieldCount(): Int
    def getSortNumeralHTML(fieldName: String, sortIndex: Int): HTMLString
    def getSortSpecifier(fieldName: String): SortSpecifier
    def getSortState(): ListGridSortState
    def getSummaryFieldValue(field: T, record: R): String
    def getSummaryTitle(field: T): String
    def getToggleFreezeText(field: T): HTMLString
    def getTotalRows(): Int
    def getValueIcon(field: T, value: JSAny, record: R): SCImgURL
    def getViewState(): ListGridViewState
    def getVisibleRows(): IscArray[Int]
    val gridComponents: IscArray[ListGridComponent] | IscArray[JSAny]
    var gridSummaryRecordProperty: String
    def groupBy(arguments: JSDictionaryAny = js.native): void
    var groupByAsyncThreshold: Int
    var groupByComplete: js.Function1[IscArray[String], void]
    val groupByField: IscArray[String] | String
    var groupByFieldSummaries: IscArray[String]
    var groupByMaxRecords: Int
    var groupByText: HTMLString
    var groupIcon: SCImgURL
    var groupIconSize: Int
    var groupIndentSize: Int
    var groupLeadingIndent: Int
    var groupNodeBaseStyle: String
    var groupNodeStyle: String
    var groupSortDirection: SortDirection
    var groupStartOpen: IscArray[GroupStartOpen] | GroupStartOpen
    var groupState: ListGridGroupState
    var groupStateChanged: js.ThisFunction0[classHandler, void]
    var groupSummaryRecordProperty: String
    var groupSummaryStyle: CSSStyleName
    val groupTitleColumnDefaults: T
    val groupTitleColumnProperties: T
    val groupTitleField: String
    val groupTree: Tree with AutoChild
    var handleGroupBy: js.Function1[IscArray[String], Boolean]
    def hasChanges(): Boolean
    def hasErrors(): Boolean
    val header: Layout with AutoChild
    val headerAutoFitEvent: AutoFitEvent
    val headerBackgroundColor: CSSColor
    val headerBarStyle: CSSStyleName
    val headerBaseStyle: CSSStyleName
    val headerButtonConstructor: Class
    val headerButtonDefaults: Button
    val headerButtonProperties: Button
    var headerClick: js.Function1[Int, void]
    val headerContextMenu: Canvas with AutoChild
    var headerDoubleClick: js.Function1[Int, void]
    var headerHeight: Int
    var headerHover: js.Function1[Int, void]
    var headerHoverAlign: Alignment
    var headerHoverHeight: Int
    def headerHoverHTML(fieldNum: Int, defaultHTML: HTMLString): HTMLString
    var headerHoverOpacity: Int
    var headerHoverStyle: CSSStyleName
    var headerHoverVAlign: VerticalAlignment
    var headerHoverWidth: Int
    var headerHoverWrap: Boolean
    var headerMenuButton: StatefulCanvas with AutoChild
    val headerMenuButtonConstructor: SCClassName
    val headerMenuButtonHeight: String
    val headerMenuButtonIcon: SCImgURL
    val headerMenuButtonIconHeight: Int
    val headerMenuButtonIconWidth: Int
    val headerMenuButtonWidth: Int
    var headerSpan: StatefulCanvas with MultiAutoChild
    val headerSpanConstructor: SCClassName
    val headerSpanHeight: Int
    var headerSpans: IscArray[HeaderSpan]
    val headerSpanVAlign: VerticalAlignment
    def headerTitleClipped(fieldNum: Int): Boolean
    val headerTitleStyle: SCClassName
    var hideEmptySummaryRow: Boolean
    def hideField(field: String | T, suppressRelayout: Boolean = js.native): void
    def hideFields(fields: IscArray[String] | IscArray[T], suppressRelayout: Boolean = js.native): void
    val hiliteCanReplaceValue: Boolean
    var hiliteEditorSpanTitleSeparator: String
    val hiliteHTMLAfterFormat: Boolean
    val hiliteReplaceValueFieldTitle: String
    var hiliteRowOnFocus: Boolean
    var hilitesChanged: js.ThisFunction0[classHandler, void]
    var hoverMode: HoverMode
    var iconPadding: Int
    var imageSize: Int
    var includeInSummaryProperty: String
    val initialSort: IscArray[SortSpecifier]
    var instantScrollTrackRedraw: Boolean
    def invalidateCache(): void
    def invalidateRecordComponents(): void
    var invalidSummaryValue: String
    def isCheckboxField(field: T): Boolean
    def isExpanded(record: R): Boolean
    def isExpansionField(field: T): Boolean
    val isGrouped: Boolean
    def isGroupNode(record: R): Boolean
    def isPartiallySelected(record: R): Boolean
    def isRowNumberField(field: T): Boolean
    def isSelected(record: R): Boolean
    var isSeparatorProperty: String
    def isSortField(fieldName: String): Boolean
    var leaveHeaderMenuButtonSpace: Boolean
    var linkTextProperty: Boolean
    var listEndEditAction: RowEndEditAction
    var loadingDataMessage: HTMLString
    var loadingDataMessageStyle: CSSStyleName
    val loadingMessage: String
    var locateColumnsBy: String
    var locateRowsBy: String
    var longTextEditorThreshold: Int
    var longTextEditorType: String
    def markRecordRemoved(rowNum: Int): void
    def markRecordsRemoved(records: IscArray[R] | IscArray[Int]): void
    def markSelectionRemoved(): void
    var maxExpandedRecords: Int
    val maxExpandedRecordsPrompt: HTMLString
    var minFieldWidth: Int
    var modalEditing: Boolean
    var navigateOnTab: Boolean
    var neverValidate: Boolean
    val normalBaseStyle: CSSStyleName
    var normalCellHeight: Int
    var nullGroupTitle: String
    var offlineMessageStyle: CSSStyleName
    def openGroup(record: R): Boolean
    var originalData: JSObject
    var originBaseStyle: CSSStyleName
    var parseEditorValue: js.Function4[JSAny, R, Int, Int, JSAny]
    var poolComponentsPerColumn: Boolean
    var printAutoFit: Boolean
    var printBaseStyle: CSSStyleName
    var printBooleanBaseStyle: CSSStyleName
    var printBooleanFalseImage: SCImgURL
    var printBooleanPartialImage: SCImgURL
    var printBooleanTrueImage: SCImgURL
    var printCheckboxFieldFalseImage: SCImgURL
    var printCheckboxFieldPartialImage: SCImgURL
    var printCheckboxFieldTrueImage: SCImgURL
    var printHeaderStyle: CSSStyleName
    var printMaxRows: Int
    var printWrapCells: Boolean
    var quickDrawAheadRatio: Double
    def recalculateGridSummary(): void
    def recalculateSummaries(records: IscArray[R] = js.native, fields: IscArray[T] = js.native): void
    var recordBaseStyleProperty: String
    var recordCanRemoveProperty: String
    var recordCanSelectProperty: String
    var recordClick: js.ThisFunction7[classHandler, Grid[T, R], R, Int, T, Int, JSAny, JSAny, Boolean]
    var recordComponentHeight: Int
    var recordComponentPoolingMode: RecordComponentPoolingMode
    var recordComponentPosition: EmbeddedPosition
    var recordCustomStyleProperty: String
    var recordDetailDSProperty: String
    var recordDoubleClick: js.ThisFunction7[classHandler, Grid[T, R], R, Int, T, Int, JSAny, JSAny, Boolean]
    def recordDrop(dropRecords: IscArray[R], targetRecord: R, index: Int, sourceWidget: Canvas): void
    var recordDropAppearance: RecordDropAppearance
    var recordEditProperty: String
    val recordEnabledProperty: String
    def recordMarkedAsRemoved(rowNum: Int): Boolean
    var recordSummaryBaseStyle: CSSStyleName
    def redrawHeader(rightNow: Boolean): void
    def refreshCell(rowNum: Int, colNum: Int): void
    def refreshCellStyle(rowNum: Int, colNum: Int): void
    def refreshData(callback: DSCallback = js.native): void
    def refreshFields(): void
    def refreshRecordComponent(rowNum: Int, colNum: Int = js.native): void
    def refreshRow(rowNum: Int): void
    def regroup(): void
    def removeData(data: R, callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    var removedCSSText: String
    def removeEmbeddedComponent(record: R | Canvas, component: Canvas = js.native): void
    val removeFieldDefaults: T
    val removeFieldProperties: T
    var removeFieldTitle: String
    val removeIcon: SCImgURL
    var removeIconSize: Int
    var removeRecordClick: js.ThisFunction1[classHandler, Int, void]
    def removeSelectedData(callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    def reorderField(fieldNum: Int, moveToPosition: Int): void
    def reorderFields(start: Int, end: Int, moveDelta: Int): void
    val reselectOnUpdate: Boolean
    var reselectOnUpdateNotifications: SelectionNotificationType
    def resizeField(fieldNum: Int, newWidth: Int): void
    var resizeFieldsInRealTime: Boolean
    def resort(): void
    var reverseRTLAlign: Boolean
    val rollOverCanvas: Canvas with AutoChild
    val rollUnderCanvas: Canvas with AutoChild
    var rowClick: js.Function4[R, Int, Int, Boolean, Boolean]
    var rowContextClick: js.Function3[R, Int, Int, Boolean]
    var rowDoubleClick: js.Function4[R, Int, Int, Boolean, Boolean]
    var rowEditorEnter: js.Function3[R, JSObject, Int, void]
    var rowEditorExit: js.Function4[EditCompletionEvent, R, JSObject, Int, Boolean]
    var rowEndEditAction: RowEndEditAction
    def rowHasChanges(rowNum: Int): Boolean
    def rowHasErrors(rowNum: Int): Boolean
    var rowHover: js.Function3[R, Int, Int, Boolean]
    var rowMouseDown: js.Function3[R, Int, Int, Boolean]
    var rowMouseUp: js.Function3[R, Int, Int, Boolean]
    var rowNumberField: T with AutoChild
    var rowNumberStart: Int
    var rowNumberStyle: CSSStyleName
    var rowOut: js.Function3[R, Int, Int, Boolean]
    var rowOver: js.Function3[R, Int, Int, Boolean]
    var rowSpanEditMode: RowSpanEditMode
    var rowSpanSelectionMode: RowSpanSelectionMode
    def saveAllEdits(rows: IscArray[Int] = js.native, saveCallback: Callback = js.native): Boolean
    var saveByCell: Boolean
    def saveEdits(editCompletionEvent: EditCompletionEvent = js.native, callback: js.Function4[Int, Int, EditCompletionEvent, Boolean, void] = js.native, rowNum: Int = js.native): void
    val saveLocally: Boolean
    var saveRequestProperties: DSRequest
    var newRequestProperties: JSUndefined[js.Function0[DSRequest]]
    var editRequestProperties: JSUndefined[js.Function0[DSRequest]]
    var editingFields: JSUndefined[IscArray[FormItem]]
    val screenReaderCellSeparator: HTMLString
    val screenReaderRowSeparator: HTMLString
    var scrollRedrawDelay: Int
    def scrollToCell(rowNum: Int, colNum: Int, xPosition: Alignment = js.native, yPosition: VerticalAlignment = js.native): void
    var scrollToCellXPosition: Alignment
    var scrollToCellYPosition: VerticalAlignment
    def scrollToColumn(colNum: Int, xPosition: Alignment = js.native): void
    def scrollToRow(rowNum: Int, yPosition: VerticalAlignment = js.native): void
    var scrollWheelRedrawDelay: Int
    def selectAllRecords(visibleNodesOnly: Boolean = js.native): void
    def selectFirstRecord(): void
    var selectedState: ListGridSelectedState
    var selectHeaderOnSort: Boolean
    val selection: Selection
    var selectionAppearance: SelectionAppearance
    var selectionCanvas: Canvas with AutoChild
    var selectionChanged: js.Function2[R, Boolean, _]
    val selectionProperty: String
    var selectionType: SelectionStyle
    val selectionUnderCanvas: Canvas with AutoChild
    var selectionUpdated: js.Function2[R, IscArray[R], _]
    var selectOnEdit: Boolean
    def selectRecord(record: R, newState: Boolean = js.native): void
    def selectRecords(records: IscArray[R], newState: Boolean = js.native): void
    def selectSingleRecord(record: R): void
    def setAlternateBodyStyleName(styleName: CSSStyleName): void
    def setAlternateRecordStyles(alternateStyles: Boolean): void
    def setAutoFitData(autoFitData: String): void
    def setAutoFitExtraRecords(extraRecords: Int): void
    def setAutoFitFieldWidths(autoFit: Boolean, dontResetWidths: Boolean = js.native): void
    def setAutoFitMaxColumns(maxColumns: Int): void
    def setAutoFitMaxHeight(height: Int): void
    def setAutoFitMaxRecords(maxRecords: Int): void
    def setAutoFitMaxWidth(width: Int): void
    def setAutoFitWidth(fieldName: String, autoFit: Boolean): void
    def setAutoFitWidthApproach(approach: AutoFitWidthApproach): void
    def setBodyOverflow(overflow: Overflow): void
    def setBodyStyleName(styleName: CSSStyleName): void
    def setCanExpandRecords(canExpand: Boolean): void
    def setCanFreezeFields(canFreeze: Boolean): void
    def setCanReorderRecords(canReorderRecords: Boolean): void
    def setCanResizeFields(canResize: Boolean): void
    def setCriteria(criteria: Criteria | AdvancedCriteria): void
    def setData(newData: IscArray[R]): classHandler
    def setDataSource(dataSource: DataSource, fields: IscArray[T]): void
    def setDontAutoDestroyComponent(component: Canvas, dontAutoDestroy: Boolean): void
    def setEditorValueMap(fieldID: JSObject | Int | String, map: JSObject): void
    def setEditValue(rowNum: Int, colNum: Int, value: JSAny): void
    def setEditValues(rowNum: Int, values: JSObject): void
    def setFastCellUpdates(fastCellUpdates: Boolean): void
    def setFieldButtonProperties(name: String, properties: Canvas): void
    def setFieldCellIcon(fieldName: String, cellIcon: SCImgURL): void
    def setFieldError(rowNum: Int, fieldName: Int, errorMessage: String): void
    def setFieldHeaderBaseStyle(name: String, newStyle: CSSStyleName): void
    def setFieldHeaderTitleStyle(name: String, newStyle: CSSStyleName): void
    def setFieldIcon(fieldName: String, icon: SCImgURL): void
    def setFieldProperties(fieldNum: String, properties: T): void
    def setFields(newFields: IscArray[T] = js.native): void
    def setFieldState(fieldState: State): void
    def setFieldTitle(fieldNum: Int | String, title: String): void
    def setFilterEditorCriteria(criteria: Criteria | AdvancedCriteria): void
    def setGroupByFieldSummaries(groupByFieldSummaries: IscArray[String]): void
    def setGroupState(groupState: ListGridGroupState): void
    def setHeaderHeight(height: Int): void
    def setHeaderSpanBaseStyle(name: String, newStyle: CSSStyleName): void
    def setHeaderSpanButtonProperties(name: String, properties: Canvas): void
    def setHeaderSpanHeaderTitle(name: String, newTitle: String): void
    def setHeaderSpans(headerSpans: IscArray[HeaderSpan]): void
    def setHeaderSpanTitle(name: String, newTitle: String): void
    def setHeaderSpanTitleStyle(name: String, newTitle: CSSStyleName): void
    def setRecordComponentHeight(height: Int): void
    def setRowErrors(rowNum: Int, errors: JSAny): void
    def setSelectedState(selectedState: ListGridSelectedState): void
    def setSelectionAppearance(selectionAppearance: SelectionAppearance): void
    def setSelectionType(selectionType: SelectionStyle): void
    def setShowCollapsedGroupSummary(showCollapsedGroupSummary: Boolean): void
    def setShowFilterEditor(value: Boolean): void
    def setShowGridSummary(showGridSummary: Boolean): void
    def setShowGroupSummary(showGroupSummary: Boolean): void
    def setShowGroupSummaryInHeader(showGroupSummaryInHeader: Boolean): void
    def setShowHeader(show: Boolean): void
    def setShowRecordComponents(showRecordComponents: Boolean): void
    def setSort(sortSpecifiers: IscArray[SortSpecifier]): void
    def setSortByGroupFirst(sortByGroupFirst: Boolean): void
    def setSortHandler(sortSpecifiers: IscArray[SortSpecifier]): void
    def setSortState(sortState: ListGridSortState): void
    def setUserFormula(field: T | String, userFormula: UserFormula = js.native): void
    def setUserFormulaText(field: T | String, text: String = js.native): void
    def setUserSummary(field: T | String, userSummary: UserSummary = js.native): void
    def setUserSummaryText(field: T | String, text: String = js.native): void
    def setValueMap(fieldID: String | Int, map: JSObject): void
    def setViewState(viewState: ListGridViewState): void
    var showAllColumns: Boolean
    var showAllRecords: Boolean
    var showAsynchGroupingPrompt: Boolean
    var showBackgroundComponents: Boolean
    var showCellContextMenus: Boolean
    var showClippedHeaderTitlesOnHover: Boolean
    var showClippedValuesOnHover: Boolean
    var showCollapsedGroupSummary: Boolean
    var showEmptyMessage: Boolean
    var showErrorIcons: Boolean
    var showExpansionEditorSaveButton: Boolean
    def showField(field: String | T, suppressRelayout: Boolean = js.native): void
    def showFields(fields: IscArray[String] | IscArray[T], suppressRelayout: Boolean = js.native): void
    var showFilterEditor: Boolean
    var showGridSummary: Boolean
    var showGroupSummary: Boolean
    var showGroupSummaryInHeader: Boolean
    var showGroupTitleColumn: Boolean
    var showHeader: Boolean
    var showHeaderContextMenu: Boolean
    var showHeaderMenuButton: Boolean
    var showHeaderSpanTitlesInFormulaBuilder: Boolean
    var showHeaderSpanTitlesInHiliteEditor: Boolean
    var showHeaderSpanTitlesInSortEditor: Boolean
    var showHilitesInGroupSummary: Boolean
    var showPartialSelection: Boolean
    var showRecordComponent: js.Function2[R, Int, Boolean]
    var showRecordComponents: Boolean
    var showRecordComponentsByCell: Boolean
    var showRollOver: Boolean
    var showRollOverCanvas: Boolean
    var showRollUnderCanvas: Boolean
    var showRowNumbers: Boolean
    var showSelectedStyle: Boolean
    var showSelectionCanvas: Boolean
    var showSelectionUnderCanvas: Boolean
    var showSortArrow: SortArrow
    var showSortNumerals: Boolean
    var showTreeColumnPicker: Boolean
    var shrinkForFreeze: Boolean
    var singleCellValueProperty: String
    def sort(sortField: String | Int = js.native, sortDirection: SortDirection = js.native): Boolean
    var sortAscendingImage: ImgProperties
    var sortByGroupFirst: Boolean
    var sortChanged: js.Function1[IscArray[SortSpecifier], void]
    var sortDescendingImage: ImgProperties
    var sortDirection: SortDirection
    var sortEditorSpanTitleSeparator: String
    var sorterButtonTitle: String
    var sorterClick: js.ThisFunction0[classHandler, Boolean]
    val sorterConstructor: Class
    var sorterContextClick: js.ThisFunction0[classHandler, Boolean]
    val sorterDefaults: JSObject
    val sorterProperties: Button
    val sortField: String
    var sortFieldAscendingText: String
    var sortFieldDescendingText: String
    var sortFieldNum: Int
    var sortNumeralStyle: CSSStyleName
    var sortState: ListGridSortState
    val spanContextMenu: Layout with AutoChild
    def startEditing(rowNum: Int = js.native, colNum: Int = js.native, suppressFocus: Boolean = js.native): Boolean
    def startEditingNew(newValues: JSObject | R = js.native, suppressFocus: Boolean = js.native): void
    var stopHover: js.ThisFunction0[classHandler, void]
    var stopOnErrors: Boolean
    val summaryRow: Grid[T, R] with AutoChild
    var summaryRowCriteria: Criteria
    val summaryRowDataSource: DataSource
    var summaryRowFetchRequestProperties: DSRequest
    val summaryRowHeight: Int
    var summaryRowStyle: CSSStyleName
    var summaryUpdated: js.ThisFunction2[classHandler, T, UserSummary, void]
    val tallBaseStyle: CSSStyleName
    var timeFormatter: TimeDisplayFormat
    def toggleFrozen(field: T | Int | String): void
    def toggleSort(fieldName: String): void
    var trackerImage: ImgProperties
    def unfreezeField(field: T | Int | String): void
    var unfreezeFieldText: HTMLString
    def ungroup(): void
    var ungroupText: String
    def unmarkRecordRemoved(rowNum: Int): void
    val unremoveIcon: SCImgURL
    def unsort(): void
    val unspannedHeaderVAlign: VerticalAlignment
    def updateData(updatedRecord: R, callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    def updateRecordComponent(record: R, colNum: Int, component: Canvas, recordChanged: Boolean): JSUndefined[Canvas]
    val useAdvancedFieldPicker: Boolean
    var useCellRollOvers: Boolean
    var useCopyPasteShortcuts: Boolean
    var useRemoteValidators: Boolean
    var useRowSpanStyling: Boolean
    def userSelectAllRecords(): void
    var validateByCell: Boolean
    def validateCell(rowNum: Int, fieldName: String | Int): Boolean
    var validateOnChange: Boolean
    def validateRow(rowNum: Int): Boolean
    var valueIconHeight: Int
    var valueIconLeftPadding: Int
    var valueIconRightPadding: Int
    var valueIconSize: Int
    var valueIconWidth: Int
    var viewState: Int
    var viewStateChanged: js.ThisFunction0[classHandler, void]
    val virtualScrolling: Boolean
    var waitForSave: Boolean
    var warnOnRemoval: Boolean
    var warnOnRemovalMessage: Boolean
    var warnOnUnmappedValueFieldChange: Boolean
    var wrapCells: Boolean
    var wrapHeaderTitles: Boolean
    def getRowNumSelectedGridRecord(): Int
    def setSelectionChanged(func: js.Function2[R, Boolean, _]): void
    def setSelectionUpdated(func: js.Function2[R, IscArray[R], _]): void
    def unsetSelectionChanged(func: js.Function2[R, Boolean, _]): void
    def unsetSelectionUpdated(func: js.Function2[R, IscArray[R], _]): void
    def setMasterGrid(grid: ListGrid | TreeGrid | ListGridEditor | TreeGridEditor, pkFieldNames: IscArray[MasterDetailMapping] | MasterDetailMapping = js.native): void
    def selectRecordsByKey(keyValues: JSObject, newState: Boolean = js.native): R
    @JSName("masterGrid")
    var masterListGrid: UndefOr[ListGrid]
    @JSName("masterGrid")
    var masterTreeGrid: UndefOr[TreeGrid]
}

@js.native
trait ListGrid extends Grid[ListGridField, ListGridRecord] {
    var data: IscArray[ListGridRecord]
}

@js.native
abstract trait AbstractListGridCompanion extends AbstractVLayoutCompanion {
}



