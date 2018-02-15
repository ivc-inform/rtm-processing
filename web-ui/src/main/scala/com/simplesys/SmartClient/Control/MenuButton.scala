package com.simplesys.SmartClient.Control

import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Foundation.AbstractCanvasCompanion
import com.simplesys.System.Types.Alignment.Alignment
import com.simplesys.System.Types.{void, SCImgURL}

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait MenuButton extends Button {
    var autoDestroyMenu: Boolean
    var menu: MenuSSItem
    val menuAlign: Alignment
    var menuAnimationEffect: String
    val menuButtonImage: SCImgURL
    val menuButtonImageUp: SCImgURL
    val rollOverMenuHideDelay: Int
    def setShowMenuBelow(below: Boolean): void
    def setShowMenuButtonImage(show: Boolean): void
    def showMenu(): void
    var showMenuBelow: Boolean
    var showMenuButtonImage: Boolean
    var showMenuOnRollOver: Boolean
}


