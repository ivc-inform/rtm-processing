package com.simplesys.SmartClient.Grids.props.listGrid

import com.simplesys.SmartClient.Control.ImgButton
import com.simplesys.SmartClient.Forms.{DynamicForm, FilterClause}
import com.simplesys.SmartClient.Foundation.Label
import com.simplesys.SmartClient.Layout.props.HLayoutProps
import com.simplesys.option.{ScNone, ScOption}

class HiliteRuleProps extends HLayoutProps {
    var advancedClauseEditButton: ScOption[ImgButton] = ScNone
    var advancedClauseLabel: ScOption[Label] = ScNone
    var backgroundColorTitle: ScOption[String] = ScNone
    var clause: ScOption[FilterClause] = ScNone
    var colorFieldTitle: ScOption[String] = ScNone
    var foregroundColorTitle: ScOption[String] = ScNone
    var hiliteForm: ScOption[DynamicForm] = ScNone
    var iconFieldTitle: ScOption[String] = ScNone
    var removeButton: ScOption[ImgButton] = ScNone
    var removeButtonPrompt: ScOption[String] = ScNone
    var showRemoveButton: ScOption[Boolean] = ScNone
}
