package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.System.JSObject
import com.simplesys.System.Types.FormItemComponentType
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class PresetCriteriaItemProps extends SelectItemProps {
    var customOptionTitle: ScOption[String] = ScNone
    var options: ScOption[JSObject] = ScNone
    var showCustomOption: ScOption[Boolean] = ScNone
    `type` = FormItemComponentType.PresetCriteriaItem
}
