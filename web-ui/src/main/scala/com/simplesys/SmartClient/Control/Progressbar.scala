package com.simplesys.SmartClient.Control

import com.simplesys.SmartClient.Foundation.StretchImg
import com.simplesys.System.Types.void

import scala.scalajs.js

@js.native
trait Progressbar extends StretchImg {
    var breadth: Int
    def getBreadth(): Int
    def getLength(): Int
    val length: Int
    var percentChanged: js.Function1[void, _]
    var percentDone: Double
    def setBreadth(newBreadth: Int): void
    def setLength(newLength: Int): void
    def setPercentDone(newPercent: Double): void
}


