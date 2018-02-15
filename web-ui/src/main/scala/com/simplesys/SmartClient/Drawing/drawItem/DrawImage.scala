package com.simplesys.SmartClient.Drawing.drawItem

import com.simplesys.SmartClient.Drawing.DrawItem
import com.simplesys.System.Types.{URL, void}

import scala.scalajs.js

@js.native
trait DrawImage extends DrawItem {
    var height: Int
    var left: Int
    def setHeight(height: Int): void
    def setLeft(left: Int): void
    def setRect(left: Int, top: Int, width: Int, height: Int): void
    def setSrc(src: URL): void
    def setTop(top: Int): void
    def setWidth(width: Int): void
    var src: URL
    var top: Int
    val useMatrixFilter:Boolean
    var width: Int
}
