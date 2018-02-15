package com.simplesys.SmartClient.Layout.props

import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.Layout.IconMenuButtonSS
import com.simplesys.SmartClient.System.{IscArray, isc}
import com.simplesys.System.JSAny
import com.simplesys.System.Types.IconOrientation
import com.simplesys.function._
import com.simplesys.option.ScOption
import com.simplesys.option.ScOption._

import scala.scalajs.js

class IconMenuButtonSSProps extends IconMenuButtonProps {
    type classHandler <: IconMenuButtonSS

    var addOwner1: ScOption[Boolean] = true.opt

    click = {
        (thiz: classHandler) =>
            thiz.showMenu()
            false
    }.toThisFunc.opt

    initWidget = {
        (thiz: classHandler, args: IscArray[JSAny]) =>
            //isc debugTrac (thiz.getClassName(), thiz.getIdentifier())

            thiz.iconSmall = thiz.icon
            thiz.title1 = thiz.title
            thiz.Super("initWidget", args)

    }.toThisFunc.opt

    showMenu = {
        (thiz: classHandler) =>
            if (thiz.menu.isEmpty || thiz.menu.get == null)
                false
            else {
                //isc debugTrap (thiz.menu, isc.isA.String(thiz.menu), isc.isA.Menu(thiz.menu))

                if (isc.isA.String(thiz.menu))
                    thiz.menu = js.Dynamic.global.window.selectDynamic(thiz.menu.toString).asInstanceOf[MenuSS]

                val res = if (!isc.isA.Menu(thiz.menu)) {
                    thiz._createMenu(thiz.menu.get)
                    isc.isA.Menu(thiz.menu)
                } else
                    true

                //isc debugTrap res
                if (res) {
                    //isc debugTrap(thiz.menu, thiz.menu.get)

                    val menu: MenuSS = thiz.menu.get

                    //isc debugTrap (menu.items, menu.data)
                    if (menu.items.isEmpty)
                        menu.items = menu.data

                    if (thiz.addOwner1)
                        menu.items.foreach(_ foreach (_.owner1 = thiz))

                    //isc debugTrap menu.items

                    menu._showOffscreen()
                    val left = thiz.getPageLeft()
                    val top = thiz.getPageTop() + thiz.getVisibleHeight() + 1
                    menu.placeNear(left, top)
                    menu.show(thiz.menuAnimationEffect)
                }

                res
            }

    }.toThisFunc.opt

    iconOrientation = IconOrientation.center.opt
    orientation = "vertical".opt
}
