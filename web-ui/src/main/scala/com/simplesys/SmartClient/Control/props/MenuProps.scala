package com.simplesys.SmartClient.Control.props

import com.simplesys.SmartClient.Control.menu.MenuItem
import com.simplesys.SmartClient.Control.props.menu.MenuItemProps
import com.simplesys.SmartClient.Foundation.{Canvas, Img}
import com.simplesys.SmartClient.Grids.listGrid.ListGridField
import com.simplesys.SmartClient.Grids.props.GridProps
import com.simplesys.SmartClient.Layout.NavigationBar
import com.simplesys.System.JSObject
import com.simplesys.System.Types.PanelPlacement.PanelPlacement
import com.simplesys.System.Types.{CSSStyleName, HTMLString, SCClassName}
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class MenuProps extends GridProps[ListGridField, MenuItem] {
    var autoDismiss: ScOption[Boolean] = ScNone
    var autoDismissOnBlur: ScOption[Boolean] = ScNone
    var cancelButtonTitle: ScOption[HTMLString] = ScNone
    var canSelectParentItems: ScOption[Boolean] = ScNone
    var cascadeAutoDismiss: ScOption[Boolean] = ScNone
    var checkmarkDisabledImage: ScOption[Img] = ScNone
    var checkmarkImage: ScOption[Img] = ScNone
    var data: ScOption[Seq[MenuItemProps]] = ScNone
    var fillSpaceStyleName: ScOption[CSSStyleName] = ScNone
    var iconBodyStyleName: ScOption[CSSStyleName] = ScNone
    var iconFieldDefaults: ScOption[ListGridField] = ScNone
    var iconFieldProperties: ScOption[ListGridField] = ScNone
    var iconFillSpaceStyleName: ScOption[CSSStyleName] = ScNone
    var iconHeight: ScOption[Int] = ScNone
    var iconWidth: ScOption[Int] = ScNone
    var itemClick: ScOption[js.Function2[JSObject, Int, Boolean]] = ScNone
    var items: ScOption[Seq[MenuItemProps]] = ScNone
    var keyFieldDefaults: ScOption[ListGridField] = ScNone
    var keyFieldProperties: ScOption[ListGridField] = ScNone
    var menuButtonWidth: ScOption[Int] = ScNone
    var navigationBar: ScOption[NavigationBar] = ScNone
    var navStack: ScOption[Canvas] = ScNone
    var placement: ScOption[PanelPlacement] = ScNone
    var showAnimationEffect: ScOption[String] = ScNone
    var showIcons: ScOption[Boolean] = ScNone
    var showKeys: ScOption[Boolean] = ScNone
    var showSubmenus: ScOption[Boolean] = ScNone
    var submenuConstructor: ScOption[SCClassName] = ScNone
    var submenuDirection: ScOption[String] = ScNone
    var submenuDisabledImage: ScOption[Img] = ScNone
    var submenuFieldDefaults: ScOption[ListGridField] = ScNone
    var submenuFieldProperties: ScOption[ListGridField] = ScNone
    var submenuImage: ScOption[Img] = ScNone
    var target: ScOption[Canvas] = ScNone
    var titleFieldDefaults: ScOption[ListGridField] = ScNone
    var titleFieldProperties: ScOption[ListGridField] = ScNone
    var useKeys: ScOption[Boolean] = ScNone
}

