package com.simplesys.SmartClient.Tools

import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.Drawing.DrawItem
import com.simplesys.System.Types.{SCClassName, SCImgURL}
import com.simplesys.System.{JSObject, JSUndefined}

import scala.scalajs.js

@js.native
trait PaletteNode extends JSObject {
    val defaults: JSObject
    val editNodeProperties: EditNode
    val editProxyProperties: JSUndefined[EditProxy]
    val contextMenu: JSUndefined[MenuSS]
    val icon: SCImgURL
    val idPrefix: String
    val liveObject: DrawItem
    val title: String
    val `type`: SCClassName
}
