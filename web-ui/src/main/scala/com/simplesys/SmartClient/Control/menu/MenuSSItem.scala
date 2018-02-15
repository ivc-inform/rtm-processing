package com.simplesys.SmartClient.Control.menu

import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.listGrid.ListGridRecord
import com.simplesys.SmartClient.System.{IscArray, KeyIdentifier}
import com.simplesys.System.JSUndefined
import com.simplesys.System.Types._

import scala.scalajs.js


@js.native
trait MenuSSItem extends ListGridRecord {
    var owner: JSUndefined[Canvas]
    var owner1: JSUndefined[Canvas]
    var identifier: ID
    var action: js.Function1[void, _]
    var canSelectParent: Boolean
    var checked: Boolean
    var checkIf: js.Function3[Canvas, MenuSS, MenuSSItem, Boolean]
    var click: js.Function4[Canvas, MenuSSItem, MenuSS, Int, _]
    var disabledIcon: String
    var dynamicIcon: js.Function3[Canvas, MenuSS, MenuSSItem, SCImgURL]
    var dynamicTitle: js.Function3[Canvas, MenuSS, MenuSSItem, String]
    var enableIf: js.Function3[Canvas, MenuSS, MenuSSItem, Boolean]
    var fetchSubmenus: Boolean
    val icon: String
    val iconHeight: Int
    val iconWidth: Int
    val keys: IscArray[KeyIdentifier]
    val keyTitle: String
    val submenu: JSUndefined[MenuSS]
    val title: HTMLString
    def setOwner(owner: Canvas): void
    def setOwner1(owner: Canvas): void
}

