package com.simplesys.components.formItems.props

import com.simplesys.SmartClient.Control.Button
import com.simplesys.SmartClient.Forms.DynamicFormSS
import com.simplesys.SmartClient.Forms.formsItems.props._
import com.simplesys.SmartClient.Forms.formsItems.{DurationItem, DurationValue, FormItem}
import com.simplesys.SmartClient.Forms.props.DynamicFormSSProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.FormItemComponentType
import com.simplesys.System._
import com.simplesys.app.App._
import com.simplesys.function._
import com.simplesys.components.props.TimerUProps
import com.simplesys.components.{PropertyEditorDynamicForm, TimerU}
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class TimerItemProps extends FormItemWithButtonsProps {

    import com.simplesys.components.formItems.TimerItem

    type classHandler <: TimerItem

    typeEditorField = FormItemComponentType.TextAreaItem

    nameStrong = "timerProps".nameStrongOpt
    title = "Свойства".opt
    showTitle = false.opt
    colSpan = 2.opt

    var startedAt: ScOption[String] = ScNone
    var duration: ScOption[DurationValue] = ScNone
    var testDuration: ScOption[DurationValue] = ScNone
    var offset: ScOption[DurationValue] = ScNone
    var testOffset: ScOption[DurationValue] = ScNone

    clearValue = {
        (thiz: classHandler) ⇒
            thiz.Super("clearValue")

            thiz.innerForm.foreach {
                innerForm ⇒
                    innerForm.setValue("startedAt", null)
                    innerForm.setValue("duration", null)
                    innerForm.setValue("offset", null)

                    thiz.testInnerForm.foreach {
                        testInnerForm ⇒
                            testInnerForm.setValue("testDuration", null)
                            testInnerForm.setValue("testOffset", null)
                    }

                    thiz.startedAt = jSUndefined
                    thiz.duration = jSUndefined
                    thiz.testDuration = jSUndefined
                    thiz.offset = jSUndefined
                    thiz.testOffset = jSUndefined
            }
    }.toThisFunc.opt

    setValue = {
        (thiz: classHandler, value: JSUndefined[TimerU]) ⇒
            value.foreach {
                value ⇒
                    //isc debugTrap value
                    if (value == null)
                        thiz.clearValue()
                    else {
                        thiz.innerForm.foreach {
                            innerForm ⇒

                                innerForm.setValue("startedAt", value.startedAt)
                                innerForm.setValue("duration", value.duration)
                                innerForm.setValue("offset", value.offset)

                                thiz.testInnerForm.foreach {
                                    testInnerForm ⇒
                                        testInnerForm.setValue("testDuration", value.testDuration)
                                        testInnerForm.setValue("testOffset", value.testOffset)
                                }

                                thiz.startedAt = value.startedAt
                                thiz.duration = value.duration
                                thiz.testDuration = value.testDuration
                                thiz.offset = value.offset
                                thiz.testOffset = value.testOffset
                        }
                    }
            }
            thiz.Super("setValue", IscArray(value))
    }.toThisFunc.opt

    getValue = {
        (thiz: classHandler) ⇒
            if (thiz.startedAt.isEmpty && thiz.duration.isEmpty && thiz.offset.isEmpty && thiz.testDuration.isEmpty && thiz.testOffset.isEmpty)
                jSUndefined
            else
                TimerU(
                    new TimerUProps {
                        testOffset = thiz.testOffset.opt
                        offset = thiz.offset.opt
                        testDuration = thiz.testDuration.opt
                        duration = thiz.duration.opt
                        startedAt = thiz.startedAt.opt
                    }).asInstanceOf[JSAny].undef
    }.toThisFunc.opt

    changed = {
        (form: PropertyEditorDynamicForm, formItem: TimerItem, value: JSUndefined[TimerU]) ⇒
            //isc debugTrap form
            form.setPropertyOnSelection("timerProps", value)

    }.toFunc.opt

    init = {
        (thisTop: classHandler, args: IscArray[JSAny]) ⇒

            thisTop.innerForm = DynamicFormSS.create(
                new DynamicFormSSProps {
                    width = "100%"
                    isGroup = true.opt
                    groupTitle = "Параметры таймера".opt
                    fields = Seq(
                        SelectItem(
                            new SelectItemProps {
                                nameStrong = "startedAt".nameStrongOpt
                                title = "Начало отсчета".opt
                                required = true.opt
                                valueMap = js.Dictionary(
                                    "Midnight" → "Начало следующего дня",
                                    "Now" → "Немедленно"
                                ).opt
                                changed = {
                                    (form: DynamicFormSS, formItem: FormItem, value: JSUndefined[String]) ⇒
                                        thisTop.startedAt = value
                                        thisTop.form.foreach(form ⇒ thisTop.changed.foreach(_ (form, thisTop, thisTop.getValue())))
                                }.toFunc.opt
                            }
                        ),
                        DurationItem(
                            new DurationItemProps {
                                nameStrong = "offset".nameStrongOpt
                                title = "Сдвиг запуска".opt
                                groupTitle = "Продолжительность".opt
                                changed = {
                                    (form: DynamicFormSS, formItem: FormItem, value: JSUndefined[DurationValue]) ⇒
                                        thisTop.offset = value
                                        thisTop.form.foreach(form ⇒ thisTop.changed.foreach(_ (form, thisTop, thisTop.getValue())))
                                }.toFunc.opt
                            }
                        ),
                        DurationItem(
                            new DurationItemProps {
                                nameStrong = "duration".nameStrongOpt
                                title = "Время ожидания".opt
                                required = true.opt
                                groupTitle = "Продолжительность".opt
                                changed = {
                                    (form: DynamicFormSS, formItem: FormItem, value: JSUndefined[DurationValue]) ⇒
                                        thisTop.duration = value
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
                                    (thisTopLow: classHandler, form: DynamicFormSS, item: DurationItem) =>
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
                                                            nameStrong = "testOffset".nameStrongOpt
                                                            title = "Сдвиг запуска".opt
                                                            groupTitle = "Продолжительность".opt
                                                            changed = {
                                                                (form: DynamicFormSS, formItem: FormItem, value: JSUndefined[DurationValue]) ⇒
                                                                    thisTop.testOffset = value
                                                                    thisTop.form.foreach(form ⇒ thisTop.changed.foreach(_ (form, thisTop, thisTop.getValue())))
                                                            }.toFunc.opt
                                                        }
                                                    ),
                                                    DurationItem(
                                                        new DurationItemProps {
                                                            nameStrong = "testDuration".nameStrongOpt
                                                            title = "Время ожидания".opt
                                                            groupTitle = "Продолжительность".opt
                                                            changed = {
                                                                (form: DynamicFormSS, formItem: FormItem, value: JSUndefined[DurationValue]) ⇒
                                                                    thisTop.testDuration = value
                                                                    thisTop.form.foreach(form ⇒ thisTop.changed.foreach(_ (form, thisTop, thisTop.getValue())))
                                                            }.toFunc.opt
                                                        }
                                                    )
                                                ).opt
                                            }
                                        )
                                        thisTop.testInnerForm.get
                                }.toThisFunc.opt
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
                          click = {
                              (thiz: classHandler) =>
                                  thisTop.form.foreach {
                                      form ⇒
                                          thisTop.nameStrong.foreach {
                                              nameStrong ⇒
                                                  form.setPropertyOnSelection(nameStrong.name, jSUndefined)
                                                  form.setValue(nameStrong.name, null)
                                          }
                                  }
                                  true
                          }.toThisFunc.opt
                      })
              ).undef

            thisTop.Super("init", args)
    }.toThisFunc.opt


    `type` = FormItemComponentType.TimerItem
}
