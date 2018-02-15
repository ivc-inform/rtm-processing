package com.simplesys.SmartClient.Layout.props

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Layout.{OkCancelPanel, WindowSSDialog}
import com.simplesys.SmartClient.System.{OkCancelPanel, isc}
import com.simplesys.System.Types.HTMLString
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js.ThisFunction0

class WindowSSDialogProps extends WindowSSProps {

    type classHandler <: WindowSSDialog

    height = 400
    width = 300
    canDragResize = true.opt
    canDragReposition = true.opt
    keepInParentRect = true.opt
    autoCenter = false.opt
    autoSize = false.opt
    showMinimizeButton = false.opt
    showMaximizeButton = false.opt

    var wrapCanvas: ScOption[Canvas] = ScNone
    var okCaption: ScOption[HTMLString] = "Сохранить".opt
    var okFunction: ScOption[ThisFunction0[classHandler, _]] = {
        (thiz: classHandler) ⇒
              isc info "Нет реализации."
    }.toThisFunc.opt
    var cancelFunction: ScOption[ThisFunction0[classHandler, _]] = {
        (thiz: classHandler) ⇒
            thiz.markForDestroy()
    }.toThisFunc.opt
    var cancelCaption: ScOption[HTMLString] = ScNone
    var okCancelPanel: ScOption[OkCancelPanel] = ScNone

    initWidget = {
        import com.simplesys.SmartClient.System.IscArray
        import com.simplesys.System.JSAny
        (thisTop: classHandler, args: IscArray[JSAny]) ⇒

            thisTop.Super("initWidget", args)
            thisTop.wrapCanvas.foreach(thisTop addItem _)

            //isc debugTrap thisTop.okCancelPanel

            thisTop.okCancelPanel = OkCancelPanel.create(
                new OkCancelPanelProps {
                    identifier = "56EA8234-06D5-27B6-034B-4070B9FB0F59".opt
                    owner = thisTop.opt
                    //okCaption = thisTop.okCaption.opt
                    okCaption = if (thisTop.okCaption.isEmpty) ScNone else thisTop.okCaption.get.opt
                    cancelCaption = if (thisTop.cancelCaption.isEmpty) ScNone else thisTop.cancelCaption.get.opt
                    ownerDestroy = false.opt
                    ownerHide = false.opt
                    cancelFunction = {
                        (thiz: classHandler) ⇒
                            thisTop.cancelFunction()
                            /*thiz.owner.foreach(_.markForDestroy())*/
                    }.toThisFunc.opt

                    okFunction = {
                        (thiz: classHandler) ⇒
                            thisTop.okFunction()
                            /*if (thiz.ownerDestroy)
                                thiz.owner.foreach(_.markForDestroy())
                            else if (thiz.ownerHide)
                                thiz.owner.foreach(_.hide())*/
                    }.toThisFunc.opt
                })

            thisTop addItem thisTop.okCancelPanel.get

    }.toThisFunc.opt
}
