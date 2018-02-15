package com.simplesys.SmartClient.Layout.props

import com.simplesys.SmartClient.Foundation.{Canvas, Label}
import com.simplesys.SmartClient.Layout.navigationBar.{MiniNavControl, NavigationButton}
import com.simplesys.System.Types.Alignment.Alignment
import com.simplesys.System.Types.NavigationDirection.NavigationDirection
import com.simplesys.System.Types.{SCImgURL, HTMLString, void}
import com.simplesys.option.{ScOption, ScNone}

import scala.scalajs.js

class NavigationBarProps extends HLayoutProps {
    var alwaysShowLeftButtonTitle: ScOption[Boolean] = ScNone
    var animateStateChanges: ScOption[Boolean] = ScNone
    var customNavControl: ScOption[Canvas] = ScNone
    var downClick: ScOption[js.ThisFunction0[classHandler, void]] = ScNone
    var leftButton: ScOption[NavigationButton] = ScNone
    var leftButtonIcon: ScOption[SCImgURL] = ScNone
    var leftButtonTitle: ScOption[HTMLString] = ScNone
    var maxCenterOffset: ScOption[Int] = ScNone
    var miniNavAlign: ScOption[Alignment] = ScNone
    var miniNavControl: ScOption[MiniNavControl] = ScNone
    var navigationClick: ScOption[js.ThisFunction1[classHandler, NavigationDirection, void]] = ScNone
    var rightButton: ScOption[NavigationButton] = ScNone
    var rightButtonIcon: ScOption[SCImgURL] = ScNone
    var rightButtonTitle: ScOption[HTMLString] = ScNone
    var shortLeftButtonTitle: ScOption[HTMLString] = ScNone
    var showLeftButton: ScOption[Boolean] = ScNone
    var showMiniNavControl: ScOption[Boolean] = ScNone
    var showRightButton: ScOption[Boolean] = ScNone
    var title: ScOption[HTMLString] = ScNone
    var titleLabel: ScOption[Label] = ScNone
    var upClick: ScOption[js.ThisFunction0[classHandler, void]] = ScNone
}
