package com.simplesys.SmartClient.App

import com.simplesys.SmartClient.Control.{AbstractMenuSSCompanion, MenuSS}
import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.JSUndefined

import scala.scalajs.js

@js.native
trait TreeListGridContextMenu extends MenuSS {
    var simpleTableTree: Boolean
    var simpleTableList: Boolean

    var captionMenuTree: String
    var captionMenuList: String

    var customMenuTree: JSUndefined[IscArray[MenuSSItem]]
    var customMenuList: JSUndefined[IscArray[MenuSSItem]]

    var customSubMenuItem: JSUndefined[MenuSSItem]
}

@js.native
abstract trait AbstractUser_ComponentMenuCompanion extends AbstractMenuSSCompanion {
}


