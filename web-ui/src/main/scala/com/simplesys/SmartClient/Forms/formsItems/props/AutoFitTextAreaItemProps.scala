package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.System.Types.FormItemComponentType
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js._

class AutoFitTextAreaItemProps extends TextAreaItemProps {
    var maxHeight: ScOption[Int] = ScNone
    `type` = FormItemComponentType.AutoFitTextAreaItem
}
