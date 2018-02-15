package com.simplesys.js.components.formItems.props

import com.simplesys.SmartClient.Forms.DynamicFormSS
import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.SmartClient.Forms.formsItems.props._
import com.simplesys.SmartClient.Forms.props.DynamicFormSSProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.{FormItemComponentType, ValueMap}
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.js.components.PropertyEditorDynamicForm
import com.simplesys.js.components.formItems._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class LoggingItemProps extends FormItemWithButtonsProps {
    type classHandler <: LoggingItem

    nameStrong = "loggingProps".nameStrongOpt

    title = "Свойства протоколирования".opt
    showTitle = false.opt
    colSpan = 2.opt

    var sStage: ScOption[String] = "stateName.identifier".opt
    var sParentStage: ScOption[String] = "data.asString(\"sParentSate\")".opt
    var fBonusBase: ScOption[String] = ScNone
    var idBonusMessage: ScOption[String] = ScNone
    var sBonusMessage: ScOption[String] = ScNone
    var idMarketingMessage: ScOption[String] = ScNone
    var sMarketingMessage: ScOption[String] = ScNone
    var sEvent: ScOption[String] = ScNone
    var sActionType: ScOption[String] = ScNone

    var loggingValueMap4String: ScOption[ValueMap] = ScNone
    var loggingValueMap4Double: ScOption[ValueMap] = ScNone
    var loggingValueMap4Long: ScOption[ValueMap] = ScNone

    clearValue = {
        (thiz: classHandler) ⇒
            thiz.Super("clearValue")

            thiz.innerForm.foreach {
                innerForm ⇒
                    innerForm.setValue("sStage", null)
                    innerForm.setValue("sParentStage", null)
                    innerForm.setValue("fBonusBase", null)
                    innerForm.setValue("idBonusMessag", null)
                    innerForm.setValue("sBonusMessage", null)
                    innerForm.setValue("idMarketingMessage", null)
                    innerForm.setValue("sMarketingMessage", null)
                    innerForm.setValue("sEvent", null)
                    innerForm.setValue("sActionType", null)

                    thiz.sStage = jSUndefined
                    thiz.sParentStage = jSUndefined
                    thiz.fBonusBase = jSUndefined
                    thiz.idBonusMessage = jSUndefined
                    thiz.sBonusMessage = jSUndefined
                    thiz.idMarketingMessage = jSUndefined
                    thiz.sMarketingMessage = jSUndefined
                    thiz.sEvent = jSUndefined
                    thiz.sActionType = jSUndefined
            }
    }.toThisFunc.opt

    setValue = {
        (thiz: classHandler, value: JSUndefined[LoggingValue]) ⇒

            value.foreach {
                value ⇒
                    //isc debugTrap value
                    if (value == null)
                        thiz.clearValue()
                    else {
                        thiz.innerForm.foreach {
                            innerForm ⇒

                                innerForm.setValue("sStage", value.sStage)
                                innerForm.setValue("sParentStage", value.sParentStage)
                                innerForm.setValue("fBonusBase", value.fBonusBase)
                                innerForm.setValue("idBonusMessage", value.idBonusMessage)
                                innerForm.setValue("sBonusMessage", value.sBonusMessage)
                                innerForm.setValue("idMarketingMessage", value.idMarketingMessage)
                                innerForm.setValue("sMarketingMessage", value.sMarketingMessage)
                                innerForm.setValue("sEvent", value.sEvent)
                                innerForm.setValue("sActionType", value.sActionType)

                                thiz.sStage = value.sStage
                                thiz.sParentStage = value.sParentStage
                                thiz.fBonusBase = value.fBonusBase
                                thiz.idBonusMessage = value.idBonusMessage
                                thiz.sBonusMessage = value.sBonusMessage
                                thiz.idMarketingMessage = value.idMarketingMessage
                                thiz.sMarketingMessage = value.sMarketingMessage
                                thiz.sEvent = value.sEvent
                                thiz.sActionType = value.sActionType
                        }
                    }
                    thiz.Super("setValue", IscArray(value))
            }
    }.toThisFunc.opt

    getValue = {
        (thiz: classHandler) ⇒
            if (thiz.sStage.isEmpty &&
              thiz.sParentStage.isEmpty &&
              thiz.fBonusBase.isEmpty &&
              thiz.idBonusMessage.isEmpty &&
              thiz.sBonusMessage.isEmpty &&
              thiz.idMarketingMessage.isEmpty &&
              thiz.sMarketingMessage.isEmpty &&
              thiz.sEvent.isEmpty &&
              thiz.sActionType.isEmpty)
                jSUndefined
            else
                new LoggingValue {
                    override val sActionType: JSUndefined[String] = thiz.sActionType
                    override val sEvent: JSUndefined[String] = thiz.sEvent
                    override val sBonusMessage: JSUndefined[String] = thiz.sBonusMessage
                    override val idMarketingMessage: JSUndefined[String] = thiz.idMarketingMessage
                    override val sParentStage: JSUndefined[String] = thiz.sParentStage
                    override val sStage: JSUndefined[String] = thiz.sStage
                    override val fBonusBase: JSUndefined[String] = thiz.fBonusBase
                    override val sMarketingMessage: JSUndefined[String] = thiz.sMarketingMessage
                    override val idBonusMessage: JSUndefined[String] = thiz.idBonusMessage
                }.asInstanceOf[JSAny].undef
    }.toThisFunc.opt

    changed = {
        (form: PropertyEditorDynamicForm, formItem: SubProgramItem, value: JSUndefined[JSObject]) ⇒
            //isc debugTrap form
            form.setPropertyOnSelection("loggingProps", value)

    }.toFunc.opt

    init = {
        (thisTop: classHandler, args: IscArray[JSAny]) ⇒
            //isc debugTrap thisTop.loggingValueMap

            thisTop.innerForm = DynamicFormSS.create(
                new DynamicFormSSProps {
                    width = "100%"
                    isGroup = true.opt
                    groupTitle = "Свойства  протоколирования".opt
                    fields = Seq(
                        ComboboxItemWithButtonsJS(
                            new ComboboxItemWithButtonsJSProps {
                                nameStrong = "sStage".nameStrongOpt
                                prompt = "sStage".opt
                                title = "Этап сценария".opt
                                defaultValue = "stateName.identifier".asInstanceOf[JSAny].opt
                                valueMap = js.Dictionary("stateName.identifier" → "Текущее состояние").opt
                                disabled = true.opt
                                change = {
                                    (form: DynamicFormSS, item: FormItem, value: JSUndefined[_ <: JSAny], oldValue: JSUndefined[_ <: JSAny]) ⇒
                                        if (value.isEmpty)
                                            item setValue "stateName.identifier".asInstanceOf[JSAny]
                                        true
                                }.toFunc.opt
                                changed = {
                                    (form: PropertyEditorDynamicForm, formItem: FormItem, value: JSUndefined[String]) =>
                                        thisTop.sStage = value
                                        thisTop.form.foreach(form ⇒ thisTop.changed.foreach(_ (form, thisTop, thisTop.getValue())))
                                }.toFunc.opt
                            }),
                        ComboboxItemWithButtonsJS(
                            new ComboboxItemWithButtonsJSProps {
                                nameStrong = "sParentStage".nameStrongOpt
                                prompt = "sParentStage".opt
                                valueMap = js.Dictionary("data.asString(\"sParentSate\")" → "Предыдущее состояние").opt
                                defaultValue = "data.asString(\"sParentSate\")".asInstanceOf[JSAny].opt
                                title = "Предыдущий этап сценария".opt
                                disabled = true.opt
                                change = {
                                    (form: DynamicFormSS, item: FormItem, value: JSUndefined[JSAny], oldValue: JSUndefined[JSAny]) ⇒
                                        if (value.isEmpty)
                                            item setValue "data.asString(\"sParentSate\")".asInstanceOf[JSAny]
                                        true
                                }.toFunc.opt
                                changed = {
                                    (form: PropertyEditorDynamicForm, formItem: FormItem, value: JSUndefined[String]) =>
                                        thisTop.sParentStage = value
                                        thisTop.form.foreach(form ⇒ thisTop.changed.foreach(_ (form, thisTop, thisTop.getValue())))
                                }.toFunc.opt
                            }),
                        ComboboxItemWithButtonsJS(
                            new ComboboxItemWithButtonsJSProps {
                                constructor = FormItemComponentType.SelectItem
                                valueMap = thisTop.loggingValueMap4Double.opt
                                nameStrong = "fBonusBase".nameStrongOpt
                                prompt = "fBonusBase".opt
                                title = "Числовое поле".opt
                                changed = {
                                    (form: PropertyEditorDynamicForm, formItem: FormItem, value: JSUndefined[String]) =>
                                        thisTop.fBonusBase = value
                                        thisTop.form.foreach(form ⇒ thisTop.changed.foreach(_ (form, thisTop, thisTop.getValue())))
                                }.toFunc.opt
                            }) ,
                       /* ComboboxItemWithButtonsJS(
                            new ComboboxItemWithButtonsJSProps {
                                nameStrong = "idBonusMessage".nameStrongOpt
                                prompt = "idBonusMessage".opt
                                title = "Информация 2".opt
                                valueMap = thisTop.loggingValueMap4Long.opt
                                changed = {
                                    (form: PropertyEditorDynamicForm, formItem: FormItem, value: JSUndefined[String]) =>
                                        thisTop.idBonusMessage = value
                                        thisTop.form.foreach(form ⇒ thisTop.changed.foreach(_ (form, thisTop, thisTop.getValue())))
                                }.toFunc.opt
                            }),
                        ComboboxItemWithButtonsJS(
                            new ComboboxItemWithButtonsJSProps {
                                nameStrong = "sBonusMessage".nameStrongOpt
                                prompt = "sBonusMessage".opt
                                title = "Информация 3".opt
                                valueMap = thisTop.loggingValueMap4String.opt
                                changed = {
                                    (form: PropertyEditorDynamicForm, formItem: FormItem, value: JSUndefined[String]) =>
                                        thisTop.sBonusMessage = value
                                        thisTop.form.foreach(form ⇒ thisTop.changed.foreach(_ (form, thisTop, thisTop.getValue())))
                                }.toFunc.opt
                            }),
                        ComboboxItemWithButtonsJS(
                            new ComboboxItemWithButtonsJSProps {
                                nameStrong = "idMarketingMessage".nameStrongOpt
                                prompt = "idMarketingMessage".opt
                                title = "Информация 4".opt
                                valueMap = thisTop.loggingValueMap4Long.opt
                                changed = {
                                    (form: PropertyEditorDynamicForm, formItem: FormItem, value: JSUndefined[String]) =>
                                        thisTop.idMarketingMessage = value
                                        thisTop.form.foreach(form ⇒ thisTop.changed.foreach(_ (form, thisTop, thisTop.getValue())))
                                }.toFunc.opt
                            }),*/
                        ComboboxItemWithButtonsJS(
                            new ComboboxItemWithButtonsJSProps {
                                nameStrong = "sMarketingMessage".nameStrongOpt
                                prompt = "sMarketingMessage".opt
                                title = "Текстовое поле".opt
                                valueMap = thisTop.loggingValueMap4String.opt
                                changed = {
                                    (form: PropertyEditorDynamicForm, formItem: FormItem, value: JSUndefined[String]) =>
                                        thisTop.sMarketingMessage = value
                                        thisTop.form.foreach(form ⇒ thisTop.changed.foreach(_ (form, thisTop, thisTop.getValue())))
                                }.toFunc.opt
                            })/*,
                        ComboboxItemWithButtonsJS(
                            new ComboboxItemWithButtonsJSProps {
                                nameStrong = "sEvent".nameStrongOpt
                                prompt = "sEvent".opt
                                title = "Информация 6".opt
                                valueMap = thisTop.loggingValueMap4String.opt
                                changed = {
                                    (form: PropertyEditorDynamicForm, formItem: FormItem, value: JSUndefined[String]) =>
                                        thisTop.sEvent = value
                                        thisTop.form.foreach(form ⇒ thisTop.changed.foreach(_ (form, thisTop, thisTop.getValue())))
                                }.toFunc.opt
                            }),
                        ComboboxItemWithButtonsJS(
                            new ComboboxItemWithButtonsJSProps {
                                nameStrong = "sActionType".nameStrongOpt
                                prompt = "sActionType".opt
                                title = "Информация 7".opt
                                valueMap = thisTop.loggingValueMap4String.opt
                                changed = {
                                    (form: PropertyEditorDynamicForm, formItem: FormItem, value: JSUndefined[String]) =>
                                        thisTop.sActionType = value
                                        thisTop.form.foreach(form ⇒ thisTop.changed.foreach(_ (form, thisTop, thisTop.getValue())))
                                }.toFunc.opt
                            })*/
                    ).opt
                }
            )

            thisTop.Super("init", args)
    }.toThisFunc.opt
}
