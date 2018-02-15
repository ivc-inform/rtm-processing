
package com.simplesys.SmartClient.Layout.props

import com.simplesys.SmartClient.Layout.TabSetSS
import com.simplesys.SmartClient.Layout.tabSet.Tab
import com.simplesys.SmartClient.System.isc
import com.simplesys.System.{JSAny, JSUndefined, jSUndefined}
import com.simplesys.function._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js
import scala.scalajs.js.{ThisFunction0, ThisFunction1}
import scala.scalajs.js.UndefOr._

class TabSetSSProps extends TabSetProps {
    type classHandler <: TabSetSS

    var tabSet: ScOption[TabSetSS] = ScNone
    var tabsReordered: ScOption[js.ThisFunction0[classHandler, _]] = ScNone
    var afterRemoveTabs : ScOption[ThisFunction0[classHandler, _]] = ScNone
    var beforeRemoveTabs : ScOption[ThisFunction1[classHandler, Tab, _]] = ScNone
    var findTab: ScOption[ThisFunction1[classHandler, JSAny, JSUndefined[Tab]]] = {
        (thiz: classHandler, tab: JSAny) =>
            var res: JSUndefined[Tab] = jSUndefined

            var tabName = ""
            if (isc.isA.Object(tab)) {
                val _tab = tab.asInstanceOf[Tab]
                _tab.name.foreach(name => tabName = name)
            } else if (isc.isA.String(tab))
                tabName = tab.toString

            if (tabName != "")
                res = thiz.tabs.find(tab => tab.name == tabName) match {
                    case None => jSUndefined
                    case Some(x) => x
                }

            res
    }.toThisFunc.opt

    canCloseTabs = true.opt
    canReorderTabs = true.opt
}
