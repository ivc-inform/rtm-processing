package com.simplesys.SmartClient.Tools.props

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.treeGrid.TreeNode
import com.simplesys.SmartClient.System.{IscArray, isc}
import com.simplesys.SmartClient.System.props.ClassProps
import com.simplesys.SmartClient.Tools._
import com.simplesys.SmartClient.Tools.props.EditContextProps.SelectedUpdated
import com.simplesys.System.Types.CSSColor
import com.simplesys.System.Types.HoopSelectionStyle.HoopSelectionStyle
import com.simplesys.System.Types.SelectedAppearance.SelectedAppearance
import com.simplesys.System.Types.SelectionStyle.SelectionStyle
import com.simplesys.System._
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.function._
import com.simplesys.option.ScOption._

import scala.scalajs.js
import scala.scalajs.js._

object EditContextProps {
    type SelectedUpdated = ThisFunction2[JSObject, JSUndefined[EditNode], IscArray[EditNode], _]
}

trait EditContextProps extends ClassProps {
    type classHandler <: EditContext

    var allowNestedDrops: ScOption[Boolean] = ScNone
    var autoEditNewNodes: ScOption[Boolean] = ScNone
    var canDragGroup: ScOption[Boolean] = ScNone
    var canGroupSelect: ScOption[Boolean] = ScNone
    var canSelectEditNodes: ScOption[Boolean] = ScNone
    var defaultPalette: ScOption[Palette] = ScNone
    var defaultParent: ScOption[EditNode] = ScNone
    var editMaskProperties: ScOption[JSObject] = ScNone
    var editNodeUpdated: ScOption[js.Function3[JSUndefined[EditNode], JSUndefined[EditContext], IscArray[String], _]] = ScNone
    var enableInlineEdit: ScOption[Boolean] = ScNone
    var extraPalettes: ScOption[IscArray[Palette]] = ScNone
    var hideGroupBorderOnDrag: ScOption[Boolean] = ScNone
    var hoopSelectionMode: ScOption[HoopSelectionStyle] = ScNone
    var hoopSelectorProperties: ScOption[JSObject] = ScNone
    var persistCoordinates: ScOption[Boolean] = ScNone
    var rootComponent: ScOption[PaletteNode] = ScNone
    var selectSingleEditNode: ScOption[ThisFunction1[classHandler, EditNode, _]] = ScNone
    var selectedAppearance: ScOption[SelectedAppearance] = ScNone
    var selectedBorder: ScOption[String] = ScNone
    var selectedLabelBackgroundColor: ScOption[String] = ScNone
    var selectedTintColor: ScOption[CSSColor] = ScNone
    var selectedTintOpacity: ScOption[Int] = ScNone
    //var selectionType: ScOption[SelectionType] = ScNone //todo  сделать анотацию @JSPropsName
    var showSelectedLabel: ScOption[Boolean] = ScNone
    var showSelectedLabelOnSelect: ScOption[Boolean] = ScNone
    var selectedEditNodesUpdated: ScOption[SelectedUpdated] = ScNone
    var selectionType: ScOption[SelectionStyle] = ScNone
}
