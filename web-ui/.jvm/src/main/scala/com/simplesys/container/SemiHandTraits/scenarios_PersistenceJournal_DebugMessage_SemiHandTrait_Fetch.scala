// This file is generated automatically (at 05.12.2016 18:32:57), do not spend any changes here, because they will be lost. Generator: "GenBOContainer, stage: #765"

package ru.simplesys.defs.app.scala.container

import akka.actor.Actor
import com.simplesys.app.SessionContextSupport
import com.simplesys.circe.Circe._
import com.simplesys.common.Strings._
import com.simplesys.isc.dataBinging.DSResponse.{DSResponseFailureEx, _}
import com.simplesys.isc.dataBinging.{DSRequest, DSResponse, RPCResponse}
import com.simplesys.jdbc.control.classBO.Where
import com.simplesys.jdbc.control.clob._
import com.simplesys.servlet.isc.{GetData, ServletActor}
import com.simplesys.tuple.TupleSS3
import io.circe.Json._
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._
import ru.simplesys.defs.bo.scenarios._

import scalaz.{Failure, Success}


trait scenarios_PersistenceJournal_DebugMessage_SemiHandTrait_Fetch extends SessionContextSupport with ServletActor {

    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!! DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////
    val requestData: DSRequest = request.JSONData.as[DSRequest].getOrElse(throw new RuntimeException("Dont parsed Request JSON"))

    logger debug s"Request for Update: ${newLine + requestData.asJson.toPrettyString}"

    val dataSet = PersistenceJournal_DebugMessageDS(oraclePool)
    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!! END DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////

    def receiveBase: Option[Actor.Receive] = Some({
        case GetData => {
            val data = requestData.data.getOrElse(Null)

            if (data.getStringOpt("persistence_id").isDefined) {
                val persistence_id = data.getString("persistence_id")
                val sequence_number = data.getLong("sequence_number")

                logger debug s"data: ${newLine + data.toPrettyString}"

                val select = dataSet.selectOne(where = Where(dataSet.persistence_idPersistenceJournal_Debug === persistence_id) And (dataSet.sequence_numberPersistenceJournal_Debug === sequence_number))

                val out = select.result match {
                    case Success(TupleSS3(messagePersistenceJournal_Debug: Blob, persistence_idPersistenceJournal_Debug: String, sequence_numberPersistenceJournal_Debug: Long)) ⇒

                        parse(wrapperBlobGetter(messagePersistenceJournal_Debug)) match {
                            case Left(_) =>
                                DSResponseOk
                            case Right(json) ⇒
                              DSResponse (
                                  data = json,
                                  status = (if (json.getStringOpt("error").isDefined) RPCResponse.statusFailure else RPCResponse.statusSuccess)
                              )
                        }

                    case Failure(_) =>
                        DSResponseFailureEx(select.printException.get.message, select.printException.get.stackTrace)
                }

                Out(out = out)
            }

            selfStop()
        }
        case x =>
            throw new RuntimeException(s"Bad branch $x")
    })

    def wrapperBlobGetter(blob: Blob): String = inputStream2Sting(blob)
}
