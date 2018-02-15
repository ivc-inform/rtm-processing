package com.simplesys.SmartClient.Forms.formsItems

import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.void

import scala.scalajs.js

@js.native
trait SectionItem extends CanvasItem {
    var canCollapse:Boolean
    var canTabToHeader:Boolean
    def collapseSection ():void
    def expandSection ():void
    def isExpanded():Boolean
    val itemIds: IscArray[String]
    val sectionExpanded:Boolean
    val sectionHeaderClass:String
}


