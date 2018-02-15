package com.simplesys.SmartClient.Forms.formsItems

import com.simplesys.SmartClient.DataBinding.{AdvancedCriteria, Criterion}
import com.simplesys.System.JSObject
import com.simplesys.System.Types.{Callback, void}

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait PresetCriteriaItem extends SelectItem {
    val customOptionTitle: String
    def getCriterion(): Criterion | AdvancedCriteria
    def getCustomCriteria(callback: Callback): void
    val showCustomOption: Boolean
}


