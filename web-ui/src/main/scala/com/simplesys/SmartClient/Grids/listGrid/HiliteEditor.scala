package com.simplesys.SmartClient.Grids.listGrid

import com.simplesys.SmartClient.Foundation.StatefulCanvas
import com.simplesys.SmartClient.Grids.Grid
import com.simplesys.SmartClient.Layout.VLayout
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.{AutoChild, Callback, SCImgURL, void}

import scala.scalajs.js

@js.native
trait HiliteEditor extends VLayout {
    val addAdvancedRuleButton: StatefulCanvas with AutoChild
    val addAdvancedRuleButtonTitle: String
    val availableFieldsColumnTitle: String
    val callback: Callback
    val cancelButton: StatefulCanvas with AutoChild
    val cancelButtonTitle: String
    def clearHilites(): void
    val fieldList: Grid[ListGridField, ListGridRecord]
    val hiliteIcons: IscArray[SCImgURL]
    val hiliteRule: HiliteRule with AutoChild
    def removeRule(hiliteRule: HiliteRule): void
    val saveButton: StatefulCanvas with AutoChild
    val saveButtonTitle: String
    def saveHilites(callback: Callback): void
    def setHilites(hilites: IscArray[Hilite]): void
}


