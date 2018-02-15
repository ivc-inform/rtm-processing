package com.simplesys.SmartClient.Layout.navigationBar

import com.simplesys.SmartClient.Control.Button
import com.simplesys.SmartClient.Foundation.AbstractCanvasCompanion
import com.simplesys.System.Types.CSSStyleName
import com.simplesys.System.Types.NavigationDirection.NavigationDirection

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait NavigationButton extends Button {
    var backBaseStyle: CSSStyleName
    var direction: NavigationDirection
    var forwardBaseStyle: CSSStyleName
}

