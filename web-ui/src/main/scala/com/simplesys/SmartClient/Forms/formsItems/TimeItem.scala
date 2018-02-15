package com.simplesys.SmartClient.Forms.formsItems

import com.simplesys.SmartClient.System.{AbstractClassCompanion, IscArray}
import com.simplesys.System.Types.TimeDisplayFormat.TimeDisplayFormat
import com.simplesys.System.Types.TimeUnit.TimeUnit
import com.simplesys.System.Types.{AutoChild, HTMLString, void}

import scala.scalajs.js

@js.native
trait TimeItem extends FormItem {
    val ampmItem: SelectItem with AutoChild
    val ampmItemProperties: SelectItem
    var ampmItemTitle: HTMLString
    var browserInputType: String
    def deselectValue(start: Boolean = js.native): void
    def getDuration(timeUnit: TimeUnit = js.native): Int
    def getEnteredValue(): String
    def getHourValues(): IscArray[Int]
    def getMillisecondValues(): IscArray[Int]
    def getMinuteValues(): IscArray[Int]
    def getSecondValues(): IscArray[Int]
    def getSelectionRange(): IscArray[Int]
    var hourIncrement: Int
    var hourItem: SelectItem with AutoChild
    var hourItemPrompt: HTMLString
    var hourItemProperties: SelectItem
    var hourItemTitle: HTMLString
    var hourMaxValue: Int
    var hourMinValue: Int
    var hourValues: IscArray[Int]
    var invalidTimeStringMessage: HTMLString
    var millisecondIncrement: Int
    var millisecondItem: SelectItem with AutoChild
    var millisecondItemPrompt: HTMLString
    var millisecondItemProperties: SelectItem
    var millisecondItemTitle: HTMLString
    var millisecondMaxValue: Int
    var millisecondMinValue: Int
    var millisecondValues: IscArray[Int]
    var minuteIncrement: Int
    var minuteItem: SelectItem with AutoChild
    var minuteItemPrompt: HTMLString
    var minuteItemProperties: SelectItem
    var minuteItemTitle: HTMLString
    var minuteMaxValue: Int
    var minuteMinValue: Int
    var minuteValues: IscArray[Int]
    var secondIncrement: Int
    var secondItem: SelectItem with AutoChild
    var secondItemPrompt: HTMLString
    var secondItemProperties: SelectItem
    var secondItemTitle: HTMLString
    var secondMaxValue: Int
    var secondMinValue: Int
    var secondValues: IscArray[Int]
    def selectValue(): void
    def setHours(hours: Int): void
    def setHourValues(values: IscArray[Int]): void
    def setMilliseconds(milliseconds: Int): void
    def setMillisecondValues(values: IscArray[Int]): void
    def setsetMinutes(milliseconds: Int): void
    def setsetMinutesValues(values: IscArray[Int]): void
    def setSeconds(milliseconds: Int): void
    def setSecondValues(values: IscArray[Int]): void
    def setSelectionRange(start: Int, end: Int): void
    var showHintInField:Boolean
    var showHourItem:Boolean
    var showMillisecondItem:Boolean
    var showMinuteItem:Boolean
    var showSecondItem:Boolean
    var textField:TextItem with AutoChild
    var textFieldProperties:TextItem
    var timeFormatter12Hour:TimeDisplayFormat
    var timeFormatter24Hour:TimeDisplayFormat
    var use24HourTime:Boolean
    var useMask:Boolean
    var usePlaceholderForHint:Boolean
    var useTextField:Boolean
}

@js.native
abstract class AbstractTimeItemCompanion extends AbstractFormItemCompanion

