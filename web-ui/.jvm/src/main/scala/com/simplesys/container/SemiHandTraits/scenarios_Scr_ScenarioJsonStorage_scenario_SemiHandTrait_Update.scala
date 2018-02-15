package ru.simplesys.defs.app.scala.container

import java.sql.Connection

import akka.actor.Actor
import com.simplesys.app.SessionContextSupport
import com.simplesys.app.seq.Sequences
import com.simplesys.circe.Circe._
import com.simplesys.common.Strings._
import com.simplesys.isc.dataBinging.DSResponse.DSResponseFailureEx
import com.simplesys.isc.dataBinging.{DSRequest, DSResponse, RPCResponse}
import com.simplesys.jdbc.control.SessionStructures._
import com.simplesys.jdbc.control.ValidationEx
import com.simplesys.jdbc.control.classBO.Where
import com.simplesys.jdbc.control.clob.Blob
import com.simplesys.servlet.isc.{GetData, ServletActor}
import io.circe.Json._
import io.circe.generic.auto._
import io.circe.syntax._
import ru.simplesys.defs.bo.scenarios._
import ru.simplesys.defs.bo.scenarios.table.Scr_Graph_CopiesTblJsonStorage_scenario

import scala.collection.mutable.ArrayBuffer
import scalaz.{Failure, Success}


trait scenarios_Scr_ScenarioJsonStorage_scenario_SemiHandTrait_Update extends SessionContextSupport with ServletActor {

    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!! DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////
    val requestData: DSRequest = request.JSONData.as[DSRequest].getOrElse(throw new RuntimeException("Dont parsed Request JSON"))

    logger debug s"Request for Update: ${newLine + requestData.asJson.toPrettyString}"

    val dataSet = Scr_ScenarioJsonStorage_scenarioDS(oraclePool)
    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!! END DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////

    def updateCopies(id_scenario: Long, connection: Connection): Unit = {

        val dataSetCopies = Scr_Graph_CopiesDS(oraclePool)
        val table = Scr_Graph_CopiesTblJsonStorage_scenario(oraclePool)

        dataSet.selectPOne(where = Where(dataSet.id_scenarioScr_Scenario === id_scenario)).result match {
            case Success(res) ⇒
                res.jsonStorage_scenarioScr_Scenario.headOption.foreach {
                    json ⇒
                        if (json != "" && json != null) {
                            val scr_Graph_CopiesJsonStorage_scenarioData =
                                Scr_Graph_CopiesJsonStorage_scenario(id = Sequences(oraclePool).nextLong1(table.databaseTablename),
                                    id_scenario_ref = id_scenario,
                                    jsonStorage_scenario = Some(json))

                            dataSetCopies.selectPList(where = Where(dataSetCopies.id_scenarioScr_Scenario_Id_scenario_ref === id_scenario)).result match {
                                case Success(list) ⇒

                                    val _list: List[Scr_Graph_CopiesDSData] = list.sortWith(_.versionScr_Graph_Copies.getMillis > _.versionScr_Graph_Copies.getMillis)
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
                logger debug s"request: ${newLine + requestData.asJson.toPrettyString}"

                val listResponse = ArrayBuffer.empty[DSResponse]

                val transactionNum = if (requestData.transaction.isEmpty) None else requestData.transaction.get.transactionNum

                val update: ValidationEx[Array[Int]] = transactionNum match {
                    case None => {
                        val data = requestData.oldValues ++ requestData.data

                        logger debug s"data: ${newLine + data.toPrettyString}"

                        val scr_ScenarioJsonStorage_scenarioData =
                            Scr_ScenarioJsonStorage_scenario(
                                id_scenario = data.getLong("id_scenario"),
                                jsonStorage_scenario = data.getStringOpt("jsonStorage_scenario"))

                        listResponse append
                          DSResponse(
                              status = RPCResponse.statusSuccess,
                              data = obj("id_scenario" -> scr_ScenarioJsonStorage_scenarioData.id_scenario,
                                  "jsonStorage_scenario" -> scr_ScenarioJsonStorage_scenarioData.jsonStorage_scenario)
                          )

                        transaction(dataSet.dataSource) { connection =>
                            updateCopies(scr_ScenarioJsonStorage_scenarioData.id_scenario, connection)

                            dataSet.updatePWithoutCommit(
                                connection = connection,
                                values = scr_ScenarioJsonStorage_scenarioData,
                                where = Where(dataSet.id_scenarioScr_Scenario === scr_ScenarioJsonStorage_scenarioData.id_scenario))
                        }

                    }
                    case _ =>
                        transaction(dataSet.dataSource) { connection =>
                            requestData.transaction.get.operations.flatMap {
                                case operation: DSRequest =>
                                    val data = operation.asJson.getJsonObjectOpt("oldValues") ++ operation.asJson.getJsonObjectOpt("data")

                                    logger debug (s"data: ${newLine + data.toPrettyString}")

                                    val scr_ScenarioJsonStorage_scenarioData =
                                        Scr_ScenarioJsonStorage_scenario(id_scenario = data.getLong("id_scenario"),
                                            jsonStorage_scenario = data.getStringOpt("jsonStorage_scenario"))

                                    listResponse append
                                      new DSResponse(
                                          status = RPCResponse.statusSuccess,
                                          data = obj("id_scenario" -> scr_ScenarioJsonStorage_scenarioData.id_scenario,
                                              "jsonStorage_scenario" -> scr_ScenarioJsonStorage_scenarioData.jsonStorage_scenario)
                                      )

                                    updateCopies(scr_ScenarioJsonStorage_scenarioData.id_scenario, connection)

                                    dataSet.updatePWithoutCommit(connection = connection,
                                        values = scr_ScenarioJsonStorage_scenarioData,
                                        where = Where(dataSet.id_scenarioScr_Scenario === scr_ScenarioJsonStorage_scenarioData.id_scenario))


                                case x =>
                                    throw new RuntimeException(s"Bad branch: $x")
                            }.toArray
                        }
                }

                val out = update result match {
                    case Success(res) => {
                        res foreach (x => logger debug (s"Updated: ${x} line(s)."))

                        DSResponse(
                            data = arr(listResponse.map(_.asJson).toSeq: _*),
                            status = RPCResponse.statusSuccess
                        )
                    }
                    case Failure(_) =>
                        DSResponseFailureEx(update.printException.get.message, update.printException.get.stackTrace)
                }
                Out(out = out)

                selfStop()
            }
            case x =>
                throw new RuntimeException(s"Bad branch $x")
        }
    )

    def wrapperBlobGetter(blob: Blob): String = inputStream2Sting(blob)
}
