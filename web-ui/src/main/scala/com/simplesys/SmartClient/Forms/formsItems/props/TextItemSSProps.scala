package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.SmartClient.Forms.DynamicFormSS
import com.simplesys.SmartClient.Forms.formsItems.{FormItem, TextItemSS}
import com.simplesys.SmartClient.System.{IscArray, isc}
import com.simplesys.System.JSUndefined
import com.simplesys.System.Types._
import com.simplesys.function._
import com.simplesys.option.ScOption._

class TextItemSSProps extends TextItemProps {
    type classHandler <: TextItemSS

    keyPress = {
        (thiz: classHandler, item: FormItem, form: DynamicFormSS, keyName: KeyName, characterValue: JSUndefined[Int]) =>
            !(thiz.readOnlyDisplay.toString == ReadOnlyDisplayAppearance.readOnly.toString)

    }.toThisFunc.opt

    `type` = FormItemComponentType.TextItemSS
}
