package com.simplesys.SmartClient.Layout.props

import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.Layout.IconMenuButton
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class IconMenuButtonProps extends IconButtonProps {
    type classHandler <: IconMenuButton

    var menu: ScOption[MenuSS] = ScNone
    var menuAnimationEffect: ScOption[String] = ScNone
    var showMenu: ScOption[js.ThisFunction0[classHandler, Boolean]] = ScNone
}
