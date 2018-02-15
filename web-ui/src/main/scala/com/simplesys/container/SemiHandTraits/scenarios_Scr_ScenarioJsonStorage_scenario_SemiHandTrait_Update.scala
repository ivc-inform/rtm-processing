package ru.simplesys.defs.app.scala.container

import java.sql.Connection

import akka.actor.Actor
import com.simplesys.app.SessionContextSupport
import com.simplesys.app.seq.Sequences
import com.simplesys.common.Strings._
import com.simplesys.isc.dataBinging.RPC.RPCResponseDyn
import com.simplesys.isc.dataBinging.dataSource.RecordDyn
import com.simplesys.isc.dataBinging.{DSRequestDyn, DSResponseDyn, DSResponseFailureExDyn}
import com.simplesys.isc.system.{ArrayDyn, ServletActorDyn}
import com.simplesys.jdbc.control.SessionStructures._
import com.simplesys.jdbc.control.ValidationEx
import com.simplesys.jdbc.control.classBO.Where
import com.simplesys.jdbc.control.clob.Blob
import com.simplesys.json.JsonObject
import com.simplesys.servlet.GetData
import ru.simplesys.defs.bo.scenarios.table.Scr_Graph_CopiesTblJsonStorage_scenario
import ru.simplesys.defs.bo.scenarios.{Scr_Graph_CopiesDSData, _}

import scalaz.{Failure, Success}


trait scenarios_Scr_ScenarioJsonStorage_scenario_SemiHandTrait_Update extends SessionContextSupport with ServletActorDyn {

    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!! DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////
    val requestData = new DSRequestDyn(request)

    logger debug s"Request for Update: ${newLine + requestData.toPrettyString}"

    val dataSet = Scr_ScenarioJsonStorage_scenarioDS(ds)
    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!! END DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////

    def updateCopies(id_scenario: Long, connection: Connection): Unit = {

        val dataSetCopies = Scr_Graph_CopiesDS(ds)
        val table = Scr_Graph_CopiesTblJsonStorage_scenario(ds)

        dataSet.selectPOne(where = Where(dataSet.id_scenarioScr_Scenario === id_scenario)).result match {
            case Success(res) ⇒
                res.jsonStorage_scenarioScr_Scenario.headOption.foreach {
                    json ⇒
                        if (json != "" && json != null) {
                            val scr_Graph_CopiesJsonStorage_scenarioData =
                                Scr_Graph_CopiesJsonStorage_scenario(id = Sequences(ds).nextLong1(table.databaseTablename),
                                    id_scenario_ref = id_scenario,
                                    jsonStorage_scenario = Some(json))

                            dataSetCopies.selectPList(where = Where(dataSetCopies.id_scenarioScr_Scenario_Id_scenario_ref === id_scenario)).result match {
                                case Success(list) ⇒

                                    val _list: List[Scr_Graph_CopiesDSData] = list.sortWith(_.versionScr_Graph_Copies.toDateTime.getMillis > _.versionScr_Graph_Copies.toDateTime.getMillis)
                                    if (_list.length >= 100) {
                                        dataSetCopies.delete(where = Where(dataSetCopies.idScr_Graph_Copies === _list.head.idScr_Graph_Copies)).result match {
                                            case Success(x) ⇒
                                                logger debug (s"deleted: ${x} line(s).")
                                            case Failure(e) ⇒
                                                throw e
                                        }
                                    }

                                case Failure(x) ⇒
                                    throw x
                            }

                            table.insertPWithoutCommit(connection = connection, scr_Graph_CopiesJsonStorage_scenarioData)
                        }
                }

            case Failure(e) ⇒ throw e
        }
    }

    def receiveBase: Option[Actor.Receive] = Some(
        {
            case GetData => {
                logger debug s"request: ${newLine + requestData.toPrettyString}"

                val listResponse = ArrayDyn.empty[DSResponseDyn]

                val update: ValidationEx[List[Int]] = requestData.Transaction.TransactionNum match {
                    case null => {
                        val data = requestData.OldValues ++ requestData.Data

                        logger debug s"data: ${newLine + data.toPrettyString}"

                        val scr_ScenarioJsonStorage_scenarioData =
                            Scr_ScenarioJsonStorage_scenario(
                                id_scenario = data.getLong("id_scenario"),
                                jsonStorage_scenario = data.getJsonOpt("jsonStorage_scenario"))

                        listResponse append
                          new DSResponseDyn {
                              Status = RPCResponseDyn.statusSuccess
                              Data = RecordDyn("id_scenario" -> scr_ScenarioJsonStorage_scenarioData.id_scenario,
                                  "jsonStorage_scenario" -> scr_ScenarioJsonStorage_scenarioData.jsonStorage_scenario)
                          }

                        transaction(dataSet.dataSource) { connection =>
                            updateCopies(scr_ScenarioJsonStorage_scenarioData.id_scenario, connection)

                            dataSet.updatePWithoutCommit(connection = connection, values = scr_ScenarioJsonStorage_scenarioData,
                                where = Where(dataSet.id_scenarioScr_Scenario === scr_ScenarioJsonStorage_scenarioData.id_scenario))
                        }

                    }
                    case _ =>
                        transaction(dataSet.dataSource) { connection =>
                            requestData.Transaction.Operations.flatMap {
                                case operation: JsonObject => {
                                    val data = operation.getJsonObjectOpt("oldValues") ++ operation.getJsonObjectOpt("data")

                                    logger debug (s"data: ${newLine + data.toPrettyString}")

                                    val scr_ScenarioJsonStorage_scenarioData =
                                        Scr_ScenarioJsonStorage_scenario(id_scenario = data.getLong("id_scenario"),
                                            jsonStorage_scenario = data.getJsonOpt("jsonStorage_scenario"))

                                    listResponse append
                                      new DSResponseDyn {
                                          Status = RPCResponseDyn.statusSuccess
                                          Data = RecordDyn("id_scenario" -> scr_ScenarioJsonStorage_scenarioData.id_scenario,
                                              "jsonStorage_scenario" -> scr_ScenarioJsonStorage_scenarioData.jsonStorage_scenario)
                                      }

                                    updateCopies(scr_ScenarioJsonStorage_scenarioData.id_scenario, connection)

                                    dataSet.updatePWithoutCommit(connection = connection,
                                        values = scr_ScenarioJsonStorage_scenarioData,
                                        where = Where(dataSet.id_scenarioScr_Scenario === scr_ScenarioJsonStorage_scenarioData.id_scenario))

                                }
                                case x =>
                                    throw new RuntimeException(s"Bad branch: $x")
                            }.toList
                        }
                }

                Out(array = update result match {
                    case Success(res) => {
                        res foreach (x => logger debug (s"Updated: ${x} line(s)."))

                        listResponse
                    }
                    case Failure(_) =>
                        ArrayDyn(new DSResponseFailureExDyn(update))
                })

                selfStop()
            }
            case x =>
                throw new RuntimeException(s"Bad branch $x")
        }
    )

    def wrapperBlobGetter(blob: Blob): String = blob.asString
}
