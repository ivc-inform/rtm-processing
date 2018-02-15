package com.simplesys.SmartClient.Forms.formsItems

import com.simplesys.System.Types.void

import scala.scalajs.js

@js.native
trait ShadowItem extends FormItemWithButtons {

    import com.simplesys.SmartClient.Drawing.Shadow
    import com.simplesys.System.JSUndefined

    var shadow: JSUndefined[Shadow]
    def checkShadowUndefined(): void
}

@js.native
abstract trait AbstractShadowItemCompanion extends AbstractFormItemWithButtonsCompanion {
}

