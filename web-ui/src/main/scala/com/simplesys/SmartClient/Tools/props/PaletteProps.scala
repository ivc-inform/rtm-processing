package com.simplesys.SmartClient.Tools.props

import com.simplesys.SmartClient.Grids.tileGrid.TileRecord
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.SmartClient.System.props.ClassProps
import com.simplesys.SmartClient.Tools.{EditContext, EditNode, Palette}
import com.simplesys.System.JSAny
import com.simplesys.function._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js.ThisFunction2

trait PaletteProps extends ClassProps {
    type callbackHandler <: Palette

    var defaultEditContext: ScOption[EditContext] = ScNone
    val generateNames: ScOption[Boolean] = ScNone
    var makeEditNode: ScOption[ThisFunction2[callbackHandler, TileRecord, IscArray[JSAny], EditNode]] = ScNone
}
