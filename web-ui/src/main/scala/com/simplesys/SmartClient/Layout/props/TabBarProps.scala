package com.simplesys.SmartClient.Layout.props

import com.simplesys.SmartClient.Layout.tabSet.Tab
import com.simplesys.SmartClient.System.KeyIdentifier
import com.simplesys.System.Types.SCImgURL
import com.simplesys.option.{ScOption, ScNone}

import scala.scalajs.js
import scala.scalajs.js._

class TabBarProps extends ToolbarProps {
    var baseLineCapSize: ScOption[Int] = ScNone
    var baseLineSrc: ScOption[SCImgURL] = ScNone
    var baseLineThickness: ScOption[Int] = ScNone
    var breadth: ScOption[Int] = ScNone
    var closeTabKeys: ScOption[Seq[KeyIdentifier]] = ScNone
    var defaultTabSize: ScOption[Int] = ScNone
    var moreTab: ScOption[Tab] = ScNone
    var moreTabCount: ScOption[Int] = ScNone
    var showMoreTab: ScOption[Boolean] = ScNone
    var tabs: ScOption[Seq[Tab]] = ScNone
}
