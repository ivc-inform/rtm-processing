package com.simplesys.SmartClient.Forms.formsItems

import com.simplesys.SmartClient.DataBinding.Criterion
import com.simplesys.SmartClient.Forms.DynamicForm
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.Grid
import com.simplesys.SmartClient.Grids.listGrid.{ListGridField, ListGridRecord}
import com.simplesys.SmartClient.Layout.NavigationBar
import com.simplesys.SmartClient.Layout.navigationBar.NavigationButton
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.PanelPlacement.PanelPlacement
import com.simplesys.System.Types.{AutoChild, CSSStyleName, HTMLString, void}

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait ComboBoxItem extends TextItem with PickList {
    var addUnknownValues: Boolean
    var allowEmptyValue: Boolean
    var autoFetchData: Boolean
    def canEditCriterion(): void
    var completeOnTab: Boolean
    var defaultToFirstOption: Boolean
    val filterFields: IscArray[String]
    var filterWithValue: Boolean
    var generateExactMatchCriteria: Boolean
    def getCriterion(): Criterion
    var minimumSearchLength: Int
    var pendingTextBoxStyle: CSSStyleName
    var pickerExitButton: NavigationButton with AutoChild
    val pickerExitButtonTitle: HTMLString
    var pickerNavigationBar: NavigationBar with AutoChild
    var pickerSaveButton: NavigationButton with AutoChild
    var pickerSaveButtonTitle: HTMLString
    var pickerSearchField: TextItem with AutoChild
    var pickerSearchFieldHint: HTMLString
    var pickerSearchForm: DynamicForm with AutoChild
    var pickListPlacement: PanelPlacement | Canvas | String
    var progressiveLoading: Boolean
    var searchStringTooShortMessage: String
    var separateSpecialValues: Boolean
    var separateValuesList: Grid[ListGridField, ListGridRecord]
    def setCriterion(): void
    def shouldGenerateExactMatchCriteria(): Boolean
    var showPickListOnKeypress: Boolean
}




