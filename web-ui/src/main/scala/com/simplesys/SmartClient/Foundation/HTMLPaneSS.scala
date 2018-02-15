package com.simplesys.SmartClient.Foundation

import com.simplesys.System.Types.void

import scala.scalajs.js

@js.native
trait HTMLPaneSS extends HTMLPane {
    def addContents(content: String): void
    def clearContents(): void
}

@js.native
abstract trait AbstractHTMLPaneSSCompanion extends AbstractHTMLPaneCompanion {
}


