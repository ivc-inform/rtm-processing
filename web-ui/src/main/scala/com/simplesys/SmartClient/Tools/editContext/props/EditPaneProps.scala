package com.simplesys.SmartClient.Tools.editContext.props

import com.simplesys.SmartClient.Foundation.props.CanvasProps
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.SmartClient.Tools.{EditContext, Palette, PaletteNode}
import com.simplesys.option.{ScNone, ScOption}

class EditPaneProps extends CanvasProps {
    var allowNestedDrops: ScOption[Boolean] = ScNone
    var autoEditNewNodes: ScOption[Boolean] = ScNone
    var canDragGroup: ScOption[Boolean] = ScNone
    var canGroupSelect: ScOption[Boolean] = ScNone
    var defaultPalette: ScOption[Palette] = ScNone
    var editContextProperties: ScOption[EditContext] = ScNone
    var extraPalettes: ScOption[IscArray[Palette]] = ScNone
    var hideGroupBorderOnDrag: ScOption[Boolean] = ScNone
    var persistCoordinates: ScOption[Boolean] = ScNone
    var rootComponent: ScOption[PaletteNode] = ScNone
    var selectedBorder: ScOption[String] = ScNone
    var selectedLabelBackgroundColor: ScOption[String] = ScNone
    var showSelectedLabel: ScOption[Boolean] = ScNone
}
