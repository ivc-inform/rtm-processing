package com.simplesys.SmartClient.Layout.sectionStack

import com.simplesys.SmartClient.Foundation.{Canvas, StretchImg}
import com.simplesys.SmartClient.Layout.{HLayout, Layout, SectionStack}
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.{void, _}

import scala.scalajs.js

@js.native
trait ImgSectionHeader extends HLayout {
    var background: StretchImg with AutoChild
    var clipTitle: Boolean
    var controls: IscArray[Canvas]
    val controlsLayout: Layout with AutoChild
    def getSectionStack(): SectionStack
    var icon: SCImgURL
    val iconAlign: String
    val iconHeight: Int
    val iconOrientation: String
    val iconSize: Int
    val iconWidth: Int
    def setAlign(align: String): void
    def setIcon(icon: URL): void
    def setIconOrientation(orientation: String): void
    def setPrompt(prompt: HTMLString): void
    var showClippedTitleOnHover: Boolean
    var title: HTMLString
    var titleClipped: js.ThisFunction0[classHandler, Boolean]
    var titleHover: js.ThisFunction0[classHandler, Boolean]
    def titleHoverHTML(defaultHTML: HTMLString): HTMLString
}



