package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.SmartClient.Forms.formsItems.ComboboxItemWithClearButton
import com.simplesys.System.Types.FormItemComponentType
import com.simplesys.System.Types.FormItemComponentType._
import com.simplesys.System.Types.FormItemType._
import com.simplesys.option.{FormItemType_FormItemComponentType, ScNone, ScOption}

class ComboboxItemWithClearButtonProps extends ComboboxItemWithButtonsProps {
    type classHandler <: ComboboxItemWithClearButton

    `type` = FormItemComponentType.ComboboxItemWithClearButton

    var constructor: ScOption[FormItemType_FormItemComponentType[FormItemType, FormItemComponentType]] = FormItemComponentType.ComboBoxItem
}
