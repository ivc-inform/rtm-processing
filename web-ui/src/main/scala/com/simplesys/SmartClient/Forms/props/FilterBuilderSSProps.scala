package com.simplesys.SmartClient.Forms.props

import com.simplesys.SmartClient.Forms.FilterBuilderSS
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption
import com.simplesys.option.ScOption._

import scala.scalajs.js._

class FilterBuilderSSProps extends FilterBuilderProps {
    type classHandler <: FilterBuilderSS

    valueItemWidth = 250
    var getValidatedValues: ScOption[ThisFunction0[classHandler, JSUndefined[JSObject]]] = {
        (thiz: classHandler) ⇒
            if (thiz.clause.isEmpty)
                jSUndefined
            else
                thiz.clause.get.getValidatedValues().undef
    }.toThisFunc.opt
}
