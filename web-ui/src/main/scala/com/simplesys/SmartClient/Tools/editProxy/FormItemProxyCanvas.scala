package com.simplesys.SmartClient.Tools.editProxy

import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.SmartClient.Foundation.{AbstractCanvasCompanion, Canvas}

import scala.scalajs.js

@js.native
trait FormItemProxyCanvas extends Canvas {
    val formItem: FormItem
}

@js.native
abstract trait AbstractFormItemProxyCanvasCompanion extends AbstractCanvasCompanion {
}

//@js.native
//object FormItemProxyCanvas extends AbstractFormItemProxyCanvasCompanion
