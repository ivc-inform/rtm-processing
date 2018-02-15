package com.simplesys.SmartClient.Layout.props.navPanel

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.props.treeGrid.TreeNodeProps
import com.simplesys.SmartClient.Layout.navPanel.NavItem
import com.simplesys.System.Types.CSSStyleName
import com.simplesys.option.{ScOption, ScNone}

import scala.scalajs.js
import scala.scalajs.js._

class NavItemProps extends TreeNodeProps {
    var customStyle: ScOption[CSSStyleName] = ScNone
    var isHeader: ScOption[Boolean] = ScNone
    var isSeparator: ScOption[Boolean] = ScNone
    var items: ScOption[Seq[NavItem]] = ScNone
    var pane: ScOption[Canvas | String] = ScNone
}
