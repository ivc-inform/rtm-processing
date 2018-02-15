package com.simplesys.SmartClient.Grids.props.listGrid

import com.simplesys.SmartClient.DataBinding.DSRequest
import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.SmartClient.Forms.formsItems.formItem.FormItemIcon
import com.simplesys.SmartClient.Forms.{DynamicForm, Validator}
import com.simplesys.SmartClient.Grids.listGrid.{ListGridField, ListGridRecord, UserFormula, UserSummary}
import com.simplesys.SmartClient.Grids.{Grid, ListGrid}
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.Alignment.Alignment
import com.simplesys.System.Types.AutoComplete.AutoComplete
import com.simplesys.System.Types.AutoFitWidthApproach.AutoFitWidthApproach
import com.simplesys.System.Types.DateDisplayFormat.DateDisplayFormat
import com.simplesys.System.Types.EditCompletionEvent.EditCompletionEvent
import com.simplesys.System.Types.EnterKeyEditAction.EnterKeyEditAction
import com.simplesys.System.Types.EscapeKeyEditAction.EscapeKeyEditAction
import com.simplesys.System.Types.FormItemComponentType.FormItemComponentType
import com.simplesys.System.Types.FormItemType._
import com.simplesys.System.Types.HiliteIconPosition.HiliteIconPosition
import com.simplesys.System.Types.ListGridFieldType._
import com.simplesys.System.Types.OperatorId.OperatorId
import com.simplesys.System.Types.RecordSummaryFunction.RecordSummaryFunction
import com.simplesys.System.Types.SortDirection.SortDirection
import com.simplesys.System.Types.SummaryFunction.SummaryFunction
import com.simplesys.System.Types.TextMatchStyle.TextMatchStyle
import com.simplesys.System.Types.TimeDisplayFormat.TimeDisplayFormat
import com.simplesys.System.Types._
import com.simplesys.System.{JSAny, JSDictionary, JSObject, NameStrong}
import com.simplesys.option.{FormItemType_FormItemComponentType, ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

import scala.scalajs.js
import scala.scalajs.js.Function5

class ListGridFieldProps extends AbstractClassProps {
    type classHandler <: ListGridField
    var align: ScOption[Alignment] = ScNone
    var applyAfterSummary: ScOption[Boolean] = ScNone
    var autoComplete: ScOption[AutoComplete] = ScNone
    var autoFetchDisplayMap: ScOption[Boolean] = ScNone
    var autoFitWidth: ScOption[Boolean] = ScNone
    var autoFitWidthApproach: ScOption[AutoFitWidthApproach] = ScNone
    var autoFreeze: ScOption[Boolean] = ScNone
    var baseStyle: ScOption[CSSStyleName] = ScNone
    var canAutoFitWidth: ScOption[Boolean] = ScNone
    var canDragResize: ScOption[Boolean] = ScNone
    var canEdit: ScOption[Boolean] = ScNone
    var canEditFormula: ScOption[Boolean] = ScNone
    var canEditSummary: ScOption[Boolean] = ScNone
    var canExport: ScOption[Boolean] = ScNone
    var canFilter: ScOption[Boolean] = ScNone
    var canFreeze: ScOption[Boolean] = ScNone
    var canGroupBy: ScOption[Boolean] = ScNone
    var canHide: ScOption[Boolean] = ScNone
    var canHilite: ScOption[Boolean] = ScNone
    var canReorder: ScOption[Boolean] = ScNone
    var canSort: ScOption[Boolean] = ScNone
    var canSortClientOnly: ScOption[Boolean] = ScNone
    var canToggle: ScOption[Boolean] = ScNone
    var cellAlign: ScOption[Alignment] = ScNone
    var cellChanged: ScOption[js.Function6[ListGridRecord, JSAny, JSAny, Int, Int, Grid[ListGridField, ListGridRecord], void]] = ScNone
    var cellIcon: ScOption[SCImgURL] = ScNone
    var change: ScOption[js.Function4[DynamicForm, FormItem, JSAny, JSAny, Boolean]] = ScNone
    var changed: ScOption[js.Function3[DynamicForm, FormItem, JSAny, void]] = ScNone
    var dataPath: ScOption[String] = ScNone
    var dateFormatter: ScOption[DateDisplayFormat] = ScNone
    var decimalPad: ScOption[Int] = ScNone
    var decimalPrecision: ScOption[Int] = ScNone
    var defaultFilterValue: ScOption[JSAny] = ScNone
    var defaultGroupingMode: ScOption[String] = ScNone
    var defaultIconSrc: ScOption[String] = ScNone
    var defaultValue: ScOption[JSAny] = ScNone
    var defaultWidth: ScOption[Int] = ScNone
    var displayField: ScOption[String] = ScNone
    var displayValueFromRecord: ScOption[Boolean] = ScNone
    var editorEnter: ScOption[js.Function5[ListGridRecord, JSAny, Int, Int, Grid[ListGridField, ListGridRecord], void]] = ScNone
    var editorExit: ScOption[js.Function6[EditCompletionEvent, ListGridRecord, JSAny, Int, Int, Grid[ListGridField, ListGridRecord], void]] = ScNone
    var editorIconHeight: ScOption[Int] = ScNone
    var editorIconWidth: ScOption[Int] = ScNone
    var editorImageURLPrefix: ScOption[String] = ScNone
    var editorImageURLSuffix: ScOption[String] = ScNone
    var editorProperties: ScOption[FormItem] = ScNone
    var editorType: ScOption[FormItemType_FormItemComponentType[FormItemType, FormItemComponentType]] = ScNone
    var editorValueIconHeight: ScOption[Int] = ScNone
    var editorValueIcons: ScOption[JSObject] = ScNone
    var editorValueIconWidth: ScOption[Int] = ScNone
    var editorValueMap: ScOption[JSObject] = ScNone
    var emptyCellValue: ScOption[HTMLString] = ScNone
    var enterKeyEditAction: ScOption[EnterKeyEditAction] = ScNone
    var escapeHTML: ScOption[Boolean] = ScNone
    var escapeKeyEditAction: ScOption[EscapeKeyEditAction] = ScNone
    var exportFieldWidth: ScOption[Boolean] = ScNone
    var exportFormat: ScOption[FormatString] = ScNone
    var exportRawValues: ScOption[Boolean] = ScNone
    var filterEditorProperties: ScOption[FormItem] = ScNone
    var filterEditorType: ScOption[FormItemType_FormItemComponentType[FormItemType, FormItemComponentType]] = ScNone
    var filterEditorValueMap: ScOption[JSObject] = ScNone
    var filterOnKeypress: ScOption[Boolean] = ScNone
    var filterOperator: ScOption[OperatorId] = ScNone
    var format: ScOption[FormatString] = ScNone
    var formatCellValue: ScOption[Function5[JSAny, ListGridRecord, Int, Int, ListGrid, HTMLString]] = ScNone
    var formatGridSummary: ScOption[String] = ScNone
    var formatGroupSummary: ScOption[String] = ScNone
    var formatEditorValue: ScOption[Function5[JSAny, ListGridRecord, Int, Int, ListGrid, JSAny]] = ScNone
    var frozen: ScOption[Boolean] = ScNone
    var groupGranularity: ScOption[Int] = ScNone
    var groupingMode: ScOption[String] = ScNone
    var groupingModes: ScOption[ValueMap] = ScNone
    var groupPrecision: ScOption[Int] = ScNone
    var headerBaseStyle: ScOption[CSSStyleName] = ScNone
    var headerHoverDelay: ScOption[Int] = ScNone
    var headerTitle: ScOption[String] = ScNone
    var headerTitleStyle: ScOption[CSSStyleName] = ScNone
    var hidden: ScOption[Boolean] = ScNone
    var hiliteHTMLAfterFormat: ScOption[Boolean] = ScNone
    var hiliteIconHeight: ScOption[Int] = ScNone
    var hiliteIconLeftPadding: ScOption[Int] = ScNone
    var hiliteIconPosition: ScOption[HiliteIconPosition] = ScNone
    var hiliteIconRightPadding: ScOption[Int] = ScNone
    var hiliteIconSize: ScOption[Int] = ScNone
    var hiliteIconWidth: ScOption[Int] = ScNone
    var hoverDelay: ScOption[Int] = ScNone
    var hoverHTML: ScOption[js.Function5[ListGridRecord, JSAny, Int, Int, Grid[ListGridField, ListGridRecord], HTMLString]] = ScNone
    var hoverWidth: ScOption[Int] = ScNone
    var hoverWrap: ScOption[Boolean] = ScNone
    var icon: ScOption[SCImgURL] = ScNone
    var iconHeight: ScOption[Int] = ScNone
    var iconOrientation: ScOption[String] = ScNone
    var icons: ScOption[IscArray[FormItemIcon]] = ScNone
    var iconSize: ScOption[Int] = ScNone
    var iconSpacing: ScOption[Int] = ScNone
    var iconVAlign: ScOption[String] = ScNone
    var iconWidth: ScOption[Int] = ScNone
    var ignoreKeyboardClicks: ScOption[Boolean] = ScNone
    var imageHeight: ScOption[Int] = ScNone
    var imageSize: ScOption[Int] = ScNone
    var imageURLPrefix: ScOption[String] = ScNone
    var imageURLSuffix: ScOption[String] = ScNone
    var imageWidth: ScOption[Int] = ScNone
    var includeFrom: ScOption[String] = ScNone
    var includeInRecordSummary: ScOption[Boolean] = ScNone
    var includeInRecordSummaryFields: ScOption[IscArray[String]] = ScNone
    var initialValue: ScOption[JSAny] = ScNone
    var inputFormat: ScOption[DateInputFormat] = ScNone
    var isRemoveField: ScOption[Boolean] = ScNone
    var leaveHeaderMenuButtonSpace: ScOption[Boolean] = ScNone
    var linkText: ScOption[String] = ScNone
    var linkTextProperty: ScOption[String] = ScNone
    var linkURLPrefix: ScOption[String] = ScNone
    var linkURLSuffix: ScOption[String] = ScNone
    var multiple: ScOption[Boolean] = ScNone
    @deprecated(message = "Use nameStrong instead.", since = "")
    var name: ScOption[String] = ScNone
    var nameStrong: ScOption[NameStrong] = ScNone
    var optionCriteria: ScOption[Criteria] = ScNone
    var optionDataSource: ScOption[String] = ScNone
    var optionFilterContext: ScOption[DSRequest] = ScNone
    var optionOperationId: ScOption[String] = ScNone
    var optionTextMatchStyle: ScOption[TextMatchStyle] = ScNone
    var partialSummary: ScOption[Boolean] = ScNone
    var prompt: ScOption[HTMLString] = ScNone
    var recordClick: ScOption[js.Function7[Grid[ListGridField, ListGridRecord], ListGridRecord, Int, ListGridField, Int, JSAny, JSAny, Boolean]] = ScNone
    var recordDoubleClick: ScOption[js.Function7[Grid[ListGridField, ListGridRecord], ListGridRecord, Int, ListGridField, Int, JSAny, JSAny, Boolean]] = ScNone
    var recordSummaryFunction: ScOption[RecordSummaryFunction] = ScNone
    var required: ScOption[Boolean] = ScNone
    var shouldPrint: ScOption[Boolean] = ScNone
    var showAlternateStyle: ScOption[Boolean] = ScNone
    var showDefaultContextMenu: ScOption[Boolean] = ScNone
    var showDisabledIcon: ScOption[Boolean] = ScNone
    var showDownIcon: ScOption[Boolean] = ScNone
    var showFileInline: ScOption[Boolean] = ScNone
    var showFocusedIcon: ScOption[Boolean] = ScNone
    var showGridSummary: ScOption[Boolean] = ScNone
    var showGroupSummary: ScOption[Boolean] = ScNone
    var showHover: ScOption[Boolean] = ScNone
    var showIf: ScOption[js.Function3[Grid[ListGridField, ListGridRecord], ListGridField, Int, Boolean]] = ScNone
    var showRollOverIcon: ScOption[Boolean] = ScNone
    var showSelectedIcon: ScOption[Boolean] = ScNone
    var showTitle: ScOption[Boolean] = ScNone
    var showValueIconOnly: ScOption[Boolean] = ScNone
    var sortByDisplayField: ScOption[Boolean] = ScNone
    var sortByMappedValue: ScOption[Boolean] = ScNone
    var sortDirection: ScOption[SortDirection] = ScNone
    var summaryFunction: ScOption[Seq[SummaryFunction]] = ScNone
    var summaryTitle: ScOption[String] = ScNone
    var summaryValue: ScOption[HTMLString] = ScNone
    var summaryValueTitle: ScOption[String] = ScNone
    var suppressValueIcon: ScOption[Boolean] = ScNone
    var target: ScOption[String] = ScNone
    var `type`: ScOption[ListGridFieldType] = ScNone
    var timeFormatter: ScOption[TimeDisplayFormat] = ScNone
    var title: ScOption[String] = ScNone
    var treeField: ScOption[Boolean] = ScNone
    var userFormula: ScOption[UserFormula] = ScNone
    var userSummary: ScOption[UserSummary] = ScNone
    var validateOnChange: ScOption[Boolean] = ScNone
    var validators: ScOption[IscArray[Validator]] = ScNone
    var valueField: ScOption[String] = ScNone
    var valueIconClick: ScOption[js.Function6[Grid[ListGridField, ListGridRecord], ListGridRecord, Int, ListGridField, JSAny, FormItem, Boolean]] = ScNone
    var valueIconHeight: ScOption[Int] = ScNone
    var valueIconLeftPadding: ScOption[Int] = ScNone
    var valueIconOrientation: ScOption[String] = ScNone
    var valueIconRightPadding: ScOption[Int] = ScNone
    var valueIconSize: ScOption[Int] = ScNone
    var valueIconWidth: ScOption[Int] = ScNone
    var valueIcons: ScOption[JSDictionary[String]] = ScNone
    var valueMap: ScOption[ValueMap] = ScNone
    var width: ScOption[Int] = ScNone
    var wrap: ScOption[Boolean] = ScNone
}
