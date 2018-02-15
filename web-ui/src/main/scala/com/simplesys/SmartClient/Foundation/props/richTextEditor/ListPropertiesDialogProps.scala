package com.simplesys.SmartClient.Foundation.props.richTextEditor

import com.simplesys.SmartClient.Control.IButton
import com.simplesys.SmartClient.Foundation.richTextEditor.{ListProperties, ListPropertiesPane}
import com.simplesys.SmartClient.Layout.props.WindowProps
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class ListPropertiesDialogProps extends WindowProps {
    var applyButton: ScOption[IButton] = ScNone
    var applyButtonTitle: ScOption[String] = ScNone
    var applyClick: ScOption[js.Function1[ListProperties, _]] = ScNone
    var cancelButton: ScOption[IButton] = ScNone
    var cancelButtonTitle: ScOption[String] = ScNone
    var cancelClick: ScOption[js.Function1[ListProperties, _]] = ScNone
    var listPropertiesPane: ScOption[ListPropertiesPane] = ScNone
}
