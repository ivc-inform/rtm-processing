package com.simplesys.components.drawing.drawItems

import com.simplesys.SmartClient.Drawing.drawItem.{AbstractDrawRectCompanion, DrawRect}
import com.simplesys.System.JSUndefined
import ru.simplesys.defs.app.scala.container.ScenariosListsDataRecord

import scala.scalajs.js

object ListAnything extends BaseCompanionDrawItem

@js.native
trait ListAnything extends DrawRect {
}

@js.native
abstract trait AbstractListPhonesCompanion extends AbstractDrawRectCompanion {
}

