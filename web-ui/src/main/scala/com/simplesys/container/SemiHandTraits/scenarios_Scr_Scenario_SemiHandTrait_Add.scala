// This file is generated automatically (at 18.08.2016 14:13:09), do not spend any changes here, because they will be lost. Generator: "GenBOContainer, stage: #765"

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
import com.simplesys.jdbc.control.SessionStructures._
import com.simplesys.jdbc.control.ValidationEx
import com.simplesys.jdbc.control.clob.Blob
import com.simplesys.js.components.StatusScenario
import com.simplesys.json.JsonObject
import com.simplesys.messages.Message
import com.simplesys.servlet.GetData
import ru.simplesys.defs.bo.scenarios.{Scr_Scenario, _}

import scalaz.{Failure, Success}


trait scenarios_Scr_Scenario_SemiHandTrait_Add extends SessionContextSupport with ServletActorDyn {

    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!! DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////
    val requestData = new DSRequestDyn(request)

    logger debug s"Request for Add: ${newLine + requestData.toPrettyString}"

    val dataSet = Scr_ScenarioDS(ds)
    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!! END DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////

    def receiveBase: Option[Actor.Receive] = Some(
        {
            case GetData => {
                import com.simplesys.messages.ActorConfig._

                val listResponse = ArrayDyn.empty[DSResponseDyn]

                var _transactionNum = Number("0")

                logger debug s"request: ${newLine + requestData.toPrettyString}"

                val insert: ValidationEx[List[Int]] = requestData.Transaction.TransactionNum match {
                    case null => {
                        val data = requestData.Data

                        logger debug s"data: ${newLine + data.toPrettyString}"

                        val scr_ScenarioData =
                            Scr_Scenario(id_scenario = Sequences(ds).nextLong1(dataSet.fromBO.fromTable.databaseTablename),
                                version = data.getLocalDateTimeOpt("version"),
                                code_scenario = data.getString("code_scenario"),
                                caption_scenario = data.getString("caption_scenario"),
                                description_scenario = data.getStringOpt("description_scenario"),
                                begin_scenario = data.getLocalDateTimeOpt("begin_scenario"),
                                end_scenario = data.getLocalDateTimeOpt("end_scenario"),
                                status = Some(StatusScenario.notReady.value.toLong),
                                debug_mode = Some(false),
                                id_cmpgn = data.getLongOpt("id_cmpgn"))

                        listResponse append
                          new DSResponseDyn {
                              Status = RPCResponseDyn.statusSuccess
                              Data =
                                RecordDyn("id_scenario" -> scr_ScenarioData.id_scenario, "version" -> scr_ScenarioData.version, "code_scenario" -> scr_ScenarioData.code_scenario, "caption_scenario" -> scr_ScenarioData.caption_scenario, "description_scenario" -> scr_ScenarioData.description_scenario, "begin_scenario" -> scr_ScenarioData.begin_scenario, "end_scenario" -> scr_ScenarioData.end_scenario, "status" -> scr_ScenarioData.status, "id_cmpgn" -> scr_ScenarioData.id_cmpgn, "code_cmpgn" -> data.getString("code_cmpgn"), "caption_cmpgn" -> data.getString("caption_cmpgn"))
                          }

                        dataSet.insertP(scr_ScenarioData)
                    }
                    case transactionNum =>
                        transaction(dataSet.dataSource) {
                            connection =>
                                _transactionNum = transactionNum
                                val values: Seq[Scr_Scenario] =
                                    requestData.Transaction.Operations.map {
                                        case operation: JsonObject => {
                                            val data = operation.getJsonObjectOpt("data")
                                            logger debug (s"data: ${newLine + data.toPrettyString}")

                                            val scr_ScenarioData =
                                                Scr_Scenario(id_scenario = Sequences(ds).nextLong1(dataSet.fromBO.fromTable.databaseTablename),
                                                    version = data.getLocalDateTimeOpt("version"),
                                                    code_scenario = data.getString("code_scenario"),
                                                    caption_scenario = data.getString("caption_scenario"),
                                                    description_scenario = data.getStringOpt("description_scenario"),
                                                    begin_scenario = data.getLocalDateTimeOpt("begin_scenario"),
                                                    end_scenario = data.getLocalDateTimeOpt("end_scenario"),
                                                    status = Some(StatusScenario.notReady.value.toLong),
                                                    debug_mode = Some(false),
                                                    id_cmpgn = data.getLongOpt("id_cmpgn"))

                                            listResponse append
                                              new DSResponseDyn {
                                                  Status = RPCResponseDyn.statusSuccess
                                                  Data =
                                                    RecordDyn("id_scenario" -> scr_ScenarioData.id_scenario, "version" -> scr_ScenarioData.version, "code_scenario" -> scr_ScenarioData.code_scenario, "caption_scenario" -> scr_ScenarioData.caption_scenario, "description_scenario" -> scr_ScenarioData.description_scenario, "begin_scenario" -> scr_ScenarioData.begin_scenario, "end_scenario" -> scr_ScenarioData.end_scenario, "status" -> scr_ScenarioData.status, "id_cmpgn" -> scr_ScenarioData.id_cmpgn, "code_cmpgn" -> data.getString("code_cmpgn"), "caption_cmpgn" -> data.getString("caption_cmpgn"))
                                              }

                                            SendMessage(Message(channels = s"ListElements_Add_$transactionNum"))

                                            scr_ScenarioData
                                        }
                                        case x =>
                                            throw new RuntimeException(s"Bad branch: $x")
                                    }
                                dataSet.insertPWithoutCommit(connection = connection, values: _*)
                        }
                }

                Out(array = insert result match {
                    case Success(res) => {
                        res foreach (x => logger debug (s"Inserted: ${x} line(s)."))

                        listResponse
                    }
                    case Failure(_) =>
                        ArrayDyn(new DSResponseFailureExDyn(insert))
                })

                if (_transactionNum.toInt != 0) SendMessage(Message(channels = s"ListElements_EndAdd_${_transactionNum.toInt}"))

                selfStop()
            }
            case x =>
                throw new RuntimeException(s"Bad branch $x")
        }
    )

    def wrapperBlobGetter(blob: Blob): String = blob.asString
}
