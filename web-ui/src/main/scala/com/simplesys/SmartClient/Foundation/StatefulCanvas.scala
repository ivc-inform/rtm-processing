package com.simplesys.SmartClient.Foundation

import com.simplesys.System.JSUndefined
import com.simplesys.System.Types.Alignment.Alignment
import com.simplesys.System.Types.SelectionType.SelectionType
import com.simplesys.System.Types.State._
import com.simplesys.System.Types.VerticalAlignment._
import com.simplesys.System.Types._

import scala.scalajs.js

@js.native
trait StatefulCanvas extends Canvas {

    var actionType: SelectionType
    def addToRadioGroup(groupID: String): void
    var align: Alignment
    var autoFit: Boolean
    var baseStyle: CSSStyleName
    def deselect(): void
    def getActionType(): void
    def getState(): State
    def getStateSuffix(): String
    def getTitle(): HTMLString
    var icon: SCImgURL
    var iconSmall: JSUndefined[SCImgURL]
    var title1: JSUndefined[String]
    val iconHeight: Int
    val iconOrientation: String
    val iconSize: Int
    val iconSpacing: Int
    var iconStyle: CSSStyleName
    val iconWidth: Int
    var ignoreRTL: Boolean
    def isSelected(): Boolean
    val overCanvas: Canvas with AutoChild
    var overCanvasConstructor: String
    var overCanvasDefaults: Canvas
    var radioGroup: String
    var redrawOnStateChange: Boolean
    def removeFromRadioGroup(groupID: String = js.native): void
    def select(): void
    val selected: Boolean
    def setActionType(): void
    def setAutoFit(autoFit: Boolean): void
    def setBaseStyle(style: CSSStyleName): void
    def setIcon(icon: URL): void
    def setIconOrientation(orientation: String): void
    def setIconStyle(iconStyle: CSSStyleName): void
    def setIgnoreRTL(ignoreRTL: Boolean): void
    def setSelected(newIsSelected: Boolean): void
    def setState(newState: State): void
    def setTitle(newTitle: HTMLString): void
    var showDisabled: Boolean
    var showDown: Boolean
    val showDownIcon: Boolean
    var showFocused: Boolean
    var showFocusedAsOver: Boolean
    var showFocusedIcon: Boolean
    var showOverCanvas: Boolean
    var showRollOver: Boolean
    var showRollOverIcon: Boolean
    var showRTLIcon: Boolean
    var showSelectedIcon: Boolean
    val state: State
    val title: HTMLString
    var valign: VerticalAlignment
}

@js.native
abstract class AbstractStatefulCanvasCompanion extends AbstractCanvasCompanion

