package com.simplesys.SmartClient.Forms.props

import com.simplesys.SmartClient.Control.ImgButton
import com.simplesys.SmartClient.Forms.formsItems.{FormItem, PickList, SelectItem}
import com.simplesys.SmartClient.Forms.SearchForm
import com.simplesys.SmartClient.Layout.props.LayoutProps
import com.simplesys.System.Types.{Criteria, void}
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class FilterClauseProps extends LayoutProps {
    var clause: ScOption[SearchForm] = ScNone
    var criterion: ScOption[Criteria] = ScNone
    var fieldPicker: ScOption[PickList] = ScNone
    var fieldPickerProperties: ScOption[FormItem] = ScNone
    var fieldPickerTitle: ScOption[String] = ScNone
    var operatorPicker: ScOption[SelectItem] = ScNone
    var operatorPickerProperties: ScOption[FormItem] = ScNone
    var operatorPickerTitle: ScOption[String] = ScNone
    var remove: ScOption[js.Function1[void, _]] = ScNone
    var removeButton: ScOption[ImgButton] = ScNone
    var removeButtonPrompt: ScOption[String] = ScNone
    var showFieldTitles: ScOption[Boolean] = ScNone
    var showRemoveButton: ScOption[Boolean] = ScNone
    var validateOnChange: ScOption[Boolean] = ScNone
    var valueItemTitle: ScOption[String] = ScNone
}
