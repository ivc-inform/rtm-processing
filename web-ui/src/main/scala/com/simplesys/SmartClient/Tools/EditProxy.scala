package com.simplesys.SmartClient.Tools

import com.simplesys.SmartClient.Forms.DynamicForm
import com.simplesys.SmartClient.Foundation.{Canvas, Label}
import com.simplesys.SmartClient.System.{AbstractClassCompanion, Class}
import com.simplesys.System.JSUndefined
import com.simplesys.System.Types.InlineEditEvent.InlineEditEvent
import com.simplesys.System.Types.SelectedAppearance.SelectedAppearance
import com.simplesys.System.Types.{AutoChild, CSSColor, MultiAutoChild, void}

import scala.scalajs.js

@js.native
trait EditProxy extends Class {
    val allowNestedDrops: Boolean
    def setEditMode(editingOn: Boolean):void
    val autoMaskChildren: Boolean
    val bringToFrontOnSelect: Boolean
    val canSelect: JSUndefined[Boolean]
    val canSelectChildren: Boolean
    val childrenSnapResizeToGrid: Boolean
    val childrenSnapToGrid: Boolean
    val editMask: Canvas with AutoChild
    def getInlineEditText(): String
    val hoopSelector: Canvas with AutoChild
    val inlineEditEvent: InlineEditEvent
    val inlineEditForm: DynamicForm with MultiAutoChild
    val inlineEditInstructionLabel: Label with AutoChild
    val inlineEditInstructions: String
    val inlineEditMultiline: Boolean
    val inlineEditOnDrop: Boolean
    val persistCoordinates: Boolean
    val selectedAppearance: SelectedAppearance
    val selectedBorder: String
    val selectedLabelBackgroundColor: String
    val selectedTintColor: CSSColor
    val selectedTintOpacity: Int
    def setCanSelectChildren(canSelect: Boolean): void
    def setInlineEditText(newValue: String): void
    def showSelectedAppearance(show: Boolean): void
    //def startInlineEditing(appendChar: String = js.native): void
    val supportsInlineEdit: Boolean
}

@js.native
abstract class AbstractEditProxyCompanion extends AbstractClassCompanion
