package com.simplesys.SmartClient.Control.props.menu

import com.simplesys.SmartClient.Control.Menu
import com.simplesys.SmartClient.Control.menu.MenuItem
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridRecordProps
import com.simplesys.SmartClient.System.{IscArray, KeyIdentifier}
import com.simplesys.System.Types.{HTMLString, SCImgURL, void}
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class MenuItemProps extends ListGridRecordProps {
    var action: ScOption[js.Function1[void, _]] = ScNone
    var canSelectParent: ScOption[Boolean] = ScNone
    var checked: ScOption[Boolean] = ScNone
    var checkIf: ScOption[js.Function3[Canvas, Menu, MenuItem, Boolean]] = ScNone
    var click: ScOption[js.Function4[Canvas, MenuItem, Menu, Int, _]] = ScNone
    var disabledIcon: ScOption[String] = ScNone
    var dynamicIcon: ScOption[js.Function3[Canvas, Menu, MenuItem, SCImgURL]] = ScNone
    var dynamicTitle: ScOption[js.Function3[Canvas, Menu, MenuItem, String]] = ScNone
    var enableIf: ScOption[js.Function3[Canvas, Menu, MenuItem, Boolean]] = ScNone
    var fetchSubmenus: ScOption[Boolean] = ScNone
    var icon: ScOption[String] = ScNone
    var iconHeight: ScOption[Int] = ScNone
    var iconWidth: ScOption[Int] = ScNone
    var keys: ScOption[IscArray[KeyIdentifier]] = ScNone
    var keyTitle: ScOption[String] = ScNone
    var submenu: ScOption[Menu] = ScNone
    var title: ScOption[HTMLString] = ScNone
}
