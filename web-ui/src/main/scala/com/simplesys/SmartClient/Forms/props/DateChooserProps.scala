package com.simplesys.SmartClient.Forms.props

import com.simplesys.SmartClient.Control.IButton
import com.simplesys.SmartClient.Forms.formsItems.TimeItem
import com.simplesys.SmartClient.Forms.{DateChooser, DateGrid}
import com.simplesys.SmartClient.Layout.HLayout
import com.simplesys.SmartClient.Layout.props.VLayoutProps
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.JSDictionaryAny
import com.simplesys.System.Types.{CSSStyleName, SCClassName, SCImgURL}
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js
import scala.scalajs.js._

class DateChooserProps extends VLayoutProps {
    var alternateStyleSuffix: ScOption[String] = ScNone
    var alternateWeekStyles: ScOption[Boolean] = ScNone
    var applyButton: ScOption[IButton] = ScNone
    var applyButtonTitle: ScOption[String] = ScNone
    var baseBottomButtonStyle: ScOption[CSSStyleName] = ScNone
    var baseButtonStyle: ScOption[CSSStyleName] = ScNone
    var baseFiscalYearStyle: ScOption[CSSStyleName] = ScNone
    var baseNavButtonStyle: ScOption[CSSStyleName] = ScNone
    var baseWeekdayStyle: ScOption[CSSStyleName] = ScNone
    var baseWeekendStyle: ScOption[CSSStyleName] = ScNone
    var baseWeekStyle: ScOption[CSSStyleName] = ScNone
    var buttonLayout: ScOption[HLayout] = ScNone
    var cancelButton: ScOption[IButton] = ScNone
    var cancelButtonTitle: ScOption[String] = ScNone
    var cancelClick: ScOption[js.ThisFunction0[DateChooser, _]] = ScNone
    var closeOnDateClick: ScOption[Boolean] = ScNone
    var closeOnEscapeKeypress: ScOption[Boolean] = ScNone
    var dataChanged: ScOption[js.ThisFunction0[DateChooser, _]] = ScNone
    var dateGrid: ScOption[DateGrid] = ScNone
    var dayNameLength: ScOption[Int] = ScNone
    var disabledDates: ScOption[IscArray[Date]] = ScNone
    var disabledWeekdayStyle: ScOption[CSSStyleName] = ScNone
    var disabledWeekendStyle: ScOption[CSSStyleName] = ScNone
    var disableWeekends: ScOption[Boolean] = ScNone
    var endYear: ScOption[Int] = ScNone
    var firstDayOfWeek: ScOption[Int] = ScNone
    var fiscalYearChooserButton: ScOption[IButton] = ScNone
    var fiscalYearFieldTitle: ScOption[String] = ScNone
    var fiscalYearHeaderStyle: ScOption[CSSStyleName] = ScNone
    var headerStyle: ScOption[CSSStyleName] = ScNone
    var monthChooserButton: ScOption[IButton] = ScNone
    var monthMenuStyle: ScOption[CSSStyleName] = ScNone
    var navButtonConstructor: ScOption[SCClassName] = ScNone
    var navigationLayout: ScOption[HLayout] = ScNone
    var nextMonthButton: ScOption[IButton] = ScNone
    var nextMonthIcon: ScOption[SCImgURL] = ScNone
    var nextMonthIconHeight: ScOption[Int] = ScNone
    var nextMonthIconRTL: ScOption[SCImgURL] = ScNone
    var nextMonthIconWidth: ScOption[Int] = ScNone
    var nextYearButton: ScOption[IButton] = ScNone
    var nextYearIcon: ScOption[SCImgURL] = ScNone
    var nextYearIconHeight: ScOption[Int] = ScNone
    var nextYearIconRTL: ScOption[SCImgURL] = ScNone
    var nextYearIconWidth: ScOption[Int] = ScNone
    var previousMonthButton: ScOption[IButton] = ScNone
    var prevMonthIcon: ScOption[SCImgURL] = ScNone
    var prevMonthIconHeight: ScOption[Int] = ScNone
    var prevMonthIconRTL: ScOption[SCImgURL] = ScNone
    var prevMonthIconWidth: ScOption[Int] = ScNone
    var previousYearButton: ScOption[IButton] = ScNone
    var prevYearIcon: ScOption[SCImgURL] = ScNone
    var prevYearIconHeight: ScOption[Int] = ScNone
    var prevYearIconRTL: ScOption[SCImgURL] = ScNone
    var prevYearIconWidth: ScOption[Int] = ScNone
    var selectedWeekStyle: ScOption[CSSStyleName] = ScNone
    var showApplyButton: ScOption[Boolean] = ScNone
    var showCancelButton: ScOption[Boolean] = ScNone
    var showDoubleYearIcon: ScOption[Boolean] = ScNone
    var showFiscalYearChooser: ScOption[Boolean] = ScNone
    var showSecondItem: ScOption[Boolean] = ScNone
    var showTimeItem: ScOption[Boolean] = ScNone
    var showTodayButton: ScOption[Boolean] = ScNone
    var showWeekChooser: ScOption[Boolean] = ScNone
    var showWeekends: ScOption[Boolean] = ScNone
    var startYear: ScOption[Int] = ScNone
    var timeItem: ScOption[TimeItem] = ScNone
    var timeItemProperties: ScOption[JSDictionaryAny] = ScNone
    var timeItemTitle: ScOption[String] = ScNone
    var todayButton: ScOption[IButton] = ScNone
    var todayButtonHeight: ScOption[Int] = ScNone
    var todayButtonTitle: ScOption[String] = ScNone
    var todayClick: ScOption[js.ThisFunction0[DateChooser, _]] = ScNone
    var use24HourTime: ScOption[Boolean] = ScNone
    var useFirstDayOfFiscalWeek: ScOption[Boolean] = ScNone
    var weekChooserButton: ScOption[IButton] = ScNone
    var weekendDays: ScOption[IscArray[Int]] = ScNone
    var weekendHeaderStyle: ScOption[String] = ScNone
    var weekFieldTitle: ScOption[String] = ScNone
    var weekHeaderStyle: ScOption[CSSStyleName] = ScNone
    var weekMenuStyle: ScOption[CSSStyleName] = ScNone
    var yearChooserButton: ScOption[IButton] = ScNone
    var yearMenuStyle: ScOption[CSSStyleName] = ScNone

}
