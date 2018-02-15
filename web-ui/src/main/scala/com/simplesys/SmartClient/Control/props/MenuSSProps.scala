package com.simplesys.SmartClient.Control.props

import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.Control.menu.{MenuItem, MenuSSItem}
import com.simplesys.SmartClient.Control.props.menu.{MenuItemProps, MenuSSItemProps}
import com.simplesys.SmartClient.Foundation.{Canvas, Img}
import com.simplesys.SmartClient.Grids.listGrid.ListGridField
import com.simplesys.SmartClient.Grids.props.GridProps
import com.simplesys.SmartClient.Layout.NavigationBar
import com.simplesys.SmartClient.System.{MenuSS, MenuSSItem}
import com.simplesys.System.Types.PanelPlacement._
import com.simplesys.System.Types._
import com.simplesys.System.{JSObject, JSUndefined}
import com.simplesys.function._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js
import scala.scalajs.js.ThisFunction1

class MenuSSProps extends GridProps[ListGridField, MenuSSItem] {
    type classHandler <: MenuSS

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
    var items: ScOption[Seq[MenuSSItemProps]] = ScNone
    var keyFieldDefaults: ScOption[ListGridField] = ScNone
    var keyFieldProperties: ScOption[ListGridField] = ScNone
    var menuButtonWidth: ScOption[Int] = ScNone
    var merge: ScOption[ThisFunction1[classHandler, JSUndefined[MenuSS], MenuSS]] = {
        (thiz: classHandler, menu: JSUndefined[MenuSS]) =>

            if (menu.isDefined) {

                if (menu.get.items.isEmpty)
                    menu.get.items = menu.get.data

                val items = menu.get.items.get.duplicate()
                items add MenuSSItem(
                    new MenuSSItemProps {
                        isSeparator = true.opt
                    }
                )
                items addArray thiz.items.get.duplicate()
                val res = MenuSS.create()
                res.setData(items)
                res

            } else
                thiz
    }.toThisFunc.opt
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
