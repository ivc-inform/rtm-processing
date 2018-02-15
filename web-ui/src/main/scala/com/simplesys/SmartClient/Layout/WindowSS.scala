package com.simplesys.SmartClient.Layout

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.JSUndefined
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

@js.native
trait WindowSS extends Window {
    var headerIconPath: String
    var autoPosition: Boolean
    var autoDestroy: JSUndefined[Boolean]
}

@js.native
abstract trait AbstractWindowSSCompanion extends AbstractWindowCompanion {
}

