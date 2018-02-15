package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.SmartClient.Forms.DynamicForm
import com.simplesys.SmartClient.Forms.formsItems.{ComboBoxItem, TextItem}
import com.simplesys.SmartClient.Grids.Grid
import com.simplesys.SmartClient.Grids.listGrid.{ListGridField, ListGridRecord}
import com.simplesys.SmartClient.Layout.NavigationBar
import com.simplesys.SmartClient.Layout.navigationBar.NavigationButton
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.PanelPlacement.PanelPlacement
import com.simplesys.System.Types.{CSSStyleName, FormItemComponentType, HTMLString}
import com.simplesys.option.{ScNone, ScOption}

class ComboBoxItemProps extends TextItemProps with PickListProps{
    type classHandler <: ComboBoxItem

    var addUnknownValues: ScOption[Boolean] = ScNone
    var allowEmptyValue: ScOption[Boolean] = ScNone
    var autoFetchData: ScOption[Boolean] = ScNone
    var completeOnTab: ScOption[Boolean] = ScNone
    var defaultToFirstOption: ScOption[Boolean] = ScNone
    var filterFields: ScOption[IscArray[String]] = ScNone
    var filterWithValue: ScOption[Boolean] = ScNone
    var generateExactMatchCriteria: ScOption[Boolean] = ScNone
    var minimumSearchLength: ScOption[Int] = ScNone
    var pendingTextBoxStyle: ScOption[CSSStyleName] = ScNone
    var pickerExitButton: ScOption[NavigationButton ] = ScNone
    var pickerExitButtonTitle: ScOption[HTMLString] = ScNone
    var pickerNavigationBar: ScOption[NavigationBar ] = ScNone
    var pickerSaveButton: ScOption[NavigationButton ] = ScNone
    var pickerSaveButtonTitle: ScOption[HTMLString] = ScNone
    var pickerSearchField: ScOption[TextItem ] = ScNone
    var pickerSearchFieldHint: ScOption[HTMLString] = ScNone
    var pickerSearchForm: ScOption[DynamicForm ] = ScNone
    var pickListPlacement: ScOption[PanelPlacement] = ScNone
    var progressiveLoading: ScOption[Boolean] = ScNone
    var searchStringTooShortMessage: ScOption[String] = ScNone
    var separateSpecialValues: ScOption[Boolean] = ScNone
    var separateValuesList: ScOption[Grid[ListGridField, ListGridRecord]] = ScNone
    var showPickListOnKeypress: ScOption[Boolean] = ScNone
    `type` = FormItemComponentType.ComboBoxItem
}
