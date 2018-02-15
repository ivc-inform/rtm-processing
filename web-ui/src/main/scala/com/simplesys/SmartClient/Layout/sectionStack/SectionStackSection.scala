package com.simplesys.SmartClient.Layout.sectionStack

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.JSObject
import com.simplesys.System.Types.{HTMLString, SCImgURL}

import scala.scalajs.js

@js.native
trait SectionStackSection extends JSObject {
    var canCollapse: Boolean
    var canDropBefore: Boolean
    var canReorder: Boolean
    var canTabToHeader: Boolean
    var clipTitle: Boolean
    val controls: IscArray[Canvas]
    var expanded: Boolean
    var hidden: Boolean
    var icon: SCImgURL
    var ID: String
    val items: IscArray[Canvas]
    var name: String
    var resizeable: Boolean
    var showClippedTitleOnHover: Boolean
    var showHeader: Boolean
    var title: HTMLString
}
