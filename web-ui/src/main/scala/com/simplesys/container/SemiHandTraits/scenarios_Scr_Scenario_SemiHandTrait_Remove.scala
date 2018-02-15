// This file is generated automatically (at 18.08.2016 14:13:09), do not spend any changes here, because they will be lost. Generator: "GenBOContainer, stage: #765"

package ru.simplesys.defs.app.scala.container

import akka.actor.Actor
import com.simplesys.app.SessionContextSupport
import com.simplesys.common.Strings._
import com.simplesys.isc.dataBinging.RPC.RPCResponseDyn
import com.simplesys.isc.dataBinging.{DSRequestDyn, DSResponseDyn, DSResponseFailureExDyn}
import com.simplesys.isc.system.misc.Number
import com.simplesys.isc.system.{ArrayDyn, ServletActorDyn}
import com.simplesys.jdbc.control.SessionStructures.transaction
import com.simplesys.jdbc.control.ValidationEx
import com.simplesys.jdbc.control.classBO.Where
import com.simplesys.jdbc.control.clob.Blob
import com.simplesys.json.JsonObject
import com.simplesys.messages.Message
import com.simplesys.servlet.GetData
import ru.simplesys.defs.bo.scenarios._

import scalaz.{Failure, Success}


trait scenarios_Scr_Scenario_SemiHandTrait_Remove extends SessionContextSupport with ServletActorDyn {

    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!! DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////
    val requestData = new DSRequestDyn(request)

    logger debug s"Request for Remove: ${newLine + requestData.toPrettyString}"

    val dataSet = Scr_ScenarioDS(ds)
    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!! END DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////

    def receiveBase: Option[Actor.Receive] = Some({
        case GetData => {
            import com.simplesys.messages.ActorConfig._

            var _transactionNum = Number("0")

            logger debug s"request: ${newLine + requestData.toPrettyString}"

            val dataSetGraphCopies = Scr_Graph_CopiesDS(ds)

            val listResponse = ArrayDyn.empty[DSResponseDyn]
            val delete: ValidationEx[List[Int]] = requestData.Transaction.TransactionNum match {
                case null => {
                    val data = requestData.Data

                    logger debug (s"data: ${newLine + data.toPrettyString}")
                    val id_scenario = data.getLong("id_scenario")

                    dataSetGraphCopies.delete(where = Where(dataSetGraphCopies.id_scenario_refScr_Graph_Copies === id_scenario)).result match {
                        case Success(_) =>
                            listResponse append
                              new DSResponseDyn {
                                  Status = RPCResponseDyn.statusSuccess
                                  Data =
                                    JsonObject()
                              }
                            dataSet.delete(where = Where(dataSet.id_scenarioScr_Scenario === id_scenario))
                        case Failure(e) ⇒ throw e
                    }
                }
                case transactionNum =>
                    transaction(dataSet.dataSource) {
                        connection =>
                            _transactionNum = transactionNum
                            requestData.Transaction.Operations.flatMap {
                                case operation: JsonObject => {
                                    val data = operation.getJsonObjectOpt("data")
                                    logger debug (s"data: ${newLine + data.toPrettyString}")

                                    val id_scenario = data.getLong("id_scenario")

                                    dataSetGraphCopies.delete(where = Where(dataSetGraphCopies.id_scenario_refScr_Graph_Copies === id_scenario)).result match {
                                        case Success(_) =>
                                            listResponse append
                                              new DSResponseDyn {
                                                  Status = RPCResponseDyn.statusSuccess
                                                  Data =
                                                    JsonObject()
                                              }
                                            SendMessage(Message(channels = s"ListElements_Remove_$transactionNum"))

                                            dataSet.deleteWithoutCommit(connection = connection, where = Where(dataSet.id_scenarioScr_Scenario === id_scenario))
                                        case Failure(e) ⇒ throw e
                                    }

                                }
                                case x =>
                                    throw new RuntimeException(s"Bad branch: $x")
                            }.toList
                    }
            }

            Out(array = delete result match {
                case Success(res) => {
                    res foreach (x => logger debug (s"Deleted: ${x} line(s)."))

                    listResponse
                }
                case Failure(_) =>
                    ArrayDyn(new DSResponseFailureExDyn(delete))
            })

            if (_transactionNum.toInt != 0) SendMessage(Message(channels = s"ListElements_EndRemove_${_transactionNum.toInt}"))

            selfStop()
        }
        case x =>
            throw new RuntimeException(s"Bad branch $x")
    })

    def wrapperBlobGetter(blob: Blob): String = blob.asString
}
