package com.simplesys.SmartClient.System.props

import com.simplesys.SmartClient.Grids.treeGrid.TreeNode
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.JSAny
import com.simplesys.System.Types.TreeModelType.TreeModelType
import com.simplesys.System.Types.void
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js._

class TreeProps extends ClassProps {
    var autoOpenRoot: ScOption[Boolean] = ScNone
    var childrenProperty: ScOption[String] = ScNone
    var data: ScOption[IscArray[TreeNode]] = ScNone
    var dataChanged: ScOption[ThisFunction0[void, _]] = ScNone
    var defaultIsFolder: ScOption[Boolean] = ScNone
    var defaultNodeTitle: ScOption[String] = ScNone
    var discardParentlessNodes: ScOption[Boolean] = ScNone
    var idField: ScOption[String] = ScNone
    var isFolderProperty: ScOption[String] = ScNone
    var modelType: ScOption[TreeModelType] = ScNone
    var nameProperty: ScOption[String] = ScNone
    var openProperty: ScOption[String] = ScNone
    var parentIdField: ScOption[String] = ScNone
    var pathDelim: ScOption[String] = ScNone
    var reportCollisions: ScOption[Boolean] = ScNone
    var root: ScOption[TreeNode] = ScNone
    var rootValue: ScOption[JSAny] = ScNone
    var separateFolders: ScOption[Boolean] = ScNone
    var showRoot: ScOption[Boolean] = ScNone
    var sortFoldersBeforeLeaves: ScOption[Boolean] = ScNone
    var titleProperty: ScOption[String] = ScNone
}
