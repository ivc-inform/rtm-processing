package com.simplesys.js.components.formItems.props

import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.SmartClient.Forms.formsItems.props.TextAreaItemSSProps
import com.simplesys.SmartClient.Forms.props.DynamicFormSSProps
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.js.components.PropertyEditorDynamicForm
import com.simplesys.js.components.drawing.drawItems.SendMessageValue
import com.simplesys.js.components.formItems.{ScalaTypes, ScopeVisiblity, SendMessageItem}
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

class SendMessageItemProps extends VariableItemProps {
    type classHandler <: SendMessageItem

    returnType = ScalaTypes.String.opt
    transition2Data = true.opt
    scopeVisivlity = ScopeVisiblity.state.opt

    var textMessage: ScOption[String] = ScNone

    title = "Отправка сообщения".opt

    clearValue = {
        (thiz: classHandler) ⇒

            thiz.innerForm.foreach {
                innerForm ⇒
                    innerForm.setValue("textMessage", null)

                    thiz.textMessage = jSUndefined
            }
    }.toThisFunc.opt

    setValue = {
        (thiz: classHandler, value: JSUndefined[SendMessageValue]) ⇒
            value.foreach {
                value ⇒
                    //isc debugTrap value
                    if (value == null)
                        thiz.clearValue()
                    else {
                        thiz.innerForm.foreach {
                            innerForm ⇒
                                innerForm.setValue("textMessage", value.textMessage)

                                thiz.textMessage = value.textMessage
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
            if (thiz.textMessage.isEmpty)
                jSUndefined
            else
                new SendMessageValue {
                    override val returnType: JSUndefined[String] = thiz.returnType
                    override val routineCode: JSUndefined[String] = thiz.routineCode
                    override val transition2Data: JSUndefined[Boolean] = thiz.transition2Data
                    override val scopeVisivlity: JSUndefined[String] = thiz.scopeVisivlity
                    override val textMessage: JSUndefined[String] = thiz.textMessage
                }.asInstanceOf[JSAny].undef
    }.toThisFunc.opt

    init = {
        (thisTop: classHandler, args: IscArray[JSAny]) ⇒

            thisTop.innerForm = DynamicFormSS.create(
                new DynamicFormSSProps {
                    width = "100%"
                    isGroup = true.opt
                    groupTitle = "Свойства подпрограммы отправки сообщения".opt
                    fields = Seq(
                        TextAreaItemSS(
                            new TextAreaItemSSProps {
                                nameStrong = "textMessage".nameStrongOpt
                                title = "Текст сообщения".opt
                                required = true.opt
                                changed = {
                                    (form: PropertyEditorDynamicForm, formItem: FormItem, value: JSUndefined[String]) =>
                                        //isc debugTrap value
                                        thisTop.textMessage = value
                                        value.foreach (value ⇒ thisTop.routineCode = s"sendMessage(${value.dblQuoted}, data)")
                                        thisTop.form.foreach(form ⇒ thisTop.changed.foreach(_ (form, thisTop, thisTop.getValue())))
                                }.toFunc.opt
                            })
                    ).opt
                }
            )

            thisTop.Super("init", args)
    }.toThisFunc.opt

    changed = {
        (form: PropertyEditorDynamicForm, formItem: SendMessageItem, value: JSObject) ⇒
            //isc debugTrap (form, value)
            form.setPropertyOnSelection("variableProps", value)

    }.toFunc.opt
}
