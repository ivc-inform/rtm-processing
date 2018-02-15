package com.simplesys.SmartClient.System.date

import com.simplesys.SmartClient.System.IscArray
import com.simplesys.SmartClient.System.date.FiscalCalendar.FiscalCalendar
import com.simplesys.SmartClient.System.date.FiscalYear.FiscalYear
import com.simplesys.System.JSObject
import com.simplesys.System.Types.DateDisplayFormat.DateDisplayFormat
import com.simplesys.System.Types.void

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait Date extends JSObject {
    def duplicate(): Date
    def getDayName(): String
    def getDefaultDateSeparator(): String
    def getFiscalWeek(fiscalCalendar: FiscalCalendar): Int
    def getFiscalYear(): FiscalYear
    def getMonthName(): String
    def getShortYear(): String
    def getShortDayName(length: Int): String
    def getShortMonthName(length: Int): String
    def getWeek(): Int
    def setDefaultDateSeparator(separator: String): void
    def toDateStamp(): String
    def toEuropeanShortDate(useCustomTimezone: Boolean = js.native): String
    def toEuropeanShortDateTime(useCustomTimezone: Boolean = js.native): String
    def toJapanShortDate(): String
    def toJapanShortDateTime(): String
    def toNormalDate(format: DateDisplayFormat): String
    def toNormalDatetime(format: DateDisplayFormat, useCustomTimezone: Boolean = js.native): String
    def toShortDate(format: DateDisplayFormat, useCustomTimezone: Boolean = js.native): String
    def toShortDateTime(format: DateDisplayFormat, useCustomTimezone: Boolean = js.native): String
    def toPrettyString(): String
    def toSerializeableDate(): String
    def toUSShortDate(): String
    def toUSShortDateTime(): String
}

@js.native
abstract trait AbstractDateCompanion extends JSObject {
    val prototype: JSObject = js.native
    def combineLogicalDateAndTime(date: Date, time: Date): Date = js.native
    def compareDates(date1: Date, date2: Date): Int = js.native
    def compareLogicalDates(date1: Date, date2: Date): Int = js.native
    def create(): Date = js.native
    def createLogicalDate(year: Int, month: Int, date: Int): Date = js.native
    def createLogicalTime(hour: Int, minute: Int, second: Int): Date = js.native
    var dayNames: IscArray[String] = js.native
    def getDefaultDateSeparator(): String = js.native
    def getFirstDayOfWeek(): Int = js.native
    def getFiscalCalendar(): FiscalCalendar = js.native
    def getFiscalStartDate(date: Date | Int, fiscalCalendar: FiscalCalendar = js.native): Date = js.native
    def getFiscalYear(date: Date | Int, fiscalCalendar: FiscalCalendar = js.native): FiscalYear = js.native
    def getFiscalWeek(date: Date, fiscalCalendar: FiscalCalendar = js.native): Int = js.native
    def getInputFormat(): String = js.native
    def getLogicalDateOnly(date: Date): Date = js.native
    def getLogicalTimeOnly(date: Date): Date = js.native
    def getWeekendDays(): IscArray[Int] = js.native
    var monthNames: IscArray[String] = js.native
    def parseInput(dateString: String, format: String = js.native, centuryThreshold: Int = js.native, suppressConversion: Boolean = js.native): Date = js.native
    def setDefaultDateSeparator(separator: String): void = js.native
    def setFirstDayOfWeek(firstDayOfWeek: Int): void = js.native
    def setFiscalCalendar(fiscalCalendar: FiscalCalendar): void = js.native
    def setInputFormat(format: String): void = js.native
    def setNormalDatetimeDisplayFormat(format: String | DateDisplayFormat): void = js.native
    def setNormalDisplayFormat(format: String | DateDisplayFormat): void = js.native
    def setShortDatetimeDisplayFormat(format: String | DateDisplayFormat): void = js.native
    def setShortDisplayFormat(format: String | DateDisplayFormat): void = js.native
    def setShowChooserFiscalYearPickers(showChooserFiscalYearPickers: Boolean): void = js.native
    def setShowChooserWeekPickers(showChooserWeekPickers: Boolean): void = js.native
    def setWeekendDays(weekendDays: IscArray[Int]): void = js.native
    var shortDayNames: IscArray[String] = js.native
    var shortMonthNames: IscArray[String] = js.native
    var weekendDays: IscArray[Int] = js.native
}


