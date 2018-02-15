package com.simplesys.SmartClient.Layout

import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.System.JSUndefined

import scala.scalajs.js

@js.native
trait IconMenuButton extends IconButton {
    var menu: JSUndefined[MenuSS]
    var menuAnimationEffect: JSUndefined[String]
    def showMenu(): Boolean
}



