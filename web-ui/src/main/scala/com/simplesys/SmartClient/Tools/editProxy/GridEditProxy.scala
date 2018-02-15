package com.simplesys.SmartClient.Tools.editProxy

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait GridEditProxy extends LayoutEditProxy {
    val canAddFormulaFields: Boolean
    val canAddSummaryFields: Boolean
    val canEditHilites: Boolean
    val canGroupBy: Boolean
    val canReorderFields: Boolean
    val canResizeFields: Boolean
    val generateEditableFormulas: Boolean
    val generateEditableHilites: Boolean
    val generateEditableSummaries: Boolean
    val saveFieldFrozenState: Boolean
    val saveFieldOrder: Boolean
    val saveFieldVisibility: Boolean
    val saveFilterCriteria: Boolean
    val saveGroupBy: Boolean
    val saveSort: Boolean
}

