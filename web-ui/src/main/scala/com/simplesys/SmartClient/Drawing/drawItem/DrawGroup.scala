package com.simplesys.SmartClient.Drawing.drawItem

import com.simplesys.SmartClient.Drawing.DrawItem
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.void

import scala.scalajs.js

@js.native
trait DrawGroup extends DrawItem {
    val drawItems: IscArray[DrawItem]
    def getGroupRect(): IscArray[Int]
    var height: Int
    var left: Int
    def setHeight(height: Int): void
    def setLeft(left: Int): void
    def setTop(top: Int): void
    def setWidth(width: Int): void
    var top: Int
    val useGroupRect: Boolean
    var width: Int
}



