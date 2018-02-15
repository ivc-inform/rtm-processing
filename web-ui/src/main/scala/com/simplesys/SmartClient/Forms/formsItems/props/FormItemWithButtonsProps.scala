package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.SmartClient.Control.Button
import com.simplesys.SmartClient.Forms.DynamicFormSS
import com.simplesys.SmartClient.Forms.formsItems.{CanvasItem, FormItemWithButtons}
import com.simplesys.SmartClient.Layout.LayoutSS
import com.simplesys.SmartClient.Layout.props.{HLayoutSSProps, LayoutSSProps}
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.FormItemComponentType.FormItemComponentType
import com.simplesys.System.Types.FormItemType._
import com.simplesys.System.Types._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.option.{FormItemType_FormItemComponentType, ScNone, ScOption}

import scala.scalajs.js.{ThisFunction0, ThisFunction1}

class FormItemWithButtonsProps extends CanvasItemProps {
    type classHandler <: FormItemWithButtons

    width = "100%"
    readOnlyDisplay = ReadOnlyDisplayAppearance.readOnly.opt

    var buttons: ScOption[Seq[Button]] = ScNone
    var buttonsVertical: ScOption[Boolean] = true.opt
    var cancelButtonName: ScOption[String] = "CancelButton".opt

    var typeEditorField: ScOption[FormItemType_FormItemComponentType[FormItemType, FormItemComponentType]] = FormItemComponentType.TextItem

    var showCancelButton: ScOption[ThisFunction0[classHandler, _]] = {
        (thiz: classHandler) ⇒
            //isc debugTrap thiz.cancelButton
            thiz.cancelButton.foreach(_.foreach { button ⇒ button.show(); button.owner.foreach(owner ⇒ if (!thiz.isDisabled()) owner.show() else owner.hide()) })
    }.toThisFunc.opt

    var hideCancelButton: ScOption[ThisFunction0[classHandler, _]] = {
        (thiz: classHandler) ⇒
            //isc debugTrap thiz.cancelButton
            thiz.cancelButton.foreach(_.foreach { button ⇒ button.hide(); button.owner.foreach { owner ⇒ if (owner.asInstanceOf[LayoutSS].getMembers().length == 1) owner.hide() } })
    }.toThisFunc.opt

    var updateCancelButton: ScOption[ThisFunction1[classHandler, JSUndefined[JSAny], _]] = {
        (thiz: classHandler, value: JSUndefined[JSAny]) ⇒
            value.foreach {
                value ⇒
                    /*if (value != null)
                        isc debugTrap(value, thiz)*/

                    value match {
                        case null ⇒ thiz.hideCancelButton()
                        case _ ⇒ thiz.showCancelButton()
                    }
            }
    }.toThisFunc.opt

    setValue = {
        (thiz: classHandler, value: JSUndefined[JSAny]) ⇒
            value.foreach {
                /*if (value != null)
                    isc debugTrap (value, thiz)*/

                value ⇒
                    thiz.updateCancelButton(value)
                    thiz.Super("setValue", IscArray(value))
            }
    }.toThisFunc.opt

    createCanvas = {
        (thisTop: classHandler, form: DynamicFormSS, item: CanvasItem) =>
            //isc debugTrap thisTop
            if (thisTop.innerForm.isEmpty) {
                isc error s"Не задан параметр innerForm компонента: ${thisTop.getID()}"
                Canvas.create()

            } else {

                val res = HLayoutSS.create(
                    new HLayoutSSProps {
                        height = 20
                        members = Seq(thisTop.innerForm.get).opt
                    }
                )

                val buttonLayout = LayoutSS.create(
                    new LayoutSSProps {
                        width = if (thisTop.buttonsVertical) 20 else ScNone
                        height = if (!thisTop.buttonsVertical) 20 else ScNone
                        vertical = thisTop.buttonsVertical.opt
                    }
                )

                thisTop.buttons.foreach(_.foreach { button ⇒ buttonLayout addMember button; button.owner = buttonLayout })

                thisTop.cancelButton = thisTop.buttons.map(_.filter(_.identifier == thisTop.cancelButtonName))
                thisTop.cancelButton.foreach(_.foreach(button ⇒ button.observe(button, "click", () ⇒ {
                    button.hide();
                    if (buttonLayout.getMembers().length == 1) buttonLayout.hide()
                })))

                if (buttonLayout.getMembers().length > 0)
                    res addMember buttonLayout
                res
            }
    }.toThisFunc.opt


    `type` = FormItemComponentType.FormItemWithButtons
}
