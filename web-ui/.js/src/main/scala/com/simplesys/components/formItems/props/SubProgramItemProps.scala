package com.simplesys.components.formItems.props

import com.simplesys.SmartClient.Forms.DynamicFormSS
import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.SmartClient.Forms.formsItems.props.{BooleanItemProps, FormItemWithButtonsProps, SelectItemProps, TextAreaItemSSProps}
import com.simplesys.SmartClient.Forms.props.DynamicFormSSProps
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.components.PropertyEditorDynamicForm
import com.simplesys.components.drawing.drawItems.SubProgramValue
import com.simplesys.components.formItems.ScalaTypes._
import com.simplesys.components.formItems.ScopeVisiblity.ScopeVisiblity
import com.simplesys.components.formItems._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

class SubProgramItemProps extends FormItemWithButtonsProps {
    type classHandler <: SubProgramItem

    nameStrong = "subProgramProps".nameStrongOpt

    var returnType: ScOption[ScalaTypes] = ScNone
    var optionType: ScOption[Boolean] = ScNone
    var loggedVariable: ScOption[Boolean] = ScNone
    var routineCode: ScOption[String] = ScNone
    var transition2Data: ScOption[Boolean] = ScNone
    var scopeVisivlity: ScOption[ScopeVisiblity] = ScNone

    title = "Свойства процедуры".opt
    showTitle = false.opt
    colSpan = 2.opt

    clearValue = {
        (thiz: classHandler) ⇒
            thiz.Super("clearValue")

            thiz.innerForm.foreach {
                innerForm ⇒
                    innerForm.setValue("returnType", null)
                    innerForm.setValue("optionType", null)
                    innerForm.setValue("loggedVariable", null)
                    innerForm.setValue("routineCode", null)
                    innerForm.setValue("transition2Data", null)
                    innerForm.setValue("scopeVisivlity", null)

                    thiz.returnType = jSUndefined
                    thiz.optionType = jSUndefined
                    thiz.loggedVariable = jSUndefined
                    thiz.routineCode = jSUndefined
                    thiz.transition2Data = jSUndefined
                    thiz.scopeVisivlity = jSUndefined
            }
    }.toThisFunc.opt

    setValue = {
        (thiz: classHandler, value: JSUndefined[SubProgramValue]) ⇒
            value.foreach {
                value ⇒
                    //isc debugTrap value
                    if (value == null)
                        thiz.clearValue()
                    else {
                        thiz.innerForm.foreach {
                            innerForm ⇒

                                innerForm.setValue("returnType", value.returnType.toString)
                                innerForm.setValue("optionType", value.optionType)
                                innerForm.setValue("loggedVariable", value.loggedVariable)
                                innerForm.setValue("routineCode", value.routineCode)
                                innerForm.setValue("transition2Data", value.transition2Data)
                                innerForm.setValue("scopeVisivlity", value.scopeVisivlity.toString)

                                thiz.returnType = value.returnType
                                thiz.optionType = value.optionType
                                thiz.loggedVariable = value.loggedVariable
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
            if (thiz.returnType.isEmpty && thiz.optionType.isEmpty && thiz.loggedVariable.isEmpty && thiz.routineCode.isEmpty && thiz.transition2Data.isEmpty && thiz.scopeVisivlity.isEmpty)
                jSUndefined
            else
                new SubProgramValue {
                    override val returnType: JSUndefined[String] = thiz.returnType
                    override val optionType: JSUndefined[Boolean] = thiz.optionType
                    override val loggedVariable: JSUndefined[Boolean] = thiz.loggedVariable
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
                    groupTitle = "Свойства подпрограммы".opt
                    fields = Seq(
                        SelectItem(
                            new SelectItemProps {
                                nameStrong = "returnType".nameStrongOpt
                                title = "Тип возвращаемого значения".opt
                                valueMap = IscArray(ScalaTypes1.values.filter(!_.toString.contains("$")).map(_.toString).toSeq: _*).opt
                                changed = {
                                    (form: DynamicFormSS, formItem: FormItem, value: JSUndefined[String]) ⇒
                                        thisTop.returnType = value
                                        //isc debugTrap thisTop.returnType
                                        thisTop.form.foreach(form ⇒ thisTop.changed.foreach(_ (form, thisTop, thisTop.getValue())))
                                }.toFunc.opt
                            }
                        ),
                        BooleanItem(
                            new BooleanItemProps {
                                nameStrong = "optionType".nameStrongOpt
                                title = "Опциональность".opt
                                changed = {
                                    (form: DynamicFormSS, formItem: FormItem, value: JSUndefined[Boolean]) ⇒
                                        thisTop.optionType = value
                                        //isc debugTrap thisTop.returnType
                                        thisTop.form.foreach(form ⇒ thisTop.changed.foreach(_ (form, thisTop, thisTop.getValue())))
                                }.toFunc.opt
                            }
                        ),
                        BooleanItem(
                            new BooleanItemProps {
                                nameStrong = "loggedVariable".nameStrongOpt
                                title = "Протоколирование результата".opt
                                changed = {
                                    (form: DynamicFormSS, formItem: FormItem, value: JSUndefined[Boolean]) ⇒
                                        thisTop.loggedVariable = value
                                        thisTop.form.foreach(form ⇒ thisTop.changed.foreach(_ (form, thisTop, thisTop.getValue())))
                                }.toFunc.opt
                            }
                        ),
                        TextAreaItemSS(
                            new TextAreaItemSSProps {
                                nameStrong = "routineCode".nameStrongOpt
                                title = "Текст подпрограммы".opt
                                required = true.opt
                                changed = {
                                    (form: PropertyEditorDynamicForm, formItem: FormItem, value: JSUndefined[String]) =>
                                        thisTop.routineCode = value
                                        thisTop.form.foreach(form ⇒ thisTop.changed.foreach(_ (form, thisTop, thisTop.getValue())))
                                }.toFunc.opt
                            }),
                        BooleanItem(
                            new BooleanItemProps {
                                nameStrong = "transition2Data".nameStrongOpt
                                title = "Передавать результат между состояниями".opt
                                changed = {
                                    (form: PropertyEditorDynamicForm, formItem: FormItem, value: JSUndefined[Boolean]) =>
                                        thisTop.transition2Data = value
                                        thisTop.form.foreach(form ⇒ thisTop.changed.foreach(_ (form, thisTop, thisTop.getValue())))
                                }.toFunc.opt
                            }),
                        SelectItem(
                            new SelectItemProps {
                                nameStrong = "scopeVisivlity".nameStrongOpt
                                title = "Уровень видимости".opt
                                valueMap = IscArray(ScopeVisiblity.values.filter(!_.toString.contains("$")).map(_.toString).toSeq: _*).opt
                                changed = {
                                    (form: DynamicFormSS, formItem: FormItem, value: JSUndefined[String]) ⇒
                                        thisTop.scopeVisivlity = value.toString
                                        //isc debugTrap thisTop.scopeVisivlity
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
        (form: PropertyEditorDynamicForm, formItem: SubProgramItem, value: JSObject) ⇒
            //isc debugTrap form
            form.setPropertyOnSelection("subProgramProps", value)

    }.toFunc.opt
}
