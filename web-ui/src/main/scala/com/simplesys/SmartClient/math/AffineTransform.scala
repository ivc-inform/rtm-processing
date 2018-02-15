package com.simplesys.SmartClient.math

import com.simplesys.SmartClient.System.{AbstractClassCompanion, Class, IscArray, Point}
import com.simplesys.System.Types.void

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait AffineTransform extends Class {
    val m00: Double
    val m01: Double
    val m02: Double
    val m10: Double
    val m11: Double
    val m12: Double

    def init(m00: Double, m01: Double, m02: Double, m10: Double, m11: Double, m12: Double): AffineTransform
    def duplicate(): AffineTransform
    def getDeterminant(): Double
    def rightMultiply(transform: AffineTransform): AffineTransform
    def leftMultiply(transform: AffineTransform): AffineTransform
    def transform(v0: Double | IscArray[Double], v1: Double, output: Point): Point
    def preTranslate(dx:Double, dy:Double): this.type
    def translate(dx:Double, dy:Double): this.type
}

@js.native
abstract trait AbstractAffineTransformCompanion extends AbstractClassCompanion {
    def getTranslateTransform(dx: Double, dy: Double): AffineTransform = js.native
}

@js.native
object AffineTransformStatic extends AbstractAffineTransformCompanion {
}
