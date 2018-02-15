package com.simplesys.SmartClient.App

import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Grids.{AbstractTreeGridEditorCompanion, TreeGridEditor}
import com.simplesys.System.JSUndefined

import scala.scalajs.js

@js.native
trait CommonTreeGridEditorComponent extends TreeGridEditor {

    import com.simplesys.SmartClient.System.IscArray

    var customMenu: JSUndefined[IscArray[MenuSSItem]]
}

@js.native
abstract trait AbstractCommonTreeGridEditorComponentCompanion extends AbstractTreeGridEditorCompanion {
}


