package com.simplesys.SmartClient.Drawing

import com.simplesys.SmartClient.Drawing.drawItem.{DrawLabel, DrawSector}
import com.simplesys.SmartClient.Drawing.gauge.GaugeSector
import com.simplesys.SmartClient.System.{IscArray, Point}
import com.simplesys.System.Types.{AutoChild, CSSColor, MultiAutoChild, void}

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait Gauge extends DrawPane {
    def addSector(newSectorL: GaugeSector): Int
    val borderColor: CSSColor
    val borderWidth: Int
    var dialRadius: Double
    var drawnClockwise: Boolean
    val fontSize: Int
    def formatLabelContents(value: Double): String
    def getDefaultFillColor(sectorIndex: Int): CSSColor
    def getNumSectors(): Int
    def getSectorFillColor(sectorIndex: Int): CSSColor
    def getSectorLabelContents(sectorIndex: Int): String
    def getSectorValue(sectorIndex: Int): Double
    var labelPrefix: String
    var labelSuffix: String
    var maxValue: Double
    var minValue: Double
    val needle: DrawItem with AutoChild
    var numMajorTicks: Int
    var numMinorTicks: Int
    var pivotPoint: Point
    val pivotPointHeight: Int | String
    val pivotShape: DrawItem with AutoChild
    def reformatLabelContents(): void
    def removeSector(sectorIndex: Int): void
    val sectorColors: IscArray[CSSColor]
    val sectors: IscArray[GaugeSector]
    val sectorShape: DrawSector with MultiAutoChild
    def setDialRadius(dialRadius: Double): void
    def setDrawnClockwise(drawnClockwise: Boolean): void
    def setLabelPrefix(labelPrefix: String): void
    def setLabelSuffix(labelSuffix: String): void
    def setMaxValue(maxValue: Double): void
    def setMinValue(maxValue: Double): void
    def setNumMajorTicks(numMajorTicks: Int): void
    def setNumMinorTicks(numMinorTicks: Int): void
    def setPivotPoint(point: Point): void
    def setSectorFillColor(sectorIndex: Int, fillColor: CSSColor): void
    def setSectors(): void
    def setValue(value: Double): void
    val tickLine: DrawItem with MultiAutoChild
    var value: Double
    val valueLabel: DrawLabel with MultiAutoChild
}


