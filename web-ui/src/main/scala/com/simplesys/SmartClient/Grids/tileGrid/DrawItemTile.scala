package com.simplesys.SmartClient.Grids.tileGrid

import com.simplesys.SmartClient.Drawing.DrawPane
import com.simplesys.SmartClient.Grids.props.tileGrid.SimpleTileProps
import com.simplesys.SmartClient.Tools.PaletteNode
import com.simplesys.SmartClient.Tools.palette.TilePalette

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait DrawItemTile extends SimpleTile {
    var drawPane: DrawPane
    var record: PaletteNode | TileRecord
    var tilePalette: TilePalette
}


