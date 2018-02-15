package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.SmartClient.Forms.formsItems.{FormItem, SelectItem, TextItem, TimeItem}
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.TimeDisplayFormat.TimeDisplayFormat
import com.simplesys.System.Types.{FormItemComponentType, HTMLString}
import com.simplesys.option.{ScNone, ScOption}

class TimeItemProps extends FormItemProps {
    type classHandler <: TimeItem

    var ampmItem: ScOption[SelectItem ] = ScNone
    var ampmItemProperties: ScOption[SelectItem] = ScNone
    var ampmItemTitle: ScOption[HTMLString] = ScNone
    var browserInputType: ScOption[String] = ScNone
    var hourIncrement: ScOption[Int] = ScNone
    var hourItem: ScOption[SelectItem ] = ScNone
    var hourItemPrompt: ScOption[HTMLString] = ScNone
    var hourItemProperties: ScOption[SelectItem] = ScNone
    var hourItemTitle: ScOption[HTMLString] = ScNone
    var hourMaxValue: ScOption[Int] = ScNone
    var hourMinValue: ScOption[Int] = ScNone
    var hourValues: ScOption[IscArray[Int]] = ScNone
    var invalidTimeStringMessage: ScOption[HTMLString] = ScNone
    var millisecondIncrement: ScOption[Int] = ScNone
    var millisecondItem: ScOption[SelectItem ] = ScNone
    var millisecondItemPrompt: ScOption[HTMLString] = ScNone
    var millisecondItemProperties: ScOption[SelectItem] = ScNone
    var millisecondItemTitle: ScOption[HTMLString] = ScNone
    var millisecondMaxValue: ScOption[Int] = ScNone
    var millisecondMinValue: ScOption[Int] = ScNone
    var millisecondValues: ScOption[IscArray[Int]] = ScNone
    var minuteIncrement: ScOption[Int] = ScNone
    var minuteItem: ScOption[SelectItem ] = ScNone
    var minuteItemPrompt: ScOption[HTMLString] = ScNone
    var minuteItemProperties: ScOption[SelectItem] = ScNone
    var minuteItemTitle: ScOption[HTMLString] = ScNone
    var minuteMaxValue: ScOption[Int] = ScNone
    var minuteMinValue: ScOption[Int] = ScNone
    var minuteValues: ScOption[IscArray[Int]] = ScNone
    var secondIncrement: ScOption[Int] = ScNone
    var secondItem: ScOption[SelectItem ] = ScNone
    var secondItemPrompt: ScOption[HTMLString] = ScNone
    var secondItemProperties: ScOption[SelectItem] = ScNone
    var secondItemTitle: ScOption[HTMLString] = ScNone
    var secondMaxValue: ScOption[Int] = ScNone
    var secondMinValue: ScOption[Int] = ScNone
    var secondValues: ScOption[IscArray[Int]] = ScNone
    var showHintInField: ScOption[Boolean] = ScNone
    var showHourItem: ScOption[Boolean] = ScNone
    var showMillisecondItem: ScOption[Boolean] = ScNone
    var showMinuteItem: ScOption[Boolean] = ScNone
    var showSecondItem: ScOption[Boolean] = ScNone
    var textField: ScOption[TextItem ] = ScNone
    var textFieldProperties: ScOption[TextItem] = ScNone
    var timeFormatter12Hour: ScOption[TimeDisplayFormat] = ScNone
    var timeFormatter24Hour: ScOption[TimeDisplayFormat] = ScNone
    var use24HourTime: ScOption[Boolean] = ScNone
    var useMask: ScOption[Boolean] = ScNone
    var usePlaceholderForHint: ScOption[Boolean] = ScNone
    var useTextField: ScOption[Boolean] = ScNone
    `type` = FormItemComponentType.TimeItem
}
