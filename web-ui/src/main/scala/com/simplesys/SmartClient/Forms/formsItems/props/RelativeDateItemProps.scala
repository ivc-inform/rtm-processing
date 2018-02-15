package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.SmartClient.Forms.formsItems.formItem.FormItemIcon
import com.simplesys.SmartClient.Forms.formsItems.{BlurbItem, SpinnerItem, TimeItem}
import com.simplesys.System.JSObject
import com.simplesys.System.Types.FormItemComponentType
import com.simplesys.System.Types.RelativeDateRangePosition.RelativeDateRangePosition
import com.simplesys.option.{IntString, ScNone, ScOption}

import scala.scalajs.js
import scala.scalajs.js._

class RelativeDateItemProps extends CanvasItemProps {
    var baseDate: ScOption[Date] = ScNone
    var calculatedDateField: ScOption[BlurbItem ] = ScNone
    var centuryThreshold: ScOption[Int] = ScNone
    var daysAgoTitle: ScOption[String] = ScNone
    var daysFromNowTitle: ScOption[String] = ScNone
    var defaultQuantity: ScOption[Int] = ScNone
    var endDate: ScOption[Date] = ScNone
    var hoursAgoTitle: ScOption[String] = ScNone
    var hoursFromNowTitle: ScOption[String] = ScNone
    var millisecondsAgoTitle: ScOption[String] = ScNone
    var millisecondsFromNowTitle: ScOption[String] = ScNone
    var minutesFromNowTitle: ScOption[String] = ScNone
    var monthsAgoTitle: ScOption[String] = ScNone
    var monthsFromNowTitle: ScOption[String] = ScNone
    var pickerIcon: ScOption[FormItemIcon ] = ScNone
    var pickerTimeItemProperties: ScOption[TimeItem] = ScNone
    var presetOptions: ScOption[JSObject] = ScNone
    var quantityField: ScOption[SpinnerItem ] = ScNone
    var quartersAgoTitle: ScOption[String] = ScNone
    var quartersFromNowTitle: ScOption[String] = ScNone
    var rangePosition: ScOption[RelativeDateRangePosition] = ScNone
    var useSharedPicker: ScOption[Boolean] = ScNone
    var valueFieldWidth: ScOption[IntString[Int, String]] = ScNone
    var weeksAgoTitle: ScOption[String] = ScNone
    var weeksFromNowTitle: ScOption[String] = ScNone
    var yearsAgoTitle: ScOption[String] = ScNone
    var yearsFromNowTitle: ScOption[String] = ScNone
    `type` = FormItemComponentType.RelativeDateItem
}
