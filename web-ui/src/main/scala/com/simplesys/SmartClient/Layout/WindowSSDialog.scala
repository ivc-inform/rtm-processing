package com.simplesys.SmartClient.Layout

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.System.JSUndefined
import com.simplesys.System.Types.{HTMLString, void}

import scala.scalajs.js

@js.native
trait WindowSSDialog extends WindowSS {
    var wrapCanvas: JSUndefined[Canvas]

    def okFunction(): void
    def cancelFunction(): void

    val okCaption: JSUndefined[HTMLString]
    val cancelCaption: JSUndefined[HTMLString]
    var okCancelPanel: JSUndefined[OkCancelPanel]
}

@js.native
abstract trait AbstractWindowSSDialogCompanion extends AbstractWindowSSCompanion {
}

