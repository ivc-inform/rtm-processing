package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.SmartClient.Drawing.drawItem.DrawLabel
import com.simplesys.SmartClient.Forms.DynamicFormSS
import com.simplesys.SmartClient.Forms.formsItems.{FontItem, FormItem}
import com.simplesys.SmartClient.Forms.props.DynamicFormSSProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.{CSSColor, FormItemComponentType}
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

class FontItemProps extends FormItemWithButtonsProps {

    type classHandler <: FontItem

    nameStrong = "titleLabelProperties".nameStrongOpt
    title = "Шрифт".opt
    typeEditorField = FormItemComponentType.TextAreaItem
    showTitle = false.opt
    colSpan = 2.opt

    clearValue = {
        (thiz: classHandler) ⇒
            thiz.Super("clearValue")

            thiz.innerForm.foreach {
                innerForm ⇒
                    innerForm.setValue("fontFamily", null)
                    innerForm.setValue("fontSize", null)
                    innerForm.setValue("fontStyle", null)
                    innerForm.setValue("fontWeight", null)
                    innerForm.setValue("lineColor", null)
                    innerForm.setValue("rotation", null)

                    thiz.fontFamily = jSUndefined
                    thiz.fontSize = jSUndefined
                    thiz.fontStyle = jSUndefined
                    thiz.fontWeight = jSUndefined
                    thiz.lineColor = jSUndefined
                    thiz.rotation = jSUndefined
            }
    }.toThisFunc.opt

    setValue = {
        (thiz: classHandler, value: JSUndefined[DrawLabel]) ⇒
            //isc debugTrap value
            value.foreach {
                value ⇒
                    if (value == null)
                        thiz.clearValue()
                    else {
                        thiz.innerForm.foreach {
                            innerForm ⇒
                                innerForm.setValue("fontFamily", value.fontFamily)
                                innerForm.setValue("fontSize", value.fontSize)
                                innerForm.setValue("fontStyle", value.fontStyle)
                                innerForm.setValue("fontWeight", value.fontWeight)
                                innerForm.setValue("lineColor", value.lineColor)
                                innerForm.setValue("rotation", value.rotation)

                                thiz.fontFamily = value.fontFamily
                                thiz.fontSize = value.fontSize
                                thiz.fontStyle = value.fontStyle
                                thiz.fontWeight = value.fontWeight
                                thiz.lineColor = value.lineColor
                                thiz.rotation = value.rotation
                        }
                    }
            }
            thiz.Super("setValue", IscArray(value))

    }.toThisFunc.opt

    getValue = {
        (thiz: classHandler) ⇒

            import com.simplesys.SmartClient.Drawing.drawItem.props.DrawLabelProps
            if (thiz.fontFamily.isEmpty && thiz.fontSize.isEmpty && thiz.fontStyle.isEmpty && thiz.fontWeight.isEmpty && thiz.lineColor.isEmpty && thiz.rotation.isEmpty)
                jSUndefined
            else
                DrawLabel(
                    new DrawLabelProps {
                        fontStyle = thiz.fontStyle.opt
                        fontWeight = thiz.fontWeight.opt
                        fontSize = thiz.fontSize.opt
                        rotation = thiz.rotation.opt
                        lineColor = thiz.lineColor.opt
                        fontFamily = thiz.fontFamily.opt
                    }
                ).asInstanceOf[JSAny].undef
    }.toThisFunc.opt

    var fontFamily: ScOption[String] = ScNone
    var fontSize: ScOption[Int] = ScNone
    var fontStyle: ScOption[String] = ScNone
    var fontWeight: ScOption[String] = ScNone
    var lineColor: ScOption[CSSColor] = ScNone
    var rotation: ScOption[Double] = ScNone

    init = {
        (thisTop: classHandler, args: IscArray[JSAny]) ⇒

            thisTop.innerForm = DynamicFormSS.create(
                new DynamicFormSSProps {
                    width = "100%"
                    isGroup = true.opt
                    groupTitle = "Шрифт".opt
                    fields = Seq(
                        SelectItem(
                            new SelectItemProps {
                                nameStrong = "fontFamily".nameStrongOpt
                                title = "Font Family".opt
                                defaultValue = thisTop.fontFamily.optAny
                                valueMap = IscArray(
                                    "Arial",
                                    "Arial Black",
                                    "Comic Sans MS",
                                    "Courier New",
                                    "Helvetica",
                                    "Garamond",
                                    "Georgia",
                                    "Impact",
                                    "Lucida Console",
                                    "Lucida Sans Unicode",
                                    "Microsoft Sans Serif",
                                    "Tahoma",
                                    "Times",
                                    "Times New Roman",
                                    "Trebuchet MS",
                                    "Verdana",
                                    "Webdings").opt
                                changed = {
                                    (form: DynamicFormSS, formItem: FormItem, value: JSUndefined[String]) ⇒
                                        thisTop.fontFamily = value
                                        thisTop.form.foreach(form ⇒ thisTop.changed.foreach(_ (form, thisTop, thisTop.getValue())))

                                }.toFunc.opt
                            }
                        ),
                        SpinnerItem(
                            new SpinnerItemProps {
                                nameStrong = "fontSize".nameStrongOpt
                                title = "Font Size".opt
                                defaultValue = thisTop.fontSize.optAny
                                min = 10.0.opt
                                max = 38.0.opt
                                step = 1.0.opt
                                changed = {
                                    (form: DynamicFormSS, formItem: FormItem, value: JSUndefined[Int]) ⇒
                                        thisTop.fontSize = value
                                        thisTop.form.foreach(form ⇒ thisTop.changed.foreach(_ (form, thisTop, thisTop.getValue())))
                                }.toFunc.opt
                            }
                        ),
                        SelectItem(
                            new SelectItemProps {
                                nameStrong = "fontStyle".nameStrongOpt
                                title = "Font Style".opt
                                defaultValue = thisTop.fontStyle.optAny
                                valueMap = IscArray("normal", "italic", "oblique").opt
                                changed = {
                                    (form: DynamicFormSS, formItem: FormItem, value: JSUndefined[String]) ⇒
                                        thisTop.fontStyle = value
                                        thisTop.form.foreach(form ⇒ thisTop.changed.foreach(_ (form, thisTop, thisTop.getValue())))
                                }.toFunc.opt
                            }
                        ),
                        SelectItem(
                            new SelectItemProps {
                                nameStrong = "fontWeight".nameStrongOpt
                                title = "Font Weight".opt
                                defaultValue = thisTop.fontWeight.optAny
                                valueMap = IscArray("bold", "bolder", "lighter", "normal", "100", "200", "300", "400", "500", "600", "700", "800", "900").opt
                                changed = {
                                    (form: DynamicFormSS, formItem: FormItem, value: JSUndefined[String]) ⇒
                                        thisTop.fontWeight = value
                                        thisTop.form.foreach(form ⇒ thisTop.changed.foreach(_ (form, thisTop, thisTop.getValue())))
                                }.toFunc.opt
                            }
                        ),
                        ColorItem(
                            new ColorItemProps {
                                nameStrong = "lineColor".nameStrongOpt
                                title = "Font Color".opt
                                defaultValue = thisTop.lineColor.optAny
                                changed = {
                                    (form: DynamicFormSS, formItem: FormItem, value: JSUndefined[CSSColor]) ⇒
                                        thisTop.lineColor = value
                                        thisTop.form.foreach(form ⇒ thisTop.changed.foreach(_ (form, thisTop, thisTop.getValue())))
                                }.toFunc.opt
                            }
                        ),
                        SpinnerItem(
                            new SpinnerItemProps {
                                nameStrong = "rotation".nameStrongOpt
                                title = "Font Rotation".opt
                                defaultValue = thisTop.rotation.optAny
                                min = 0.0.opt
                                max = 360.0.opt
                                step = 1.0.opt
                                changed = {
                                    (form: DynamicFormSS, formItem: FormItem, value: JSUndefined[Double]) ⇒
                                        thisTop.rotation = value
                                        thisTop.form.foreach(form ⇒ thisTop.changed.foreach(_ (form, thisTop, thisTop.getValue())))
                                }.toFunc.opt
                            }
                        )
                    ).opt
                }
            )

            thisTop.Super("init", args)
    }.toThisFunc.opt

    `type` = FormItemComponentType.FontItem
}
