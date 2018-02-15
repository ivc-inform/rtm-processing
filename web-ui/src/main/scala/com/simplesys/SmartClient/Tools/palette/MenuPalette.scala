package com.simplesys.SmartClient.Tools.palette

import com.simplesys.SmartClient.Control.Menu
import com.simplesys.SmartClient.Layout.AbstractLayoutCompanion
import com.simplesys.SmartClient.Tools.Palette
import com.simplesys.System.Types.void

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait MenuPalette extends Menu with Palette{
}

@js.native
abstract trait AbstractMenuPaletteCompanion extends AbstractLayoutCompanion {
    def hideAllMenus(): void = js.native
}

@js.native
object MenuPaletteStatic extends AbstractMenuPaletteCompanion
