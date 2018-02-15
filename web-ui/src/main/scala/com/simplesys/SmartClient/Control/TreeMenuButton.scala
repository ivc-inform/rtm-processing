package com.simplesys.SmartClient.Control

import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Grids.treeGrid.Tree
import com.simplesys.System.Types.{SCClassName, AutoChild, CSSStyleName, HTMLString}

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait TreeMenuButton extends MenuButton {
    var dataProperties: Tree
    var emptyMenuMessage: HTMLString
    var pathSeparatorString: HTMLString
    var selectedBaseStyle: CSSStyleName
    var showPath: Boolean
    var treeMenu: MenuSSItem with AutoChild
    val treeMenuConstructor: SCClassName
    var unselectedTitle: HTMLString
}


