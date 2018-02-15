package com.simplesys.SmartClient.Forms.formsItems

import com.simplesys.System.Types._
import com.simplesys.System._

import scala.scalajs.js

@js.native
trait PointItem extends CanvasItem {

    import com.simplesys.SmartClient.System.Point

    var x: JSUndefined[Double]
    var y: JSUndefined[Double]

    var point: JSUndefined[Point]

    var maxX: JSUndefined[Double]
    var maxY: JSUndefined[Double]

    var minX: JSUndefined[Double]
    var minY: JSUndefined[Double]

    var step: JSUndefined[Double]
    var titleGroup: JSUndefined[HTMLString]
    def checkPointUndefined(): void
}

@js.native
abstract trait AbstractPointItemCompanion extends AbstractCanvasItemCompanion {
}

