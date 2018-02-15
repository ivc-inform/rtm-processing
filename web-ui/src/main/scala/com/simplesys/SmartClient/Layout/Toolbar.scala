package com.simplesys.SmartClient.Layout

import com.simplesys.SmartClient.Control.Button
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.System.{Class, IscArray}
import com.simplesys.System.JSObject
import com.simplesys.System.Types.void

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait Toolbar extends Layout {
    def addButtons(buttons: IscArray[Button] = js.native, position: Int = js.native): void
    var buttonConstructor: Class
    var buttonDefaults: JSObject
    var buttons: IscArray[Button]
    var canReorderItems: Boolean
    var canResizeItems: Boolean
    def deselectButton(buttonID: Int | String | JSObject | Canvas): void
    def getButton(index: Int): Button
    def getButtonNumber(button: Int | String | Button | JSObject): Int
    var itemClick: js.ThisFunction2[classHandler, Button, Int, void]
    var itemDoubleClick: js.ThisFunction2[classHandler, Button, Int, void]
    var itemDragResized: js.ThisFunction2[classHandler, Int, Int, void]
    def removeButtons(buttons: IscArray[Button] = js.native): void
    def selectButton(buttonID: Int | String | JSObject | Canvas): void
    def setButtons(buttons: IscArray[Button] = js.native): void
    def setCanResizeItems(canResizeItems: Boolean): void
}



