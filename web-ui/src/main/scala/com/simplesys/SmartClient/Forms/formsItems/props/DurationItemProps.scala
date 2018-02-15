package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.SmartClient.Forms.DynamicFormSS
import com.simplesys.SmartClient.Forms.formsItems.{DurationValue, FormItem}
import com.simplesys.SmartClient.Forms.props.DynamicFormSSProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.Alignment.{apply ⇒ _}
import com.simplesys.System.Types._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScNone
import com.simplesys.option.ScOption._

class DurationItemProps extends CanvasItemProps {

    import com.simplesys.SmartClient.Forms.formsItems.DurationItem
    import com.simplesys.option.ScOption

    type classHandler <: DurationItem

    var days: ScOption[Int] = ScNone
    var hours: ScOption[Int] = ScNone
    var mins: ScOption[Int] = ScNone
    var secs: ScOption[Int] = ScNone

    var groupTitle: ScOption[HTMLString] = ScNone

    clearValue = {
        (thiz: classHandler) ⇒
            thiz.Super("clearValue")
            thiz.innerForm.foreach {
                innerForm ⇒
                    innerForm.setValue("days", null)
                    innerForm.setValue("hours", null)
                    innerForm.setValue("mins", null)
                    innerForm.setValue("secs", null)

                    thiz.days = jSUndefined
                    thiz.hours = jSUndefined
                    thiz.mins = jSUndefined
                    thiz.secs = jSUndefined
            }
    }.toThisFunc.opt

    setValue = {
        (thiz: classHandler, value: JSUndefined[DurationItem]) ⇒
            value.foreach {
                value ⇒
                    //isc debugTrap value
                    if (value == null)
                        thiz.clearValue()
                    else {
                        thiz.innerForm.foreach {
                            innerDynamicForm ⇒
                                innerDynamicForm.setValue("days", value.days)
                                innerDynamicForm.setValue("hours", value.hours)
                                innerDynamicForm.setValue("mins", value.mins)
                                innerDynamicForm.setValue("secs", value.secs)

                                thiz.days = value.days
                                thiz.hours = value.hours
                                thiz.mins = value.mins
                                thiz.secs = value.secs
                        }
                    }
            }
            thiz.Super("setValue", IscArray(value))
    }.toThisFunc.opt

    getValue = {
        (thiz: classHandler) ⇒
            if (thiz.hours.isEmpty && thiz.mins.isEmpty && thiz.days.isEmpty && thiz.secs.isEmpty)
                jSUndefined
            else
                new DurationValue {
                    override val hours: JSUndefined[Int] = thiz.hours
                    override val mins: JSUndefined[Int] = thiz.mins
                    override val days: JSUndefined[Int] = thiz.days
                    override val secs: JSUndefined[Int] = thiz.secs
                }.asInstanceOf[JSAny].undef
    }.toThisFunc.opt

    createCanvas = {
        (thisTop: classHandler, form: DynamicFormSS, item: DurationItem) =>

            thisTop.innerForm =
              DynamicFormSS.create(
                  new DynamicFormSSProps {
                      colWidths = Seq[JSAny]("25%", "25%", "25%", "25%").opt
                      isGroup = true.opt
                      groupTitle = item.groupTitle.opt
                      width = "*"
                      numCols = 4.opt
                      items = Seq(
                          SpinnerItem(
                              new SpinnerItemProps {
                                  nameStrong = "days".nameStrongOpt
                                  title = "Дней".opt
                                  titleOrientation = TitleOrientation.top.opt
                                  titleAlign = Alignment.center.opt
                                  defaultValue = item.days.optAny
                                  min = 0.0.opt
                                  step = 1.0.opt
                                  changed = {
                                      (form: DynamicFormSS, formItem: FormItem, value: JSUndefined[Int]) ⇒
                                          thisTop.days = value
                                          thisTop.changed.foreach(_ (form, formItem, thisTop.getValue()))
                                  }.toFunc.opt
                              }
                          ),
                          SpinnerItem(
                              new SpinnerItemProps {
                                  nameStrong = "hours".nameStrongOpt
                                  title = "Часов".opt
                                  titleOrientation = TitleOrientation.top.opt
                                  titleAlign = Alignment.center.opt
                                  defaultValue = item.hours.optAny
                                  min = 0.0.opt
                                  max = 23.0.opt
                                  step = 1.0.opt
                                  changed = {
                                      (form: DynamicFormSS, formItem: FormItem, value: JSUndefined[Int]) ⇒
                                          thisTop.hours = value
                                          thisTop.changed.foreach(_ (form, formItem, thisTop.getValue()))

                                  }.toFunc.opt
                              }
                          ),
                          SpinnerItem(
                              new SpinnerItemProps {
                                  nameStrong = "mins".nameStrongOpt
                                  title = "Минут".opt
                                  titleOrientation = TitleOrientation.top.opt
                                  titleAlign = Alignment.center.opt
                                  defaultValue = item.hours.optAny
                                  min = 0.0.opt
                                  max = 59.0.opt
                                  step = 1.0.opt
                                  changed = {
                                      (form: DynamicFormSS, formItem: FormItem, value: JSUndefined[Int]) ⇒
                                          thisTop.mins = value
                                          thisTop.changed.foreach(_ (form, formItem, thisTop.getValue()))
                                  }.toFunc.opt
                              }
                          ),
                          SpinnerItem(
                              new SpinnerItemProps {
                                  nameStrong = "secs".nameStrongOpt
                                  title = "Секунд".opt
                                  titleOrientation = TitleOrientation.top.opt
                                  titleAlign = Alignment.center.opt
                                  defaultValue = item.hours.optAny
                                  min = 0.0.opt
                                  max = 59.0.opt
                                  step = 1.0.opt
                                  changed = {
                                      (form: DynamicFormSS, formItem: FormItem, value: JSUndefined[Int]) ⇒
                                          thisTop.secs = value
                                          thisTop.changed.foreach(_ (form, formItem, thisTop.getValue()))
                                  }.toFunc.opt
                              }
                          )
                      ).opt
                  }
              )

            //isc debugTrap thisTop.innerComponent
            thisTop.innerForm.get
    }.toThisFunc.opt

}
