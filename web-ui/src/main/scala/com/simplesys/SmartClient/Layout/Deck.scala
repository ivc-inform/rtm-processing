package com.simplesys.SmartClient.Layout

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.void

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait Deck extends Layout {
    var currentPane: Canvas
    def hideCurrentPane(): void
    var panes: IscArray[Canvas]
    def setCurrentPane(pane: String | Canvas): void
}

@js.native
abstract trait AbstractDeckCompanion extends AbstractLayoutCompanion {
}

@js.native
   object Deck extends AbstractDeckCompanion

