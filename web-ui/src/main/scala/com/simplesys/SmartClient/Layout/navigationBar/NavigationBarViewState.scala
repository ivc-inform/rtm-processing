package com.simplesys.SmartClient.Layout.navigationBar

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.JSObject
import com.simplesys.System.Types.HTMLString

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait NavigationBarViewState extends JSObject {
    var alwaysShowLeftButtonTitle: Boolean
    var controls: IscArray[String] | IscArray[Canvas]
    var leftButtonTitle: HTMLString
    var shortLeftButtonTitle: HTMLString
    var showLeftButton: Boolean
    var title: HTMLString
}
