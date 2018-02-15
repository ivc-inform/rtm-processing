package com.simplesys.SmartClient.Grids.listGrid

import com.simplesys.SmartClient.Control.Button
import com.simplesys.SmartClient.Grids.Grid
import com.simplesys.System.Types.{void, _}
import com.simplesys.System.{JSDictionaryAny, JSAny}

import scala.scalajs.js

@js.native
trait RecordEditor extends Grid[ListGridField, ListGridRecord] {
    val actionButton: Button with AutoChild
    val actionButtonProperties: JSDictionaryAny
    val actionButtonStyle: CSSStyleName
    val filterImg: SCImgURL
    var performAction: js.ThisFunction0[void, void]
    val skinImgDir: URL
}



