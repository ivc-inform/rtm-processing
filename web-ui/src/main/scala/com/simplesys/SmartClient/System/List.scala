package com.simplesys.SmartClient.System

import com.simplesys.SmartClient.DataBinding._
import com.simplesys.System.Types.void
import com.simplesys.System.{JSAny, JSObject}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName
import scala.scalajs.js.|

@js.native
@JSName("List")
trait IscList[A] extends js.Array[A] with Class {
    def add(`object`: JSAny): this.type = js.native
    def addPos(`object`: JSAny, pos: Int): this.type = js.native
    def addList(list: this.type, listStartRow: Int = js.native, listEndRow: Int = js.native): this.type = js.native
    def addListAt(list: this.type, pos: Int = js.native): this.type = js.native
    def contains(obj: JSAny, pos: Int = js.native): Boolean = js.native
    def containsAll(list: this.type): Boolean = js.native
    var dataChanged: Function0[void] = js.native
    def duplicate(): this.type = js.native
    def equals(list: this.type): Boolean = js.native
    @JSName("find")
    def Find(propertyName: String | JSObject | AdvancedCriteria, value: JSObject = js.native): JSObject = js.native
    def findIndex(propertyName: String | JSObject | AdvancedCriteria, value: JSObject = js.native): Int = js.native
    def findNextIndex(startIndex: Int, propertyName: String | JSObject | AdvancedCriteria, value: JSObject = js.native): Int = js.native
    def findAll(propertyName: String | JSObject | AdvancedCriteria, value: JSObject = js.native, endIndex: Int = js.native): IscArray[JSObject] = js.native
    def first(): JSObject = js.native
    def last(): JSObject = js.native
    def get(pos: Int): JSObject = js.native
    def getItems(itemList: IscArray[Int]): IscArray[JSAny] = js.native
    def getLength(): Int = js.native
    def getProperty(property: String): IscArray[JSAny] = js.native
    def getRange(start: Int, end: Int): IscArray[JSAny] = js.native
    def getValueMap(idField: String, displayField: String): JSObject = js.native
    def indexOf[T](obj: JSObject, pos: Int = js.native, endPos: Int = js.native, comparator: js.Function2[T, T, Boolean] = js.native): Int = js.native
    def lastIndexOf[T](obj: JSObject, pos: Int = js.native, endPos: Int = js.native, comparator: js.Function2[T, T, Boolean] = js.native): Int = js.native
    def intersect(lists: this.type): this.type = js.native
    def removeList(lists: this.type): this.type = js.native
    def isEmpty(): Boolean = js.native
    //@JSName("remove")
    def remove[T](obj: JSAny, pos: Int = js.native, endPos: Int = js.native, comparator: js.Function2[T, T, Boolean] = js.native): Boolean = js.native
    def removeAt(pos: Int): JSObject = js.native
    def set(pos: Int, obj: JSObject): JSObject = js.native
    def setLength(length: Int): void = js.native
    def sort(comparator: Function2[_, _, Int]): this.type = js.native
    def sortByProperty(property: String, up: Boolean, normalizer: Function2[_, _, Int] = js.native, context: JSObject = js.native): this.type = js.native
}
