package com.simplesys.SmartClient.Drawing

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.System.Types.{AutoChild, void}

import scala.scalajs.js

@js.native
trait DrawKnob extends Canvas {
    val drawPane: DrawPane
    val knobShape: DrawItem with AutoChild
    val knobShapeDefaults: DrawItem
    def setCenterPoint(x: Double, y: Double, viewboxCoords: Boolean = js.native): void
    def resetKnobPosition(): void
    var updatePoints: js.Function5[Double, Double, Double, Double, String, _]
    val x: Double
    val y: Double
}

