package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.SmartClient.Forms.formsItems.TextItem
import com.simplesys.System.JSAny
import com.simplesys.System.Types.CharacterCasing.CharacterCasing
import com.simplesys.System.Types.{FormItemComponentType, SCClassName}
import com.simplesys.option.{ScNone, ScOption}

class TextItemProps extends FormItemProps {
    type classHandler <: TextItem

    var browserAutoCapitalize: ScOption[Boolean] = ScNone
    var browserAutoCorrect: ScOption[Boolean] = ScNone
    var browserInputType: ScOption[String] = ScNone
    var characterCasing: ScOption[CharacterCasing] = ScNone
    var editProxyConstructor: ScOption[SCClassName] = ScNone
    var emptyStringValue: ScOption[JSAny] = ScNone
    var enforceLength: ScOption[Boolean] = ScNone
    var fetchMissingValues: ScOption[Boolean] = ScNone
    var formatOnBlur: ScOption[Boolean] = ScNone
    var formatOnFocusChange: ScOption[Boolean] = ScNone
    var keyPressFilter: ScOption[String] = ScNone
    var length: ScOption[Int] = ScNone
    var mask: ScOption[String] = ScNone
    var maskOverwriteMode: ScOption[Boolean] = ScNone
    var maskPadChar: ScOption[String] = ScNone
    var maskPromptChar: ScOption[String] = ScNone
    var maskSaveLiterals: ScOption[Boolean] = ScNone
    var printFullText: ScOption[Boolean] = ScNone
    var showHintInField: ScOption[Boolean] = ScNone
    var usePlaceholderForHint: ScOption[Boolean] = ScNone
    `type` = FormItemComponentType.TextItem
}
