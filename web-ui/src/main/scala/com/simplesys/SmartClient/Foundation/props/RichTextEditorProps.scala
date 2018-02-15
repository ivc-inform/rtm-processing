package com.simplesys.SmartClient.Foundation.props

import com.simplesys.SmartClient.Forms.formsItems.SelectItem
import com.simplesys.SmartClient.Foundation.richTextEditor.ListPropertiesDialog
import com.simplesys.SmartClient.Foundation.{Canvas, RichTextEditor}
import com.simplesys.SmartClient.Layout.Layout
import com.simplesys.SmartClient.Layout.props.VLayoutProps
import com.simplesys.SmartClient.System.{IscArray, isc}
import com.simplesys.System.JSObject
import com.simplesys.System.Types.ControlName.ControlName
import com.simplesys.System.Types.MultiAutoChild
import com.simplesys.function._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js.{ThisFunction0, ThisFunction1}

class RichTextEditorProps extends VLayoutProps {
    type classHandler <: RichTextEditor

    /*var addContents: ScOption[ThisFunction1[classHandler, String, _]] = {
        (thiz: classHandler, content: String) ⇒
            isc debugTrap content
            if (content == """&nbsp;""")
                thiz setContents content
            else {
                thiz setContents s"${thiz.getContents()}<p>$content"
            }

    }.toThisFunc.opt*/
    var clear: ScOption[ThisFunction0[classHandler, _]] = {
        (thiz: classHandler) ⇒
            thiz setContents """&nbsp;"""

    }.toThisFunc.opt
    var alignCenterPrompt: ScOption[String] = ScNone
    var alignLeftPrompt: ScOption[String] = ScNone
    var alignRightPrompt: ScOption[String] = ScNone
    var backgroundColorPrompt: ScOption[String] = ScNone
    var boldSelectionPrompt: ScOption[String] = ScNone
    var bulletControls: ScOption[Seq[ControlName]] = ScNone
    var colorControls: ScOption[Seq[ControlName]] = ScNone
    var colorPrompt: ScOption[String] = ScNone
    var controlGroups: ScOption[Seq[ControlName]] = ScNone
    var copySelectionPrompt: ScOption[String] = ScNone
    var cutSelectionPrompt: ScOption[String] = ScNone
    var editArea: ScOption[Canvas] = ScNone
    var editAreaBackgroundColor: ScOption[String] = ScNone
    var fontControls: ScOption[Seq[ControlName]] = ScNone
    var fontNames: ScOption[JSObject] = ScNone
    var fontSelectorItem: ScOption[SelectItem] = ScNone
    var fontSelectorPrompt: ScOption[String] = ScNone
    var fontSizes: ScOption[JSObject] = ScNone
    var fontSizeSelectorItem: ScOption[SelectItem] = ScNone
    var fontSizeSelectorPrompt: ScOption[String] = ScNone
    var formatControls: ScOption[Seq[ControlName]] = ScNone
    var indentPrompt: ScOption[String] = ScNone
    var italicSelectionPrompt: ScOption[String] = ScNone
    var justifyPrompt: ScOption[String] = ScNone
    var linkPrompt: ScOption[String] = ScNone
    var linkUrlTitle: ScOption[String] = ScNone
    var listPropertiesDialog: ScOption[ListPropertiesDialog] = ScNone
    var listPropertiesPrompt: ScOption[String] = ScNone
    var listPropertiesWarningText: ScOption[String] = ScNone
    var moveFocusOnTab: ScOption[Boolean] = ScNone
    var orderedListPrompt: ScOption[String] = ScNone
    var outdentPrompt: ScOption[String] = ScNone
    var pasteSelectionPrompt: ScOption[String] = ScNone
    var strikethroughSelectionPrompt: ScOption[String] = ScNone
    var styleControls: ScOption[IscArray[ControlName]] = ScNone
    var toolArea: ScOption[Layout] = ScNone
    var toolbar: ScOption[Layout with MultiAutoChild] = ScNone
    var toolbarBackgroundColor: ScOption[String] = ScNone
    var underlineSelectionPrompt: ScOption[String] = ScNone
    var unorderedListPrompt: ScOption[String] = ScNone
    var value: ScOption[String] = ScNone
}
