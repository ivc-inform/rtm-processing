package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.SmartClient.Forms.DynamicForm
import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.System.Types.DateFieldLayout.DateFieldLayout
import com.simplesys.System.Types.{FormItemComponentType, RelativeDateString}
import com.simplesys.System.Types.TimeUnit.TimeUnit
import com.simplesys.System.Types.TitleOrientation.TitleOrientation
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js._

class DateRangeItemProps extends CanvasItemProps {
    var absoluteDateTimeItemConstructor: ScOption[String] = ScNone
    var absoluteItemConstructor: ScOption[String] = ScNone
    var allowRelativeDates: ScOption[Boolean] = ScNone
    var dateRangeForm: ScOption[DynamicForm ] = ScNone
    var fieldLayout: ScOption[DateFieldLayout] = ScNone
    var fromDate: ScOption[Date] = ScNone
    var fromField: ScOption[FormItem ] = ScNone
    var fromTitle: ScOption[String] = ScNone
    var innerTitleOrientation: ScOption[TitleOrientation] = ScNone
    var invalidRangeErrorMessage: ScOption[String] = ScNone
    var relativeItemConstructor: ScOption[String] = ScNone
    var toDate: ScOption[Date] = ScNone
    var toField: ScOption[FormItem ] = ScNone
    var toTitle: ScOption[String] = ScNone
    var validateCriteria: ScOption[Boolean] = ScNone
    `type` = FormItemComponentType.DateRangeItem
}
