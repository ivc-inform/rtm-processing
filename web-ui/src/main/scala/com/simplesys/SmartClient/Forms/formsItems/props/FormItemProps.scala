package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.SmartClient.DataBinding.DataSource
import com.simplesys.SmartClient.Drawing.DrawItem
import com.simplesys.SmartClient.Forms.formsItems.formItem.FormItemIcon
import com.simplesys.SmartClient.Forms.formsItems.{CanvasItem, FormItem}
import com.simplesys.SmartClient.Forms.{DynamicForm, DynamicFormSS, Validator}
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.ListGridEditor
import com.simplesys.SmartClient.Grids.listGrid.ListGridRecord
import com.simplesys.SmartClient.RPC.RPCRequest
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.SmartClient.System.props.ClassProps
import com.simplesys.System.Types.Alignment.Alignment
import com.simplesys.System.Types.AutoComplete.AutoComplete
import com.simplesys.System.Types.DateDisplayFormat.DateDisplayFormat
import com.simplesys.System.Types.FormItemComponentType.FormItemComponentType
import com.simplesys.System.Types.FormItemType.FormItemType
import com.simplesys.System.Types.OperatorId.OperatorId
import com.simplesys.System.Types.ReadOnlyDisplayAppearance.ReadOnlyDisplayAppearance
import com.simplesys.System.Types.TimeDisplayFormat.TimeDisplayFormat
import com.simplesys.System.Types.TitleOrientation.TitleOrientation
import com.simplesys.System.Types.VerticalAlignment.VerticalAlignment
import com.simplesys.System.Types.{SCImgURL, _}
import com.simplesys.System._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.option.{FormItemType_FormItemComponentType, IntString, ScNone, ScOption}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName
import scala.scalajs.js.{ThisFunction0, ThisFunction1, ThisFunction3, ThisFunction4}

class FormItemProps extends ClassProps {
    type classHandler <: FormItem

    var accessKey: ScOption[Char] = ScNone
    var align: ScOption[Alignment] = ScNone
    var allowExpressions: ScOption[Boolean] = ScNone
    var alwaysFetchMissingValues: ScOption[Boolean] = ScNone
    var applyAlignToText: ScOption[Boolean] = ScNone
    var applyHeightToTextBox: ScOption[Boolean] = ScNone
    var ariaRole: ScOption[String] = ScNone
    var ariaState: ScOption[JSObject] = ScNone
    var autoComplete: ScOption[AutoComplete] = ScNone
    var blur: ScOption[js.Function2[DynamicFormSS, FormItem, _]] = ScNone
    var browserSpellCheck: ScOption[Boolean] = ScNone
    var canEdit: ScOption[Boolean] = ScNone
    var canEditOpaqueValues: ScOption[Boolean] = ScNone
    var canFocus: ScOption[Boolean] = ScNone
    var canSelectText: ScOption[Boolean] = ScNone
    var cellHeight: ScOption[Int] = ScNone
    var cellStyle: ScOption[FormItemBaseStyle] = ScNone
    var change: ScOption[js.Function4[_ <: DynamicFormSS, FormItem, _ <:  JSUndefined[JSAny], _ <: JSUndefined[JSAny], Boolean]] = ScNone
    var changed: ScOption[js.Function3[_ <: DynamicFormSS, _ <: FormItem, _ <: JSUndefined[JSAny], _]] = ScNone
    var changeOnKeypress: ScOption[Boolean] = ScNone
    var clearValue: ScOption[ThisFunction0[classHandler, _]] = ScNone
    var click: ScOption[js.Function2[DynamicFormSS, FormItem, Boolean]] = ScNone
    var clipStaticValue: ScOption[Boolean] = ScNone
    var clipTitle: ScOption[Boolean] = ScNone
    var colSpan: ScOption[Int] = ScNone
    var containerWidget: ScOption[CanvasItem] = ScNone
    var controlStyle: ScOption[FormItemBaseStyle] = ScNone
    var criteriaField: ScOption[String] = ScNone
    var dataPath: ScOption[DataPath] = ScNone
    var dateFormatter: ScOption[DateDisplayFormat] = ScNone
    var decimalPad: ScOption[Int] = ScNone
    var decimalPrecision: ScOption[Int] = ScNone
    var defaultDynamicValue: ScOption[js.Function3[FormItem, DynamicFormSS, JSObject, _]] = ScNone
    var defaultIconSrc: ScOption[SCImgURL] = ScNone
    var defaultValue: ScOption[JSAny] = ScNone
    var disabled: ScOption[Boolean] = ScNone
    var disableIconsOnReadOnly: ScOption[Boolean] = ScNone
    var displayField: ScOption[String] = ScNone
    var doubleClick: ScOption[js.Function2[DynamicFormSS, FormItem, Boolean]] = ScNone
    var drawItems: ScOption[IscArray[DrawItem]] = ScNone
    var editorEnter: ScOption[js.Function3[DynamicFormSS, FormItem, JSAny, _]] = ScNone
    var editorExit: ScOption[js.Function3[DynamicFormSS, FormItem, JSAny, _]] = ScNone
    var editorType: ScOption[FormItemComponentType] = ScNone
    var editPendingCSSText: ScOption[CSSText] = ScNone
    var emptyDisplayValue: ScOption[String] = ScNone
    var emptyValueIcon: ScOption[String] = ScNone
    var endRow: ScOption[Boolean] = ScNone
    var errorIconHeight: ScOption[Int] = ScNone
    var errorIconSrc: ScOption[SCImgURL] = ScNone
    var errorIconWidth: ScOption[Int] = ScNone
    var errorMessageWidth: ScOption[Int] = ScNone
    var errorOrientation: ScOption[Alignment] = ScNone
    var exportFormat: ScOption[FormatString] = ScNone
    var filterLocally: ScOption[Boolean] = ScNone
    var focus: ScOption[js.Function2[DynamicFormSS, FormItem, _]] = ScNone
    var foreignDisplayField: ScOption[String] = ScNone
    var form: ScOption[DynamicFormSS] = ScNone
    var format: ScOption[FormatString] = ScNone
    var formatEditorValue: ScOption[js.Function4[JSAny, ListGridRecord, DynamicFormSS, FormItem, String]] = ScNone
    var formatValue: ScOption[js.Function4[JSAny, ListGridRecord, DynamicFormSS, FormItem, String]] = ScNone
    var getValue: ScOption[ThisFunction0[classHandler, JSUndefined[JSAny]]] = ScNone
    var globalTabIndex: ScOption[Int] = ScNone
    var height: ScOption[IntString[Int, String]] = ScNone
    var hidden: ScOption[Boolean] = ScNone
    var hint: ScOption[HTMLString] = ScNone
    var hintStyle: ScOption[CSSClassName] = ScNone
    var hoverAlign: ScOption[Alignment] = ScNone
    var hoverDelay: ScOption[Int] = ScNone
    var hoverHeight: ScOption[Int] = ScNone
    var hoverOpacity: ScOption[Int] = ScNone
    var hoverStyle: ScOption[CSSStyleName] = ScNone
    var hoverVAlign: ScOption[VerticalAlignment] = ScNone
    var hoverWidth: ScOption[Int] = ScNone
    var iconClick: ScOption[js.Function3[DynamicFormSS, FormItem, FormItemIcon, _]] = ScNone
    var iconHeight: ScOption[Int] = ScNone
    var iconHSpace: ScOption[Int] = ScNone
    var iconKeyPress: ScOption[js.Function5[String, Char, DynamicFormSS, FormItem, FormItemIcon, _]] = ScNone
    var iconPrompt: ScOption[HTMLString] = ScNone
    var icons: ScOption[Seq[FormItemIcon]] = ScNone
    var iconVAlign: ScOption[VerticalAlignment] = ScNone
    var iconWidth: ScOption[Int] = ScNone
    var ID: ScOption[String] = ScNone
    var imageURLPrefix: ScOption[String] = ScNone
    var imageURLSuffix: ScOption[String] = ScNone
    var implicitSave: ScOption[Boolean] = ScNone
    var implicitSaveOnBlur: ScOption[Boolean] = ScNone
    var inputFormat: ScOption[DateInputFormat] = ScNone
    var itemHover: ScOption[js.Function2[FormItem, DynamicFormSS, _]] = ScNone
    var itemHoverHTML: ScOption[js.Function2[FormItem, DynamicFormSS, HTMLString]] = ScNone
    var keyDown: ScOption[js.Function3[FormItem, DynamicFormSS, String, Boolean]] = ScNone
    var keyPress: ScOption[ThisFunction4[classHandler, FormItem, DynamicFormSS, KeyName, JSUndefined[Int], Boolean]] = ScNone
    var keyUp: ScOption[js.Function3[FormItem, DynamicFormSS, KeyName, Boolean]] = ScNone
    var left: ScOption[Int] = ScNone
    var loadingDisplayValue: ScOption[String] = ScNone
    var locateItemBy: ScOption[String] = ScNone
    var multipleValueSeparator: ScOption[String] = ScNone
    @deprecated(message = "Use nameStrong instead.", since = "")
    var name: ScOption[String] = ScNone
    @JSName("name")
    var _name: ScOption[String] = ScNone
    var nameStrong: ScOption[NameStrong] = ScNone
    var operator: ScOption[OperatorId] = ScNone
    var optionCriteria: ScOption[Criteria] = ScNone
    var optionDataSource: ScOption[DataSource] = ScNone
    var optionFilterContext: ScOption[RPCRequest] = ScNone
    var optionOperationId: ScOption[String] = ScNone
    var originalValueMessage: ScOption[HTMLString] = ScNone
    var parseEditorValue: ScOption[js.Function3[String, DynamicFormSS, FormItem, JSAny]] = ScNone
    var pendingStatusChanged: ScOption[js.Function5[DynamicFormSS, FormItem, Boolean, JSAny, JSAny, Boolean]] = ScNone
    var picker: ScOption[Canvas] = ScNone
    var pickerConstructor: ScOption[SCClassName] = ScNone
    var pickerIconClick: ScOption[js.Function3[DynamicFormSS, FormItem, FormItemIcon, _]] = ScNone
    var pickerIconDefaults: ScOption[FormItemIcon] = ScNone
    var pickerIconHeight: ScOption[Int] = ScNone
    var pickerIconName: ScOption[String] = ScNone
    var pickerIconPrompt: ScOption[HTMLString] = ScNone
    var pickerIconProperties: ScOption[FormItemIcon] = ScNone
    var pickerIconSrc: ScOption[SCImgURL] = ScNone
    var pickerIconStyle: ScOption[FormItemBaseStyle] = ScNone
    var pickerIconWidth: ScOption[Int] = ScNone
    var pickerProperties: ScOption[Canvas] = ScNone
    var printTextBoxStyle: ScOption[FormItemBaseStyle] = ScNone
    var printTitleStyle: ScOption[FormItemBaseStyle] = ScNone
    var prompt: ScOption[HTMLString] = ScNone
    var readOnlyDisplay: ScOption[ReadOnlyDisplayAppearance] = ScNone
    var readOnlyTextBoxStyle: ScOption[FormItemBaseStyle] = ScNone
    var redrawOnChange: ScOption[Boolean] = ScNone
    var rejectInvalidValueOnChange: ScOption[Boolean] = ScNone
    var required: ScOption[Boolean] = ScNone
    var requiredMessage: ScOption[String] = ScNone
    var rowSpan: ScOption[Int] = ScNone
    var saveOnEnter: ScOption[Boolean] = ScNone
    var selectOnClick: ScOption[Boolean] = ScNone
    var selectOnFocus: ScOption[Boolean] = ScNone
    var setValue: ScOption[ThisFunction1[classHandler, _ <: JSUndefined[JSAny], _]] = ScNone
    var shouldSaveValue: ScOption[Boolean] = ScNone
    var shouldStopKeyPressBubbling: ScOption[js.Function2[String, Int, Boolean]] = ScNone
    var showClippedTitleOnHover: ScOption[Boolean] = ScNone
    var showClippedValueOnHover: ScOption[Boolean] = ScNone
    var showDeletions: ScOption[Boolean] = ScNone
    var showDisabled: ScOption[Boolean] = ScNone
    var showErrorIcon: ScOption[Boolean] = ScNone
    var showErrorStyle: ScOption[Boolean] = ScNone
    var showErrorText: ScOption[Boolean] = ScNone
    var showFocused: ScOption[Boolean] = ScNone
    var showFocusedErrorState: ScOption[Boolean] = ScNone
    var showFocusedIcons: ScOption[Boolean] = ScNone
    var showFocusedPickerIcon: ScOption[Boolean] = ScNone
    var showHint: ScOption[Boolean] = ScNone
    var showIcons: ScOption[Boolean] = ScNone
    var showIf: ScOption[js.Function4[FormItem, JSAny, DynamicFormSS, JSObject, Boolean]] = ScNone
    var showOldValueInHover: ScOption[Boolean] = ScNone
    var showOverIcons: ScOption[Boolean] = ScNone
    var showPending: ScOption[Boolean] = ScNone
    var showPickerIcon: ScOption[Boolean] = ScNone
    var showRTL: ScOption[Boolean] = ScNone
    var showTitle: ScOption[Boolean] = ScNone
    var showValueIconOnly: ScOption[Boolean] = ScNone
    var startRow: ScOption[Boolean] = ScNone
    var staticHeight: ScOption[Int] = ScNone
    var stopOnError: ScOption[Boolean] = ScNone
    var suppressValueIcon: ScOption[Boolean] = ScNone
    var synchronousValidation: ScOption[Boolean] = ScNone
    var tabIndex: ScOption[Int] = ScNone
    var textAlign: ScOption[Alignment] = ScNone
    var textBoxStyle: ScOption[FormItemBaseStyle] = ScNone
    var timeFormatter: ScOption[TimeDisplayFormat] = ScNone
    var title: ScOption[String] = ScNone
    var titleAlign: ScOption[Alignment] = ScNone
    var titleClassName: ScOption[CSSStyleName] = ScNone
    var titleClick: ScOption[js.Function2[DynamicFormSS, FormItem, Boolean]] = ScNone
    var titleColSpan: ScOption[Int] = ScNone
    var titleDoubleClick: ScOption[js.Function2[DynamicFormSS, FormItem, Boolean]] = ScNone
    var titleHover: ScOption[js.Function2[DynamicFormSS, FormItem, Boolean]] = ScNone
    var titleHoverHTML: ScOption[js.Function2[DynamicFormSS, FormItem, HTMLString]] = ScNone
    var titleOrientation: ScOption[TitleOrientation] = ScNone
    var titleStyle: ScOption[FormItemBaseStyle] = ScNone
    var titleVAlign: ScOption[VerticalAlignment] = ScNone
    var top: ScOption[Int] = ScNone
    var transformInput: ScOption[js.Function4[DynamicFormSS, FormItem, JSAny, JSAny, JSAny]] = ScNone
    var useDisabledHintStyleForReadOnly: ScOption[Boolean] = ScNone
    var validateOnChange: ScOption[Boolean] = true.opt
    var validateOnExit: ScOption[Boolean] = true.opt
    var validators: ScOption[Seq[Validator]] = ScNone
    var validOperators: ScOption[Seq[OperatorId]] = ScNone
    var vAlign: ScOption[VerticalAlignment] = ScNone
    var value: ScOption[JSAny] = ScNone
    var valueDeselectedCSSText: ScOption[CSSText] = ScNone
    var valueField: ScOption[String] = ScNone
    var valueHover: ScOption[js.Function2[FormItem, DynamicFormSS, _]] = ScNone
    var valueHoverHTML: ScOption[js.Function2[FormItem, DynamicFormSS, HTMLString]] = ScNone
    var valueIconClick: ScOption[js.Function3[FormItem, DynamicFormSS, JSAny, Boolean]] = ScNone
    var valueIconHeight: ScOption[Int] = ScNone
    var valueIconLeftPadding: ScOption[Int] = ScNone
    var valueIconRightPadding: ScOption[Int] = ScNone
    var valueIconSize: ScOption[Int] = ScNone
    var valueIconWidth: ScOption[Int] = ScNone
    var valueIcons: ScOption[JSDictionary[String]] = ScNone
    var valueMap: ScOption[ValueMap] = ScNone
    var visible: ScOption[Boolean] = ScNone
    var width: ScOption[IntString[Int, String]] = "100%"
    var wrapTitle: ScOption[Boolean] = ScNone
    var `type`: ScOption[FormItemType_FormItemComponentType[FormItemType, FormItemComponentType]] = ScNone
    var filteredGrid: ScOption[ListGridEditor] = ScNone
}
