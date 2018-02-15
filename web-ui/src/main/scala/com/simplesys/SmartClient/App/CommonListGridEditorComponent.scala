package com.simplesys.SmartClient.App

import com.simplesys.SmartClient.App.props.MenuItemType.MenuItemType
import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Grids.{AbstractListGridEditorCompanion, ListGridEditor}
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.JSUndefined

import scala.scalajs.js

@js.native
trait CommonListGridEditorComponent extends ListGridEditor {

    var customMenu: JSUndefined[IscArray[MenuSSItem]]
    var itemsType: JSUndefined[IscArray[MenuItemType]]

}

@js.native
abstract trait AbstractCommonListGridEditorComponentCompanion extends AbstractListGridEditorCompanion {
}


