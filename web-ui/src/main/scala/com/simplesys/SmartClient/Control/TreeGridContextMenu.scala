package com.simplesys.SmartClient.Control

import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.JSUndefined

import scala.scalajs.js

@js.native
trait TreeGridContextMenu extends MenuSS {
    var customMenu: JSUndefined[IscArray[MenuSSItem]]
}

//@js.native
//abstract trait AbstractTreeGridContextMenuCompanion extends AbstractMenuSSCompanion {
//}


