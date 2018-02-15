package com.simplesys.components.formItems.props

import com.simplesys.SmartClient.Forms.DynamicFormSS
import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.SmartClient.Forms.formsItems.props.TextItemSSProps
import com.simplesys.SmartClient.Forms.props.DynamicFormSSProps
import com.simplesys.SmartClient.System.{IscArray, _}
import com.simplesys.System._
import com.simplesys.app.App._
import com.simplesys.function._
import com.simplesys.components.drawing.drawItems.{CalcGroupValue, CounterVariableValue, SubProgramValue, VariableValue}
import com.simplesys.components.formItems._
import com.simplesys.components.{MultiElementsValue, PropertyEditorDynamicForm}
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class CalcGroupItemProps extends VariableItemProps {
    type classHandler <: CalcGroupItem

    returnType = ScalaTypes.Int.opt
    transition2Data = true.opt
    scopeVisivlity = ScopeVisiblity.state.opt

    var scaleLine: ScOption[String] = ScNone
    var counter: ScOption[CounterVariableValue] = ScNone

    var counterVariableName: ScOption[String] = "counter".opt
    var calcGroupVariableName: ScOption[String] = "calcGroup".opt

    nameStrong = "multiElementsProps".nameStrongOpt

    clearValue = {
        (thiz: classHandler) ⇒

            thiz.innerForm.foreach {
                innerForm ⇒
                    innerForm.setValue("scaleLine", null)
                    innerForm.setValue("counter", null)

                    thiz.scaleLine = jSUndefined
                    thiz.counter = jSUndefined
            }
    }.toThisFunc.opt

    setValue = {
        (thiz: classHandler, value: JSUndefined[MultiElementsValue]) ⇒
            //isc debugTrap thiz
            value.foreach {
                value ⇒
                    if (value == null)
                        thiz.clearValue()
                    else {
                        thiz.innerForm.foreach {
                            innerForm ⇒
                                value.variables.foreach {
                                    variables ⇒

                                        val calcGroupValue = variables(thiz.calcGroupVariableName).asInstanceOf[CalcGroupValue]
                                        //isc debugTrap calcGroupValue

                                        innerForm.setValue("scaleLine", calcGroupValue.scaleLine)
                                        thiz.scaleLine = calcGroupValue.scaleLine
                                        thiz.returnType = calcGroupValue.returnType
                                        thiz.routineCode = calcGroupValue.routineCode
                                        thiz.transition2Data = calcGroupValue.transition2Data
                                        thiz.scopeVisivlity = calcGroupValue.scopeVisivlity


                                        val counterValue = variables(thiz.counterVariableName).asInstanceOf[CounterVariableValue]
                                        //isc debugTrap counterValue
                                        innerForm.setValue(thiz.counterVariableName, counterValue)
                                        thiz.counter = counterValue
                                }
                        }
                    }
            }
            thiz.Super("setValue", IscArray(value))
    }.toThisFunc.opt

    getValue = {
        (thiz: classHandler) ⇒
            if (thiz.scaleLine.isEmpty && thiz.counter.isEmpty)
                jSUndefined
            else
                new CalcGroupValue {
                    override val returnType: JSUndefined[String] = thiz.returnType
                    override val routineCode: JSUndefined[String] = thiz.routineCode
                    override val transition2Data: JSUndefined[Boolean] = thiz.transition2Data
                    override val scopeVisivlity: JSUndefined[String] = thiz.scopeVisivlity
                    override val scaleLine: JSUndefined[String] = thiz.scaleLine
                    override val counter: JSUndefined[CounterVariableValue] = thiz.counter
                }.asInstanceOf[JSAny].undef
    }.toThisFunc.opt

    init = {
        (thisTop: classHandler, args: IscArray[JSAny]) ⇒
            thisTop.innerForm = DynamicFormSS.create(
                new DynamicFormSSProps {
                    width = "100%"
                    isGroup = true.opt
                    groupTitle = "Свойства подпрограммы вычисления группы".opt
                    fields = Seq(
                        CounterAccumItem(
                            new CounterAccumItemProps {
                                nameStrong = thisTop.counterVariableName.nameStrongOpt
                                title = "Счетчик - аккумулятор".opt
                                changed = {
                                    (form: DynamicFormSS, formItem: FormItem, value: JSUndefined[CounterVariableValue]) ⇒
                                        thisTop.counter = value
                                        thisTop.routineCode = s"""calcGroup(${thisTop.identifier}_${thisTop.counterVariableName}, "${thisTop.scaleLine}", ${if (value.isEmpty) -1 else value.get.startValue})"""
                                        thisTop.form.foreach(form ⇒ thisTop.changed.foreach(_ (form, thisTop, thisTop.getValue())))

                                }.toFunc.opt
                            }
                        ),
                        TextItemSS(
                            new TextItemSSProps {
                                nameStrong = "scaleLine".nameStrongOpt
                                title = "Количество элементов в группах".opt
                                required = true.opt
                                changed = {
                                    (form: PropertyEditorDynamicForm, formItem: FormItem, value: JSUndefined[String]) =>
                                        //isc debugTrap (thisTop.ID, thisTop.counterVariableName)
                                        thisTop.scaleLine = value
                                        thisTop.counter.foreach(counter ⇒ thisTop.routineCode = s"""calcGroup(${thisTop.identifier}_${thisTop.counterVariableName}, "${value.getOrElse("0")}", ${counter.startValue})""")
                                        thisTop.form.foreach(form ⇒ thisTop.changed.foreach(_ (form, thisTop, thisTop.getValue())))
                                }.toFunc.opt
                            })
                    ).opt
                }
            )

            thisTop.Super("init", args)
    }.toThisFunc.opt

    changed = {
        (form: PropertyEditorDynamicForm, formItem: CalcGroupItem, value: JSUndefined[CalcGroupValue]) ⇒
            //isc debugTrap value
            value.foreach {
                value ⇒
                    val _variables: JSUndefined[JSDictionary[VariableValue]] = js.Dictionary(
                        formItem.counterVariableName → value.counter.get,
                        formItem.calcGroupVariableName → new CalcGroupValue {
                            override val scaleLine: JSUndefined[String] = value.scaleLine
                            override val counter: JSUndefined[CounterVariableValue] = jSUndefined
                            override val returnType: JSUndefined[String] = value.returnType
                            override val routineCode: JSUndefined[String] = value.routineCode
                            override val transition2Data: JSUndefined[Boolean] = value.transition2Data
                            override val scopeVisivlity: JSUndefined[String] = value.scopeVisivlity
                        }
                    ).undef

                    form.setPropertyOnSelection("multiElementsProps", new MultiElementsValue {
                        override val subPrograms: JSUndefined[JSDictionary[SubProgramValue]] = jSUndefined
                        override val variables: JSUndefined[JSDictionary[VariableValue]] = _variables
                    })
            }
    }.toFunc.opt
}
