package com.simplesys.SmartClient.Grids.tileGrid

import com.simplesys.SmartClient.Foundation.{AbstractCanvasCompanion, StatefulCanvas}
import com.simplesys.SmartClient.Grids.TileGrid
import com.simplesys.System.Types.{HTMLString, CSSClassName}

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait SimpleTile extends StatefulCanvas {
    def getRecord(): TileRecord
    var tileGrid: TileGrid
}


