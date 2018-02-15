package com.simplesys.SmartClient.DataBinding.props

import com.simplesys.SmartClient.DataBinding.RestDataSource
import com.simplesys.System.Types.void
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class RestDataSourceProps extends DataSourceProps {

    type classHandler <: RestDataSource

    var addDataURL: ScOption[String] = ScNone
    var disableQueuing: ScOption[Boolean] = ScNone
    var fetchDataURL: ScOption[String] = ScNone
    var jsonRecordXPath: ScOption[String] = ScNone
    var metaDataPrefix: ScOption[String] = ScNone
    var prettyPrintJSON: ScOption[Boolean] = ScNone
    var removeDataURL: ScOption[String] = ScNone
    var sendMetaData: ScOption[Boolean] = ScNone
    var transformRequest: ScOption[js.Function1[void, _]] = ScNone
    var updateDataURL: ScOption[String] = ScNone
    var xmlRecordXPath: ScOption[String] = ScNone

}
