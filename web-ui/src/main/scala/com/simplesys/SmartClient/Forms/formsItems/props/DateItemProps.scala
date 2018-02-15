package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.SmartClient.Forms.DateChooser
import com.simplesys.SmartClient.Forms.formsItems.{SelectItem, TextItem, TimeItem}
import com.simplesys.System.Types.DateDisplayFormat.DateDisplayFormat
import com.simplesys.System.Types.DateItemSelectorFormat.DateItemSelectorFormat
import com.simplesys.System.Types.{FormItemComponentType, SCClassName}
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js._

class DateItemProps extends FormItemProps {
    var browserInputType: ScOption[String] = ScNone
    var centuryThreshold: ScOption[Int] = ScNone
    var daySelector: ScOption[SelectItem] = ScNone
    var daySelectorProperties: ScOption[SelectItem] = ScNone
    var defaultChooserDate: ScOption[Date] = ScNone
    var displayFormat: ScOption[DateDisplayFormat] = ScNone
    var editProxyConstructor: ScOption[SCClassName] = ScNone
    var endDate: ScOption[Date] = ScNone
    var enforceDate: ScOption[Boolean] = ScNone
    var invalidDateStringMessage: ScOption[String] = ScNone
    var maskDateSeparator: ScOption[String] = ScNone
    var monthSelector: ScOption[SelectItem] = ScNone
    var monthSelectorProperties: ScOption[SelectItem] = ScNone
    //override var pickerProperties: ScOption[DateChooser] = ScNone
    var pickerTimeItemProperties: ScOption[TimeItem] = ScNone
    var selectorFormat: ScOption[DateItemSelectorFormat] = ScNone
    var showChooserFiscalYearPicker: ScOption[Boolean] = ScNone
    var showChooserWeekPicker: ScOption[Boolean] = ScNone
    var showHintInField: ScOption[Boolean] = ScNone
    var showPickerTimeItem: ScOption[Boolean] = ScNone
    var startDate: ScOption[Date] = ScNone
    var textField: ScOption[TextItem] = ScNone
    var textFieldProperties: ScOption[TextItem] = ScNone
    var use24HourTime: ScOption[Boolean] = ScNone
    var useMask: ScOption[Boolean] = ScNone
    var usePlaceholderForHint: ScOption[Boolean] = ScNone
    var useSharedPicker: ScOption[Boolean] = ScNone
    var useTextField: ScOption[Boolean] = ScNone
    var yearSelector: ScOption[SelectItem] = ScNone
    var yearSelectorProperties: ScOption[SelectItem] = ScNone
    `type` = FormItemComponentType.DateItem
}
