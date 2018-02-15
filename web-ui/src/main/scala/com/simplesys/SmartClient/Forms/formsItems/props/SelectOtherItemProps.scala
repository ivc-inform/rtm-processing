package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.System.Types.{FormItemComponentType, HTMLString}
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js._

class SelectOtherItemProps extends SelectItemProps {
    var dialogWidth: ScOption[Int] = ScNone
    var otherTitle: ScOption[String] = ScNone
    var otherValue: ScOption[String] = ScNone
    var selectOtherPrompt: ScOption[HTMLString] = ScNone
    var separatorTitle: ScOption[String] = ScNone
    var separatorValue: ScOption[String] = ScNone
    `type` = FormItemComponentType.SelectOtherItem
}
