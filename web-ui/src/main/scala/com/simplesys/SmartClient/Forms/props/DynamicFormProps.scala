package com.simplesys.SmartClient.Forms.props

import com.simplesys.SmartClient.DataBinding.props.DataBoundComponentProps
import com.simplesys.SmartClient.Forms.DynamicForm
import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.SmartClient.Foundation.props.CanvasProps
import com.simplesys.SmartClient.System.KeyIdentifier
import com.simplesys.SmartClient.Workdlow.UserTask
import com.simplesys.System.JSObject
import com.simplesys.System.Types.Alignment.Alignment
import com.simplesys.System.Types.AutoComplete.AutoComplete
import com.simplesys.System.Types.DSOperationType.DSOperationType
import com.simplesys.System.Types.DateDisplayFormat.DateDisplayFormat
import com.simplesys.System.Types.Encoding.Encoding
import com.simplesys.System.Types.FormMethod.FormMethod
import com.simplesys.System.Types.OperatorId.OperatorId
import com.simplesys.System.Types.ReadOnlyDisplayAppearance.ReadOnlyDisplayAppearance
import com.simplesys.System.Types.TextMatchStyle.TextMatchStyle
import com.simplesys.System.Types.TimeDisplayFormat.TimeDisplayFormat
import com.simplesys.System.Types.TitleOrientation.TitleOrientation
import com.simplesys.System.Types.VisibilityMode.VisibilityMode
import com.simplesys.System.Types._
import com.simplesys.option.{IntString, ScNone, ScOption}

import scala.scalajs.js._

class DynamicFormProps extends CanvasProps with DataBoundComponentProps{

    import com.simplesys.System.JSAny

    type classHandler <: DynamicForm

    var action: ScOption[URL] = ScNone
    var allowExpressions: ScOption[Boolean] = ScNone
    var autoComplete: ScOption[AutoComplete] = ScNone
    var autoFetchTextMatchStyle: ScOption[TextMatchStyle] = ScNone
    var autoFocus: ScOption[Boolean] = ScNone
    var autoFocusOnError: ScOption[Boolean] = ScNone
    var browserSpellCheck: ScOption[Boolean] = ScNone
    var cancelParamName: ScOption[String] = ScNone
    var cancelParamValue: ScOption[String] = ScNone
    var canEdit: ScOption[Boolean] = ScNone
    var canSubmit: ScOption[Boolean] = ScNone
    var canTabToSectionHeaders: ScOption[Boolean] = ScNone
    var cellBorder: ScOption[Int] = ScNone
    var cellPadding: ScOption[Int] = ScNone
    var clearValues : ScOption[ThisFunction0[classHandler, _]] = ScNone
    var clearValue : ScOption[ThisFunction1[classHandler, ID, _]] = ScNone
    var clearErrors : ScOption[ThisFunction1[classHandler, Boolean, _]] = ScNone
    var clearFieldErrors : ScOption[ThisFunction2[classHandler, String, Boolean, _]] = ScNone
    var clipItemTitles: ScOption[Boolean] = ScNone
    var clipStaticValue: ScOption[Boolean] = ScNone
    var colWidths: ScOption[Seq[JSAny]] = ScNone
    var dateFormatter: ScOption[DateDisplayFormat] = ScNone
    var datetimeFormatter: ScOption[DateDisplayFormat] = ScNone
    var defaultSearchOperator: ScOption[OperatorId] = ScNone
    var disableValidation: ScOption[Boolean] = ScNone
    var encoding: ScOption[Encoding] = ScNone
    var errorItemCellStyle: ScOption[String] = ScNone
    var errorItemProperties: ScOption[JSObject] = ScNone
    var errorOrientation: ScOption[Alignment] = ScNone
    var errors: ScOption[Seq[JSObject]] = ScNone
    var errorsPreamble: ScOption[HTMLString] = ScNone
    var fields: ScOption[Seq[FormItem]] = ScNone  //Не менять !!!!!
    var fixedColWidths: ScOption[Boolean] = ScNone
    var formSubmitFailedWarning: ScOption[String] = ScNone
    var handleHiddenValidationErrors: ScOption[ThisFunction1[DynamicForm, JSObject, Boolean]] = ScNone
    var hiliteRequiredFields: ScOption[Boolean] = ScNone
    var implicitSave: ScOption[Boolean] = ScNone
    var implicitSaveDelay: ScOption[Int] = ScNone
    var implicitSaveOnBlur: ScOption[Boolean] = ScNone
    var itemChange: ScOption[Function3[FormItem, JSAny, JSAny, Boolean]] = ScNone
    var itemChanged: ScOption[Function2[FormItem, JSAny, _]] = ScNone
    var itemHoverAlign: ScOption[Alignment] = ScNone
    var itemHoverDelay: ScOption[Int] = ScNone
    var itemHoverHeight: ScOption[Int] = ScNone
    var itemHoverHTML: ScOption[Function1[FormItem, _]] = ScNone
    var itemHoverOpacity: ScOption[Int] = ScNone
    var itemHoverStyle: ScOption[CSSStyleName] = ScNone
    var itemHoverVAlign: ScOption[Int] = ScNone
    var itemHoverWidth: ScOption[Int] = ScNone
    var itemKeyPress: ScOption[Function3[FormItem, String, Int, Boolean]] = ScNone
    var itemLayout: ScOption[String] = ScNone
    var items: ScOption[Seq[FormItem]] = ScNone  //Не менять !!!!!
    var longTextEditorThreshold: ScOption[Int] = ScNone
    var longTextEditorType: ScOption[String] = ScNone
    var method: ScOption[FormMethod] = ScNone
    var minColWidth: ScOption[Int] = ScNone
    var nestedEditorType: ScOption[String] = ScNone
    var nestedListEditorType: ScOption[String] = ScNone
    var numCols: ScOption[Int] = ScNone
    var operator: ScOption[OperatorId] = ScNone
    var originalValueMessage: ScOption[HTMLString] = ScNone
    var readOnlyDisplay: ScOption[ReadOnlyDisplayAppearance] = ScNone
    var readOnlyTextBoxStyle: ScOption[FormItemBaseStyle] = ScNone
    var rejectInvalidValueOnChange: ScOption[Boolean] = ScNone
    var requiredMessage: ScOption[HTMLString] = ScNone
    var requiredRightTitlePrefix: ScOption[HTMLString] = ScNone
    var requiredTitlePrefix: ScOption[HTMLString] = ScNone
    var requiredTitleSuffix: ScOption[HTMLString] = ScNone
    var revertValueKey: ScOption[KeyIdentifier] = ScNone
    var rightTitlePrefix: ScOption[HTMLString] = ScNone
    var rightTitleSuffix: ScOption[HTMLString] = ScNone
    var saveOnEnter: ScOption[Boolean] = ScNone
    var saveOperationType: ScOption[DSOperationType] = ScNone
    var sectionVisibilityMode: ScOption[VisibilityMode] = ScNone
    var selectOnClick: ScOption[Boolean] = ScNone
    var selectOnFocus: ScOption[Boolean] = ScNone
    var showComplexFieldsRecursively: ScOption[Boolean] = ScNone
    var showDeletions: ScOption[Boolean] = ScNone
    var showErrorIcons: ScOption[Boolean] = ScNone
    var showErrorStyle: ScOption[Boolean] = ScNone
    var showErrorText: ScOption[Boolean] = ScNone
    var showInlineErrors: ScOption[Boolean] = ScNone
    var showOldValueInHover: ScOption[Boolean] = ScNone
    var showTitlesWithErrorMessages: ScOption[Boolean] = ScNone
    var stopOnError: ScOption[Boolean] = ScNone
    var submitValues: ScOption[Function2[JSObject, DynamicForm, _]] = ScNone
    var suppressValidationErrorCallback: ScOption[Boolean] = ScNone
    var synchronousValidation: ScOption[Boolean] = ScNone
    var target: ScOption[String] = ScNone
    var timeFormatter: ScOption[TimeDisplayFormat] = ScNone
    var titleAlign: ScOption[Alignment] = ScNone
    var titleClipped: ScOption[Function1[FormItem, Boolean]] = ScNone
    var titleHoverHTML: ScOption[Function1[FormItem, HTMLString]] = ScNone
    var titleOrientation: ScOption[TitleOrientation] = ScNone
    var titlePrefix: ScOption[HTMLString] = ScNone
    var titleSuffix: ScOption[HTMLString] = ScNone
    var titleWidth: ScOption[IntString[Int, String]] = ScNone
    var userTask: ScOption[UserTask] = ScNone
    var validateOnChange: ScOption[Boolean] = ScNone
    var validateOnExit: ScOption[Boolean] = ScNone
    var validationURL: ScOption[URL] = ScNone
    var values: ScOption[JSObject] = ScNone
    var valuesChanged: ScOption[ThisFunction0[classHandler, _]] = ScNone
    var wrapItemTitles: ScOption[Boolean] = ScNone
}
