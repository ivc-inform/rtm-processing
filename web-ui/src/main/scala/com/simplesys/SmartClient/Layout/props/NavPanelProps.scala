package com.simplesys.SmartClient.Layout.props

import com.simplesys.SmartClient.Grids.TreeGrid
import com.simplesys.SmartClient.Layout.Deck
import com.simplesys.SmartClient.Layout.navPanel.NavItem
import com.simplesys.System.Types.CSSStyleName
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class NavPanelProps extends SplitPanelProps {
    var currentItem: ScOption[NavItem] = ScNone
    var currentItemId: ScOption[String] = ScNone
    var headerStyle: ScOption[CSSStyleName] = ScNone
    var isTree: ScOption[Boolean] = ScNone
    var navDeck: ScOption[Deck] = ScNone
    var navGrid: ScOption[TreeGrid] = ScNone
    var navItems: ScOption[Seq[NavItem]] = ScNone

}
