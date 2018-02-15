package com.simplesys.SmartClient.Layout

import com.simplesys.SmartClient.Layout.tabSet.Tab
import com.simplesys.SmartClient.System.{IscArray, KeyIdentifier}
import com.simplesys.System.Types.{SCImgURL, void}

import scala.scalajs.js

@js.native
trait TabBar extends Toolbar {
    val baseLineCapSize: Int
    val baseLineSrc: SCImgURL
    val baseLineThickness: Int
    var breadth: Int
    val closeTabKeys: IscArray[KeyIdentifier]
    val defaultTabSize: Int
    val moreTab: Tab
    var moreTabCount: Int
    def selectTab(tabNum: Int): void
    val showMoreTab: Boolean
    val tabs: IscArray[Tab]
}



