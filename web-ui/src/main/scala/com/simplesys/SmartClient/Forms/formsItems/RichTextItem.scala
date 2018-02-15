package com.simplesys.SmartClient.Forms.formsItems

import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.void

import scala.scalajs.js

@js.native
trait RichTextItem extends CanvasItem {
    var controlGroups: IscArray[String]
    var moveFocusOnTab:Boolean
    def  setMoveFocusOnTab (moveFocusOnTab:Boolean):void
}


