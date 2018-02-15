package com.simplesys.SmartClient.Charts

import com.simplesys.SmartClient.Analytics.Facet
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.JSObject
import com.simplesys.System.Types.CellRecord
import com.simplesys.System.Types.ChartType.ChartType

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait Chart extends JSObject {
    var chartType: ChartType
    val data: CellRecord | IscArray[CellRecord]
    val facets: Facet | IscArray[Facet]
    def getFacet(facetId: String): Facet
}

@js.native
abstract trait AbstractChartCompanion extends JSObject {
    val allChartTypes: IscArray[ChartType] = js.native
}

@js.native
object Chart extends AbstractChartCompanion
