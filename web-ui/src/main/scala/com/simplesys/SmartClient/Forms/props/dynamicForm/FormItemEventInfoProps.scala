package com.simplesys.SmartClient.Forms.props.dynamicForm

import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

class FormItemEventInfoProps extends AbstractClassProps {
    var icon: ScOption[String] = ScNone
    var item: ScOption[FormItem] = ScNone
    var overItem: ScOption[Boolean] = ScNone
    var overTitle: ScOption[Boolean] = ScNone

}
