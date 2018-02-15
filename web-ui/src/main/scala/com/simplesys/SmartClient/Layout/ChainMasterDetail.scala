package com.simplesys.SmartClient.Layout

import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.System.Types.void

import scala.scalajs.js

@js.native
trait ChainMasterDetail extends LayoutSS {
    def setViewState(): ChainMasterDetail
    def getViewState(): ChainMasterDetail
    def setFuncMenu(menu: MenuSS): void
}

@js.native
abstract trait AbstractChainMasterDetailCompanion extends AbstractLayoutSSCompanion {
}

