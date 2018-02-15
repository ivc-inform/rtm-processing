// This file is generated automatically (at 04.08.2016 07:18:40), do not spend any changes here, because they will be lost. Generator: "GenBOContainer, stage: #765"

package ru.simplesys.defs.app.scala.container

import akka.actor.Actor
import com.simplesys.app.SessionContextSupport
import com.simplesys.common.Strings._
import com.simplesys.isc.dataBinging.RPC.RPCResponseDyn
import com.simplesys.isc.dataBinging.{DSRequestDyn, DSResponseDyn, DSResponseFailureExDyn}
import com.simplesys.isc.system.ServletActorDyn
import com.simplesys.jdbc.control.classBO.Where
import com.simplesys.jdbc.control.clob.Blob
import com.simplesys.json.{Json, JsonObject}
import com.simplesys.servlet.GetData
import com.simplesys.tuple.TupleSS3
import ru.simplesys.defs.bo.scenarios._

import scalaz.{Failure, Success}


trait scenarios_PersistenceJournalMessage_SemiHandTrait_Fetch extends SessionContextSupport with ServletActorDyn {

    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!! DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////
    val requestData = new DSRequestDyn(request)

    logger debug s"Request for Fetch: ${newLine + requestData.toPrettyString}"

    val dataSet = PersistenceJournalMessageDS(ds)
    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!! END DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////

    def receiveBase: Option[Actor.Receive] = Some({
        case GetData => {
            val data = requestData

            if (data.getStringOpt("persistence_id").isDefined) {
                val persistence_id = data.getString("persistence_id")
                val sequence_number = data.getLong("sequence_number")

                logger debug s"data: ${newLine + data.toPrettyString}"

                val select = dataSet.selectOne(where = Where(dataSet.persistence_idPersistenceJournal === persistence_id) And (dataSet.sequence_numberPersistenceJournal === sequence_number))

                Out(classDyn = select.result match {
                    case Success(TupleSS3(messagePersistenceJournal: Blob, persistence_idPersistenceJournal: String, sequence_numberPersistenceJournal: Long)) => {

                        Json.getJsonOptExt(wrapperBlobGetter(messagePersistenceJournal)) match {
                            case None =>
                                new DSResponseDyn {
                                    Status = RPCResponseDyn.statusSuccess
                                }
                            case Some(json1: JsonObject) =>
                                new DSResponseDyn {
                                    Status = if (json1.getStringOpt("error").isDefined) RPCResponseDyn.statusFailure else RPCResponseDyn.statusSuccess
                                    Data = json1.toPrettyString
                                }
                        }
                    }
                    case Failure(_) =>
                        new DSResponseFailureExDyn(select)
                })
            }

            selfStop()
        }
        case x =>
            throw new RuntimeException(s"Bad branch $x")
    })

    def wrapperBlobGetter(blob: Blob): String = blob.asString
}
