package com.simplesys.SmartClient.Grids.props

import com.simplesys.SmartClient.DataBinding.DetailViewer
import com.simplesys.SmartClient.DataBinding.props.DataBoundComponentProps
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.TileGrid
import com.simplesys.SmartClient.Grids.props.detailViewer.DetailViewerFieldProps
import com.simplesys.SmartClient.Grids.tileGrid.{SimpleTile, TileRecord}
import com.simplesys.System.JSDictionaryAny
import com.simplesys.System.Types.DragAppearance.DragAppearance
import com.simplesys.System.Types.SelectionType.SelectionType
import com.simplesys.System.Types.TextMatchStyle.TextMatchStyle
import com.simplesys.System.Types._
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class TileGridProps extends TileLayoutProps with DataBoundComponentProps{
    type classHandler <: TileGrid

    var autoFetchTextMatchStyle: ScOption[TextMatchStyle] = ScNone
    var canAcceptDroppedRecords: ScOption[Boolean] = ScNone
    var canDragTilesOut: ScOption[Boolean] = ScNone
    var canReorderTiles: ScOption[Boolean] = ScNone
    var createTile: ScOption[js.ThisFunction2[classHandler, TileRecord, Int, Canvas]] = ScNone
    var data: ScOption[Seq[Record]] = ScNone
    var dataArrived: ScOption[js.Function2[Int, Int, void]] = ScNone
    var detailViewer: ScOption[DetailViewer] = ScNone
    var detailViewerProperties: ScOption[JSDictionaryAny] = ScNone
    var drawAllMaxTiles: ScOption[Int] = ScNone
    var emptyMessage: ScOption[HTMLString] = ScNone
    var emptyMessageStyle: ScOption[CSSStyleName] = ScNone
    var fields: ScOption[Seq[DetailViewerFieldProps]] = ScNone
    var loadingMessage: ScOption[HTMLString] = ScNone
    var printTilesPerLine: ScOption[Int] = ScNone
    var recordClick: ScOption[js.Function3[TileGrid, Canvas, TileRecord, void]] = ScNone
    var recordContextClick: ScOption[js.Function3[TileGrid, Canvas, TileRecord, Boolean]] = ScNone
    var recordDoubleClick: ScOption[js.Function3[TileGrid, Canvas, TileRecord, void]] = ScNone
    var recycleTiles: ScOption[Boolean] = ScNone
    var selectionChanged: ScOption[js.Function2[TileRecord, Boolean, void]] = ScNone
    var selectionType: ScOption[SelectionType] = ScNone
    var showAllRecords: ScOption[Boolean] = ScNone
    var showEmptyMessage: ScOption[Boolean] = ScNone
    var tile: ScOption[SimpleTile with MultiAutoChild] = ScNone
    var tileConstructor: ScOption[SCClassName] = ScNone
    var tileDragAppearance: ScOption[DragAppearance] = ScNone
    var tileProperties: ScOption[Canvas] = ScNone
    var tileValueAlign: ScOption[String] = ScNone
    var tileValueStyle: ScOption[CSSClassName] = ScNone
    var wrapValues: ScOption[Boolean] = ScNone
}
