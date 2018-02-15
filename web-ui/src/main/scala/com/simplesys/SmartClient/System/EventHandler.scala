package com.simplesys.SmartClient.System

import com.simplesys.SmartClient.Foundation._
import com.simplesys.System.JSObject
import com.simplesys.System.Types.{KeyName, SCImgURL, void}
import org.scalajs.dom.raw.HTMLElement

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait EventHandler extends Class {
}

@js.native
abstract trait AbstractEventHandlerCompanion extends AbstractClassCompanion {
    val ALL_EDGES: String = js.native
    def altKeyDown(): Boolean = js.native
    def ctrlKeyDown(): Boolean = js.native
    def getDragRect(): IscArray[Int] = js.native
    def getDragTarget(): Canvas = js.native
    def getKey(): KeyName = js.native
    def getKeyEventCharacter(): String = js.native
    def getKeyEventCharacterValue(): Int = js.native
    def getNativeDragData(): JSObject = js.native
    def getNativeMouseTarget(): HTMLElement = js.native
    def getTarget(): Canvas = js.native
    def getWheelDeltaY(): Double = js.native
    def getWheelDeltaX(): Double = js.native
    def getX(): Int = js.native
    def getY(): Int = js.native
    var IDLE_DELAY: Int = js.native
    def leftButtonDown(): Boolean = js.native
    def middleButtonDown(): Boolean = js.native
    def rightButtonDown(): Boolean = js.native
    def setDragTracker(html: String, newWidth: Int = js.native, newHeight: Int = js.native, offsetX: Int = js.native, offsetY: Int = js.native, properties: Canvas = js.native): void = js.native
    def setDragTrackerImage(src: SCImgURL, x: Int = js.native, y: Int = js.native): void = js.native
    def setNativeDragData(data: JSObject | String, strData: String = js.native): void = js.native
    def shiftKeyDown(): Boolean = js.native
    var STILL_DOWN_DELAY: Int = js.native
    var STOP_BUBBLING: String = js.native
    def targetIsMasked(target: Canvas): Boolean = js.native
}

@js.native
object EventHandler extends AbstractEventHandlerCompanion

