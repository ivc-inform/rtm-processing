package com.simplesys.SmartClient.Forms.props

import com.simplesys.SmartClient.Forms.DynamicFormSS
import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.function._
import com.simplesys.option.ScOption
import com.simplesys.option.ScOption._

import scala.scalajs.js.ThisFunction2

class DynamicFormSSProps extends DynamicFormProps {

    import com.simplesys.System._
    import com.simplesys.option.ScNone

    type classHandler <: DynamicFormSS

    var createItem: ScOption[ThisFunction2[classHandler, FormItem, String, _]] = {
        (thiz: classHandler, item: FormItem, tp: String) =>
            //isc debugTrap item
            item.nameStrong.foreach(name => item.name = name.name)
            //isc debugTrap item
            thiz.Super("createItem", IscArray[JSAny](item, tp))
    }.toThisFunc.opt

    var setPropertyOnSelection: ScOption[ThisFunction2[classHandler, String, JSUndefined[JSAny], _]] = ScNone
}
