package com.simplesys.SmartClient.Drawing.drawItem

import com.simplesys.SmartClient.Drawing.DrawItem
import com.simplesys.SmartClient.Drawing.drawItem.drawShape.DrawShapeCommand
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.void

import scala.scalajs.js

@js.native
trait DrawShape extends DrawItem {
    var commands: IscArray[DrawShapeCommand]
    def setCommands(commands: IscArray[DrawShapeCommand]): void
}


