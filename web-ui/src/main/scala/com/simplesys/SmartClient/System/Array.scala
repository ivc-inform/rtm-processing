package com.simplesys.SmartClient.System

import com.simplesys.SmartClient.DataBinding._
import com.simplesys.System.Types.void
import com.simplesys.System._

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSName, ScalaJSDefined}
import scala.scalajs.js.|

@ScalaJSDefined
trait Rect extends JSObject {
    val left: Double
    val top: Double
    val width: Double
    val height: Double
}

@ScalaJSDefined
trait Rect1 extends Rect {
    val success: Boolean
}

@js.native
@JSName("Array")
trait IscArray[A] extends IscList[A] {
    def addAt(`object`: JSAny, pos: Int = js.native): this.type
    def and(start: Int, end: Int): Boolean
    def clearProperty(property: String): Boolean
    def containsProperty(property: String, value: JSObject = js.native): Boolean
    def getUniqueItems(): IscArray[JSObject]
    def makeIndex(property: String, alwaysMakeArray: Boolean): JSObject
    @JSName("addList")
    def addArray(list: IscArray[A] | js.Array[A], listStartRow: Int = js.native, listEndRow: Int = js.native): IscArray[A] = js.native
    @JSName("map")
    def mapArray(method: js.Function1[_, _], arguments: JSObject*): IscArray[JSObject]
    def max(start: Int = js.native, end: Int = js.native): Int
    def min(start: Int = js.native, end: Int = js.native): Int
    def or(start: Int = js.native, end: Int = js.native): Boolean
    def setSort(sortSpecifiers: SortSpecifier): IscArray[JSAny]
    def slide(start: Int, destination: Int): void
    def slideRange(start: Int, end: Int, destination: Int): void
    def sum(start: Int = js.native, end: Int = js.native): Int
    def unsort(): Boolean
    def getX(): JSUndefined[A]
    def setX(x: A): A
    def getY(): JSUndefined[A]
    def setY(y: A): A
    def getAppex(): IscArray[JSUndefined[Point]]
    def getBoundingBox(): IscArray[JSUndefined[Point]]
    def getRect(): Rect
    def getRect1(): Rect
    def Exists(index: Int): Boolean
    def notExists(index: Int): Boolean
}

@js.native
abstract trait AbstractArrayCompanion extends JSObject {
    var compareAscending: js.Function2[JSAny, JSAny, Int] = js.native
    var compareDescending: js.Function2[JSAny, JSAny, Int] = js.native
}

@js.native
@JSName("Array")
object IscArray extends AbstractArrayCompanion {
    def apply[A](items: A*): IscArray[A] = js.native
    def empty[A]: IscArray[A] = js.native
}
