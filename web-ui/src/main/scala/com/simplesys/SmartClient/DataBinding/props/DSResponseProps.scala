package com.simplesys.SmartClient.DataBinding.props

import com.simplesys.SmartClient.RPC.props.ResponseCommon
import com.simplesys.System.Types.DSOperationType._
import com.simplesys.System.{JSObject, JSDictionaryAny}
import com.simplesys.option.{ScNone, ScOption}


class DSResponseProps extends ResponseCommon[JSDictionaryAny] {
    var dataSource: ScOption[String] = ScNone
    var endRow: ScOption[Int] = ScNone
    var errors: ScOption[JSObject] = ScNone
    var fromOfflineCache: ScOption[Boolean] = ScNone
    var offlineTimestamp: ScOption[Int] = ScNone
    var operationType: ScOption[DSOperationType] = ScNone
    var queueStatus: ScOption[Int] = ScNone
    var startRow: ScOption[Int] = ScNone
    var totalRows: ScOption[Int] = ScNone
}
