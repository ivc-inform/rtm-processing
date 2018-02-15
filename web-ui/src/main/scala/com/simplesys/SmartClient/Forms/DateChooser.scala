package com.simplesys.SmartClient.Forms

import com.simplesys.SmartClient.Control.IButton
import com.simplesys.SmartClient.Forms.formsItems.TimeItem
import com.simplesys.SmartClient.Layout.{AbstractVLayoutCompanion, HLayout, VLayout}
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.SmartClient.System.date.Date
import com.simplesys.SmartClient.System.date.FiscalCalendar.FiscalCalendar
import com.simplesys.System.JSDictionaryAny
import com.simplesys.System.Types.{void, _}

import scala.scalajs.js

@js.native
trait DateChooser extends VLayout {
    var alternateStyleSuffix: String
    var alternateWeekStyles: Boolean
    val applyButton: IButton with AutoChild
    var applyButtonTitle: String
    var baseBottomButtonStyle: CSSStyleName
    var baseButtonStyle: CSSStyleName
    var baseFiscalYearStyle: CSSStyleName
    var baseNavButtonStyle: CSSStyleName
    var baseWeekdayStyle: CSSStyleName
    var baseWeekendStyle: CSSStyleName
    var baseWeekStyle: CSSStyleName
    val buttonLayout: HLayout with AutoChild
    val cancelButton: IButton with AutoChild
    var cancelButtonTitle: String
    var cancelClick: js.ThisFunction0[DateChooser, _]
    var closeOnDateClick: Boolean
    var closeOnEscapeKeypress: Boolean
    var dataChanged: js.ThisFunction0[DateChooser, _]
    val dateGrid: DateGrid with AutoChild
    val dayNameLength: Int
    var disabledDates: IscArray[Date]
    var disabledWeekdayStyle: CSSStyleName
    var disabledWeekendStyle: CSSStyleName
    val disableWeekends: Boolean
    val endYear: Int
    val firstDayOfWeek: Int
    val fiscalYearChooserButton: IButton with AutoChild
    var fiscalYearFieldTitle: String
    var fiscalYearHeaderStyle: CSSStyleName
    def getData(): Date
    def getFiscalCalendar(): FiscalCalendar
    def getHeaderYearTitle(year: Int): String
    def getYearTitle(year: Int): String
    var headerStyle: CSSStyleName
    val monthChooserButton: IButton with AutoChild
    val monthMenuStyle: CSSStyleName
    val navButtonConstructor: SCClassName
    val navigationLayout: HLayout with AutoChild
    val nextMonthButton: IButton with AutoChild
    var nextMonthIcon: SCImgURL
    var nextMonthIconHeight: Int
    var nextMonthIconRTL: SCImgURL
    var nextMonthIconWidth: Int
    val nextYearButton: IButton with AutoChild
    var nextYearIcon: SCImgURL
    var nextYearIconHeight: Int
    var nextYearIconRTL: SCImgURL
    var nextYearIconWidth: Int
    val previousMonthButton: IButton with AutoChild
    var prevMonthIcon: SCImgURL
    var prevMonthIconHeight: Int
    var prevMonthIconRTL: SCImgURL
    var prevMonthIconWidth: Int
    val previousYearButton: IButton with AutoChild
    var prevYearIcon: SCImgURL
    var prevYearIconHeight: Int
    var prevYearIconRTL: SCImgURL
    var prevYearIconWidth: Int
    var selectedWeekStyle: CSSStyleName
    def setData(date: Date): void
    def setFiscalCalendar(fiscalCalendar: FiscalCalendar = js.native): void
    var showApplyButton: Boolean
    var showCancelButton: Boolean
    var showDoubleYearIcon: Boolean
    var showFiscalYearChooser: Boolean
    var showSecondItem: Boolean
    var showTimeItem: Boolean
    var showTodayButton: Boolean
    var showWeekChooser: Boolean
    var showWeekends: Boolean
    var startYear: Int
    val timeItem: TimeItem with AutoChild
    val timeItemProperties: JSDictionaryAny
    var timeItemTitle: String
    val todayButton: IButton with AutoChild
    var todayButtonHeight: Int
    var todayButtonTitle: String
    var todayClick: js.ThisFunction0[DateChooser, _]
    var use24HourTime: Boolean
    var useFirstDayOfFiscalWeek: Boolean
    val weekChooserButton: IButton with AutoChild
    var weekendDays: IscArray[Int]
    var weekendHeaderStyle: String
    var weekFieldTitle: String
    var weekHeaderStyle: CSSStyleName
    var weekMenuStyle: CSSStyleName
    val yearChooserButton: IButton with AutoChild
    val yearMenuStyle: CSSStyleName
}

@js.native
abstract trait AbstractDateChooserCompanion extends AbstractVLayoutCompanion {

}



