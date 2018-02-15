package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.SmartClient.Control.Button
import com.simplesys.SmartClient.Forms.DynamicFormSS
import com.simplesys.SmartClient.Forms.formsItems.{ComboboxItemWithButtonsJS, FormItem}
import com.simplesys.SmartClient.Forms.props.DynamicFormSSProps
import com.simplesys.SmartClient.System.{ComboBoxItem, _}
import com.simplesys.System.Types.Alignment.{apply ⇒ _}
import com.simplesys.System.Types.FormItemComponentType.FormItemComponentType
import com.simplesys.System.Types.FormItemType._
import com.simplesys.System.Types.{FormItemComponentType, _}
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.option.{FormItemType_FormItemComponentType, ScNone, ScOption}

class ComboboxItemWithButtonsJSProps extends FormItemWithButtonsProps {
    type classHandler <: ComboboxItemWithButtonsJS

    var comboBoxItemName: ScOption[String] = "ComboboxWithClearButton".opt
    var constructor: ScOption[FormItemType_FormItemComponentType[FormItemType, FormItemComponentType]] = FormItemComponentType.ComboBoxItem
    var specialValues: ScOption[ValueMap] = ScNone
    var separateSpecialValues: ScOption[Boolean] = ScNone
    var comboBoxValue: ScOption[JSAny] = ScNone

    width = "100%"

    clearValue = {
        (thisTop: classHandler) ⇒

            //thisTop.Super("clearValue")

            thisTop.comboBoxValue = jSUndefined
            thisTop.innerForm.foreach(_.setValue(thisTop.comboBoxItemName, null))
            thisTop.form.foreach(form ⇒ thisTop.changed.foreach(_ (form, thisTop, thisTop.getValue())))

    }.toThisFunc.opt

    setValue = {
        (thiz: classHandler, value: JSUndefined[JSAny]) ⇒
            value.foreach {
                value ⇒
                    //isc debugTrap thiz.form
                    //thiz.Super("setValue", IscArray(value))

                    thiz.innerForm.foreach {
                        innerForm ⇒
                            innerForm.setValue(thiz.comboBoxItemName, value)

                            thiz.comboBoxValue = value
                    }
            }
    }.toThisFunc.opt

    getValue = {
        (thiz: classHandler) ⇒
            thiz.updateCancelButton(if (thiz.comboBoxValue.isEmpty) null else thiz.comboBoxValue)
            thiz.comboBoxValue
    }.toThisFunc.opt

    init = {
        (thisTop: classHandler, args: IscArray[JSAny]) ⇒


            thisTop.innerForm = DynamicFormSS.create(
                new DynamicFormSSProps {
                    width = "100%"
                    fields = Seq(
                        ComboBoxItem(
                            new ComboBoxItemProps {
                                width = "100%"
                                colSpan = 2.opt
                                nameStrong = thisTop.comboBoxItemName.nameStrongOpt
                                showTitle = false.opt
                                defaultValue = thisTop.defaultValue.opt
                                valueMap = thisTop.valueMap.opt
                                specialValues = thisTop.specialValues.opt
                                separateSpecialValues = thisTop.separateSpecialValues.opt
                                changed = {
                                    (form: DynamicFormSS, formItem: FormItem, value: JSUndefined[JSAny]) ⇒
                                        //isc debugTrap value
                                        thisTop.comboBoxValue = value
                                        thisTop.form.foreach(form ⇒ thisTop.changed.foreach(_ (form, thisTop, thisTop.getValue())))
                                }.toFunc.opt
                            }
                        )
                    ).opt
                }
            )

            thisTop.buttons =
              IscArray[Button](
                  CancelButton.create(
                      new CancelButtonProps {
                          width = 20
                          identifier = thisTop.cancelButtonName.opt
                          icon = Common.cancel.opt
                          showDisabledIcon = false.opt
                          click = {
                              (thiz: classHandler) =>
                                  //isc debugTrap thisTop
                                  thisTop.clearValue()
                                  true
                          }.toThisFunc.opt
                      })
              ).undef


            thisTop.Super("init", args)

    }.toThisFunc.opt

    `type` = FormItemComponentType.ComboboxItemWithButtonsJS
}
