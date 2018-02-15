package com.simplesys.SmartClient.Grids.props

import com.simplesys.SmartClient.Control.{Button, IButton}
import com.simplesys.SmartClient.DataBinding._
import com.simplesys.SmartClient.DataBinding.props.{DataBoundComponentProps, SortSpecifierProps}
import com.simplesys.SmartClient.Forms.DynamicForm
import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.SmartClient.Forms.formsItems.props.FormItemProps
import com.simplesys.SmartClient.Foundation.canvas.ImgProperties
import com.simplesys.SmartClient.Foundation.{Canvas, GridRenderer, HTMLFlow, StatefulCanvas}
import com.simplesys.SmartClient.Grids.listGrid._
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.Grids.treeGrid.Tree
import com.simplesys.SmartClient.Grids.{Grid, ListGrid}
import com.simplesys.SmartClient.Layout.props.VLayoutProps
import com.simplesys.SmartClient.Layout._
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
import com.simplesys.System.Types.RowEndEditAction.RowEndEditAction
import com.simplesys.System.Types.RowSpanEditMode.RowSpanEditMode
import com.simplesys.System.Types.RowSpanSelectionMode.RowSpanSelectionMode
import com.simplesys.System.Types.SelectionAppearance.SelectionAppearance
import com.simplesys.System.Types.SelectionNotificationType.SelectionNotificationType
import com.simplesys.System.Types.SelectionStyle.SelectionStyle
import com.simplesys.System.Types.SortArrow.SortArrow
import com.simplesys.System.Types.SortDirection.SortDirection
import com.simplesys.System.Types.TextMatchStyle.TextMatchStyle
import com.simplesys.System.Types.TimeDisplayFormat.TimeDisplayFormat
import com.simplesys.System.Types.VerticalAlignment.VerticalAlignment
import com.simplesys.System.Types._
import com.simplesys.System._
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js
import scala.scalajs.js.{ThisFunction0, ThisFunction4}

class GridProps[T <: ListGridField, R <: ListGridRecord] extends VLayoutProps with DataBoundComponentProps {
    var advancedFieldPickerThreshold: ScOption[Int] = ScNone
    var allowFilterExpressions: ScOption[Boolean] = ScNone
    var allowRowSpanning: ScOption[Boolean] = ScNone
    var alternateBodyStyleName: ScOption[CSSStyleName] = ScNone
    var alternateFieldFrequency: ScOption[Int] = ScNone
    var alternateFieldStyles: ScOption[Boolean] = ScNone
    var alternateRecordFrequency: ScOption[Int] = ScNone
    var alternateRecordStyles: ScOption[Boolean] = ScNone
    var alwaysShowEditors: ScOption[Boolean] = ScNone
    var animateFolderEffect: ScOption[AnimationAcceleration] = ScNone
    var animateFolderMaxRows: ScOption[Int] = ScNone
    var animateFolders: ScOption[Boolean] = ScNone
    var animateFolderSpeed: ScOption[Int] = ScNone
    var animateFolderTime: ScOption[Int] = ScNone
    var animateRemoveRecord: ScOption[Boolean] = ScNone
    var animateRemoveSpeed: ScOption[Int] = ScNone
    var animateRemoveTime: ScOption[Int] = ScNone
    var animateRollOver: ScOption[Boolean] = ScNone
    var animateRollUnder: ScOption[Boolean] = ScNone
    var animateSelection: ScOption[Boolean] = ScNone
    var animateSelectionUnder: ScOption[Boolean] = ScNone
    var applyFormulaAfterSummary: ScOption[Boolean] = ScNone
    var applyRowNumberStyle: ScOption[Boolean] = ScNone
    var arrowKeyAction: ScOption[String] = ScNone
    var asynchGroupingPrompt: ScOption[HTMLString] = ScNone
    var autoComplete: ScOption[AutoComplete] = ScNone
    var autoConfirmSaveEdits: ScOption[Boolean] = ScNone
    var autoFetchDisplayMap: ScOption[Boolean] = ScNone
    var autoFetchTextMatchStyle: ScOption[TextMatchStyle] = ScNone
    var autoFitAllText: ScOption[String] = ScNone
    var autoFitClipFields: ScOption[Seq[String]] = ScNone
    var autoFitData: ScOption[Autofit] = ScNone
    var autoFitExpandField: ScOption[String] = ScNone
    var autoFitExtraRecords: ScOption[Int] = ScNone
    var autoFitFieldsFillViewport: ScOption[Boolean] = ScNone
    var autoFitFieldText: ScOption[String] = ScNone
    var autoFitFieldWidths: ScOption[Boolean] = ScNone
    var autoFitHeaderHeights: ScOption[Boolean] = ScNone
    var autoFitIconFields: ScOption[AutoFitIconFieldType] = ScNone
    var autoFitMaxColumns: ScOption[Int] = ScNone
    var autoFitMaxHeight: ScOption[Int] = ScNone
    var autoFitMaxRecords: ScOption[Int] = ScNone
    var autoFitMaxWidth: ScOption[Int] = ScNone
    var autoFitWidthApproach: ScOption[AutoFitWidthApproach] = ScNone
    var autoSaveEdits: ScOption[Boolean] = ScNone
    var autoSizeHeaderSpans: ScOption[Boolean] = ScNone
    var backgroundComponent: ScOption[Canvas with MultiAutoChild] = ScNone
    var baseStyle: ScOption[CSSStyleName] = ScNone
    var body: ScOption[GridRenderer with MultiAutoChild] = ScNone
    var bodyBackgroundColor: ScOption[String] = ScNone
    var bodyKeyPress: ScOption[js.ThisFunction0[classHandler, Boolean]] = ScNone
    var bodyOverflow: ScOption[Overflow] = ScNone
    var bodyStyleName: ScOption[CSSStyleName] = ScNone
    var booleanBaseStyle: ScOption[CSSStyleName] = ScNone
    var booleanFalseImage: ScOption[SCImgURL] = ScNone
    var booleanImageHeight: ScOption[Int] = ScNone
    var booleanImageWidth: ScOption[Int] = ScNone
    var booleanPartialImage: ScOption[SCImgURL] = ScNone
    var booleanTrueImage: ScOption[SCImgURL] = ScNone
    var canAcceptDroppedRecords: ScOption[Boolean] = ScNone
    var canAutoFitFields: ScOption[Boolean] = ScNone
    var cancelEditingConfirmationMessage: ScOption[String] = ScNone
    var canCollapseGroup: ScOption[Boolean] = ScNone
    var canDragRecordsOut: ScOption[Boolean] = ScNone
    var canDragSelect: ScOption[Boolean] = ScNone
    var canDragSelectText: ScOption[Boolean] = ScNone
    var canDropInEmptyArea: ScOption[Boolean] = ScNone
    var canEdit: ScOption[Boolean] = ScNone
    var canEditTitles: ScOption[Boolean] = ScNone
    var canExpandMultipleRecords: ScOption[Boolean] = ScNone
    var canExpandRecordProperty: ScOption[String] = ScNone
    var canExpandRecords: ScOption[Boolean] = ScNone
    var canFocusInEmptyGrid: ScOption[Boolean] = ScNone
    var canFreezeFields: ScOption[Boolean] = ScNone
    var canGroupBy: ScOption[Boolean] = ScNone
    var canMultiGroup: ScOption[Boolean] = ScNone
    var canMultiSort: ScOption[Boolean] = ScNone
    var canPickFields: ScOption[Boolean] = ScNone
    var canPickOmittedFields: ScOption[Boolean] = ScNone
    var canRemoveRecords: ScOption[Boolean] = ScNone
    var canReorderFields: ScOption[Boolean] = ScNone
    var canReorderRecords: ScOption[Boolean] = ScNone
    var canResizeFields: ScOption[Boolean] = ScNone
    var canSelectAll: ScOption[Boolean] = ScNone
    var canSelectCells: ScOption[Boolean] = ScNone
    var canSelectGroups: ScOption[Boolean] = ScNone
    var canSort: ScOption[Boolean] = ScNone
    var canTabToHeader: ScOption[Boolean] = ScNone
    var cellChanged: ScOption[js.Function5[R, JSAny, Int, Int, Grid[T, R], void]] = ScNone
    var cellClick: ScOption[js.Function3[R, Int, Int, Boolean]] = ScNone
    var cellContextClick: ScOption[js.Function3[R, Int, Int, Boolean]] = ScNone
    var cellContextMenu: ScOption[Layout] = ScNone
    var cellDoubleClick: ScOption[js.Function3[R, Int, Int, Boolean]] = ScNone
    var cellErrorIconHover: ScOption[js.Function3[R, Int, Int, Boolean]] = ScNone
    var cellHeight: ScOption[Int] = ScNone
    var cellHover: ScOption[js.Function3[R, Int, Int, Boolean]] = ScNone
    var cellHoverHTML: ScOption[js.Function3[R, Int, Int, HTMLString]] = ScNone
    var cellMouseDown: ScOption[js.Function3[R, Int, Int, Boolean]] = ScNone
    var cellMouseUp: ScOption[js.Function3[R, Int, Int, Boolean]] = ScNone
    var cellOut: ScOption[js.Function3[R, Int, Int, Boolean]] = ScNone
    var cellOver: ScOption[js.Function3[R, Int, Int, Boolean]] = ScNone
    var cellPadding: ScOption[Int] = ScNone
    var cellSelectionChanged: ScOption[js.Function1[IscArray[JSAny], Boolean]] = ScNone
    var cellValueHover: ScOption[js.Function3[R, Int, Int, Boolean]] = ScNone
    var cellValueHoverHTML: ScOption[js.Function3[R, Int, Int, HTMLString]] = ScNone
    var chartConstructor: ScOption[String] = ScNone
    var chartType: ScOption[ChartType] = ScNone
    var checkboxField: ScOption[T] = ScNone
    var checkboxFieldFalseImage: ScOption[SCImgURL] = ScNone
    var checkboxFieldHSpace: ScOption[Int] = ScNone
    var checkboxFieldImageHeight: ScOption[Int] = ScNone
    var checkboxFieldImageWidth: ScOption[Int] = ScNone
    var checkboxFieldPartialImage: ScOption[SCImgURL] = ScNone
    var checkboxFieldTrueImage: ScOption[SCImgURL] = ScNone
    var childExpansionMode: ScOption[ExpansionMode] = ScNone
    var clearAllSortingText: ScOption[String] = ScNone
    var clearFilterText: ScOption[String] = ScNone
    var clearSortFieldText: ScOption[String] = ScNone
    var clipHeaderTitles: ScOption[Boolean] = ScNone
    var collapseGroupOnRowClick: ScOption[Boolean] = ScNone
    var configureGroupingText: ScOption[String] = ScNone
    var configureSortText: ScOption[String] = ScNone
    var confirmCancelEditing: ScOption[Boolean] = ScNone
    var confirmDiscardEdits: ScOption[Boolean] = ScNone
    var confirmDiscardEditsMessage: ScOption[String] = ScNone
    var createRecordComponent: ScOption[js.ThisFunction2[classHandler, R, Int, Canvas]] = ScNone
    var dataArrived: ScOption[js.ThisFunction2[classHandler, Int, Int, void]] = ScNone
    var dataProperties: ScOption[ResultSet] = ScNone
    var dateFormatter: ScOption[DateDisplayFormat] = ScNone
    var dateInputFormat: ScOption[DateInputFormat] = ScNone
    var datetimeFormatter: ScOption[DateDisplayFormat] = ScNone
    var deferRemoval: ScOption[Boolean] = ScNone
    var detailDS: ScOption[String] = ScNone
    var detailField: ScOption[String] = ScNone
    var discardEditsOnHideField: ScOption[Boolean] = ScNone
    var discardEditsSaveButtonTitle: ScOption[String] = ScNone
    var dragTrackerMode: ScOption[DragTrackerMode] = ScNone
    var drawAheadRatio: ScOption[Double] = ScNone
    var drawAllMaxCells: ScOption[Int] = ScNone
    var drawAreaChanged: ScOption[js.ThisFunction4[classHandler, Int, Int, Int, Int, void]] = ScNone
    var editByCell: ScOption[Boolean] = ScNone
    var editComplete: ScOption[js.ThisFunction6[classHandler, Int, Int, Record, Record, EditCompletionEvent, DSResponse, void]] = ScNone
    var editEvent: ScOption[ListGridEditEvent] = ScNone
    var editFailed: ScOption[js.ThisFunction6[classHandler, Int, Int, Record, Record, EditCompletionEvent, DSResponse, void]] = ScNone
    var editFailedBaseStyle: ScOption[CSSStyleName] = ScNone
    var editFailedCSSText: ScOption[String] = ScNone
    var editOnF2Keypress: ScOption[Boolean] = ScNone
    var editOnFocus: ScOption[Boolean] = ScNone
    var editorEnter: ScOption[js.ThisFunction4[classHandler, R, JSAny, Int, Int, void]] = ScNone
    var editorExit: ScOption[js.ThisFunction5[classHandler, EditCompletionEvent, R, JSAny, Int, Int, Boolean]] = ScNone
    var editPendingBaseStyle: ScOption[CSSStyleName] = ScNone
    var editPendingCSSText: ScOption[String] = ScNone
    var emptyCellValue: ScOption[HTMLString] = ScNone
    var emptyMessage: ScOption[HTMLString] = ScNone
    var emptyMessageStyle: ScOption[CSSStyleName] = ScNone
    var enforceVClipping: ScOption[Boolean] = ScNone
    var enterKeyEditAction: ScOption[EnterKeyEditAction] = ScNone
    var enumCriteriaAsInitialValues: ScOption[Boolean] = ScNone
    var errorIconHeight: ScOption[Int] = ScNone
    var errorIconSrc: ScOption[SCImgURL] = ScNone
    var errorIconWidth: ScOption[Int] = ScNone
    var escapeKeyEditAction: ScOption[EscapeKeyEditAction] = ScNone
    var expansionCanEdit: ScOption[Boolean] = ScNone
    var expansionComponentPoolingMode: ScOption[ExpansionComponentPoolingMode] = ScNone
    var expansionDetailField: ScOption[HTMLFlow with MultiAutoChild] = ScNone
    var expansionDetailRelated: ScOption[HLayout with MultiAutoChild] = ScNone
    var expansionDetails: ScOption[DetailViewer with MultiAutoChild] = ScNone
    var expansionEditor: ScOption[DynamicForm with MultiAutoChild] = ScNone
    var expansionEditorCollapseOnSave: ScOption[Boolean] = ScNone
    var expansionEditorSaveButton: ScOption[IButton with MultiAutoChild] = ScNone
    var expansionEditorSaveButtonTitle: ScOption[String] = ScNone
    var expansionEditorSaveDialogPrompt: ScOption[String] = ScNone
    var expansionEditorShowSaveDialog: ScOption[Boolean] = ScNone
    var expansionField: ScOption[Grid[T, R]] = ScNone
    var expansionFieldFalseImage: ScOption[SCImgURL] = ScNone
    var expansionFieldImageHeight: ScOption[Int] = ScNone
    var expansionFieldImageWidth: ScOption[Int] = ScNone
    var expansionFieldTrueImage: ScOption[SCImgURL] = ScNone
    var expansionLayout: ScOption[VLayout with MultiAutoChild] = ScNone
    var expansionMode: ScOption[ExpansionMode] = ScNone
    var expansionRelated: ScOption[Grid[T, R] with MultiAutoChild] = ScNone
    var exportAlternateRowBGColor: ScOption[CSSColor] = ScNone
    var exportDefaultBGColor: ScOption[CSSColor] = ScNone
    var exportFieldAlignments: ScOption[Boolean] = ScNone
    var exportFieldWidths: ScOption[Boolean] = ScNone
    var exportHeaderHeights: ScOption[Boolean] = ScNone
    var exportRawValues: ScOption[Boolean] = ScNone
    var exportWidthScale: ScOption[Double] = ScNone
    var exportWrapHeaderTitles: ScOption[Boolean] = ScNone
    var fastCellUpdates: ScOption[Boolean] = ScNone
    var fetchDelay: ScOption[Int] = ScNone
    var fieldPickerFieldProperties: ScOption[Seq[String]] = ScNone
    var fieldPickerShowSampleValues: ScOption[String] = ScNone
    var fieldState: ScOption[String] = ScNone
    var fieldStateChanged: ScOption[js.ThisFunction0[void, void]] = ScNone
    var fieldVisibilitySubmenuTitle: ScOption[String] = ScNone
    var filterButtonPrompt: ScOption[String] = ScNone
    var filterButtonProperties: ScOption[Seq[Button]] = ScNone
    var filterByCell: ScOption[Boolean] = ScNone
    var filterEditor: ScOption[RecordEditor] = ScNone
    var filterEditorHeight: ScOption[Int] = ScNone
    var filterEditorProperties: ScOption[RecordEditor] = ScNone
    var filterEditorSubmit: ScOption[js.ThisFunction1[classHandler, Criteria, Boolean]] = ScNone
    var filterLocalData: ScOption[Boolean] = ScNone
    var filterOnKeypress: ScOption[Boolean] = ScNone
    var fixedFieldWidths: ScOption[Boolean] = ScNone
    var fixedRecordHeights: ScOption[Boolean] = ScNone
    var formatCellValue: ScOption[js.ThisFunction3[classHandler, R, Int, Int, HTMLString]] = ScNone
    var formatEditorValue: ScOption[js.ThisFunction3[classHandler, R, Int, Int, JSAny]] = ScNone
    var formulaBuilderSpanTitleSeparator: ScOption[String] = ScNone
    var freezeFieldText: ScOption[HTMLString] = ScNone
    var freezeOnLeftText: ScOption[String] = ScNone
    var freezeOnRightText: ScOption[String] = ScNone
    var frozenBaseStyle: ScOption[String] = ScNone
    var frozenHeaderBaseStyle: ScOption[CSSStyleName] = ScNone
    var frozenHeaderTitleStyle: ScOption[CSSStyleName] = ScNone
    var generateClickOnEnter: ScOption[Boolean] = ScNone
    var generateClickOnSpace: ScOption[Boolean] = ScNone
    var generateDoubleClickOnEnter: ScOption[Boolean] = ScNone
    var generateDoubleClickOnSpace: ScOption[Boolean] = ScNone
    var getBaseStyle: ScOption[js.ThisFunction3[classHandler, R, Int, Int, CSSStyleName]] = ScNone
    var getCellHoverComponent: ScOption[js.ThisFunction3[classHandler, R, Int, Int, Canvas]] = ScNone
    var getCellRecord: ScOption[js.ThisFunction1[classHandler, Int, R]] = ScNone
    var getCellStyle: ScOption[js.ThisFunction3[classHandler, R, Int, Int, CSSStyleName]] = ScNone
    var getCellValue: ScOption[js.ThisFunction3[classHandler, R, Int, Int, String]] = ScNone
    var getEditorProperties: ScOption[js.Function3[T, R, Int, JSObject]] = ScNone
    var getDragData: ScOption[js.Function1[DataBoundComponent, IscArray[R]]] = ScNone
    var getRowHeight: ScOption[js.Function2[Int, Int, Int]] = ScNone
    var getRowSpan: ScOption[js.Function3[R, Int, Int, Int]] = ScNone
    var gridComponents: ScOption[Seq[ListGridComponent]] = ScNone
    var gridSummaryRecordProperty: ScOption[String] = ScNone
    var groupByAsyncThreshold: ScOption[Int] = ScNone
    var groupByComplete: ScOption[js.Function1[IscArray[String], void]] = ScNone
    var groupByField: ScOption[Seq[String]] = ScNone
    var groupByFieldSummaries: ScOption[Seq[String]] = ScNone
    var groupByMaxRecords: ScOption[Int] = ScNone
    var groupByText: ScOption[HTMLString] = ScNone
    var groupIcon: ScOption[SCImgURL] = ScNone
    var groupIconSize: ScOption[Int] = ScNone
    var groupIndentSize: ScOption[Int] = ScNone
    var groupLeadingIndent: ScOption[Int] = ScNone
    var groupNodeBaseStyle: ScOption[String] = ScNone
    var groupNodeStyle: ScOption[String] = ScNone
    var groupSortDirection: ScOption[SortDirection] = ScNone
    var groupStartOpen: ScOption[Seq[GroupStartOpen]] = ScNone
    var groupState: ScOption[ListGridGroupState] = ScNone
    var groupStateChanged: ScOption[js.ThisFunction0[classHandler, void]] = ScNone
    var groupSummaryRecordProperty: ScOption[String] = ScNone
    var groupSummaryStyle: ScOption[CSSStyleName] = ScNone
    var groupTitleColumnDefaults: ScOption[T] = ScNone
    var groupTitleColumnProperties: ScOption[T] = ScNone
    var groupTitleField: ScOption[String] = ScNone
    var groupTree: ScOption[Tree] = ScNone
    var handleGroupBy: ScOption[js.Function1[IscArray[String], Boolean]] = ScNone
    var header: ScOption[Layout] = ScNone
    var headerAutoFitEvent: ScOption[AutoFitEvent] = ScNone
    var headerBackgroundColor: ScOption[CSSColor] = ScNone
    var headerBarStyle: ScOption[CSSStyleName] = ScNone
    var headerBaseStyle: ScOption[CSSStyleName] = ScNone
    var headerButtonConstructor: ScOption[Class] = ScNone
    var headerButtonDefaults: ScOption[Button] = ScNone
    var headerButtonProperties: ScOption[Button] = ScNone
    var headerClick: ScOption[js.Function1[Int, void]] = ScNone
    var headerContextMenu: ScOption[Canvas] = ScNone
    var headerDoubleClick: ScOption[js.Function1[Int, void]] = ScNone
    var headerHeight: ScOption[Int] = ScNone
    var headerHover: ScOption[js.Function1[Int, void]] = ScNone
    var headerHoverAlign: ScOption[Alignment] = ScNone
    var headerHoverHeight: ScOption[Int] = ScNone
    var headerHoverOpacity: ScOption[Int] = ScNone
    var headerHoverStyle: ScOption[CSSStyleName] = ScNone
    var headerHoverVAlign: ScOption[VerticalAlignment] = ScNone
    var headerHoverWidth: ScOption[Int] = ScNone
    var headerHoverWrap: ScOption[Boolean] = ScNone
    var headerMenuButton: ScOption[StatefulCanvas] = ScNone
    var headerMenuButtonConstructor: ScOption[SCClassName] = ScNone
    var headerMenuButtonHeight: ScOption[String] = ScNone
    var headerMenuButtonIcon: ScOption[SCImgURL] = ScNone
    var headerMenuButtonIconHeight: ScOption[Int] = ScNone
    var headerMenuButtonIconWidth: ScOption[Int] = ScNone
    var headerMenuButtonWidth: ScOption[Int] = ScNone
    var headerSpan: ScOption[StatefulCanvas with MultiAutoChild] = ScNone
    var headerSpanConstructor: ScOption[SCClassName] = ScNone
    var headerSpanHeight: ScOption[Int] = ScNone
    var headerSpans: ScOption[Seq[HeaderSpan]] = ScNone
    var headerSpanVAlign: ScOption[VerticalAlignment] = ScNone
    var headerTitleStyle: ScOption[SCClassName] = ScNone
    var hideEmptySummaryRow: ScOption[Boolean] = ScNone
    var hiliteCanReplaceValue: ScOption[Boolean] = ScNone
    var hiliteEditorSpanTitleSeparator: ScOption[String] = ScNone
    var hiliteHTMLAfterFormat: ScOption[Boolean] = ScNone
    var hiliteReplaceValueFieldTitle: ScOption[String] = ScNone
    var hiliteRowOnFocus: ScOption[Boolean] = ScNone
    var hilitesChanged: ScOption[js.ThisFunction0[classHandler, void]] = ScNone
    var hoverMode: ScOption[HoverMode] = ScNone
    var iconPadding: ScOption[Int] = ScNone
    var imageSize: ScOption[Int] = ScNone
    var includeInSummaryProperty: ScOption[String] = ScNone
    var initialSort: ScOption[Seq[SortSpecifierProps]] = ScNone
    var instantScrollTrackRedraw: ScOption[Boolean] = ScNone
    var invalidSummaryValue: ScOption[String] = ScNone
    var isGrouped: ScOption[Boolean] = ScNone
    var isSeparatorProperty: ScOption[String] = ScNone
    var leaveHeaderMenuButtonSpace: ScOption[Boolean] = ScNone
    var linkTextProperty: ScOption[Boolean] = ScNone
    var listEndEditAction: ScOption[RowEndEditAction] = ScNone
    var loadingDataMessage: ScOption[HTMLString] = ScNone
    var loadingDataMessageStyle: ScOption[CSSStyleName] = ScNone
    var loadingMessage: ScOption[String] = ScNone
    var locateColumnsBy: ScOption[String] = ScNone
    var locateRowsBy: ScOption[String] = ScNone
    var longTextEditorThreshold: ScOption[Int] = ScNone
    var longTextEditorType: ScOption[String] = ScNone
    var maxExpandedRecords: ScOption[Int] = ScNone
    var maxExpandedRecordsPrompt: ScOption[HTMLString] = ScNone
    var minFieldWidth: ScOption[Int] = ScNone
    var modalEditing: ScOption[Boolean] = ScNone
    var navigateOnTab: ScOption[Boolean] = ScNone
    var neverValidate: ScOption[Boolean] = ScNone
    var normalBaseStyle: ScOption[CSSStyleName] = ScNone
    var normalCellHeight: ScOption[Int] = ScNone
    var nullGroupTitle: ScOption[String] = ScNone
    var offlineMessageStyle: ScOption[CSSStyleName] = ScNone
    var originalData: ScOption[JSObject] = ScNone
    var originBaseStyle: ScOption[CSSStyleName] = ScNone
    var parseEditorValue: ScOption[js.Function4[JSAny, R, Int, Int, JSAny]] = ScNone
    var poolComponentsPerColumn: ScOption[Boolean] = ScNone
    var printAutoFit: ScOption[Boolean] = ScNone
    var printBaseStyle: ScOption[CSSStyleName] = ScNone
    var printBooleanBaseStyle: ScOption[CSSStyleName] = ScNone
    var printBooleanFalseImage: ScOption[SCImgURL] = ScNone
    var printBooleanPartialImage: ScOption[SCImgURL] = ScNone
    var printBooleanTrueImage: ScOption[SCImgURL] = ScNone
    var printCheckboxFieldFalseImage: ScOption[SCImgURL] = ScNone
    var printCheckboxFieldPartialImage: ScOption[SCImgURL] = ScNone
    var printCheckboxFieldTrueImage: ScOption[SCImgURL] = ScNone
    var printHeaderStyle: ScOption[CSSStyleName] = ScNone
    var printMaxRows: ScOption[Int] = ScNone
    var printWrapCells: ScOption[Boolean] = ScNone
    var quickDrawAheadRatio: ScOption[Double] = ScNone
    var recordBaseStyleProperty: ScOption[String] = ScNone
    var recordCanRemoveProperty: ScOption[String] = ScNone
    var recordCanSelectProperty: ScOption[String] = ScNone
    var recordClick: ScOption[js.ThisFunction7[classHandler, Grid[T, R], R, Int, T, Int, JSAny, JSAny, Boolean]] = ScNone
    var recordComponentHeight: ScOption[Int] = ScNone
    var recordComponentPoolingMode: ScOption[RecordComponentPoolingMode] = ScNone
    var recordComponentPosition: ScOption[EmbeddedPosition] = ScNone
    var recordCustomStyleProperty: ScOption[String] = ScNone
    var recordDetailDSProperty: ScOption[String] = ScNone
    var recordDoubleClick: ScOption[js.ThisFunction7[classHandler, Grid[T, R], R, Int, T, Int, JSAny, JSAny, Boolean]] = ScNone
    var recordDropAppearance: ScOption[RecordDropAppearance] = ScNone
    var recordEditProperty: ScOption[String] = ScNone
    var recordEnabledProperty: ScOption[String] = ScNone
    var recordSummaryBaseStyle: ScOption[CSSStyleName] = ScNone
    var removedCSSText: ScOption[String] = ScNone
    var removeFieldDefaults: ScOption[T] = ScNone
    var removeFieldProperties: ScOption[T] = ScNone
    var removeFieldTitle: ScOption[String] = ScNone
    var removeIcon: ScOption[SCImgURL] = ScNone
    var removeIconSize: ScOption[Int] = ScNone
    var removeRecordClick: ScOption[js.ThisFunction1[classHandler, Int, void]] = ScNone
    var reselectOnUpdate: ScOption[Boolean] = ScNone
    var reselectOnUpdateNotifications: ScOption[SelectionNotificationType] = ScNone
    var resizeFieldsInRealTime: ScOption[Boolean] = ScNone
    var reverseRTLAlign: ScOption[Boolean] = ScNone
    var rollOverCanvas: ScOption[Canvas] = ScNone
    var rollUnderCanvas: ScOption[Canvas] = ScNone
    var rowClick: ScOption[js.Function4[R, Int, Int, Boolean, Boolean]] = ScNone
    var rowContextClick: ScOption[js.Function3[R, Int, Int, Boolean]] = ScNone
    var rowDoubleClick: ScOption[js.Function4[R, Int, Int, Boolean, Boolean]] = ScNone
    var rowEditorEnter: ScOption[js.Function3[R, JSObject, Int, void]] = ScNone
    var rowEditorExit: ScOption[js.Function4[EditCompletionEvent, R, JSObject, Int, Boolean]] = ScNone
    var rowEndEditAction: ScOption[RowEndEditAction] = ScNone
    var rowHover: ScOption[js.Function3[R, Int, Int, Boolean]] = ScNone
    var rowMouseDown: ScOption[js.Function3[R, Int, Int, Boolean]] = ScNone
    var rowMouseUp: ScOption[js.Function3[R, Int, Int, Boolean]] = ScNone
    var rowNumberField: ScOption[T] = ScNone
    var rowNumberStart: ScOption[Int] = ScNone
    var rowNumberStyle: ScOption[CSSStyleName] = ScNone
    var rowOut: ScOption[js.Function3[R, Int, Int, Boolean]] = ScNone
    var rowOver: ScOption[js.Function3[R, Int, Int, Boolean]] = ScNone
    var rowSpanEditMode: ScOption[RowSpanEditMode] = ScNone
    var rowSpanSelectionMode: ScOption[RowSpanSelectionMode] = ScNone
    var saveByCell: ScOption[Boolean] = ScNone
    var saveLocally: ScOption[Boolean] = ScNone
    var saveRequestProperties: ScOption[DSRequest] = ScNone
    var screenReaderCellSeparator: ScOption[HTMLString] = ScNone
    var screenReaderRowSeparator: ScOption[HTMLString] = ScNone
    var scrollRedrawDelay: ScOption[Int] = ScNone
    var scrollToCellXPosition: ScOption[Alignment] = ScNone
    var scrollToCellYPosition: ScOption[VerticalAlignment] = ScNone
    var scrollWheelRedrawDelay: ScOption[Int] = ScNone
    var selectedState: ScOption[ListGridSelectedState] = ScNone
    var selectHeaderOnSort: ScOption[Boolean] = ScNone
    var selection: ScOption[Selection] = ScNone
    var selectionAppearance: ScOption[SelectionAppearance] = ScNone
    var selectionCanvas: ScOption[Canvas] = ScNone
    var selectionChanged: ScOption[js.Function2[R, Boolean, void]] = ScNone
    var selectionProperty: ScOption[String] = ScNone
    var selectionType: ScOption[SelectionStyle] = ScNone
    var selectionUnderCanvas: ScOption[Canvas] = ScNone
    var selectionUpdated: ScOption[js.Function2[R, IscArray[R], void]] = ScNone
    var selectOnEdit: ScOption[Boolean] = ScNone
    var showAllColumns: ScOption[Boolean] = ScNone
    var showAllRecords: ScOption[Boolean] = ScNone
    var showAsynchGroupingPrompt: ScOption[Boolean] = ScNone
    var showBackgroundComponents: ScOption[Boolean] = ScNone
    var showCellContextMenus: ScOption[Boolean] = ScNone
    var showClippedHeaderTitlesOnHover: ScOption[Boolean] = ScNone
    var showClippedValuesOnHover: ScOption[Boolean] = ScNone
    var showCollapsedGroupSummary: ScOption[Boolean] = ScNone
    var showEmptyMessage: ScOption[Boolean] = ScNone
    var showErrorIcons: ScOption[Boolean] = ScNone
    var showExpansionEditorSaveButton: ScOption[Boolean] = ScNone
    var showFilterEditor: ScOption[Boolean] = ScNone
    var showGridSummary: ScOption[Boolean] = ScNone
    var showGroupSummary: ScOption[Boolean] = ScNone
    var showGroupSummaryInHeader: ScOption[Boolean] = ScNone
    var showGroupTitleColumn: ScOption[Boolean] = ScNone
    var showHeader: ScOption[Boolean] = ScNone
    var showHeaderContextMenu: ScOption[Boolean] = ScNone
    var showHeaderMenuButton: ScOption[Boolean] = ScNone
    var showHeaderSpanTitlesInFormulaBuilder: ScOption[Boolean] = ScNone
    var showHeaderSpanTitlesInHiliteEditor: ScOption[Boolean] = ScNone
    var showHeaderSpanTitlesInSortEditor: ScOption[Boolean] = ScNone
    var showHilitesInGroupSummary: ScOption[Boolean] = ScNone
    var showPartialSelection: ScOption[Boolean] = ScNone
    var showRecordComponent: ScOption[js.Function2[R, Int, Boolean]] = ScNone
    var showRecordComponents: ScOption[Boolean] = ScNone
    var showRecordComponentsByCell: ScOption[Boolean] = ScNone
    var showRollOver: ScOption[Boolean] = ScNone
    var showRollOverCanvas: ScOption[Boolean] = ScNone
    var showRollUnderCanvas: ScOption[Boolean] = ScNone
    var showRowNumbers: ScOption[Boolean] = ScNone
    var showSelectedStyle: ScOption[Boolean] = ScNone
    var showSelectionCanvas: ScOption[Boolean] = ScNone
    var showSelectionUnderCanvas: ScOption[Boolean] = ScNone
    var showSortArrow: ScOption[SortArrow] = ScNone
    var showSortNumerals: ScOption[Boolean] = ScNone
    var showTreeColumnPicker: ScOption[Boolean] = ScNone
    var shrinkForFreeze: ScOption[Boolean] = ScNone
    var singleCellValueProperty: ScOption[String] = ScNone
    var sortAscendingImage: ScOption[ImgProperties] = ScNone
    var sortByGroupFirst: ScOption[Boolean] = ScNone
    var sortChanged: ScOption[js.Function1[IscArray[SortSpecifier], void]] = ScNone
    var sortDescendingImage: ScOption[ImgProperties] = ScNone
    var sortDirection: ScOption[SortDirection] = ScNone
    var sortEditorSpanTitleSeparator: ScOption[String] = ScNone
    var sorterButtonTitle: ScOption[String] = ScNone
    var sorterClick: ScOption[js.ThisFunction0[classHandler, Boolean]] = ScNone
    var sorterConstructor: ScOption[Class] = ScNone
    var sorterContextClick: ScOption[js.ThisFunction0[classHandler, Boolean]] = ScNone
    var sorterDefaults: ScOption[JSObject] = ScNone
    var sorterProperties: ScOption[Button] = ScNone
    var sortField: ScOption[String] = ScNone
    var sortFieldAscendingText: ScOption[String] = ScNone
    var sortFieldDescendingText: ScOption[String] = ScNone
    var sortFieldNum: ScOption[Int] = ScNone
    var sortNumeralStyle: ScOption[CSSStyleName] = ScNone
    var sortState: ScOption[ListGridSortState] = ScNone
    var spanContextMenu: ScOption[Layout] = ScNone
    var stopHover: ScOption[js.ThisFunction0[classHandler, void]] = ScNone
    var stopOnErrors: ScOption[Boolean] = ScNone
    var summaryRow: ScOption[Grid[T, R]] = ScNone
    var summaryRowCriteria: ScOption[Criteria] = ScNone
    var summaryRowDataSource: ScOption[DataSource] = ScNone
    var summaryRowFetchRequestProperties: ScOption[DSRequest] = ScNone
    var summaryRowHeight: ScOption[Int] = ScNone
    var summaryRowStyle: ScOption[CSSStyleName] = ScNone
    var summaryUpdated: ScOption[js.ThisFunction2[classHandler, T, UserSummary, void]] = ScNone
    var tallBaseStyle: ScOption[CSSStyleName] = ScNone
    var timeFormatter: ScOption[TimeDisplayFormat] = ScNone
    var trackerImage: ScOption[ImgProperties] = ScNone
    var unfreezeFieldText: ScOption[HTMLString] = ScNone
    var ungroupText: ScOption[String] = ScNone
    var unremoveIcon: ScOption[SCImgURL] = ScNone
    var unspannedHeaderVAlign: ScOption[VerticalAlignment] = ScNone
    var updateRecordComponent: ScOption[ThisFunction4[classHandler, R, Int, Canvas, Boolean, Canvas]] = ScNone
    var useAdvancedFieldPicker: ScOption[Boolean] = ScNone
    var useCellRollOvers: ScOption[Boolean] = ScNone
    var useCopyPasteShortcuts: ScOption[Boolean] = ScNone
    var useRemoteValidators: ScOption[Boolean] = ScNone
    var useRowSpanStyling: ScOption[Boolean] = ScNone
    var validateByCell: ScOption[Boolean] = ScNone
    var validateOnChange: ScOption[Boolean] = ScNone
    var valueIconHeight: ScOption[Int] = ScNone
    var valueIconLeftPadding: ScOption[Int] = ScNone
    var valueIconRightPadding: ScOption[Int] = ScNone
    var valueIconSize: ScOption[Int] = ScNone
    var valueIconWidth: ScOption[Int] = ScNone
    var viewState: ScOption[Int] = ScNone
    var viewStateChanged: ScOption[js.ThisFunction0[classHandler, void]] = ScNone
    var virtualScrolling: ScOption[Boolean] = ScNone
    var waitForSave: ScOption[Boolean] = ScNone
    var warnOnRemoval: ScOption[Boolean] = ScNone
    var warnOnRemovalMessage: ScOption[Boolean] = ScNone
    var warnOnUnmappedValueFieldChange: ScOption[Boolean] = ScNone
    var wrapCells: ScOption[Boolean] = ScNone
    var wrapHeaderTitles: ScOption[Boolean] = ScNone
    var newRequestProperties: ScOption[ThisFunction0[classHandler, DSRequest]] = ScNone
    var editRequestProperties: ScOption[ThisFunction0[classHandler, DSRequest]] = ScNone
    var editingFields  : ScOption[Seq[FormItem]] = ScNone
    var editWindowProperties: ScOption[WindowSS] = ScNone
}

class ListGridProps extends GridProps[ListGridField, ListGridRecord] {
    type classHandler <: ListGrid
    var fields: ScOption[Seq[ListGridFieldProps]] = ScNone
    var defaultFields: ScOption[Seq[ListGridFieldProps]] = ScNone
    var data: ScOption[Seq[ListGridRecord]] = ScNone
}
