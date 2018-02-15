// This file is generated automatically (at 18.08.2016 14:13:09), do not spend any changes here, because they will be lost. Generator: "GenBOContainer, stage: #765"

package ru.simplesys.defs.app.scala.container

import akka.actor.Actor
import akka.pattern.ask
import akka.util.Timeout
import com.simplesys.actors.AppConfig
import com.simplesys.actors.Reaper.{Request2ScenarioInUse, ScenarioInUse, ScenarioNotInUse, ScenarioProps}
import com.simplesys.app.SessionContextSupport
import com.simplesys.common.Strings._
import com.simplesys.isc.dataBinging.RPC.RPCResponseDyn
import com.simplesys.isc.dataBinging.dataSource.RecordDyn
import com.simplesys.isc.dataBinging.{DSRequestDyn, DSResponseDyn, DSResponseFailureExDyn}
import com.simplesys.isc.grids.RecordsDynList
import com.simplesys.isc.system.ServletActorDyn
import com.simplesys.jdbc._
import com.simplesys.jdbc.control.DSRequest
import com.simplesys.jdbc.control.clob.Blob
import com.simplesys.js.components.StatusScenario
import com.simplesys.servlet.GetData
import com.simplesys.tuple.{TupleSS12, TupleSS13}
import org.joda.time.LocalDateTime
import ru.simplesys.defs.bo.scenarios._

import scala.concurrent.Await
import scala.concurrent.duration._
import scalaz.{Failure, Success}


trait scenarios_Scr_Scenario_SemiHandTrait_Fetch extends SessionContextSupport with ServletActorDyn {

    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!! DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////
    val requestData = new DSRequestDyn(request)

    logger debug s"Request for Fetch: ${newLine + requestData.toPrettyString}"

    val dataSet = Scr_ScenarioDS(ds)
    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!! END DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////

    implicit val actorSystemTimeout = 1 minute

    def receiveBase: Option[Actor.Receive] = None

    def wrapperBlobGetter(blob: Blob): String = blob.asString
}
