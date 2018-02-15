package com.simplesys.SmartClient.Layout.props

import com.simplesys.SmartClient.Layout.BreakType
import com.simplesys.SmartClient.Layout.BreakType._
import com.simplesys.SmartClient.System.isc
import com.simplesys.System.JSUndefined
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._

class WindowProgressDialogProps extends WindowSSProps {

    import com.simplesys.SmartClient.Layout.WindowProgressDialog
    import com.simplesys.option.{ScNone, ScOption}

    import scala.scalajs.js.{ThisFunction0, ThisFunction1}

    type classHandler <: WindowProgressDialog

    height = 70
    width = 800
    canDragResize = true.opt
    canDragReposition = true.opt
    keepInParentRect = true.opt
    autoCenter = false.opt
    autoSize = false.opt
    showMinimizeButton = false.opt
    showMaximizeButton = false.opt
    showCloseButton = false.opt
    isModal = false.opt

    var minValue: ScOption[Double] = 0.0.opt
    var maxValue: ScOption[Double] = 100.0.opt
    var oneStep: ScOption[Double] = ScNone
    var _breakType: ScOption[BreakType] = BreakType.notBreak.opt

    identifier = "3D4AD6BB-9715-38A9-7360-808559532CE8".opt

    var getBreak: ScOption[ThisFunction0[classHandler, JSUndefined[BreakType]]] = {
        (thiz: classHandler) ⇒
            thiz._breakType
    }.toThisFunc.opt

    var cancelFunction: ScOption[ThisFunction0[classHandler, _]] = ScNone
    var setPercentDone: ScOption[ThisFunction1[classHandler, Double, _]] = {
        (thiz: classHandler, newPercent: Double) ⇒
            thiz.progressBar setPercentDone newPercent
    }.toThisFunc.opt

    var nextStep: ScOption[ThisFunction0[classHandler, _]] = {
        (thiz: classHandler) ⇒
            isc.Timer.setTimeout({
                () ⇒
                    var percentDone: Double = thiz.progressBar.percentDone + thiz.oneStep

                    if (100.0 - percentDone < thiz.oneStep)
                        percentDone = 100.0

                    //isc debugTrap (percentDone)
                    //println(s"percentDone: $percentDone")

                    thiz.progressBar setPercentDone percentDone
                    thiz.progressBar setTitle s"Progress: ${thiz.progressBar.percentDone}%"
            }, 0)
    }.toThisFunc.opt

    initWidget = {
        import com.simplesys.SmartClient.System.IscArray
        import com.simplesys.System.JSAny
        (thizTop: classHandler, args: IscArray[JSAny]) ⇒

            import com.simplesys.SmartClient.Control.props.ProgressbarProps
            import com.simplesys.SmartClient.System.Progressbar

            thizTop.Super("initWidget", args)

            thizTop.progressBar = Progressbar.create(
                new ProgressbarProps {
                    breadth = 10.opt
                }
            )

            thizTop.oneStep = 1 / ((thizTop.maxValue - thizTop.minValue) / 100)

            /*println(s"thizTop.maxValue: ${thizTop.maxValue}")
            println(s"thizTop.minValue: ${thizTop.minValue}")
            println(s"thizTop.oneStep: ${thizTop.oneStep}")*/

            //isc debugTrap (thizTop.maxValue, thizTop.minValue, thizTop.oneStep)

            thizTop addItem thizTop.progressBar

            import com.simplesys.SmartClient.System._

            val okPanel = OkCancelPanel.create(
                new OkCancelPanelProps {
                    owner = thizTop.opt
                    okCaption = "Отменить c сохранением".ellipsis.opt
                    cancelCaption = "Отменить без сохранения".ellipsis.opt
                    ownerDestroy = false.opt
                    ownerHide = false.opt
                    okFunction = {
                        (thiz: classHandler) ⇒

                    }.toThisFunc.opt
                    cancelFunction = {
                        (thiz: classHandler) ⇒
                            thizTop.markForDestroy()
                    }.toThisFunc.opt
                }
            )

            thizTop addItem okPanel


    }.toThisFunc.opt
}
