package com.simplesys.SmartClient.Tools

import com.simplesys.System._

import scala.scalajs.js

@js.native
trait EditNode extends PaletteNode {
    val canSelectChildren: JSUndefined[Boolean]
    val useEditMask: Boolean
}
