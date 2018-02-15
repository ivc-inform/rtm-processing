package com.simplesys.SmartClient.Tools.palette.props

import com.simplesys.SmartClient.Grids.props.TileGridProps
import com.simplesys.SmartClient.Grids.tileGrid.TileRecord
import com.simplesys.SmartClient.Tools.EditNode
import com.simplesys.SmartClient.Tools.palette.TilePalette
import com.simplesys.SmartClient.Tools.props.PaletteProps
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.option.ScOption._
import com.simplesys.function._

import scala.scalajs.js._

class TilePaletteProps extends TileGridProps with PaletteProps {
    type classHandler <: TilePalette
    var title: ScOption[String] = ScNone
    var makeEditNode1: ScOption[ThisFunction1[classHandler, TileRecord, EditNode]] = {
        (thiz: classHandler, record: TileRecord) =>
            thiz makeEditNode record
    }.toThisFunc.opt
}
