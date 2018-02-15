package com.simplesys.SmartClient.Grids.props.tileGrid

import com.simplesys.SmartClient.Foundation.StatefulCanvas
import com.simplesys.SmartClient.Foundation.props.StatefulCanvasProps
import com.simplesys.SmartClient.Grids.TileGrid
import com.simplesys.SmartClient.Grids.tileGrid.SimpleTile
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js._

class SimpleTileProps extends StatefulCanvasProps {
    type classHandler <: SimpleTile

    var creator: ScOption[TileGrid] = ScNone
    var tileGrid: ScOption[TileGrid] = ScNone
}
