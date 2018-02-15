package com.simplesys.SmartClient.Grids.props

import com.simplesys.SmartClient.Control.IButton
import com.simplesys.SmartClient.DataBinding.props.DataBoundComponentProps
import com.simplesys.SmartClient.Grids.Grid
import com.simplesys.SmartClient.Grids.listGrid.{ListGridField, ListGridRecord}
import com.simplesys.SmartClient.Grids.treeGrid.{Tree, TreeNode}
import com.simplesys.SmartClient.Layout.props.LayoutProps
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.JSDictionaryAny
import com.simplesys.System.Types.TextMatchStyle.TextMatchStyle
import com.simplesys.System.Types.{MultiAutoChild, SCImgURL}
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class ColumnTreeProps extends LayoutProps with DataBoundComponentProps{
    var autoFetchTextMatchStyle: ScOption[TextMatchStyle] = ScNone
    var backButton: ScOption[IButton ] = ScNone
    var backButtonTitle: ScOption[String] = ScNone
    var closedIconSuffix: ScOption[String] = ScNone
    var column: ScOption[Grid[ListGridField, ListGridRecord] with MultiAutoChild] = ScNone
    var columnProperties: ScOption[JSDictionaryAny] = ScNone
    var customIconOpenProperty: ScOption[String] = ScNone
    var customIconProperty: ScOption[String] = ScNone
    var data: ScOption[Tree] = ScNone
    var dataProperties: ScOption[Tree] = ScNone
    var fields: ScOption[IscArray[ListGridField]] = ScNone
    var firstColumnTitle: ScOption[String] = ScNone
    var folderIcon: ScOption[SCImgURL] = ScNone
    var loadDataOnDemand: ScOption[Boolean] = ScNone
    var nodeIcon: ScOption[SCImgURL] = ScNone
    var nodeSelected: ScOption[js.Function2[Grid[ListGridField, ListGridRecord], TreeNode, Boolean]] = ScNone
    var openIconSuffix: ScOption[String] = ScNone
    var showCustomIconOpen: ScOption[Boolean] = ScNone
    var showHeaders: ScOption[Boolean] = ScNone
    var showMultipleColumns: ScOption[Boolean] = ScNone
    var showNodeCount: ScOption[Boolean] = ScNone
    var showOpenIcons: ScOption[Boolean] = ScNone
}
