package com.simplesys.SmartClient.Forms.formsItems

import com.simplesys.System.JSUndefined
import com.simplesys.System.Types.CSSColor

import scala.scalajs.js

@js.native
trait GradientItem extends FormItemWithButtons {
    var startColor: JSUndefined[CSSColor]
    var endColor: JSUndefined[CSSColor]
    var direction: JSUndefined[Double]
}

@js.native
abstract trait AbstractGradientItemCompanion extends AbstractFormItemWithButtonsCompanion {
}


