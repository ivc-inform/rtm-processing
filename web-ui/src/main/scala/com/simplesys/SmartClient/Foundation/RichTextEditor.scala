package com.simplesys.SmartClient.Foundation

import com.simplesys.SmartClient.Forms.formsItems.SelectItem
import com.simplesys.SmartClient.Foundation.richTextEditor.ListPropertiesDialog
import com.simplesys.SmartClient.Layout.{AbstractLayoutCompanion, AbstractVLayoutCompanion, Layout, VLayout}
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.ControlName.ControlName
import com.simplesys.System.Types.{AutoChild, MultiAutoChild, void}
import com.simplesys.System.{JSAny, JSObject}

import scala.scalajs.js

@js.native
trait RichTextEditor extends VLayout {
    //def addContents(content: String): void
    def clear(): void
    var alignCenterPrompt: String
    var alignLeftPrompt: String
    var alignRightPrompt: String
    var backgroundColorPrompt: String
    var boldSelectionPrompt: String
    val bulletControls: IscArray[ControlName]
    val colorControls: IscArray[ControlName]
    var colorPrompt: String
    var controlGroups: IscArray[ControlName]
    var copySelectionPrompt: String
    var cutSelectionPrompt: String
    def doWarn(): void
    val editArea: Canvas with AutoChild
    val editAreaBackgroundColor: String
    var fontControls: IscArray[ControlName]
    var fontNames: JSObject
    val fontSelectorItem: SelectItem with AutoChild
    var fontSelectorPrompt: String
    val fontSizes: JSObject
    val fontSizeSelectorItem: SelectItem with AutoChild
    var fontSizeSelectorPrompt: String
    var formatControls: IscArray[ControlName]
    def getValue(): JSAny
    var indentPrompt: String
    var italicSelectionPrompt: String
    var justifyPrompt: String
    var linkPrompt: String
    var linkUrlTitle: String
    var listPropertiesDialog: ListPropertiesDialog with AutoChild
    var listPropertiesPrompt: String
    var listPropertiesWarningText: String
    var moveFocusOnTab: Boolean
    var orderedListPrompt: String
    var outdentPrompt: String
    var pasteSelectionPrompt: String
    def richEditorSupported(): Boolean
    def setMoveFocusOnTab(moveFocusOnTab: Boolean): void
    def setValue(value: String): void
    var strikethroughSelectionPrompt: String
    val styleControls: IscArray[ControlName]
    val toolArea: Layout with AutoChild
    val toolbar: Layout with MultiAutoChild
    val toolbarBackgroundColor: String
    val underlineSelectionPrompt: String
    val unorderedListPrompt: String
    val value: String
}

@js.native
abstract trait AbstractRichTextEditorCompanion extends AbstractVLayoutCompanion {
}



