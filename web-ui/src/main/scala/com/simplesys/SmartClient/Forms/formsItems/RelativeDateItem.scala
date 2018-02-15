package com.simplesys.SmartClient.Forms.formsItems

import com.simplesys.SmartClient.DataBinding.Criterion
import com.simplesys.SmartClient.Forms.formsItems.formItem.FormItemIcon
import com.simplesys.SmartClient.System.date.Date
import com.simplesys.SmartClient.System.date.FiscalCalendar.FiscalCalendar
import com.simplesys.System.JSObject
import com.simplesys.System.Types.RelativeDateRangePosition.RelativeDateRangePosition
import com.simplesys.System.Types.{AutoChild, DateInputFormat}

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait RelativeDateItem extends CanvasItem {
    val baseDate: Date
    val calculatedDateField: BlurbItem with AutoChild
    var centuryThreshold: Int
    var daysAgoTitle: String
    var daysFromNowTitle: String
    val defaultQuantity: Int
    var endDate: Date
    def getCriterion(absolute: Boolean = js.native): Criterion
    def getEnteredValue(): String
    def getFiscalCalendar(): FiscalCalendar
    def getInputFormat(): DateInputFormat
    def getRelativeDate(): JSObject
    var hoursAgoTitle: String
    var hoursFromNowTitle: String
    var millisecondsAgoTitle: String
    var millisecondsFromNowTitle: String
    var minutesFromNowTitle: String
    var monthsAgoTitle: String
    var monthsFromNowTitle: String
    var pickerIcon: FormItemIcon with AutoChild
    val pickerTimeItemProperties: TimeItem
    val presetOptions: JSObject
    val quantityField: SpinnerItem with AutoChild
    var quartersAgoTitle: String
    var quartersFromNowTitle: String
    val rangePosition: RelativeDateRangePosition
    val useSharedPicker: Boolean
    val valueFieldWidth: Int | String
    val weeksAgoTitle: String
    val weeksFromNowTitle: String
    val yearsAgoTitle: String
    val yearsFromNowTitle: String
}


