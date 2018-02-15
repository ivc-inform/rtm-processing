package com.simplesys.SmartClient.Foundation.props.richTextEditor

import com.simplesys.SmartClient.Forms.DynamicForm
import com.simplesys.SmartClient.Forms.formsItems.SpinnerItem
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Foundation.richTextEditor.ListProperties
import com.simplesys.SmartClient.Grids.TileLayout
import com.simplesys.SmartClient.Layout.props.LayoutProps
import com.simplesys.System.Types.MultiAutoChild
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class ListPropertiesPaneProps extends LayoutProps {
    var listProperties: ScOption[ListProperties] = ScNone
    var listPropertiesChanged: ScOption[js.Function1[ListProperties, _]] = ScNone
    var sampleTile: ScOption[Canvas with MultiAutoChild] = ScNone
    var sampleTileLayout: ScOption[TileLayout] = ScNone
    var startNumberField: ScOption[SpinnerItem] = ScNone
    var startNumberForm: ScOption[DynamicForm] = ScNone
}
