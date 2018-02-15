package com.simplesys.SmartClient.System.date

import com.simplesys.SmartClient.System.IscArray
import com.simplesys.SmartClient.System.date.FiscalYear.FiscalYear
import com.simplesys.System.JSObject
import com.simplesys.System.Types.FiscalYearMode.FiscalYearMode

import scala.scalajs.js

@js.native
object FiscalCalendar extends JSObject {
    type FiscalCalendar = FiscalCalendar.type

    var defaultDate: Int = js.native
    var defaultMonth: Int = js.native
    var defaultYearMode:FiscalYearMode = js.native
    var fiscalYears:IscArray[FiscalYear] = js.native
}
