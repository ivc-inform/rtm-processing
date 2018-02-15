package com.simplesys.SmartClient.Forms.formsItems

import com.simplesys.SmartClient.Forms.DateChooser
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.SmartClient.System.date.Date
import com.simplesys.SmartClient.System.date.FiscalCalendar.FiscalCalendar
import com.simplesys.System.Types.DateDisplayFormat.DateDisplayFormat
import com.simplesys.System.Types.DateItemSelectorFormat.DateItemSelectorFormat
import com.simplesys.System.Types.{AutoChild, DateInputFormat, SCClassName, void}

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait DateItem extends FormItem {
    var browserInputType: String
    var centuryThreshold: Int
    var daySelector: SelectItem with AutoChild
    val daySelectorProperties: SelectItem
    var defaultChooserDate: Date
    def deselectValue(start: Boolean): void
    var displayFormat: DateDisplayFormat
    val editProxyConstructor: SCClassName
    var endDate: Date
    var enforceDate: Boolean
    def getDefaultChooserDate(): Date
    def getEnteredValue(): String
    def getFiscalCalendar(): FiscalCalendar
    def getInputFormat(): DateInputFormat
    def getSelectionRange(): IscArray[Date]
    var invalidDateStringMessage: String
    var maskDateSeparator: String
    var monthSelector: SelectItem with AutoChild
    val monthSelectorProperties: SelectItem
    var pickerProperties: DateChooser
    var pickerTimeItemProperties: TimeItem
    var selectorFormat: DateItemSelectorFormat
    def selectValue(): void
    def setEndDate(endDate: Date | String): void
    def setFiscalCalendar(fiscalCalendar: FiscalCalendar = js.native): void
    def setSelectionRange(start: Int, end: Int): void
    def setStartDate(endDate: Date | String): void
    var showChooserFiscalYearPicker: Boolean
    var showChooserWeekPicker: Boolean
    var showHintInField: Boolean
    var showPickerTimeItem: Boolean
    var startDate: Date
    var textField: TextItem with AutoChild
    var textFieldProperties: TextItem
    var use24HourTime: Boolean
    var useMask: Boolean
    var usePlaceholderForHint: Boolean
    var useSharedPicker: Boolean
    var useTextField: Boolean
    var yearSelector: SelectItem with AutoChild
    var yearSelectorProperties: SelectItem
}

@js.native
abstract class AbstractDateItemCompanion extends AbstractFormItemCompanion


