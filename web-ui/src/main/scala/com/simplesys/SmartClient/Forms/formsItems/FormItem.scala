package com.simplesys.SmartClient.Forms.formsItems

import com.simplesys.SmartClient.DataBinding.{Criterion, DataSource}
import com.simplesys.SmartClient.Drawing.DrawItem
import com.simplesys.SmartClient.Forms.formsItems.formItem.FormItemIcon
import com.simplesys.SmartClient.Forms.{DynamicFormSS, Validator}
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.listGrid.ListGridRecord
import com.simplesys.SmartClient.Grids.{ListGrid, ListGridEditor, TreeGridEditor}
import com.simplesys.SmartClient.RPC.RPCRequest
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.Alignment.Alignment
import com.simplesys.System.Types.AutoComplete.AutoComplete
import com.simplesys.System.Types.DateDisplayFormat.DateDisplayFormat
import com.simplesys.System.Types.FormItemComponentType.FormItemComponentType
import com.simplesys.System.Types.FormItemElementType.FormItemElementType
import com.simplesys.System.Types.FormItemType.FormItemType
import com.simplesys.System.Types.OperatorId.OperatorId
import com.simplesys.System.Types.ReadOnlyDisplayAppearance.ReadOnlyDisplayAppearance
import com.simplesys.System.Types.TextMatchStyle.TextMatchStyle
import com.simplesys.System.Types.TimeDisplayFormat.TimeDisplayFormat
import com.simplesys.System.Types.TitleOrientation.TitleOrientation
import com.simplesys.System.Types.VerticalAlignment.VerticalAlignment
import com.simplesys.System.Types._
import com.simplesys.System._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName
import scala.scalajs.js.{Function0, Function3, Function4, |}

@js.native
trait FormItem extends Class {
    val drawItems : JSUndefined[IscArray[DrawItem]]
    val parentItem: JSUndefined[FormItem]
    var _origCanEdit: Boolean
    val lookup: JSUndefined[Boolean]
    val captionClassLookup: JSUndefined[String]
    val foreignField: JSUndefined[String]
    var accessKey: Char
    var align: Alignment
    var allowExpressions: Boolean
    var alwaysFetchMissingValues: Boolean
    var applyAlignToText: Boolean
    var applyHeightToTextBox: Boolean
    var ariaRole: String
    var ariaState: JSObject
    var autoComplete: AutoComplete
    var blur: js.Function2[DynamicFormSS, FormItem, _]
    def blurItem(): void
    var browserSpellCheck: Boolean
    var canEdit: Boolean
    def canEditCriterion(criterion: Criterion): Boolean
    var canEditOpaqueValues: Boolean
    var canFocus: Boolean
    var canSelectText: Boolean
    var cellHeight: Int
    var cellStyle: FormItemBaseStyle
    var change: JSUndefined[js.Function4[DynamicFormSS, FormItem, JSAny, JSAny, Boolean]]
    var changed: JSUndefined[js.Function3[DynamicFormSS, FormItem, JSAny, _]]
    var changeOnKeypress: Boolean
    def clearErrors(): void
    def clearValue(): void
    var click: js.Function2[DynamicFormSS, FormItem, Boolean]
    var clipStaticValue: Boolean
    var clipTitle: Boolean
    var colSpan: Int
    var containerWidget: CanvasItem
    var controlStyle: FormItemBaseStyle
    val criteriaField: String
    val dataPath: DataPath
    var dateFormatter: DateDisplayFormat
    var decimalPad: Int
    var decimalPrecision: Int
    var defaultDynamicValue: js.Function3[FormItem, DynamicFormSS, JSObject, _]
    var defaultIconSrc: SCImgURL
    var defaultValue: JSAny
    def disable(): void
    var disabled: Boolean
    def disableIcon(icon: String): void
    var disableIconsOnReadOnly: Boolean
    var displayField: JSUndefined[String]
    var doubleClick: js.Function2[DynamicFormSS, FormItem, Boolean]
    var editorEnter: js.Function3[DynamicFormSS, FormItem, JSAny, _]
    var editorExit: js.Function3[DynamicFormSS, FormItem, JSAny, _]
    var editorType: FormItem
    var editPendingCSSText: CSSText
    var listGridEditor: JSUndefined[ListGridEditor]
    var treeGridEditor: JSUndefined[TreeGridEditor]
    var emptyDisplayValue: String
    var emptyValueIcon: String
    def enable()
    def enableIcon(icon: String)
    var endRow: Boolean
    var errorIconHeight: Int
    var errorIconSrc: SCImgURL
    var errorIconWidth: Int
    var errorMessageWidth: Int
    var errorOrientation: Alignment
    var exportFormat: FormatString
    var filterLocally: Boolean
    var focus: js.Function2[DynamicFormSS, FormItem, _]
    def focusInItem(): void
    val foreignDisplayField: String
    val form: JSUndefined[DynamicFormSS]
    val format: FormatString
    var formatEditorValue: js.Function4[JSAny, ListGridRecord, DynamicFormSS, FormItem, String]
    var formatValue: js.Function4[JSAny, ListGridRecord, DynamicFormSS, FormItem, String]
    def getCanEdit(): Boolean
    def getCanFocus(): Boolean
    def getCriterion(textMatchStyle: TextMatchStyle): Criterion
    def getCursorPosition(): Int
    def getCustomState(elementType: FormItemElementType, derivedState: String): String
    def getDataPath(): DataPath
    def getDisplayFieldName(): String
    def getDisplayValue(value: JSAny): JSAny
    def getErrorHTML(error: String | IscArray[String]): HTMLString
    def getErrors(): String | IscArray[String]
    def getFieldName(): String
    def getFullDataPath(): DataPath
    def getGridColNum(): Int
    def getGridRowNum(): Int
    def getIcon(name: String): FormItemIcon
    def getIconHeight(icon: JSObject): Int
    def getIconWidth(icon: JSObject): Int
    def getLeft(): Int
    def getOptionDataSource(): DataSource
    def getPageLeft(): Int
    def getPageRect(): IscArray[Int]
    def getPageTop(): Int
    def getPixelHeight(): Int
    def getPixelWidth(): Int
    def getPrintValueIcon(value: JSAny): SCImgURL
    def getPrintValueIconStyle(value: JSAny): CSSClassName
    def getRect(): IscArray[Int]
    def getSelectedRecord(): ListGridRecord
    def getTitle(): HTMLString
    def getTop(): Int
    def getValue(): JSUndefined[JSAny]
    def getValueFieldName(): String
    def getValueIcon(value: JSAny): SCImgURL
    def getValueIconStyle(value: JSAny): CSSClassName
    def getVisibleHeight(): Int
    def getVisibleTitleWidth(labelOnly: Boolean): Int
    def getVisibleWidth(): Int
    def getWidth(): Int
    var globalTabIndex: Int
    def handleChanged(value: JSAny): void
    def hasAdvancedCriteria(): Boolean
    def hasErrors(): Boolean
    var height: String | Int
    var hidden: Boolean
    def hide(): void
    def hideIcon(icon: String): void
    var hint: HTMLString
    var hintStyle: CSSClassName
    var hoverAlign: Alignment
    var hoverDelay: Int
    var hoverHeight: Int
    var hoverOpacity: Int
    var hoverStyle: CSSStyleName
    var hoverVAlign: VerticalAlignment
    var hoverWidth: Int
    var iconClick: js.Function3[DynamicFormSS, FormItem, FormItemIcon, _]
    var iconHeight: Int
    var iconHSpace: Int
    var iconKeyPress: js.Function5[String, Char, DynamicFormSS, FormItem, FormItemIcon, _]
    var iconPrompt: HTMLString
    var icons: IscArray[FormItemIcon]
    var iconVAlign: VerticalAlignment
    var iconWidth: Int
    var ID: JSUndefined[String]
    var _origReadOnlyDisplay: ReadOnlyDisplayAppearance
    val options: JSUndefined[JSObject]
    def getReadOnlyDisplay(): JSObject
    var imageURLPrefix: String
    var imageURLSuffix: String
    var implicitSave: Boolean
    var implicitSaveOnBlur: Boolean
    var inputFormat: DateInputFormat
    def invalidateDisplayValueCache(): void
    def isDisabled(): Boolean
    def isDrawn(): Boolean
    def isFocused(): Boolean
    def isInGrid(): Boolean
    def isVisible(): Boolean
    var itemHover: js.Function2[FormItem, DynamicFormSS, _]
    var itemHoverHTML: js.Function2[FormItem, DynamicFormSS, HTMLString]
    var keyDown: js.Function3[FormItem, DynamicFormSS, String, Boolean]
    var keyPress: js.Function3[FormItem, DynamicFormSS, Char, Boolean]
    var keyUp: js.Function3[FormItem, DynamicFormSS, KeyName, Boolean]
    var left: Int
    var loadingDisplayValue: String
    var locateItemBy: String
    def mapDisplayToValue(value: String): JSObject
    def mapValueToDisplay(value: JSObject): String
    val multipleValueSeparator: String
    @deprecated(message = "Use nameStrong instead.", since = "")
    var name: String
    @JSName("name")
    var _name: String
    var nameStrong: JSUndefined[NameStrong]
    val operator: OperatorId
    val optionCriteria: Criteria
    val optionDataSource: String | DataSource
    var optionFilterContext: RPCRequest
    var optionOperationId: String
    var originalValueMessage: HTMLString
    var parseEditorValue: js.Function3[String, DynamicFormSS, FormItem, JSAny]
    var pendingStatusChanged: js.Function5[DynamicFormSS, FormItem, Boolean, JSAny, JSAny, Boolean]
    var picker: Canvas with AutoChild
    var pickerConstructor: SCClassName
    var pickerIconClick: js.Function3[DynamicFormSS, FormItem, FormItemIcon, _]
    var pickerIconDefaults: FormItemIcon
    var pickerIconHeight: Int
    var pickerIconName: String
    var pickerIconPrompt: HTMLString
    var pickerIconProperties: FormItemIcon
    var pickerIconSrc: SCImgURL
    var pickerIconStyle: FormItemBaseStyle
    var pickerIconWidth: Int
    var pickerProperties: Canvas
    var printTextBoxStyle: FormItemBaseStyle
    var printTitleStyle: FormItemBaseStyle
    var prompt: HTMLString
    var readOnlyDisplay: JSUndefined[ReadOnlyDisplayAppearance]
    var readOnlyTextBoxStyle: FormItemBaseStyle
    val record: JSUndefined[ListGridRecord]
    def redraw(reason: String): void
    var redrawOnChange: Boolean
    var rejectInvalidValueOnChange: Boolean
    var required: Boolean
    var requiredMessage: String
    var rowSpan: Int
    var saveOnEnter: Boolean
    var selectOnClick: Boolean
    var selectOnFocus: Boolean
    def setCanEdit(canEdit: Boolean): void
    def setCellStyle(newCellStyle: FormItemBaseStyle): void
    def setCriterion(criterion: Criterion): void
    def setDisabled(disabled: Boolean): void
    def setErrors(errors: IscArray[String] | String): void
    def setHint(newHint: HTMLString): void
    def setHintStyle(hintStyle: CSSStyleName): void
    def setIconDisabled(icon: String, disabled: Boolean): void
    def setLeft(): void
    def setOptionDataSource(dataSource: DataSource): void
    def setPrompt(newPrompt: HTMLString): void
    def setReadOnlyDisplay(appearance: ReadOnlyDisplayAppearance): void
    def setRequired(required: Boolean): void
    def setShowDisabled(showDisabled: Boolean): void
    def setTabIndex(): void
    def setTop(): void
    def setValue(value: JSAny): void
    def setValue1(value: JSAny): void
    def setValueIcons(map: JSObject): void
    def setValueMap[T <: JSAny](valueMap: IscArray[T]): void
    @JSName("setValueMap")
    def setValueMapString(valueMap: IscArray[String]): void
    def shouldApplyHeightToTextBox(): Boolean
    def shouldFetchMissingValue(newValue: JSAny): Boolean
    def shouldSaveOnEnter(): Boolean
    var shouldSaveValue: Boolean
    var shouldStopKeyPressBubbling: js.Function2[String, Int, Boolean]
    def show(): void
    var showClippedTitleOnHover: Boolean
    var showClippedValueOnHover: Boolean
    var showDeletions: Boolean
    var showDisabled: Boolean
    var showErrorIcon: Boolean
    var showErrorStyle: Boolean
    var showErrorText: Boolean
    var showFocused: Boolean
    var showFocusedErrorState: Boolean
    var showFocusedIcons: Boolean
    var showFocusedPickerIcon: Boolean
    var showHint: Boolean
    def showIcon(icon: String): void
    var showIcons: Boolean
    var showIf: js.Function4[FormItem, JSAny, DynamicFormSS, JSObject, Boolean]
    var showOldValueInHover: Boolean
    var showOverIcons: Boolean
    var showPending: Boolean
    def showPicker(): void
    var showPickerIcon: Boolean
    var showRTL: Boolean
    var showTitle: Boolean
    var showValueIconOnly: Boolean
    var startRow: Boolean
    var staticHeight: Int
    def stopHover(): void
    var stopOnError: JSUndefined[Boolean]
    def storeValue(value: JSAny, showValue: Boolean = js.native): void
    var suppressValueIcon: Boolean
    var synchronousValidation: JSUndefined[Boolean]
    var tabIndex: Int
    var textAlign: Alignment
    var textBoxStyle: FormItemBaseStyle
    var timeFormatter: TimeDisplayFormat
    var title: String
    var titleAlign: Alignment
    var titleClassName: CSSStyleName
    var titleClick: js.Function2[DynamicFormSS, FormItem, Boolean]
    var titleColSpan: Int
    var titleDoubleClick: js.Function2[DynamicFormSS, FormItem, Boolean]
    var titleHover: js.Function2[DynamicFormSS, FormItem, Boolean]
    var titleHoverHTML: js.Function2[DynamicFormSS, FormItem, HTMLString]
    var titleOrientation: TitleOrientation
    var titleStyle: FormItemBaseStyle
    var titleVAlign: VerticalAlignment
    var top: Int
    var transformInput: js.Function4[DynamicFormSS, FormItem, JSAny, JSAny, JSAny]
    var `type`: JSUndefined[FormItemType]
    @JSName("type")
    var type1: JSUndefined[FormItemComponentType]
    @JSName("type")
    var type2: JSUndefined[String]
    var typeEditorField: JSUndefined[FormItemComponentType]
    def updateState(): void
    var useDisabledHintStyleForReadOnly: Boolean
    def validate(): Boolean
    var validateOnChange: Boolean
    var validateOnExit: Boolean
    val validators: JSUndefined[IscArray[Validator]]
    val validOperators: JSUndefined[IscArray[OperatorId]]
    var vAlign: VerticalAlignment
    @deprecated("Use getValue()", "v1.0.15")
    val value: JSAny
    def valueClipped(): Boolean
    val valueDeselectedCSSText: CSSText
    val valueField: JSUndefined[String]
    var valueHover: js.Function2[FormItem, DynamicFormSS, _]
    var valueHoverHTML: js.Function2[FormItem, DynamicFormSS, HTMLString]
    var valueIconClick: js.Function3[FormItem, DynamicFormSS, JSAny, Boolean]
    var valueIconHeight: Int
    var valueIconLeftPadding: Int
    var valueIconRightPadding: Int
    var valueIconSize: Int
    var valueIconWidth: Int
    var valueMap: JSUndefined[JSObject]
    var valueIcons: JSDictionary[String]
    var visible: Boolean
    var width: String | Int
    var wrapTitle: Boolean

    val _$height: String
    val _$width: String
    val _$colSpan: String
    val _$rowSpan: String
    def _convertRawToMeasure(value: String): JSAny
    def onInit(): void
    var _value: JSUndefined[JSAny]
    def getDefaultValue(): JSAny
    var _setToDefault: Boolean
    def _setUpIcons(): void
    val __sgwtRelink: JSUndefined[Function0[_]]
    @JSName("filteredGrid")
    var filteredGridList: JSUndefined[ListGridEditor]
    @JSName("filteredGrid")
    var filteredGridTree: JSUndefined[TreeGridEditor]
    val grid: ListGrid
}

@js.native
abstract class AbstractFormItemCompanion extends AbstractClassCompanion


