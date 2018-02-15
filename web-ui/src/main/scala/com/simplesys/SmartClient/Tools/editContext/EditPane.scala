package com.simplesys.SmartClient.Tools.editContext

import com.simplesys.SmartClient.DataBinding.Callbacks._
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.SmartClient.Tools._
import com.simplesys.System.Types.{AutoChild, _}
import com.simplesys.System._

import scala.scalajs.js

@js.native
trait EditPane extends Canvas {
    def addFromPaletteNode(paletteNode: PaletteNode, parentNode: EditNode = js.native): EditNode
    def addFromPaletteNodes(paletteNodes: IscArray[PaletteNode], parentNode: EditNode = js.native): IscArray[EditNode]
    def addNode(newNode: EditNode, parentNode: EditNode = js.native, index: Int = js.native, parentProperty: String = js.native, skipParentComponentAdd: Boolean): EditNode
    def addPaletteNodesFromJS(jsCode: String, parentNode: EditNode = js.native, globals: IscArray[String] = js.native): void
    def addPaletteNodesFromJSON(jsonString: String, parentNode: EditNode = js.native, globals: IscArray[String] = js.native, callback: JSFunction = js.native): void
    def addPaletteNodesFromXML(xmlString: String, parentNode: EditNode = js.native, globals: IscArray[String] = js.native, callback: JSFunction = js.native): void
    val allowNestedDrops: Boolean
    var autoEditNewNodes: Boolean
    val canDragGroup: Boolean
    val canGroupSelect: Boolean
    var defaultPalette: Palette
    def destroyAll(): void
    val editContextProperties: EditContext
    def enableEditing(editNode: EditNode): void
    var extraPalettes: IscArray[Palette]
    def getDefaultPalette(): Palette
    def getEditContext(): EditContext
    def getPaletteNodesFromJS(jsCode: String, callback: PaletteNodeCallback, globals: IscArray[String] = js.native): void
    def getPaletteNodesFromXML(xmlStrong: String, callback: PaletteNodeCallback): void
    def getRootEditNode(): EditNode
    def getSaveData(): IscArray[PaletteNode]
    val hideGroupBorderOnDrag: Boolean
    def isNodeEditingOn(editNode: EditNode): Boolean
    def makeEditNode(paletteNode: PaletteNode): EditNode
    def nodeAdded(newNode: EditNode, parentNode: EditNode, rootNode: EditNode): void
    var persistCoordinates: Boolean
    def removeAll(): void
    def removeNode(editNode: EditNode): void
    def removeNodeProperties(editNode: EditNode, properties: IscArray[String]): void
    def reorderNode(parentNode: EditNode, index: Int, moveToIndex: Int): void
    val rootComponent: PaletteNode
    val selectedBorder: String
    val selectedLabelBackgroundColor: String
    def serializeAllEditNodes(settings: SerializationSettings = js.native): String
    def serializeAllEditNodesAsJSON(settings: SerializationSettings = js.native): String
    def serializeEditNodes(nodes: IscArray[EditNode], settings: SerializationSettings = js.native): String
    def serializeEditNodesAsJSON(nodes: IscArray[EditNode], settings: SerializationSettings = js.native): String
    def setDefaultPalette(palette: Palette): void
    def setEditProxyProperties(editNode: EditNode, properties: EditProxy): void
    def setNodeProperties(editNode: EditNode, properties: Canvas, skipLiveObjectUpdate: Boolean = js.native): void
    val showSelectedLabel: Boolean
}

