// This file is generated automatically (at 18.08.2016 14:13:09), do not spend any changes here, because they will be lost. Generator: "GenBOContainer, stage: #765"

package ru.simplesys.defs.app.scala.container

import akka.actor.Actor
import com.simplesys.app.SessionContextSupport
import com.simplesys.circe.Circe._
import com.simplesys.common.Strings._
import com.simplesys.isc.dataBinging.DSResponse.DSResponseFailureEx
import com.simplesys.isc.dataBinging.{DSRequest, DSResponse, RPCResponse}
import com.simplesys.jdbc.control.SessionStructures._
import com.simplesys.jdbc.control.ValidationEx
import com.simplesys.jdbc.control.classBO.Where
import com.simplesys.jdbc.control.clob.Blob
import com.simplesys.messages.Message
import com.simplesys.servlet.isc.{GetData, ServletActor}
import io.circe.Json._
import io.circe.generic.auto._
import io.circe.syntax._
import ru.simplesys.defs.bo.scenarios._

import scala.collection.mutable.ArrayBuffer
import scalaz.{Failure, Success}


trait scenarios_Scr_Scenario_SemiHandTrait_Remove extends SessionContextSupport with ServletActor {

    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!! DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////
    val requestData: DSRequest = request.JSONData.as[DSRequest].getOrElse(throw new RuntimeException("Dont parsed Request JSON"))

    logger debug s"Request for Update: ${newLine + requestData.asJson.toPrettyString}"

    val dataSet = Scr_ScenarioDS(oraclePool)
    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!! END DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////

    def receiveBase: Option[Actor.Receive] = Some({
        case GetData => {
            import com.simplesys.messages.ActorConfig._

            val transactionNum = if (requestData.transaction.isEmpty) None else requestData.transaction.get.transactionNum

            logger debug s"request: ${newLine + requestData.asJson.toPrettyString}"

            val dataSetGraphCopies = Scr_Graph_CopiesDS(oraclePool)

            val listResponse = ArrayBuffer.empty[DSResponse]
            val delete: ValidationEx[Array[Int]] = transactionNum match {
                case None =>
                    val data = requestData.data

                    logger debug (s"data: ${newLine + data.toPrettyString}")
                    val id_scenario = data.getLong("id_scenario")

                    dataSetGraphCopies.delete(where = Where(dataSetGraphCopies.id_scenario_refScr_Graph_Copies === id_scenario)).result match {
                        case Success(_) =>
                            listResponse append
                              DSResponse(
                                  status = RPCResponse.statusSuccess,
                                  data = obj()
                              )
                            dataSet.delete(where = Where(dataSet.id_scenarioScr_Scenario === id_scenario))

                        case Failure(e) ⇒ throw e
                    }

                case transactionNum =>
                    transaction(dataSet.dataSource) {
                        connection =>
                            requestData.transaction.get.operations.flatMap {
                                case operation: DSRequest =>
                                    val data = operation.data
                                    logger debug (s"data: ${newLine + data.toPrettyString}")

                                    val id_scenario = data.getLong("id_scenario")

                                    dataSetGraphCopies.delete(where = Where(dataSetGraphCopies.id_scenario_refScr_Graph_Copies === id_scenario)).result match {
                                        case Success(_) =>
                                            listResponse append
                                              DSResponse(
                                                  status = RPCResponse.statusSuccess,
                                                  data = obj()
                                              )
                                            SendMessage(Message(channels = s"ListElements_Remove_$transactionNum"))

                                            dataSet.deleteWithoutCommit(connection = connection, where = Where(dataSet.id_scenarioScr_Scenario === id_scenario))

                                        case Failure(e) ⇒ throw e
                                    }


                                case x =>
                                    throw new RuntimeException(s"Bad branch: $x")
                            }.toArray
                    }
            }

            val out = delete result match {
                case Success(res) => {
                    res foreach (x => logger debug (s"Deleted: ${x} line(s)."))

                    DSResponse(
                        data = arr(listResponse.map(_.asJson).toSeq: _*),
                        status = RPCResponse.statusSuccess
                    )
                }
                case Failure(_) =>
                    DSResponseFailureEx(delete.printException.get.message, delete.printException.get.stackTrace)
            }

            Out(out = out)

            if (transactionNum.isDefined) SendMessage(Message(channels = s"ListElements_EndRemove_${transactionNum.get}"))

            selfStop()
        }
        case x =>
            throw new RuntimeException(s"Bad branch $x")
    })

    def wrapperBlobGetter(blob: Blob): String = inputStream2Sting(blob)
}
