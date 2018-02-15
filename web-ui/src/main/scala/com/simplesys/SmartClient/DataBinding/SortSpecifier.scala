package com.simplesys.SmartClient.DataBinding

import com.simplesys.System.JSObject
import com.simplesys.System.Types.SortDirection.SortDirection

import scala.scalajs.js

@js.native
trait SortSpecifier extends JSObject {
    val context: DataBoundComponent
    val direction: SortDirection
    val normalizer: js.Function
    val property: String
}
