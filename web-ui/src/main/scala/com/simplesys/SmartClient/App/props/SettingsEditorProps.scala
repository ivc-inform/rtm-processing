package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.App.SettingsEditor
import com.simplesys.SmartClient.Control.props.IButtonSSProps
import com.simplesys.SmartClient.DataBinding.ErrorStructOld
import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.SmartClient.Forms.formsItems.props._
import com.simplesys.SmartClient.Forms.props.{DynamicFormSSProps, ValidatorProps}
import com.simplesys.SmartClient.Forms.{DynamicForm, DynamicFormSS, Validator}
import com.simplesys.SmartClient.Foundation.{Canvas, CanvasStatic}
import com.simplesys.SmartClient.Layout.{OkCancelPanel, TabSetSS}
import com.simplesys.SmartClient.Layout.props.tabSet.TabProps
import com.simplesys.SmartClient.Layout.props.{OkCancelPanelProps, TabSetSSProps, WindowSSProps}
import com.simplesys.SmartClient.Layout.tabSet.Tab
import com.simplesys.SmartClient.RPC._
import com.simplesys.SmartClient.RPC.props.RPCRequestProps
import com.simplesys.SmartClient.System.{OkCancelPanel, _}
import com.simplesys.System.Types._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
trait PasswordsStruct extends JSObject {
    val oldPassword: JSUndefined[String]
    val password: JSUndefined[String]
    val confPassword: JSUndefined[String]
    var userId: JSUndefined[Double]
}

class SettingsEditorProps extends WindowSSProps {
    type classHandler <: SettingsEditor

    height = 700
    width = 500
    isModal = true.opt
    showMaximizeButton = false.opt
    showMinimizeButton = false.opt
    title = "Настройки".ellipsis.opt
    headerIconPath = Common.settings.opt
    autoPosition = false.opt

    var customSettingItems: ScOption[Seq[FormItem]] = ScNone
    var action: ScOption[URL] = ScNone
    var okCancelPanel: ScOption[OkCancelPanel] = ScNone

    initWidget = {
        (thizTop: classHandler, arguments: IscArray[JSAny]) =>
            isc debugTrac(thizTop.getClassName(), thizTop.getIdentifier())

            thizTop.Super("initWidget", arguments)

            val identifierApp = thizTop.identifier
            val oldSkin = simpleSyS.skin
            var skin: JSUndefined[String] = jSUndefined

            //isc debugTrap thiz.customSettingItems

            val commons = DynamicFormSS.create(
                new DynamicFormSSProps {

                    fields = (Seq(
                        CheckboxItem(
                            new CheckboxItemProps {
                                title = "Показать дерево виджетов".opt
                                height = 25
                                value = simpleSyS.expertMode.getOrElse(false).asInstanceOf[JSAny].opt
                                disabled = true.opt
                                changed = {
                                    (form: DynamicForm, item: FormItem, value: JSUndefined[Boolean]) =>
                                        simpleSyS.expertMode = value

                                }.toFunc.opt
                            }
                        ),
                        SkinBoxItem(
                            new SkinBoxItemProps {
                                title = "Темы оформления (Skins)".opt
                                value = simpleSyS.skin.getOrElse(Skin.Enterprise.toString).asInstanceOf[JSAny].opt
                                changed = {
                                    (form: DynamicForm, item: FormItem, value: JSUndefined[String]) =>
                                        skin = value
                                        thizTop.okCancelPanel.foreach(_.show())
                                }.toFunc.opt
                            }
                        )
                    ) ++ (if (thizTop.customSettingItems.isDefined) thizTop.customSettingItems.get else Seq.empty)).opt

                }
            )

            val minCharInPassword = 4
            val tabSet = TabSetSS.create(
                new TabSetSSProps {
                    canCloseTabs = false.opt
                    tabs = Seq(
                        Tab(
                            new TabProps {
                                title = s"${CanvasStatic.imgHTML(Common.cards, 16, 14)} Общие".opt
                                pane = commons.opt
                            }
                        ),
                        Tab(
                            new TabProps {
                                title = s"${CanvasStatic.imgHTML(Common.password, 16, 14)} Изменить пароль для входа".opt
                                tabSelected = {
                                    (tabSet: TabSetSS, tabNum: Int, tabPane: Canvas, ID: JSUndefined[ID], tab: Tab, name: JSUndefined[String]) ⇒
                                        thizTop.okCancelPanel.foreach(_.hide())
                                }.toFunc.opt
                                pane = DynamicFormSS.create(
                                    new DynamicFormSSProps {
                                        colWidths = Seq[JSAny](150, "*").opt
                                        //action = thizTop.action.opt
                                        //canSubmit = false.opt
                                        clearValues = {
                                            (thiz: classHandler) ⇒
                                                thiz.getField("oldPassword").foreach(_.clearValue())

                                                thiz.getField("password").foreach(_.clearValue())

                                                thiz.getField("confPassword").foreach {
                                                    field ⇒
                                                        field.clearValue()
                                                        field.disable()
                                                }

                                                thiz.getField("save").foreach(_.disable())
                                        }.toThisFunc.opt
                                        fields = Seq(
                                            PasswordItem(
                                                new PasswordItemProps {
                                                    title = "Введите старый пароль".opt
                                                    nameStrong = "oldPassword".nameStrongOpt
                                                    height = 25
                                                    validateOnChange = true.opt
                                                    changed = {
                                                        (form: DynamicFormSS, item: FormItem, value: JSUndefined[String]) =>
                                                            form.getField("password").foreach {
                                                                field ⇒
                                                                    if (value.isEmpty)
                                                                        field.clearValue()
                                                                    field.setDisabled(item.getValue().toString.length == 0 || value.getOrElse("").length == 0)
                                                            }
                                                    }.toFunc.opt
                                                }
                                            ),
                                            PasswordItem(
                                                new PasswordItemProps {
                                                    title = "Введите новый пароль".opt
                                                    nameStrong = "password".nameStrongOpt
                                                    height = 25
                                                    disabled = true.opt
                                                    validators = Seq(
                                                        Validator(
                                                            new ValidatorProps {
                                                                `type` = ValidatorType.lengthRange.opt
                                                                min = minCharInPassword.opt
                                                                errorMessage = s"Не может быть короче $minCharInPassword-х символов".opt
                                                            }
                                                        )
                                                    ).opt
                                                    validateOnChange = true.opt
                                                    changed = {
                                                        (form: DynamicFormSS, item: FormItem, value: JSUndefined[String]) =>
                                                            form.getField("confPassword").foreach {
                                                                field ⇒
                                                                    if (value.isEmpty)
                                                                        field.clearValue()
                                                                    field.setDisabled(item.getValue().toString.length == 0 || value.getOrElse("").length == 0)
                                                            }
                                                    }.toFunc.opt
                                                }
                                            ),
                                            PasswordItem(
                                                new PasswordItemProps {
                                                    title = "Повторите новый пароль".opt
                                                    nameStrong = "confPassword".nameStrongOpt
                                                    height = 25
                                                    disabled = true.opt
                                                    validators = Seq(
                                                        Validator(
                                                            new ValidatorProps {
                                                                `type` = ValidatorType.lengthRange.opt
                                                                min = minCharInPassword.opt
                                                                errorMessage = s"Не может быть короче $minCharInPassword-х символов".opt
                                                            }
                                                        ),
                                                        Validator(
                                                            new ValidatorProps {
                                                                `type` = ValidatorType.custom.opt
                                                                condition = {
                                                                    var res = false
                                                                    (item: FormItem, validator: Validator, value: JSUndefined[String], record: PasswordsStruct) ⇒
                                                                        value.foreach {
                                                                            value ⇒
                                                                                item.form.foreach {
                                                                                    form ⇒
                                                                                        form.getField("save").foreach {
                                                                                            field ⇒
                                                                                                res = value.length >= minCharInPassword && record.password.getOrElse("") == record.confPassword.getOrElse("")
                                                                                                field setDisabled !res
                                                                                        }
                                                                                }
                                                                        }
                                                                        res
                                                                }.toFunc.opt
                                                                errorMessage = s"Несовпадение паролей".opt
                                                            }
                                                        )
                                                    ).opt
                                                    changed = {
                                                        (form: DynamicFormSS, item: FormItem, value: JSUndefined[String]) =>

                                                    }.toFunc.opt
                                                }
                                            ),
                                            ButtonItem(
                                                new ButtonItemProps {
                                                    title = "Сохранить".opt
                                                    icon = Common.save.opt
                                                    nameStrong = "save".nameStrongOpt
                                                    height = 25
                                                    colSpan = 2.opt
                                                    disabled = true.opt
                                                    init = {
                                                        (thizButton: classHandler, arguments: IscArray[JSAny]) ⇒
                                                            thizButton.buttonProperties = IButtonSS(
                                                                new IButtonSSProps {
                                                                    showDisabledIcon = false.opt
                                                                    click = {
                                                                        (thiz: classHandler) ⇒
                                                                            thizButton.form.foreach {
                                                                                form ⇒
                                                                                    //isc debugTrap 0
                                                                                    RPCManagerSS.sendRequest(
                                                                                        RPCRequest(
                                                                                            new RPCRequestProps {
                                                                                                timeout = 60000.opt
                                                                                                sendNoQueue = true.opt
                                                                                                data = {
                                                                                                    val res = form.getValues[PasswordsStruct]()
                                                                                                    res.userId = simpleSyS.app.userId
                                                                                                    res
                                                                                                }.opt
                                                                                                actionURL = thizTop.action.opt
                                                                                                callback = {
                                                                                                    (resp: RPCResponse, data: Response, req: RPCRequest) ⇒
                                                                                                        //isc debugTrap data.response
                                                                                                        data.response.foreach {
                                                                                                            response ⇒
                                                                                                                //isc debugTrap (response.status == RPCResponseStatic.STATUS_SUCCESS)
                                                                                                                if (response.status != RPCResponseStatic.STATUS_SUCCESS)
                                                                                                                    response.data.foreach {
                                                                                                                        error ⇒
                                                                                                                            if (!isc.isA.Object(error))
                                                                                                                                isc error(error.toString, "163B7F9E-576B-7EFA-8F3C-E536012308B4")
                                                                                                                            else {
                                                                                                                                //isc debugTrap error
                                                                                                                                val _error = error.asInstanceOf[ErrorStructOld]
                                                                                                                                _error.error.foreach {
                                                                                                                                    error ⇒
                                                                                                                                        isc errorDetail(error.message.getOrElse("Ошибка не определена"), error.stackTrace.getOrElse("Ошибка не определена"))
                                                                                                                                }
                                                                                                                            }
                                                                                                                    }
                                                                                                                else {
                                                                                                                    form.clearValues()
                                                                                                                    isc ok "Пароль изменен. При следующем входе в систему используйте новый пароль."
                                                                                                                }
                                                                                                        }

                                                                                                }.toFunc.opt
                                                                                            }
                                                                                        )
                                                                                    )
                                                                            }
                                                                            false
                                                                    }.toThisFunc.opt
                                                                }
                                                            )

                                                            thizButton.Super("init", arguments)
                                                    }.toThisFunc.opt
                                                }
                                            )
                                        ).opt

                                    }
                                ).opt
                            }
                        )
                    ).opt
                }
            )

            thizTop.okCancelPanel = OkCancelPanel.create(
                new OkCancelPanelProps {
                    height = 35
                    owner = thizTop.opt
                    ownerDestroy = false.opt
                    visibility = Visibility.hidden.opt
                    okFunction = {
                        (thiz: classHandler) =>
                            if (oldSkin != skin) {
                                simpleSyS.skin = skin

                                isc.OfflineSS.put(s"Skin$identifierApp", skin)
                                isc.OfflineSS.putBoolean(s"ScenarioTestMode$identifierApp", simpleSyS.scenarioTestMode.getOrElse(false))
                                js.Dynamic.global.window.location.reload(false)
                            }

                    }.toThisFunc.opt
                }
            )

            thizTop.addItems(IscArray(tabSet, thizTop.okCancelPanel.get))

    }.toThisFunc.opt
}
