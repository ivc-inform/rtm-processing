package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.System.Types.{FormItemComponentType, SCClassName}
import com.simplesys.System.Types.TEXTAREA_WRAP.TEXTAREA_WRAP
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js._

class TextAreaItemProps extends FormItemProps {

    import com.simplesys.System.JSAny

    var browserAutoCapitalize: ScOption[Boolean] = ScNone
    var browserAutoCorrect: ScOption[Boolean] = ScNone
    var editProxyConstructor: ScOption[SCClassName] = ScNone
    var emptyStringValue: ScOption[JSAny] = ScNone
    var enforceLength: ScOption[Boolean] = ScNone
    var formatOnBlur: ScOption[Boolean] = ScNone
    var length: ScOption[Int] = ScNone
    var minHeight: ScOption[Int] = ScNone
    var printFullText: ScOption[Boolean] = ScNone
    var showHintInField: ScOption[Boolean] = ScNone
    var usePlaceholderForHint: ScOption[Boolean] = ScNone
    var wrap: ScOption[TEXTAREA_WRAP] = ScNone
    `type` = FormItemComponentType.TextAreaItem
}
