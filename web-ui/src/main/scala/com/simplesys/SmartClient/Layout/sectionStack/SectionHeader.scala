package com.simplesys.SmartClient.Layout.sectionStack

import com.simplesys.SmartClient.Foundation.{Canvas, Label}
import com.simplesys.SmartClient.Layout.{Layout, SectionStack}
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.AutoChild

import scala.scalajs.js

@js.native
trait SectionHeader extends Label {
    var clipTitle: Boolean
    val controls: IscArray[Canvas]
    val controlsLayout: Layout with AutoChild
    def getSectionStack(): SectionStack
}


