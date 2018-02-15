package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.SmartClient.Control.Button
import com.simplesys.SmartClient.Drawing.props.ShadowProps
import com.simplesys.SmartClient.Drawing.{Point, Shadow}
import com.simplesys.SmartClient.Forms.DynamicFormSS
import com.simplesys.SmartClient.Forms.formsItems.{FormItem, ShadowItem}
import com.simplesys.SmartClient.Forms.props.DynamicFormSSProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.FormItemComponentType
import com.simplesys.System.{JSAny, _}
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js
import scala.scalajs.js.ThisFunction0

class ShadowItemProps extends FormItemWithButtonsProps {

    type classHandler <: ShadowItem

    width = "100%"
    nameStrong = "shadow".nameStrongOpt
    title = "Тень".opt
    typeEditorField = FormItemComponentType.TextAreaItem
    showTitle = false.opt
    colSpan = 2.opt

    var shadow: ScOption[Shadow] = ScNone

    clearValue = {
        (thiz: classHandler) ⇒
            thiz.Super("clearValue")

            thiz.innerForm.foreach {
                innerForm ⇒
                    innerForm.setValue("color", null)
                    innerForm.setValue("blur", null)
                    innerForm.setValue("offset", null)

                    thiz.shadow = jSUndefined
            }
    }.toThisFunc.opt

    setValue = {
        (thiz: classHandler, value: JSUndefined[Shadow]) ⇒
            value.foreach {
                value ⇒
                    //isc debugTrap value

                    if (value == null)
                        thiz.clearValue()
                    else {
                        thiz.innerForm.foreach {
                            innerForm ⇒
                                innerForm.setValue("color", value.color)
                                innerForm.setValue("blur", value.blur)
                                innerForm.setValue("offset", value.offset)

                                thiz.checkShadowUndefined()

                                //isc debugTrap(thiz.shadow, value)
                                thiz.shadow.foreach {
                                    shadow ⇒
                                        shadow.color = value.color
                                        shadow.blur = value.blur
                                        shadow.offset = value.offset
                                }
                        }
                    }

                    thiz.Super("setValue", IscArray(value))
            }
    }.toThisFunc.opt

    getValue = {
        (thiz: classHandler) ⇒

            if (thiz.shadow.isDefined && thiz.shadow == null) thiz.shadow = jSUndefined

           val value = if (thiz.shadow.isEmpty || (thiz.shadow.get.blur.isEmpty && thiz.shadow.get.color.isEmpty && thiz.shadow.get.offset.isEmpty))
                jSUndefined
            else {
                val res = Shadow(
                    new ShadowProps {
                        color = thiz.shadow.get.color opt "#FFFFFF"
                        blur = thiz.shadow.get.blur opt 0
                        offset = thiz.shadow.get.offset opt Point(0, 0)
                    }
                )
                res
            }.asInstanceOf[JSAny].undef

            thiz.updateCancelButton(if (value.isEmpty) null else value)
            value
    }.toThisFunc.opt

    changed = {
        (form: DynamicFormSS, formItem: ShadowItem, value: JSUndefined[Shadow]) ⇒
            //isc debugTrap(form, value)
            form.setPropertyOnSelection("shadow", value)
    }.toFunc.opt

    var checkShadowUndefined: ScOption[ThisFunction0[classHandler, _]] = {
        (thiz: classHandler) ⇒
            if (thiz.shadow.isEmpty) thiz.shadow = js.Object().asInstanceOf[Shadow]
    }.toThisFunc.opt

    init = {
        (thisTop: classHandler, args: IscArray[JSAny]) ⇒
            //isc debugTrap (thisTop.drawItem)

            thisTop.innerForm = DynamicFormSS.create(
                new DynamicFormSSProps {
                    width = "100%"
                    isGroup = true.opt
                    groupTitle = "Тень".opt
                    fields = Seq(
                        ColorItem(
                            new ColorItemProps {
                                nameStrong = "color".nameStrongOpt
                                title = "Цвет".opt
                                defaultValue = if (thisTop.shadow.isEmpty) ScNone else thisTop.shadow.get.color.optAny
                                changed = {
                                    import com.simplesys.System.Types.CSSColor
                                    (form: DynamicFormSS, formItem: FormItem, value: JSUndefined[CSSColor]) ⇒
                                        thisTop.checkShadowUndefined()
                                        thisTop.shadow.foreach(_.color = value)
                                        thisTop.form.foreach(form ⇒ thisTop.changed.foreach(_ (form, thisTop, thisTop.getValue())))
                                }.toFunc.opt
                            }
                        ),
                        SpinnerItem(
                            new SpinnerItemProps {
                                nameStrong = "blur".nameStrongOpt
                                title = "Размытие".opt
                                defaultValue = if (thisTop.shadow.isEmpty) ScNone else thisTop.shadow.get.blur.optAny
                                min = 0.0.opt
                                max = 20.0.opt
                                step = 1.0.opt
                                changed = {
                                    (form: DynamicFormSS, formItem: FormItem, value: JSUndefined[Int]) ⇒
                                        thisTop.checkShadowUndefined()
                                        thisTop.shadow.foreach(_.blur = value)
                                        thisTop.form.foreach(form ⇒ thisTop.changed.foreach(_ (form, thisTop, thisTop.getValue())))

                                }.toFunc.opt
                            }
                        ),
                        PointItem(
                            new PointItemProps {
                                point = if (thisTop.shadow.isEmpty) ScNone else thisTop.shadow.get.offset.opt
                                maxX = 20.0.opt
                                maxY = 20.0.opt
                                titleGroup = "Относительно нулевой точки".opt
                                nameStrong = "offset".nameStrongOpt
                                title = "Смещение".opt
                                changed = {
                                    (form: DynamicFormSS, formItem: FormItem, value: JSUndefined[Point]) ⇒
                                        //isc debugTrap value
                                        thisTop.checkShadowUndefined()
                                        thisTop.shadow.foreach(_.offset = value)
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
                          identifier = thisTop.cancelButtonName.opt
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

    `type` = FormItemComponentType.ShadowItem
}
