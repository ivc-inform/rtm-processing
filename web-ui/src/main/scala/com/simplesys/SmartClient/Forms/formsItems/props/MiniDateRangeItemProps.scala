package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.SmartClient.Forms.DateRangeDialog
import com.simplesys.SmartClient.Forms.formsItems.formItem.FormItemIcon
import com.simplesys.System.Types.DateDisplayFormat.DateDisplayFormat
import com.simplesys.System.Types.{FormItemComponentType, RelativeDateString}
import com.simplesys.System.Types.TimeUnit.TimeUnit
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js._

class MiniDateRangeItemProps extends StaticTextItemProps {
    var allowRelativeDates: ScOption[Boolean] = ScNone
    var autoValidate: ScOption[Boolean] = ScNone
    var dateDisplayFormat: ScOption[DateDisplayFormat] = ScNone
    var fromDate: ScOption[Date] = ScNone
    var fromDateOnlyPrefix: ScOption[String] = ScNone
    var pickerIcon: ScOption[FormItemIcon] = ScNone
    var rangeDialog: ScOption[DateRangeDialog ] = ScNone
    var toDate: ScOption[Date] = ScNone
    var toDateOnlyPrefix: ScOption[String] = ScNone
    `type` = FormItemComponentType.MiniDateRangeItem
}
