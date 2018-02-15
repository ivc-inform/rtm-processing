package com.simplesys.SmartClient.App

import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Grids.{AbstractTreeListGridEditorCompanion, TreeListGridEditor}
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System._

import scala.scalajs.js

@js.native
trait CommonTreeListGridEditorComponent extends TreeListGridEditor {
    var simpleTableTree: Boolean
    var simpleTableList: Boolean

    var captionMenuTree: String
    var captionMenuList: String

    var customMenuTree: JSUndefined[IscArray[MenuSSItem]]
    var customMenuList: JSUndefined[IscArray[MenuSSItem]]
}

@js.native
abstract trait AbstractCommonTreeListGridEditorComponentCompanion extends AbstractTreeListGridEditorCompanion {
}

