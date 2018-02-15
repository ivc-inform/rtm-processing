package com.simplesys.SmartClient.Control.props

import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Grids.treeGrid.Tree
import com.simplesys.System.Types.{CSSStyleName, HTMLString, SCClassName}
import com.simplesys.option.{ScNone, ScOption}

class TreeMenuButtonProps extends MenuButtonProps {
    var dataProperties: ScOption[Tree] = ScNone
    var emptyMenuMessage: ScOption[HTMLString] = ScNone
    var pathSeparatorString: ScOption[HTMLString] = ScNone
    var selectedBaseStyle: ScOption[CSSStyleName] = ScNone
    var showPath: ScOption[Boolean] = ScNone
    var treeMenu: ScOption[MenuSSItem] = ScNone
    var treeMenuConstructor: ScOption[SCClassName] = ScNone
    var unselectedTitle: ScOption[HTMLString] = ScNone
}
