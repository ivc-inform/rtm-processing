package com.simplesys.SmartClient.Tools

import com.simplesys.SmartClient.Drawing.DrawItem
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.SmartClient.Tools._
import com.simplesys.System.Types._
import com.simplesys.System._

import scala.scalajs.js

@js.native
trait EditContextSS extends EditContext {
    val _selectionLiveObject:JSObject
    def _getSelectionEditProxy(): JSUndefined[EditContextSS]
    var getEditContext: JSUndefined[js.Function0[EditContextSS]]
    val defaultsBlocks: JSUndefined[IscArray[JSAny]]
    def addPaletteNodesFromJSON1(components: Map[String, DrawItem], jsonString: JSAny, parentNode: EditNode = js.native, globals: IscArray[String] = js.native, callback: JSUndefined[Callback] = js.native, addedProps: JSObject = js.native): void
    def getSerializeableTree(node: EditNode, dontAddGlobally: JSUndefined[Boolean], topLevel: Boolean): JSObject
    //def addPaletteNodesFromJSON2(jsonString: JSAny, parentNode: EditNode = js.native, globals: IscArray[String] = js.native, callback: JSFunction = js.native, addedProps: JSObject = js.native): void
    def getDrawPaneEditNode(): JSUndefined[EditNode]
}

