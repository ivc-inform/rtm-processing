package com.simplesys.SmartClient.Layout.tabSet

import com.simplesys.SmartClient.Control.{Menu, MenuSS}
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Layout.TabSet
import com.simplesys.System.{JSObject, JSUndefined}
import com.simplesys.System.Types.{HTMLString, SCImgURL, void}

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait Tab extends JSObject {
    var canClose: Boolean
    var canEditTitle: Boolean
    var canReorder: Boolean
    var closeIcon: SCImgURL
    var closeIconSize: Int
    var disabled: Boolean
    var funcMenu: JSUndefined[MenuSS]
    var icon: SCImgURL
    var iconHeight: Int
    var iconSize: Int
    var iconWidth: Int
    var ID: String
    var name: JSUndefined[String]
    var pane: JSUndefined[Canvas]
    var paneMargin: Int
    var pickerTitle: HTMLString
    var prompt: HTMLString
    var tabDeselected: js.Function7[TabSet, Int, Canvas, String, Tab, Tab, String, Boolean]
    var tabSelected: js.Function6[TabSet, Int, Canvas, String, Tab, String, void]
    var title: HTMLString
    var width: Int
}
