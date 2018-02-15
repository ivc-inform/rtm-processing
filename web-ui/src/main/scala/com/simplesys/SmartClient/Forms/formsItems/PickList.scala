package com.simplesys.SmartClient.Forms.formsItems

import com.simplesys.SmartClient.DataBinding.Callbacks.DSCallback
import com.simplesys.SmartClient.DataBinding._
import com.simplesys.SmartClient.Grids.Grid
import com.simplesys.SmartClient.Grids.listGrid.{ListGridField, ListGridRecord}
import com.simplesys.SmartClient.System.{AbstractClassCompanion, Class, IscArray}
import com.simplesys.System.Types.PickListItemIconPlacement.PickListItemIconPlacement
import com.simplesys.System.Types.TextMatchStyle.TextMatchStyle
import com.simplesys.System.Types._
import com.simplesys.System.{JSObject, JSUndefined}

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait PickList extends Class {
    var _lastFetchID: JSUndefined[ID]
    var _fetchingPickListData: Boolean
    var animatePickList: Boolean
    var animationTime: Int
    var cachePickListResults: Boolean
    var dataArrived: js.Function3[Int, Int, ResultSet, _]
    var emptyPickListHeight: Int
    var emptyPickListMessage: String
    def fetchData(callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    var fetchDelay: Int
    var fetchDisplayedFieldsOnly: Boolean
    def filterClientPickListData(): IscArray[ListGridRecord]
    var filterLocally: Boolean
    def getClientPickListData(): IscArray[ListGridRecord]
    def getDisplayFieldName(): String
    def getOptionDataSource(): DataSource
    def getPickListFilterCriteria(): Criteria
    def getValueFieldName(): String
    def filterComplete(response: DSResponse, data: IscArray[JSObject], request: DSRequest, fromSharedPickList: Boolean): void
    def _updatePickListForFilterComplete(response: JSUndefined[DSResponse], data: JSUndefined[IscArray[JSObject]], request: JSUndefined[DSRequest]): void
    def _updateValueForFilterComplete(response: JSUndefined[DSResponse], data: JSUndefined[IscArray[JSObject]], request: JSUndefined[DSRequest]): void
    var hideEmptyPickList: Boolean
    val iconPlacement: PickListItemIconPlacement
    val initialSort: IscArray[SortSpecifier]
    val optionDataSource: String | DataSource
    val optionFilterContext: DSRequest
    var pickListApplyRowNumberStyle: Boolean
    val pickListBaseStyle: CSSStyleName
    var pickListCellHeight: Int
    val pickListConstructor: SCClassName
    var pickListCriteria: Criteria
    val pickListFields: IscArray[ListGridField]
    var pickListHeaderHeight: Int
    var pickListHeight: Int
    var pickListMaxWidth: Int
    var pickListProperties: Grid[ListGridField, ListGridRecord]
    val pickListTallBaseStyle: CSSStyleName
    var pickListWidth: Int
    var separatorRows: IscArray[ListGridRecord]
    val pickList: JSUndefined[PickList]
    var _fetchingForItem: JSUndefined[String]
    val showAllOptions: Boolean
    var showOptionsFromDataSource: Boolean
    val sortField: String | IscArray[String] | Int
    val specialValues: ValueMap
    var textMatchStyle: TextMatchStyle
    val useClientFiltering: Boolean
    var valueIconField: String
    var _processingFilterComplete: Boolean
    def clearAriaState(state:String):void
}

@js.native
abstract trait AbstractPickListCompanion extends AbstractClassCompanion {

}


