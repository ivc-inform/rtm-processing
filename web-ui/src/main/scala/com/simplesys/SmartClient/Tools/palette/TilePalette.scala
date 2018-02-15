package com.simplesys.SmartClient.Tools.palette

import com.simplesys.SmartClient.Grids.TileGrid
import com.simplesys.SmartClient.Grids.tileGrid.TileRecord
import com.simplesys.SmartClient.Tools.{EditNode, Palette, PaletteNode}
import com.simplesys.System.JSUndefined

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait TilePalette extends TileGrid with Palette{
    def makeEditNode1 (paletteNode: PaletteNode| TileRecord): EditNode
    var title: JSUndefined[String]
}

