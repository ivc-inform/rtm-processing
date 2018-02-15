package com.simplesys.SmartClient.Layout.props

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Layout.WindowSS
import com.simplesys.SmartClient.System.{IscArray, isc}
import com.simplesys.System.JSUndefined
import com.simplesys.System.Types.SCImgURL
import com.simplesys.function._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js.ThisFunction1

class WindowSSProps extends WindowProps {
    type classHandler <: WindowSS

    var headerIconPath: ScOption[SCImgURL] = ScNone
    var autoPosition: ScOption[Boolean] = ScNone
    var onDestroy: ScOption[ThisFunction1[classHandler, JSUndefined[Canvas], _]] = ScNone
    var addItem: ScOption[ThisFunction1[classHandler, Canvas, IscArray[Canvas]]] = {
        (thisTop: classHandler, item: Canvas) ⇒

            if (thisTop.items.isEmpty)
                thisTop.Super("addItem", IscArray(item)).asInstanceOf[IscArray[Canvas]]
            else
                thisTop.items.get.find(_.identifier.getOrElse("") == item.identifier.getOrElse("_")) match {
                    case None ⇒
                        //isc debugTrap(item, 0)
                        thisTop.Super("addItem", IscArray(item)).asInstanceOf[IscArray[Canvas]]
                    case Some(item) ⇒
                        //isc debugTrap(item, 1)
                        thisTop.logWarn(s"Item: ${item.getClassName()}: Identifier: ${item.getIdentifier()} exists.")
                        thisTop.items.get

                }
    }.toThisFunc.opt
    var autoDestroy: ScOption[Boolean] = ScNone
}
