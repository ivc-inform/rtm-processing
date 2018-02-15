package com.simplesys.SmartClient.DataBinding.props

import com.simplesys.SmartClient.DataBinding.DataSource
import com.simplesys.SmartClient.Grids.treeGrid.TreeNode
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.SmartClient.System.props.TreeProps
import com.simplesys.System.JSAny
import com.simplesys.System.Types.FetchMode.FetchMode
import com.simplesys.System.Types.PreserveOpenState.PreserveOpenState
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class ResultTreeProps extends TreeProps {
    var autoPreserveOpenState: ScOption[PreserveOpenState] = ScNone
    var canReturnOpenFolders: ScOption[Boolean] = ScNone
    var canReturnOpenSubfoldersProperty: ScOption[String] = ScNone
    var childCountProperty: ScOption[String] = ScNone
    var dataArrived: ScOption[js.Function1[TreeNode, _]] = ScNone
    var dataSource: ScOption[DataSource] = ScNone
    var defaultNewNodesToRoot: ScOption[Boolean] = ScNone
    var disableCacheSync: ScOption[Boolean] = ScNone
    var fetchMode: ScOption[FetchMode] = ScNone
    var keepParentsOnFilter: ScOption[Boolean] = ScNone
    var loadDataOnDemand: ScOption[Boolean] = ScNone
    var progressiveLoading: ScOption[Boolean] = ScNone
    var resultSize: ScOption[Int] = ScNone
    var rootNode: ScOption[JSAny] = ScNone
    var serverFilterFields: ScOption[IscArray[String]] = ScNone
    var updateCacheFromRequest: ScOption[Boolean] = ScNone

}
