package com.simplesys.SmartClient.Grids.props

import com.simplesys.SmartClient.DataBinding.DataBoundComponent
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Foundation.props.CanvasProps
import com.simplesys.SmartClient.Grids.TileLayout
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.Orientation.Orientation
import com.simplesys.System.Types.Record
import com.simplesys.System.Types.TileLayoutPolicy.TileLayoutPolicy
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class TileLayoutProps extends CanvasProps {
    type classHandler <: TileLayout

    var animateTileChange: ScOption[Boolean] = ScNone
    var autoWrapLines: ScOption[Boolean] = ScNone
    var dragLine: ScOption[Canvas ] = ScNone
    var expandMargins: ScOption[Boolean] = ScNone
    var getDragData: ScOption[js.Function1[DataBoundComponent, IscArray[Record]]] = ScNone
    var layoutMargin: ScOption[Int] = ScNone
    var layoutPolicy: ScOption[TileLayoutPolicy] = ScNone
    var orientation: ScOption[Orientation] = ScNone
    var paddingAsLayoutMargin: ScOption[Boolean] = ScNone
    var tileHeight: ScOption[Int] = ScNone
    var tileHMargin: ScOption[Int] = ScNone
    var tileMargin: ScOption[Int] = ScNone
    var tiles: ScOption[Seq[Canvas]] = ScNone
    var tileSize: ScOption[Int] = ScNone
    var tilesPerLine: ScOption[Int] = ScNone
    var tileVMargin: ScOption[Int] = ScNone
    var tileWidth: ScOption[Int] = ScNone
}
