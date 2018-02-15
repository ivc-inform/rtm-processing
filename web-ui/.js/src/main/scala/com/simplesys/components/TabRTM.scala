package com.simplesys.components

import com.simplesys.SmartClient.Layout.tabSet.Tab
import com.simplesys.System.JSUndefined
import com.simplesys.components.TabTypes.TabTypes

import scala.scalajs.js

object TabTypes extends Enumeration {
    type TabTypes = Value
    val WithGraph, WithOutGraph = Value
}

@js.native
trait TabRTM extends Tab {
    val tabType: JSUndefined[TabTypes]
}

