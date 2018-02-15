package com.simplesys.SmartClient.Forms.formsItems

import com.simplesys.SmartClient.Control.Button
import com.simplesys.SmartClient.Foundation.StatefulCanvas
import com.simplesys.SmartClient.System.{Class, IscArray}
import com.simplesys.System.Types.CSSStyleName

import scala.scalajs.js

@js.native
trait ToolbarItem extends CanvasItem {
    var buttonBaseStyle:CSSStyleName
    var buttonConstructor:Class
    var buttonProperties:Button
    var buttons: IscArray[StatefulCanvas]
    var vertical:Boolean
}



