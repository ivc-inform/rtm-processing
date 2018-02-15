package com.simplesys.SmartClient.Grids.props.listGrid

import com.simplesys.SmartClient.DataBinding.DataSource
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.listGrid.ListGridRecord
import com.simplesys.System.Types.{CSSStyleName, HTMLString}
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

class ListGridRecordProps extends AbstractClassProps {
    type classHandler <: ListGridRecord

    var _baseStyle: ScOption[CSSStyleName] = ScNone
    var _canEdit: ScOption[Boolean] = ScNone
    var _canRemove: ScOption[Boolean] = ScNone
    var backgroundComponent: ScOption[Canvas] = ScNone
    var canAcceptDrop: ScOption[Boolean] = ScNone
    var canDrag: ScOption[Boolean] = ScNone
    var canExpand: ScOption[Boolean] = ScNone
    var canSelect: ScOption[Boolean] = ScNone
    var customStyle: ScOption[CSSStyleName] = ScNone
    var detailDS: ScOption[DataSource] = ScNone
    var enabled: ScOption[Boolean] = ScNone
    var includeInSummary: ScOption[Boolean] = ScNone
    var isGridSummary: ScOption[Boolean] = ScNone
    var isGroupSummary: ScOption[Boolean] = ScNone
    var isSeparator: ScOption[Boolean] = ScNone
    var linkText: ScOption[String] = ScNone
    var singleCellValue: ScOption[HTMLString] = ScNone
}
