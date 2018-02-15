package com.simplesys.SmartClient.Forms.formsItems

import com.simplesys.System.JSUndefined
import com.simplesys.System.Types._

import scala.scalajs.js


@js.native
trait FontItem extends FormItemWithButtons {

    var fontFamily: JSUndefined[String]
    var fontSize: JSUndefined[Int]
    var fontStyle: JSUndefined[String]
    var fontWeight: JSUndefined[String]
    var lineColor: JSUndefined[CSSColor]
    var rotation: JSUndefined[Double]
}

@js.native
abstract trait AbstractFontItemCompanion extends AbstractFormItemWithButtonsCompanion {
}

