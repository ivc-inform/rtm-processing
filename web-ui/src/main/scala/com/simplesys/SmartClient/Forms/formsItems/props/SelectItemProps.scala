package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.SmartClient.Forms.formsItems.SelectItem
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.Grid
import com.simplesys.SmartClient.Grids.listGrid.{ListGridField, ListGridRecord}
import com.simplesys.SmartClient.Layout.NavigationBar
import com.simplesys.SmartClient.Layout.navigationBar.NavigationButton
import com.simplesys.System.Types.MultipleAppearance.MultipleAppearance
import com.simplesys.System.Types.{FormItemComponentType, HTMLString, SCClassName}
import com.simplesys.option.{ScNone, ScOption}

class SelectItemProps extends FormItemProps with PickListProps{
    type classHandler <: SelectItem

    var addUnknownValues: ScOption[Boolean] = ScNone
    var allowEmptyValue: ScOption[Boolean] = ScNone
    var autoFetchData: ScOption[Boolean] = ScNone
    var defaultToFirstOption: ScOption[Boolean] = ScNone
    var editProxyConstructor: ScOption[SCClassName] = ScNone
    var escapeHTML: ScOption[Boolean] = ScNone
    var hiliteColor: ScOption[String] = ScNone
    var multiple: ScOption[Boolean] = ScNone
    var multipleAppearance: ScOption[MultipleAppearance] = ScNone
    var openOnDownArrow: ScOption[Boolean] = ScNone
    var openOnSpace: ScOption[Boolean] = ScNone
    var pickerExitButton: ScOption[NavigationButton ] = ScNone
    var pickerExitButtonTitle: ScOption[HTMLString] = ScNone
    var pickerNavigationBar: ScOption[NavigationBar ] = ScNone
    var pickListPlacement: ScOption[Canvas] = ScNone
    var progressiveLoading: ScOption[Boolean] = ScNone
    var separateSpecialValues: ScOption[Boolean] = ScNone
    var separateValuesList: ScOption[Grid[ListGridField, ListGridRecord]] = ScNone
    var showHintInField: ScOption[Boolean] = ScNone
    var showOver: ScOption[Boolean] = ScNone
    `type` = FormItemComponentType.SelectItem
}
