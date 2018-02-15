package com.simplesys.SmartClient.Drawing.drawItem.drawShape

import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.{JSAny, JSObject}
import com.simplesys.System.Types.DrawShapeCommandType.DrawShapeCommandType

import scala.scalajs.js

@js.native
trait DrawShapeCommand extends JSObject {
    val args: IscArray[JSAny]
    val `type`: DrawShapeCommandType
}


