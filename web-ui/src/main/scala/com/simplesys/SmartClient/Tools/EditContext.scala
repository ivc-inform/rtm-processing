package com.simplesys.SmartClient.Tools

import com.simplesys.SmartClient.DataBinding.Callbacks.PaletteNodeCallback
import com.simplesys.SmartClient.Drawing.{DrawItem, DrawPane}
import com.simplesys.SmartClient.Grids.treeGrid.{Tree, TreeNode}
import com.simplesys.SmartClient.System.{AbstractClassCompanion, Class, IscArray}
import com.simplesys.SmartClient.Tools.props.EditContextProps.SelectedUpdated
import com.simplesys.System.Types.HoopSelectionStyle.HoopSelectionStyle
import com.simplesys.System.Types.SelectedAppearance.SelectedAppearance
import com.simplesys.System.Types.{CSSColor, HTMLString, void}
import com.simplesys.System._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName
import scala.scalajs.js.|

@js.native
trait EditContext extends Class {
    def _replaceRefs(item: JSObject, globals: IscArray[String]): void
    def addFromPaletteNode(paletteNode: PaletteNode | DrawPane | DrawItem, parentNode: EditNode = js.native): EditNode
    def addFromPaletteNodes(paletteNodes: IscArray[PaletteNode] | IscArray[DrawPane] | IscArray[JSObject], parentNode: JSUndefined[EditNode] = js.native): IscArray[EditContext]
    def addNode(newNode: EditNode, parentNode: EditNode = js.native, index: Int = js.native, parentProperty: String = js.native, skipParentComponentAdd: Boolean = js.native): EditNode
    def addPaletteNodesFromJS(jsCode: JSAny, parentNode: EditNode = js.native, globals: IscArray[String] = js.native): void
    def addPaletteNodesFromJSON(jsonString: JSAny, parentNode: EditNode = js.native, globals: IscArray[String] = js.native, callback: JSFunction = js.native): void
    def addPaletteNodesFromXML(xmlString: String, parentNode: EditNode = js.native, globals: IscArray[String] = js.native, callback: JSFunction = js.native): void
    val allowNestedDrops: Boolean
    var autoEditNewNodes: Boolean
    val canDragGroup: Boolean
    val canGroupSelect: Boolean
    val canSelectEditNodes: Boolean
    var defaultPalette: Palette
    var defaultParent: EditNode
    def deselectAllEditNodes(): void
    def deselectEditNodes(editNodes: IscArray[EditNode]): void
    def destroyAll(): void
    def editMaskClicked(editNode: EditNode, liveObject: JSObject): void
    val editMaskProperties: JSObject
    var editNodeUpdated: js.Function3[EditNode, EditContext, IscArray[String], _]
    def enableEditing(editNode: EditNode): void
    val enableInlineEdit: Boolean
    var extraPalettes: IscArray[Palette]
    def getDefaultPalette(): Palette
    def getCapturedComponents(): JSUndefined[IscArray[JSObject]]
    def getEditNodeTree(): Tree
    def getPaletteNodesFromJS(jsCode: String, callback: PaletteNodeCallback, globals: IscArray[String] = js.native): void
    def getPaletteNodesFromXML(xmlStrong: String, callback: PaletteNodeCallback): void
    def getRootEditNode(): EditNode
    def getSelectedEditNode(): EditNode
    def getSelectedEditNodes(): IscArray[EditNode]
    @JSName("getSelectedEditNodes")
    def getSelectedEditNodes1(): JSUndefined[IscArray[EditNode]]
    def getSelectedLabelText(component: JSObject): HTMLString
    val hideGroupBorderOnDrag: Boolean
    val hoopSelectionMode: HoopSelectionStyle
    val hoopSelectorProperties: JSObject
    def isEditNodeSelected(): Boolean
    def isNodeEditingOn(editNode: EditNode): Boolean
    def makeEditNode(paletteNode: PaletteNode): EditNode
    def nodeAdded(newNode: EditNode, parentNode: EditNode, rootNode: EditNode): void
    var persistCoordinates: Boolean
    def removeAll(): void
    def removeNode(editNode: EditNode): void
    def removeNodeProperties(editNode: EditNode, properties: IscArray[String]): void
    def requestLiveObject(newNode: EditNode, callback: JSFunction, palette: Palette): void
    val rootComponent: PaletteNode
    def selectAllEditNodes(): void
    val selectedAppearance: SelectedAppearance
    val selectedBorder: String
    var selectedEditNodesUpdated: JSUndefined[SelectedUpdated]
    def selectEditNode(editNode: EditNode): void
    val selectedLabelBackgroundColor: String
    val selectedTintColor: CSSColor
    val selectedTintOpacity: Int
    //var selectionType: SelectionType //todo  сделать анотацию @JSPropsName
    def selectSingleEditNode(editNode: EditNode): void
    def serializeAllEditNodes(settings: SerializationSettings = js.native): String
    def serializeAllEditNodesAsJSON(settings: SerializationSettings = js.native): String
    def serializeEditNodes(nodes: IscArray[EditNode], settings: SerializationSettings = js.native): String
    def serializeEditNodesAsJSON(nodes: IscArray[EditNode], settings: SerializationSettings = js.native): String
    def setDefaultPalette(palette: Palette): void
    def setEditProxyProperties(editNode: EditNode, properties: EditProxy): void
    def setNodeProperties(editNode: EditNode, properties: JSDictionaryAny, skipLiveObjectUpdate: Boolean = js.native): void
    @JSName("setNodeProperties")
    def setNodeProperties1(editNode: EditNode, properties: JSObject, skipLiveObjectUpdate: Boolean = js.native): void
    def selectSingleComponent(component: Class): void
    def selectComponent(component: Class): void
    val showSelectedLabel: Boolean
    val showSelectedLabelOnSelect: Boolean
    def serializeChildData(parentData : JSObject| JSDynamic, childNodes: IscArray[TreeNode]):void
    val liveObject: JSUndefined[JSObject]
}

@js.native
abstract trait EditContextCompanion extends AbstractClassCompanion {
    def convertActions(node: EditNode, defaults: JSObject| JSDynamic, classObj: JSUndefined[Class]): void = js.native
}

@js.native
@JSName("EditContext")
object EditContextStatic extends EditContextCompanion
