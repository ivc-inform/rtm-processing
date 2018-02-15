package com.simplesys.SmartClient.Tools.editProxy.props

import com.simplesys.SmartClient.Drawing.DrawItem
import com.simplesys.SmartClient.System._
import com.simplesys.SmartClient.Tools.editProxy.DrawItemEditProxy
import com.simplesys.SmartClient.Tools.props.EditProxyProps
import com.simplesys.function._
import com.simplesys.option.ScOption._

class DrawItemEditProxyProps extends EditProxyProps {
    type classHandler <: DrawItemEditProxy

    click = {
        (thisTop: classHandler) ⇒
            //isc debugTrap isc.EventHandler.ctrlKeyDown()

            val liveObject = thisTop.creator.asInstanceOf[DrawItem]

            if (liveObject.drawPane.isDefined && liveObject.drawPane.get.editProxy.canSelectChildren.getOrElse(false) && thisTop.canSelect != false)
                if (isc.EventHandler.ctrlKeyDown())
                    liveObject.editContext.foreach(_.selectComponent(liveObject))
                else
                    liveObject.editContext.foreach(_.selectSingleComponent(liveObject))

            false
    }.toThisFunc.opt
}
