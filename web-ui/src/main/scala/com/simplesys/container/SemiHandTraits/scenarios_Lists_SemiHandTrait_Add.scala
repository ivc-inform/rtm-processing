// This file is generated automatically (at 25.08.2016 14:25:07), do not spend any changes here, because they will be lost. Generator: "GenBOContainer, stage: #765"

package ru.simplesys.defs.app.scala.container

import akka.actor.Actor
import com.simplesys.app.SessionContextSupport
import com.simplesys.app.seq.Sequences
import com.simplesys.common.Strings._
import com.simplesys.isc.dataBinging.RPC.RPCResponseDyn
import com.simplesys.isc.dataBinging.dataSource.RecordDyn
import com.simplesys.isc.dataBinging.{DSRequestDyn, DSResponseDyn, DSResponseFailureExDyn}
import com.simplesys.isc.system.misc.Number
import com.simplesys.isc.system.{ArrayDyn, ServletActorDyn}
import com.simplesys.jdbc.control.SessionStructures.transaction
import com.simplesys.jdbc.control.ValidationEx
import com.simplesys.jdbc.control.clob.Blob
import com.simplesys.json.JsonObject
import com.simplesys.messages.Message
import com.simplesys.servlet.GetData
import ru.simplesys.defs.bo.scenarios._

import scalaz.{Failure, Success}


trait scenarios_Lists_SemiHandTrait_Add extends SessionContextSupport with ServletActorDyn {

    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!! DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////
    val requestData = new DSRequestDyn(request)

    logger debug s"Request for Add: ${newLine + requestData.toPrettyString}"

    val dataSet = ListsDS(ds)
    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!! END DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////

    def receiveBase: Option[Actor.Receive] = None

    def wrapperBlobGetter(blob: Blob): String = blob.asString
}
