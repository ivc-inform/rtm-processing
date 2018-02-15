package com.simplesys.SmartClient.DataBinding

import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.CriteriaPolicy.CriteriaPolicy
import com.simplesys.System.Types.FetchMode.FetchMode
import com.simplesys.System.Types.TextMatchStyle.TextMatchStyle
import com.simplesys.System.Types.{Criteria, Record, void}
import com.simplesys.System.{JSAny, JSObject}

import scala.scalajs.js

@js.native
trait ResultSet extends Class {
    def allMatchingRowsCached(): Boolean
    val allRows: IscArray[Record]
    def allRowsCached(): Boolean
    val alwaysRequestVisibleRows: Boolean
    def applyFilter(data: IscArray[Record], criteria: Criteria, requestProperties: DSRequest = js.native): IscArray[Record]
    def compareCriteria(newCriteria: Criteria, oldCriteria: Criteria, requestProperties: DSRequest = js.native, policy: String = js.native): Int
    val criteria: Criteria
    val criteriaPolicy: CriteriaPolicy
    var dataArrived: js.Function2[Int, Int, _]
    val dataSource: DataSource
    val disableCacheSync: Boolean
    val dropCacheOnUpdate: Boolean
    val fetchDelay: Int
    val fetchMode: FetchMode
    val fetchOperation: String
    def filterLocalData(): void
    def findByKey(keyValue: JSObject): Record
    def getAllCachedRows(): IscArray[Record]
    def getAllVisibleRows(): IscArray[Record]
    def getCriteria(): Criteria
    def getSort(): IscArray[SortSpecifier]
    val initialData: IscArray[Record]
    val initialLength: Int
    def invalidateCache(): void
    def lengthIsKnown(): Boolean
    val neverDropUpdatedRows: Boolean
    val progressiveLoading: Boolean
    def rangeIsLoaded(startRow: Int, endRow: Int): Boolean
    def resort(): void
    val resultSize: Int
    def rowIsLoaded(rowNum: Int): Boolean
    def setCriteria(newCriteria: Criteria): Boolean
    def setSort(): void
    val sortSpecifiers: IscArray[SortSpecifier]
    var transformData: js.Function2[JSAny, DSResponse, IscArray[JSObject]]
    val updateCacheFromRequest: Boolean
    val updatePartialCache: Boolean
    val useClientFiltering: Boolean
    val useClientSorting: Boolean
    def usingFilteredData(): Boolean
    def willFetchData(newCriteria: Criteria, textMatchStyle: TextMatchStyle = js.native): Boolean
}

@js.native
abstract trait AbstractResultSetCompanion extends AbstractClassCompanion {
}

@js.native
object ResultSet extends AbstractResultSetCompanion

