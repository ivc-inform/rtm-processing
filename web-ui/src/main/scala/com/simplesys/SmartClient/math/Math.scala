package com.simplesys.SmartClient.math

import com.simplesys.SmartClient.System.Point
import com.simplesys.System.JSObject

import scala.scalajs.js

@js.native
trait Math extends JSObject {
    def getRotateTransform(angle: Int, cx: Int, cy: Int): AffineTransform
    def getRotateTransform(angle: Int, cx: Point, cy: Int): AffineTransform
    def getTranslateTransform(dx: Int, dy: Int): AffineTransform
    def getTranslateTransform(dx: Point, dy: Int): AffineTransform

}

