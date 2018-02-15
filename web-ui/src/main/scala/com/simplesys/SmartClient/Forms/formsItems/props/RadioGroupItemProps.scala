package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.SmartClient.Forms.formsItems.radioGroupItem.RadioItem
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.{FormItemComponentType, SCClassName}
import com.simplesys.option.{ScNone, ScOption}

class RadioGroupItemProps extends FormItemProps {
    var disabledValues: ScOption[IscArray[String]] = ScNone
    var editProxyConstructor: ScOption[SCClassName] = ScNone
    var fillHorizontalSpace: ScOption[Boolean] = ScNone
    var itemProperties: ScOption[RadioItem] = ScNone
    var vertical: ScOption[Boolean] = ScNone
    var wrap: ScOption[Boolean] = ScNone
    `type` = FormItemComponentType.RadioGroupItem
}
