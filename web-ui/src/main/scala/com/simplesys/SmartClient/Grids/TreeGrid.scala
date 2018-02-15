package com.simplesys.SmartClient.Grids


import com.simplesys.SmartClient.Control.{Menu, MenuSS}
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.treeGrid.{Tree, TreeGridField, TreeNode}
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.{JSAny, JSUndefined}
import com.simplesys.System.Types.DisplayNodeType.DisplayNodeType
import com.simplesys.System.Types.PreserveOpenState.PreserveOpenState
import com.simplesys.System.Types._

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait TreeGrid extends Grid[TreeGridField, TreeNode] {
    var alwaysShowOpener: Boolean
    var autoPreserveOpenState: PreserveOpenState
    var canDropOnLeaves: Boolean
    def canEditCell(): Boolean
    var canReparentNodes: JSUndefined[Boolean]
    var cantDragIntoChildMessage: String
    val cantDragIntoSelfMessage: String
    val cascadeSelection: Boolean
    val closedIconSuffix: String
    def closeFolder(node: TreeNode): void
    val connectorImage: SCImgURL
    val createDefaultTreeField: Boolean
    var customIconDropProperty: String
    var customIconOpenProperty: String
    var customIconProperty: String
    var displayNodeType: DisplayNodeType
    val dropIconSuffix: String
    val extraIconGap: Int
    var fileImage: SCImgURL
    var folderClick: js.Function3[TreeGrid, TreeNode, Int, void]
    var folderClosed: js.Function1[TreeNode, Boolean]
    var folderContextClick: js.Function3[TreeGrid, TreeNode, Int, Boolean]
    def folderDrop(nodes: IscArray[TreeNode], folder: TreeNode, index: Int, sourceWidget: Canvas, callback: Callback): void
    var folderIcon: SCImgURL
    var folderOpened: js.Function1[TreeNode, Boolean]
    def getAnimateFolderMaxRows(): Int
    def getDropFolder(): TreeNode
    def getExtraIcon(node: TreeNode): URL
    def getIcon(node: TreeNode): URL
    def getNodeTitle(node: TreeNode, recordNum: Int, field: TreeGridField): HTMLString
    def getOpenIcon(node: TreeNode): URL
    def getOpenState(): TreeGridOpenState
    def getSelectedPaths(): ListGridSelectedState
    def getViewState(): TreeGridViewState
    var iconSize: Int
    var indentRecordComponents: Boolean
    var indentSize: Int
    val initialData: IscArray[TreeNode]
    def isOverExtraIcon(): Boolean
    def isOverOpenArea(): Boolean
    val keepParentsOnFilter: Boolean
    var leafClick: js.Function3[TreeGrid, TreeNode, Int, void]
    var leafContextClick: js.Function3[TreeGrid, TreeNode, Int, Boolean]
    val leaveSelectionCheckboxGap: Boolean
    var loadDataOnDemand: Boolean
    var loadingIcon: SCImgURL
    var manyItemsImage: SCImgURL
    var nodeClick: js.Function3[TreeGrid, TreeNode, Int, void]
    var nodeContextClick: js.Function3[TreeGrid, TreeNode, Int, Boolean]
    var nodeIcon: SCImgURL
    var offlineNodeMessage: String
    var openerIconHeight: Int
    var openerIconSize: Int
    var openerIconWidth: Int
    val openerImage: SCImgURL
    def openFolder(node: TreeNode): void
    val openIconSuffix: String
    def openLeaf(node: TreeNode): void
    val parentAlreadyContainsChildMessage: String
    def recordDrop(): void
    val separateFolders: Boolean
    val serverFilterFields: IscArray[String]
    def setData(newData: Tree): void
    def setNodeIcon(node: TreeNode, icon: SCImgURL): void
    def setOpenState(openState: TreeGridOpenState): void
    def setSelectedPaths(selectedPaths: ListGridSelectedState): void
    def setViewState(viewState: TreeGridViewState): void
    def houldAnimateFolder(folder: TreeNode): void
    var showConnectors: Boolean
    var showCustomIconDrop: Boolean
    var showCustomIconOpen: Boolean
    val showDisabledSelectionCheckbox: Boolean
    var showDropIcons: Boolean
    var showFullConnectors: Boolean
    var showLoadingIcons: Boolean
    var showOpener: Boolean
    var showOpenIcons: Boolean
    var showRoot: Boolean
    var sortFoldersBeforeLeaves: Boolean
    def toggleFolder(node: TreeNode): void
    def transferNodes(nodes: IscArray[TreeNode], folder: TreeNode, index: Int, sourceWidget: Canvas, callback: Callback = js.native): void
    val treeFieldTitle:String
    val treeRootValue:JSAny
    var data: Tree
}



