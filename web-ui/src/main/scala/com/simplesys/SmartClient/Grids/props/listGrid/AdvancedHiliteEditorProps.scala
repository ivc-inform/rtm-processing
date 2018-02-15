package com.simplesys.SmartClient.Grids.props.listGrid

import com.simplesys.SmartClient.Forms.{DynamicForm, FilterBuilder}
import com.simplesys.SmartClient.Foundation.StatefulCanvas
import com.simplesys.SmartClient.Layout.props.VStackProps
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.{Callback, SCImgURL}
import com.simplesys.option.{ScNone, ScOption}

class AdvancedHiliteEditorProps extends VStackProps {
    var appearanceGroupTitle: ScOption[String] = ScNone
    var callback: ScOption[Callback] = ScNone
    var cancelButton: ScOption[StatefulCanvas ] = ScNone
    var cancelButtonTitle: ScOption[String] = ScNone
    var filterBuilder: ScOption[FilterBuilder] = ScNone
    var filterGroupTitle: ScOption[String] = ScNone
    var hiliteForm: ScOption[DynamicForm ] = ScNone
    var hiliteIcons: ScOption[IscArray[SCImgURL]] = ScNone
    var invalidHilitePrompt: ScOption[String] = ScNone
    var saveButton: ScOption[StatefulCanvas ] = ScNone
    var saveButtonTitle: ScOption[String] = ScNone
    var targetFieldsItemTitle: ScOption[String] = ScNone
    var title: ScOption[String] = ScNone
}
