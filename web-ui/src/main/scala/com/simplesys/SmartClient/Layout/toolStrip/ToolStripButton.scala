package com.simplesys.SmartClient.Layout.toolStrip

import com.simplesys.SmartClient.Control.{AbstractButtonCompanion, Button}
import com.simplesys.SmartClient.Foundation.{AbstractCanvasCompanion, Canvas}
import com.simplesys.System.JSUndefined

import scala.scalajs.js
import scala.scalajs.js.{ThisFunction0, |}

@js.native
trait ToolStripButton extends Button {
    val enableIf : JSUndefined[js.ThisFunction0[Canvas, Boolean]]
}

@js.native
abstract class AbstractToolStripButtonCompanion extends  AbstractButtonCompanion



