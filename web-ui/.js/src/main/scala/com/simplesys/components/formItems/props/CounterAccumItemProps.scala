package com.simplesys.components.formItems.props

import com.simplesys.SmartClient.Forms.DynamicFormSS
import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.SmartClient.Forms.formsItems.props.SpinnerItemProps
import com.simplesys.SmartClient.Forms.props.DynamicFormSSProps
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.components.PropertyEditorDynamicForm
import com.simplesys.components.drawing.drawItems.CounterVariableValue
import com.simplesys.components.formItems.{CounterAccumItem, ScalaTypes, ScopeVisiblity, SendMessageItem}
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

class CounterAccumItemProps extends VariableItemProps {
    type classHandler <: CounterAccumItem

    returnType = ScalaTypes.AtomicInteger.opt
    transition2Data = false.opt
    scopeVisivlity = ScopeVisiblity.scenario.opt
    var startValue: ScOption[Int] = ScNone

    clearValue = {
        (thiz: classHandler) ⇒

            thiz.innerForm.foreach {
                innerForm ⇒
                    innerForm.setValue("startValue", null)

                    thiz.startValue = jSUndefined
            }
    }.toThisFunc.opt

    setValue = {
        (thiz: classHandler, value: JSUndefined[CounterVariableValue]) ⇒
            value.foreach {
                value ⇒
                    //isc debugTrap value
                    if (value == null)
                        thiz.clearValue()
                    else {
                        thiz.innerForm.foreach {
                            innerForm ⇒

                                innerForm.setValue("startValue", value.startValue)

                                thiz.startValue = value.startValue
                                thiz.returnType = value.returnType
                                thiz.routineCode = value.routineCode
                                thiz.transition2Data = value.transition2Data
                                thiz.scopeVisivlity = value.scopeVisivlity
                        }
                    }
            }
            thiz.Super("setValue", IscArray(value))
    }.toThisFunc.opt

    getValue = {
        (thiz: classHandler) ⇒
            if (thiz.startValue.isEmpty)
                jSUndefined
            else
                new CounterVariableValue {
                    override var startValue: JSUndefined[Int] = thiz.startValue
                    override val returnType: JSUndefined[String] = thiz.returnType
                    override val routineCode: JSUndefined[String] = thiz.routineCode
                    override val transition2Data: JSUndefined[Boolean] = thiz.transition2Data
                    override val scopeVisivlity: JSUndefined[String] = thiz.scopeVisivlity
                }.asInstanceOf[JSAny].undef
    }.toThisFunc.opt

    init = {
        (thisTop: classHandler, args: IscArray[JSAny]) ⇒
            thisTop.innerForm = DynamicFormSS.create(
                new DynamicFormSSProps {
                    width = "100%"
                    isGroup = true.opt
                    groupTitle = "Свойства счетчика - аккумулятора".opt
                    fields = Seq(
                        SpinnerItem(
                            new SpinnerItemProps {
                                nameStrong = "startValue".nameStrongOpt
                                title = "Начало отсчета".opt
                                defaultValue = thisTop.startValue.optAny
                                min = 0.0.opt
                                step = 1.0.opt
                                changed = {
                                    (form: DynamicFormSS, formItem: FormItem, value: JSUndefined[Int]) ⇒
                                        thisTop.startValue = value
                                        thisTop.routineCode = s"new AtomicInteger(${value.getOrElse(0)})"
                                        thisTop.form.foreach(form ⇒ thisTop.changed.foreach(_ (form, thisTop, thisTop.getValue())))
                                }.toFunc.opt
                            }
                        )
                    ).opt
                }
            )

            thisTop.Super("init", args)
    }.toThisFunc.opt


    changed = {
        (form: PropertyEditorDynamicForm, formItem: SendMessageItem, value: JSObject) ⇒
            //isc debugTrap form
            form.setPropertyOnSelection("variableProps", value)

    }.toFunc.opt
}
