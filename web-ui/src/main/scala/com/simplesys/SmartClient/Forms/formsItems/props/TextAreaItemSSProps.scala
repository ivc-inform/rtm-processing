package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.SmartClient.Forms.DynamicFormSS
import com.simplesys.SmartClient.Forms.formsItems.{FormItem, TextAreaItemSS}
import com.simplesys.System.Types.{ReadOnlyDisplayAppearance, _}
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.ScOption._

class TextAreaItemSSProps extends TextAreaItemProps {
    type classHandler <: TextAreaItemSS

    keyPress = {
        (thiz: classHandler, item: FormItem, form: DynamicFormSS, keyName: KeyName, characterValue: JSUndefined[Int]) =>
            !(thiz.readOnlyDisplay.toString == ReadOnlyDisplayAppearance.readOnly.toString)

    }.toThisFunc.opt

    `type` = FormItemComponentType.TextAreaItemSS
}
