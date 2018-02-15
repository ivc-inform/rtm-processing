package com.simplesys.SmartClient.Tools

import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.DataBinding.DataSource
import com.simplesys.SmartClient.Drawing.DrawItem
import com.simplesys.SmartClient.Tools.editProxy.DrawPaneEditProxy
import com.simplesys.System._

import scala.scalajs.js.annotation.ScalaJSDefined
import scala.scalajs.js.{ThisFunction0, ThisFunction2}

@ScalaJSDefined
trait AddedProps extends JSObject {
    val contextMenu: MenuSS
    val drawPaneEditProxyProperties: DrawPaneEditProxy
    val drawItemEditProxyProperties: EditProxy
    val canvasEditContext: EditContext
    val onDragStop: ThisFunction2[DrawItem, Int, Int, _]
    val resized: ThisFunction0[DrawItem, _]
}
