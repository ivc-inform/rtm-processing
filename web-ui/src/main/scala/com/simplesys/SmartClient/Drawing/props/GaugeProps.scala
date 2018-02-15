package com.simplesys.SmartClient.Drawing.props

import com.simplesys.SmartClient.Drawing.DrawItem
import com.simplesys.SmartClient.Drawing.drawItem.{DrawLabel, DrawSector}
import com.simplesys.SmartClient.Drawing.gauge.GaugeSector
import com.simplesys.SmartClient.System.{IscArray, Point}
import com.simplesys.System.Types.{CSSColor, MultiAutoChild}
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js._

class GaugeProps extends DrawPaneProps {
    var borderColor: ScOption[CSSColor] = ScNone
    var borderWidth: ScOption[Int] = ScNone
    var dialRadius: ScOption[Double] = ScNone
    var drawnClockwise: ScOption[Boolean] = ScNone
    var fontSize: ScOption[Int] = ScNone
    var labelPrefix: ScOption[String] = ScNone
    var labelSuffix: ScOption[String] = ScNone
    var maxValue: ScOption[Double] = ScNone
    var minValue: ScOption[Double] = ScNone
    var needle: ScOption[DrawItem] = ScNone
    var numMajorTicks: ScOption[Int] = ScNone
    var numMinorTicks: ScOption[Int] = ScNone
    var pivotPoint: ScOption[Point] = ScNone
    var pivotPointHeight: ScOption[Int | String] = ScNone
    var pivotShape: ScOption[DrawItem] = ScNone
    var sectorColors: ScOption[IscArray[CSSColor]] = ScNone
    var sectors: ScOption[IscArray[GaugeSector]] = ScNone
    var sectorShape: ScOption[DrawSector with MultiAutoChild] = ScNone
    var tickLine: ScOption[DrawItem with MultiAutoChild] = ScNone
    var value: ScOption[Double] = ScNone
    var valueLabel: ScOption[DrawLabel with MultiAutoChild] = ScNone
}
