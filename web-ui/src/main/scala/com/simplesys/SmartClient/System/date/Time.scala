package com.simplesys.SmartClient.System.date

import com.simplesys.SmartClient.System.AbstractClassCompanion
import com.simplesys.System.Types.TimeDisplayFormat.TimeDisplayFormat
import com.simplesys.System.Types.void

import scala.scalajs.js

@js.native
trait Time extends com.simplesys.SmartClient.System.Class {
}

@js.native
abstract trait AbstractTimeCompanion extends AbstractClassCompanion {
    def format(date: Date, formatter: String, shortFormat: Boolean = js.native, logicalTime: Boolean = js.native): String = js.native
    val adjustForDST: Boolean = js.native
    val AMIndicator: String = js.native
    def compareTimes(time1: Date, time2: Date): Boolean = js.native
    def createLogicalTime(hour: Int, minute: Int, second: Int): Date = js.native
    val displayFormat: TimeDisplayFormat = js.native
    def getDefaultDisplayTimezone(): String = js.native
    def parseInput(timeString: String, validTime: Boolean): void = js.native
    val PMIndicator: String = js.native
    def setDefaultDisplayTimezone(offset: String): void = js.native
    def setNormalDisplayFormat(formatter: TimeDisplayFormat): void = js.native
    def setShortDisplayFormat(formatter: TimeDisplayFormat): void = js.native
    val shortDisplayFormat: TimeDisplayFormat = js.native
    def toShortTime(date: Date, formatter: TimeDisplayFormat = js.native, logicalTime: Date = js.native): void
    def toTime(date: Date, formatter: TimeDisplayFormat = js.native, logicalTime: Date = js.native): void
}

/*@js.native
object Time extends AbstractTimeCompanion*/
