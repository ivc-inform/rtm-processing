package com.simplesys.SmartClient.Grids.treeGrid

import com.simplesys.SmartClient.DataBinding._
import com.simplesys.SmartClient.System.{Class, IscArray}
import com.simplesys.SmartClient.Tools.EditNode
import com.simplesys.System.Types.LoadState.LoadState
import com.simplesys.System.Types.TreeFilterMode.TreeFilterMode
import com.simplesys.System.Types.TreeModelType.TreeModelType
import com.simplesys.System.Types.{Callback, Criteria, void}
import com.simplesys.System.{JSAny, JSObject, JSUndefined}

import scala.scalajs.js
import scala.scalajs.js.{ThisFunction0, |}

@js.native
trait Tree extends Class {
    def add(node: TreeNode, parent: TreeNode, position: Int = js.native): TreeNode
    def addList(node: IscArray[TreeNode], parent: TreeNode, position: Int = js.native): IscArray[TreeNode]
    def allChildrenLoaded(node: TreeNode): Boolean
    var autoOpenRoot: Boolean
    var childrenProperty: String
    def closeAll(node: TreeNode = js.native): void
    def closeFolder(node: TreeNode): void
    def closeFolders(nodeList: IscArray[TreeNode]): void
    val data: IscArray[TreeNode]
    var dataChanged: ThisFunction0[void, void]
    val defaultIsFolder: Boolean
    var defaultNodeTitle: String
    val discardParentlessNodes: Boolean
    def find(fieldNameOrPath: String, value: JSAny = js.native): JSObject
    def findById(id: String): JSObject
    def getAllNodes(node: TreeNode = js.native): IscArray[TreeNode]
    def getChildren(node: TreeNode | EditNode): JSUndefined[IscArray[TreeNode]]
    def getChildrenResultSet(node: TreeNode): ResultSet
    def getDescendantFolders(node: TreeNode = js.native): IscArray[TreeNode]
    def getDescendantLeaves(node: TreeNode = js.native): IscArray[TreeNode]
    def getDescendants(node: TreeNode = js.native): IscArray[TreeNode]
    def getFilteredTree(criteria: Criteria | AdvancedCriteria, filterMode: TreeFilterMode = js.native, dataSource: DataSource = js.native, requestProperties: DSRequest = js.native): Tree
    def getFolders(node: TreeNode): IscArray[TreeNode]
    def getLeaves(node: TreeNode): IscArray[TreeNode]
    def getLength(): Int
    def getLevel(node: TreeNode): Int
    def getLoadState(node: TreeNode): LoadState
    def getName(node: TreeNode): String
    def getOpenList(node: TreeNode = js.native): IscArray[TreeNode]
    def getParent(node: TreeNode): TreeNode
    def getParentPath(node: TreeNode): String
    def getParents(node: TreeNode): IscArray[TreeNode]
    def getPath(node: TreeNode): String
    def getRoot(): TreeNode
    def getTitle(node: TreeNode): String
    def hasChildren(node: TreeNode): Boolean
    def hasFolders(node: TreeNode): Boolean
    def hasLeaves(node: TreeNode): Boolean
    val idField: String
    def indexOf(obj: JSObject, pos: Int = js.native, endPos: Int = js.native): Int
    def isDescendantOf(child: TreeNode, parent: TreeNode): Boolean
    def isFolder(node: TreeNode): Boolean
    var isFolderProperty: String
    def isLeaf(node: TreeNode): Boolean
    def isLoaded(node: TreeNode): Boolean
    def isOpen(node: TreeNode): Boolean
    def isRoot(node: TreeNode): Boolean
    def lastIndexOf(obj: JSObject, pos: Int = js.native, endPos: Int = js.native): Int
    def linkNodes(nodes: IscArray[TreeNode]): void
    def loadChildren(node: TreeNode, callback: Callback = js.native): void
    var modelType: TreeModelType
    def move(node: TreeNode, newParent: TreeNode, position: Int = js.native): void
    var nameProperty: String
    def openAll(node: TreeNode): void
    def openFolders(nodeList: IscArray[TreeNode]): void
    var openProperty: String
    val parentIdField: String
    var pathDelim: String
    def reloadChildren(node: TreeNode): void
    def remove(node: TreeNode): Boolean
    def removeChildren(node: TreeNode): void
    def removeList(nodeList: IscArray[TreeNode]): Boolean
    val reportCollisions: Boolean
    var root: TreeNode
    val rootValue: String | Int
    var separateFolders: Boolean
    def setChildren(parent: TreeNode, newChildren: IscArray[TreeNode]): void
    def setRoot(newRoot: TreeNode, autoOpen: Boolean): void
    def setSeparateFolders(separateFolders: Boolean): void
    def setShowRoot(showRoot: Boolean): void
    def setSortFoldersBeforeLeaves(sortFoldersBeforeLeaves: Boolean): void
    var showRoot: Boolean
    var sortFoldersBeforeLeaves: Boolean
    var titleProperty: String
}

