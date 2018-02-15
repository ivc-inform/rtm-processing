package com.simplesys.SmartClient.Grids.props.tileGrid

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.option.{ScOption, ScNone}
import com.simplesys.props.AbstractClassProps

import scala.scalajs.js._

class TileRecordProps extends AbstractClassProps {
    var tileConstructor: ScOption[String] = ScNone
    var tileProperties: ScOption[Canvas] = ScNone
}
