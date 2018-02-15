package com.simplesys.SmartClient.Grids.props.treeGrid

import com.simplesys.SmartClient.Grids.treeGrid.TreeNode
import com.simplesys.System.Types.{SCImgURL, HTMLString}
import com.simplesys.option.{ScOption, ScNone}
import com.simplesys.props.AbstractClassProps

import scala.scalajs.js
import scala.scalajs.js._

class TreeNodeProps extends AbstractClassProps {
    var canAcceptDrop: ScOption[Boolean] = ScNone
    var canDrag: ScOption[Boolean] = ScNone
    var children: ScOption[Seq[TreeNode]] = ScNone
    var enabled: ScOption[Boolean] = ScNone
    var icon: ScOption[SCImgURL] = ScNone
    var id: ScOption[String] = ScNone
    var isFolder: ScOption[Boolean] = ScNone
    var name: ScOption[String] = ScNone
    var parentId: ScOption[String] = ScNone
    var showDropIcon: ScOption[Boolean] = ScNone
    var showOpenIcon: ScOption[Boolean] = ScNone
    var title: ScOption[HTMLString] = ScNone
}
