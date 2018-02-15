package com.simplesys.SmartClient.DataBinding

import com.simplesys.SmartClient.RPC.{AbstractRPCResponseCompanion, RPCResponse}
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.DSOperationType.DSOperationType
import com.simplesys.System.Types.{Record, URL}
import com.simplesys.System.{JSObject, JSUndefined}

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
trait Error extends JSObject {
    val message: JSUndefined[String]
    val stackTrace: JSUndefined[String]
}

@js.native
trait DSResponse extends RPCResponse {
    val dataSource: String
    val endRow: Int
    val errors: JSObject
    val data: JSUndefined[IscArray[Record]]
    val fromOfflineCache: Boolean
    val offlineTimestamp: Int
    val operationType: DSOperationType
    val queueStatus: Int
    val startRow: Int
    val totalRows: Int
    val urlExportFile: JSUndefined[URL]
}

@js.native
abstract trait AbstractDSResponseCompanion extends AbstractRPCResponseCompanion {
}

@js.native
object DSResponse extends AbstractDSResponseCompanion

