package com.simplesys.SmartClient.Drawing

import com.simplesys.SmartClient.System.Point
import com.simplesys.System.{JSObject, JSUndefined}
import com.simplesys.System.Types.CSSColor

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@js.native
trait Shadow extends JSObject {
    var blur: JSUndefined[Int]
    var color: JSUndefined[CSSColor]
    var offset: JSUndefined[Point]
}

