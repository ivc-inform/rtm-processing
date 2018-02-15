package com.simplesys.SmartClient.Control

import com.simplesys.SmartClient.Foundation.{Canvas, Label}
import com.simplesys.SmartClient.System.{AbstractClassCompanion, Class}
import com.simplesys.System.JSObject
import com.simplesys.System.Types.{HTMLString, void}

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait Hover extends Class {
}

@js.native
abstract trait AbstractHoverCompanion extends AbstractClassCompanion {
    def hide():void = js.native
    var hoverCanvasDefaults: JSObject = js.native
    var leftOffset: Int = js.native
    val moveWithMouse: Boolean = js.native
    def show(contents: HTMLString | Canvas, properties: Label): void = js.native
    var topOffset: Int = js.native
}

@js.native
object Hover extends AbstractHoverCompanion

