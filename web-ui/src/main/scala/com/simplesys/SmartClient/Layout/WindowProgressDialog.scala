package com.simplesys.SmartClient.Layout

import com.simplesys.SmartClient.Layout.BreakType.BreakType
import com.simplesys.System.JSUndefined

import scala.scalajs.js


@js.native
trait WindowProgressDialog extends WindowSS {

    import com.simplesys.SmartClient.Control.Progressbar
    import com.simplesys.System.Types.void

    def okFunction(): void
    var progressBar: Progressbar

    var minValue: Double
    var maxValue: Double
    var oneStep: Double
    def getBreak(): JSUndefined[BreakType]
    var _breakType: JSUndefined[BreakType]
    def nextStep(): void
}

@js.native
abstract trait AbstractWindowProgressDialogCompanion extends AbstractWindowSSCompanion {
}

