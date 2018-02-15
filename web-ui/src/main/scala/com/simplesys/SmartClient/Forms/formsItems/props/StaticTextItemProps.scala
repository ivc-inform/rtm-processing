package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.System.Types.{FormItemComponentType, SCClassName}
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js._

class StaticTextItemProps extends FormItemProps {
    var clipValue: ScOption[Boolean] = ScNone
    var editProxyConstructor: ScOption[SCClassName] = ScNone
    var escapeHTML: ScOption[Boolean] = ScNone
    var wrap: ScOption[Boolean] = ScNone
    `type` = FormItemComponentType.StaticTextItem
}
