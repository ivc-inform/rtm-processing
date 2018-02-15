package com.simplesys.components.formItems.props

import com.simplesys.SmartClient.Forms.DynamicFormSS
import com.simplesys.SmartClient.Forms.formsItems.props._
import com.simplesys.SmartClient.Forms.formsItems.{DurationItem, DurationValue, FormItem}
import com.simplesys.SmartClient.Forms.props.DynamicFormSSProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.FormItemComponentType
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.components.formItems.{GroupItem, GroupValue, TimerItem}
import com.simplesys.components.{PropertyEditorDynamicForm, TimerU}
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

class GroupItemProps extends FormItemWithButtonsProps {
    type classHandler <: GroupItem

    typeEditorField = FormItemComponentType.TextAreaItem

    nameStrong = "groupProps".nameStrongOpt
    title = "Свойства".opt
    showTitle = false.opt
    colSpan = 2.opt

    var groupName: ScOption[String] = ScNone
    var silenceDuration: ScOption[DurationValue] = ScNone
    var testSilenceDuration: ScOption[DurationValue] = ScNone
    var nameValueMap: ScOption[IscArray[String]] = ScNone

    clearValue = {
        (thiz: classHandler) ⇒
            thiz.Super("clearValue")

            thiz.innerForm.foreach {
                innerForm ⇒
                    innerForm.setValue("silenceDuration", null)

                    thiz.testInnerForm.foreach {
                        testInnerForm ⇒
                            innerForm.setValue("testSilenceDuration", null)
                    }

                    thiz.silenceDuration = jSUndefined
                    thiz.testSilenceDuration = jSUndefined
                    thiz.groupName = jSUndefined
            }
    }.toThisFunc.opt

    setValue = {
        (thiz: classHandler, value: JSUndefined[GroupValue]) ⇒
            value.foreach {
                value ⇒
                    //isc debugTrap value
                    if (value == null)
                        thiz.clearValue()
                    else {
                        thiz.innerForm.foreach {
                            innerForm ⇒

                                innerForm.setValue("silenceDuration", value.silenceDuration)
                                innerForm.setValue("groupName", value.groupName)

                                thiz.testInnerForm.foreach {
                                    testInnerForm ⇒
                                        testInnerForm.setValue("testSilenceDuration", value.testSilenceDuration)
                                }

                                thiz.groupName = value.groupName
                                thiz.silenceDuration = value.silenceDuration
                                thiz.testSilenceDuration = value.testSilenceDuration
                        }
                    }
            }
            thiz.Super("setValue", IscArray(value))
    }.toThisFunc.opt

    getValue = {
        (thiz: classHandler) ⇒
            if (thiz.silenceDuration.isEmpty && thiz.testSilenceDuration.isEmpty && thiz.groupName.isEmpty)
                jSUndefined
            else
                new GroupValue {
                    override val silenceDuration: JSUndefined[DurationValue] = thiz.silenceDuration
                    override val testSilenceDuration: JSUndefined[DurationValue] = thiz.testSilenceDuration
                    override val groupName: JSUndefined[String] = thiz.groupName
                }.asInstanceOf[JSAny].undef
    }.toThisFunc.opt


    changed = {
        (form: PropertyEditorDynamicForm, formItem: TimerItem, value: JSUndefined[TimerU]) ⇒
            //isc debugTrap form
            form.setPropertyOnSelection("groupProps", value)

    }.toFunc.opt


    init = {
        (thisTop: classHandler, args: IscArray[JSAny]) ⇒

            thisTop.innerForm = DynamicFormSS.create(
                new DynamicFormSSProps {
                    width = "100%"
                    isGroup = true.opt
                    groupTitle = "Параметры группы".opt
                    fields = Seq(
                        ComboBoxItem(
                            new ComboBoxItemProps {
                                nameStrong = "groupName".nameStrongOpt
                                title = "Наименование группы".opt
                                required = true.opt
                                valueMap = thisTop.nameValueMap.opt
                                changed = {
                                    (form: DynamicFormSS, formItem: FormItem, value: JSUndefined[String]) ⇒
                                        thisTop.groupName = value
                                        thisTop.form.foreach(form ⇒ thisTop.changed.foreach(_ (form, thisTop, thisTop.getValue())))
                                }.toFunc.opt
                            }
                        ),
                        DurationItem(
                            new DurationItemProps {
                                nameStrong = "silenceDuration".nameStrongOpt
                                title = "Время недоступности сценария".opt
                                prompt = "После завершения, сценарий в продолжнении этого времени будет недоступен".opt
                                groupTitle = "Продолжительность".opt
                                changed = {
                                    (form: DynamicFormSS, formItem: FormItem, value: JSUndefined[DurationValue]) ⇒
                                        thisTop.silenceDuration = value
                                        thisTop.form.foreach(form ⇒ thisTop.changed.foreach(_ (form, thisTop, thisTop.getValue())))
                                }.toFunc.opt
                            }
                        ),
                        CanvasItem(
                            new CanvasItemProps {
                                nameStrong = "testSetting".nameStrongOpt
                                showTitle = false.opt
                                colSpan = 2.opt
                                createCanvas = {
                                    (thisTopLow: classHandler, form: DynamicFormSS, item: JSUndefined[DurationItem]) =>
                                        thisTop.testInnerForm = DynamicFormSS.create(
                                            new DynamicFormSSProps {
                                                colWidths = Seq[JSAny]("25%", "75%").opt
                                                isGroup = true.opt
                                                groupTitle = "Параметры для тестов".opt
                                                width = "*"
                                                numCols = 2.opt
                                                items = Seq(
                                                    DurationItem(
                                                        new DurationItemProps {
                                                            nameStrong = "testSilenceDuration".nameStrongOpt
                                                            title = "Время недоступности сценария".opt
                                                            prompt = "После завершения, сценарий в продолжнении этого времени будет недоступен".opt
                                                            groupTitle = "Продолжительность".opt
                                                            changed = {
                                                                (form: DynamicFormSS, formItem: FormItem, value: JSUndefined[DurationValue]) ⇒
                                                                    thisTop.testSilenceDuration = value
                                                                    thisTop.form.foreach(form ⇒ thisTop.changed.foreach(_ (form, thisTop, thisTop.getValue())))
                                                            }.toFunc.opt
                                                        }
                                                    )
                                                ).opt
                                            }
                                        )
                                        //isc debugTrap thisTop.testInnerForm
                                        thisTop.testInnerForm.get
                                }.toThisFunc.opt
                            }
                        )
                    ).opt
                }
            )

            thisTop.Super("init", args)
    }.toThisFunc.opt

    `type` = FormItemComponentType.GroupItem
}
