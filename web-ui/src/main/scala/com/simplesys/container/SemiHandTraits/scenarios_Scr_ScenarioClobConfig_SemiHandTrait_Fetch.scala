// This file is generated automatically (at 16.10.2016 09:47:53), do not spend any changes here, because they will be lost. Generator: "GenBOContainer, stage: #765"

package ru.simplesys.defs.app.scala.container

import akka.actor.Actor
import com.simplesys.app.SessionContextSupport
import com.simplesys.common.Strings._
import com.simplesys.isc.dataBinging.RPC.RPCResponseDyn
import com.simplesys.isc.dataBinging.dataSource.RecordDyn
import com.simplesys.isc.dataBinging.{DSRequestDyn, DSResponseDyn, DSResponseFailureExDyn}
import com.simplesys.isc.grids.RecordsDynList
import com.simplesys.isc.system.ServletActorDyn
import com.simplesys.jdbc.control.DSRequest
import com.simplesys.jdbc.control.clob._
import com.simplesys.servlet.GetData
import com.simplesys.tuple.TupleSS2
import ru.simplesys.defs.bo.scenarios._
import com.simplesys.jdbc._

import scalaz.{Failure, Success}


trait scenarios_Scr_ScenarioClobConfig_SemiHandTrait_Fetch extends SessionContextSupport with ServletActorDyn {

    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!! DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////
    val requestData = new DSRequestDyn(request)

    logger debug s"Request for Fetch: ${newLine + requestData.toPrettyString}"

    val dataSet = Scr_ScenarioClobConfigDS(ds)
    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!! END DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////

    def receiveBase: Option[Actor.Receive] = None

    def wrapperBlobGetter(blob: Blob): String = blob.asString
}
