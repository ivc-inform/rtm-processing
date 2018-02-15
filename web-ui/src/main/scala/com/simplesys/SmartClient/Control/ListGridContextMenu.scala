package com.simplesys.SmartClient.Control

import com.simplesys.SmartClient.App.props.MenuItemType.MenuItemType
import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.JSUndefined

import scala.scalajs.js

@js.native
trait ListGridContextMenu extends MenuSS {
    val customMenu: JSUndefined[IscArray[MenuSSItem]]
    var itemsType: JSUndefined[IscArray[MenuItemType]]
}

//@js.native
//abstract trait AbstractListGridContextMenuCompanion extends AbstractMenuSSCompanion {
//}


