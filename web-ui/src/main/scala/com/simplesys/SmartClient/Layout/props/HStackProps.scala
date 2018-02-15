package com.simplesys.SmartClient.Layout.props

import com.simplesys.System.Types.SCImgURL
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class HStackProps extends HLayoutProps {
    var largeIcon: ScOption[SCImgURL] = ScNone
    var largeIconSize: ScOption[Int] = ScNone
    var menuIconClick: ScOption[js.ThisFunction0[classHandler, Boolean]] = ScNone
    var menuIconHeight: ScOption[Int] = ScNone
    var menuIconSrc: ScOption[SCImgURL] = ScNone
    var menuIconWidth: ScOption[Int] = ScNone
    var orientation: ScOption[String] = ScNone
    var rowSpan: ScOption[Int] = ScNone
    var showButtonTitle: ScOption[Boolean] = ScNone
    var showIcon: ScOption[Boolean] = ScNone
    var showMenuIcon: ScOption[Boolean] = ScNone
    var showMenuIconDisabled: ScOption[Boolean] = ScNone
    var showMenuIconOver: ScOption[Boolean] = ScNone
    var showMenuOnClick: ScOption[Boolean] = ScNone
    var showTitle: ScOption[Boolean] = ScNone
}
