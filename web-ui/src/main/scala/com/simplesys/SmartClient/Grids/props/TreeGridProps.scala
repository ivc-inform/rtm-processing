package com.simplesys.SmartClient.Grids.props

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.{ListGrid, TreeGrid}
import com.simplesys.SmartClient.Grids.listGrid.ListGridRecord
import com.simplesys.SmartClient.Grids.props.treeGrid.TreeGridFieldProps
import com.simplesys.SmartClient.Grids.treeGrid.{Tree, TreeGridField, TreeNode}
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.JSAny
import com.simplesys.System.Types.DisplayNodeType.DisplayNodeType
import com.simplesys.System.Types.FetchMode._
import com.simplesys.System.Types.PreserveOpenState.PreserveOpenState
import com.simplesys.System.Types.{SCImgURL, void}
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js
import scala.scalajs.js.{ThisFunction2, ThisFunction4}

class TreeGridProps extends GridProps[TreeGridField, ListGridRecord] {
    type classHandler <: TreeGrid

    var alwaysShowOpener: ScOption[Boolean] = ScNone
    var autoPreserveOpenState: ScOption[PreserveOpenState] = ScNone
    var canDropOnLeaves: ScOption[Boolean] = ScNone
    var canReparentNodes: ScOption[Boolean] = ScNone
    var cantDragIntoChildMessage: ScOption[String] = ScNone
    var cantDragIntoSelfMessage: ScOption[String] = ScNone
    var cascadeSelection: ScOption[Boolean] = ScNone
    var closedIconSuffix: ScOption[String] = ScNone
    var connectorImage: ScOption[SCImgURL] = ScNone
    var createDefaultTreeField: ScOption[Boolean] = ScNone
    var customIconDropProperty: ScOption[String] = ScNone
    var customIconOpenProperty: ScOption[String] = ScNone
    var customIconProperty: ScOption[String] = ScNone
    var data: ScOption[Tree] = ScNone
    var displayNodeType: ScOption[DisplayNodeType] = ScNone
    var dropIconSuffix: ScOption[String] = ScNone
    var extraIconGap: ScOption[Int] = ScNone
    var fields: ScOption[Seq[TreeGridFieldProps]] = ScNone
    var defaultFields: ScOption[Seq[TreeGridFieldProps]] = ScNone
    var fileImage: ScOption[SCImgURL] = ScNone
    var folderClick: ScOption[js.Function3[TreeGrid, TreeNode, Int, void]] = ScNone
    var folderClosed: ScOption[js.Function1[TreeNode, Boolean]] = ScNone
    var folderContextClick: ScOption[js.Function3[TreeGrid, TreeNode, Int, Boolean]] = ScNone
    var folderIcon: ScOption[SCImgURL] = ScNone
    var folderOpened: ScOption[js.Function1[TreeNode, Boolean]] = ScNone
    var iconSize: ScOption[Int] = ScNone
    var indentRecordComponents: ScOption[Boolean] = ScNone
    var indentSize: ScOption[Int] = ScNone
    var initialData: ScOption[IscArray[TreeNode]] = ScNone
    var keepParentsOnFilter: ScOption[Boolean] = ScNone
    var leafClick: ScOption[js.Function3[TreeGrid, TreeNode, Int, void]] = ScNone
    var leafContextClick: ScOption[js.Function3[TreeGrid, TreeNode, Int, Boolean]] = ScNone
    var leaveSelectionCheckboxGap: ScOption[Boolean] = ScNone
    var loadDataOnDemand: ScOption[Boolean] = ScNone
    var loadingIcon: ScOption[SCImgURL] = ScNone
    var manyItemsImage: ScOption[SCImgURL] = ScNone
    var nodeClick: ScOption[js.Function3[TreeGrid, TreeNode, Int, void]] = ScNone
    var nodeContextClick: ScOption[js.Function3[TreeGrid, TreeNode, Int, Boolean]] = ScNone
    var nodeIcon: ScOption[SCImgURL] = ScNone
    var offlineNodeMessage: ScOption[String] = ScNone
    var openerIconHeight: ScOption[Int] = ScNone
    var openerIconSize: ScOption[Int] = ScNone
    var openerIconWidth: ScOption[Int] = ScNone
    var openerImage: ScOption[SCImgURL] = ScNone
    var openIconSuffix: ScOption[String] = ScNone
    var parentAlreadyContainsChildMessage: ScOption[String] = ScNone
    var separateFolders: ScOption[Boolean] = ScNone
    var serverFilterFields: ScOption[IscArray[String]] = ScNone
    var showConnectors: ScOption[Boolean] = ScNone
    var showCustomIconDrop: ScOption[Boolean] = ScNone
    var showCustomIconOpen: ScOption[Boolean] = ScNone
    var showDisabledSelectionCheckbox: ScOption[Boolean] = ScNone
    var showDropIcons: ScOption[Boolean] = ScNone
    var showFullConnectors: ScOption[Boolean] = ScNone
    var showLoadingIcons: ScOption[Boolean] = ScNone
    var showOpener: ScOption[Boolean] = ScNone
    var showOpenIcons: ScOption[Boolean] = ScNone
    var showRoot: ScOption[Boolean] = ScNone
    var sortFoldersBeforeLeaves: ScOption[Boolean] = ScNone
    var treeFieldTitle: ScOption[String] = ScNone
    var treeRootValue: ScOption[JSAny] = ScNone
}
