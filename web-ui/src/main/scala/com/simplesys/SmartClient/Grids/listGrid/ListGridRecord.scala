package com.simplesys.SmartClient.Grids.listGrid

import com.simplesys.SmartClient.DataBinding.DataSource
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.System.JSAny
import com.simplesys.System.Types._

import scala.scalajs.js

@js.native
trait ListGridRecord extends Record {

    var _baseStyle: CSSStyleName
    var _canEdit: Boolean
    var _canRemove: Boolean
    val backgroundComponent: Canvas
    val canAcceptDrop: Boolean
    val canDrag: Boolean
    val canExpand: Boolean
    val canSelect: Boolean
    var customStyle: CSSStyleName
    var detailDS: DataSource
    var enabled: Boolean
    var includeInSummary: Boolean
    var isGridSummary: Boolean
    var isGroupSummary: Boolean
    var isSeparator: Boolean
    var linkText: String
    var singleCellValue: HTMLString
}
