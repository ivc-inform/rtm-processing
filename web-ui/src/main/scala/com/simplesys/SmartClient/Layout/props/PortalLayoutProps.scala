package com.simplesys.SmartClient.Layout.props

import com.simplesys.SmartClient.Layout.Layout
import com.simplesys.SmartClient.Layout.portalLayout.Portlet
import com.simplesys.System.Types.Overflow.Overflow
import com.simplesys.System.Types.{MultiAutoChild, void}
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class PortalLayoutProps extends LayoutProps {
    var canResizeColumns: ScOption[Boolean] = ScNone
    var canResizePortlets: ScOption[Boolean] = ScNone
    var canShrinkColumnWidths: ScOption[Boolean] = ScNone
    var canStretchColumnWidths: ScOption[Boolean] = ScNone
    var column: ScOption[Layout with MultiAutoChild] = ScNone
    var columnBorder: ScOption[String] = ScNone
    var columnOverflow: ScOption[Overflow] = ScNone
    var numColumns: ScOption[Int] = ScNone
    var portletDropTypes: ScOption[Seq[String]] = ScNone
    var portlets: ScOption[Seq[Portlet]] = ScNone
    var portletsChanged: ScOption[js.ThisFunction0[classHandler, void]] = ScNone
    var portletsResized: ScOption[js.ThisFunction0[classHandler, void]] = ScNone
    var preventColumnUnderflow: ScOption[Boolean] = ScNone
    var preventRowUnderflow: ScOption[Boolean] = ScNone
    var preventUnderflow: ScOption[Boolean] = ScNone
    var row: ScOption[Layout with MultiAutoChild] = ScNone
    var rowLayout: ScOption[Layout with MultiAutoChild] = ScNone
    var showColumnMenus: ScOption[Boolean] = ScNone
    var stretchColumnWidthsProportionally: ScOption[Boolean] = ScNone
    var willMaximizePortlet: ScOption[js.ThisFunction1[classHandler, Portlet, Boolean]] = ScNone
    var willMinimizePortlet: ScOption[js.ThisFunction1[classHandler, Portlet, Boolean]] = ScNone
    var willRestorePortlet: ScOption[js.ThisFunction1[classHandler, Portlet, Boolean]] = ScNone

}
