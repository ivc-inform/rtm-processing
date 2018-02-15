package com.simplesys.SmartClient.Foundation.richTextEditor

import com.simplesys.SmartClient.Forms.DynamicForm
import com.simplesys.SmartClient.Forms.formsItems.SpinnerItem
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.TileLayout
import com.simplesys.SmartClient.Layout.{AbstractLayoutCompanion, Layout}
import com.simplesys.System.Types.{MultiAutoChild, AutoChild}

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait ListPropertiesPane extends Layout {
    var listProperties:ListProperties
    var listPropertiesChanged:js.Function1[ListProperties, _]
    val sampleTile: Canvas with MultiAutoChild
    val sampleTileLayout: TileLayout with AutoChild
    val startNumberField: SpinnerItem with AutoChild
    val startNumberForm:DynamicForm with AutoChild
}

@js.native
abstract trait AbstractListPropertiesPaneCompanion extends AbstractLayoutCompanion {
}

@js.native
object ListPropertiesPane extends AbstractListPropertiesPaneCompanion        
