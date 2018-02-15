package com.simplesys.SmartClient.Layout.props

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Layout.NavigationBar
import com.simplesys.SmartClient.Layout.navigationBar.NavigationButton
import com.simplesys.System.Types.CurrentPane.CurrentPane
import com.simplesys.System.Types.DeviceMode.DeviceMode
import com.simplesys.System.Types.PageOrientation.PageOrientation
import com.simplesys.System.Types.{HTMLString, void}
import com.simplesys.option.{ScOption, ScNone}

import scala.scalajs.js

class SplitPanelProps extends LayoutProps {
    var addHistoryEntries: ScOption[Boolean] = ScNone
    var animateNavigationBarStateChanges: ScOption[Boolean] = ScNone
    var autoNavigate: ScOption[Boolean] = ScNone
    var backButton: ScOption[NavigationButton] = ScNone
    var currentPane: ScOption[CurrentPane] = ScNone
    var detailNavigationControl: ScOption[Canvas] = ScNone
    var detailPane: ScOption[Canvas] = ScNone
    var detailPaneTitleTemplate: ScOption[HTMLString] = ScNone
    var detailTitle: ScOption[HTMLString] = ScNone
    var detailToolButtons: ScOption[Seq[Canvas]] = ScNone
    var detailToolStrip: ScOption[NavigationBar] = ScNone
    var deviceMode: ScOption[DeviceMode] = ScNone
    var downClick: ScOption[js.ThisFunction0[classHandler, void]] = ScNone
    var leftButton: ScOption[NavigationButton] = ScNone
    var listPane: ScOption[Canvas] = ScNone
    var listPaneTitleTemplate: ScOption[HTMLString] = ScNone
    var listTitle: ScOption[HTMLString] = ScNone
    var listToolStrip: ScOption[NavigationButton] = ScNone
    var navigationBar: ScOption[NavigationBar] = ScNone
    var navigationClick: ScOption[js.ThisFunction0[classHandler, void]] = ScNone
    var navigationPane: ScOption[Canvas] = ScNone
    var navigationTitle: ScOption[HTMLString] = ScNone
    var pageOrientation: ScOption[PageOrientation] = ScNone
    var paneChanged: ScOption[js.ThisFunction1[classHandler, CurrentPane, void]] = ScNone
    var showDetailToolStrip: ScOption[Boolean] = ScNone
    var showLeftButton: ScOption[Boolean] = ScNone
    var showListToolStrip: ScOption[Boolean] = ScNone
    var showMiniNav: ScOption[Boolean] = ScNone
    var showNavigationBar: ScOption[Boolean] = ScNone
    var showResizeBars: ScOption[Boolean] = ScNone
    var showRightButton: ScOption[Boolean] = ScNone
    var upClick: ScOption[js.ThisFunction0[classHandler, void]] = ScNone
}
