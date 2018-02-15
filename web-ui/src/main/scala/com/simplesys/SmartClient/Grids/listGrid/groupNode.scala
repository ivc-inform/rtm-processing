package com.simplesys.SmartClient.Grids.listGrid

import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.HTMLString
import com.simplesys.System.{JSAny, JSObject}

import scala.scalajs.js

@js.native
trait groupNode extends JSObject {
    val groupMembers: IscArray[ListGridRecord]
    val groupTitle: HTMLString
    val groupValue: JSAny
}


