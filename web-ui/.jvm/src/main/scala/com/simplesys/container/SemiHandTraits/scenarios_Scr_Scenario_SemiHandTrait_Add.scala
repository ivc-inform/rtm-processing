// This file is generated automatically (at 18.08.2016 14:13:09), do not spend any changes here, because they will be lost. Generator: "GenBOContainer, stage: #765"

package ru.simplesys.defs.app.scala.container

import akka.actor.Actor
import com.simplesys.app.SessionContextSupport
import com.simplesys.app.seq.Sequences
import com.simplesys.circe.Circe._
import com.simplesys.common.Strings._
import com.simplesys.components.StatusScenario
import com.simplesys.isc.dataBinging.DSResponse.DSResponseFailureEx
import com.simplesys.isc.dataBinging.{DSRequest, DSResponse, RPCResponse}
import com.simplesys.jdbc.control.SessionStructures._
import com.simplesys.jdbc.control.ValidationEx
import com.simplesys.jdbc.control.clob.Blob
import com.simplesys.messages.Message
import com.simplesys.servlet.isc.{GetData, ServletActor}
import io.circe.Json._
import io.circe.generic.auto._
import io.circe.syntax._
import ru.simplesys.defs.bo.scenarios._

import scala.collection.mutable.ArrayBuffer
import scalaz.{Failure, Success}


trait scenarios_Scr_Scenario_SemiHandTrait_Add extends SessionContextSupport with ServletActor {

    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!! DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////
    val requestData: DSRequest = request.JSONData.as[DSRequest].getOrElse(throw new RuntimeException("Dont parsed Request JSON"))

    logger debug s"Request for Update: ${newLine + requestData.asJson.toPrettyString}"

    val dataSet = Scr_ScenarioDS(oraclePool)
    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!! END DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////

    def receiveBase: Option[Actor.Receive] = Some(
        {
            case GetData => {
                import com.simplesys.messages.ActorConfig._

                val listResponse = ArrayBuffer.empty[DSResponse]
                logger debug s"request: ${newLine + requestData.asJson.toPrettyString}"

                val transactionNum = if (requestData.transaction.isEmpty) None else requestData.transaction.get.transactionNum

                val insert: ValidationEx[Array[Int]] = transactionNum match {
                    case None =>
                        val data = requestData.data

                        logger debug s"data: ${newLine + data.toPrettyString}"

                        val scr_ScenarioData =
                            Scr_Scenario(id_scenario = Sequences(oraclePool).nextLong1(dataSet.fromBO.fromTable.databaseTablename),
                                version = data.getLocalDateTimeOpt("version"),
                                code_scenario = data.getString("code_scenario"),
                                caption_scenario = data.getString("caption_scenario"),
                                description_scenario = data.getStringOpt("description_scenario"),
                                begin_scenario = data.getLocalDateTimeOpt("begin_scenario"),
                                end_scenario = data.getLocalDateTimeOpt("end_scenario"),
                                status = Some(StatusScenario.notReady.value.toLong),
                                debug_mode = Some(false),
                                id_cmpgn = data.getLongOpt("id_cmpgn"))


                        listResponse append DSResponse(
                            status = RPCResponse.statusSuccess,
                            data =
                              obj("id_scenario" -> scr_ScenarioData.id_scenario, "version" -> scr_ScenarioData.version, "code_scenario" -> scr_ScenarioData.code_scenario, "caption_scenario" -> scr_ScenarioData.caption_scenario, "description_scenario" -> scr_ScenarioData.description_scenario, "begin_scenario" -> scr_ScenarioData.begin_scenario, "end_scenario" -> scr_ScenarioData.end_scenario, "status" -> scr_ScenarioData.status, "id_cmpgn" -> scr_ScenarioData.id_cmpgn, "code_cmpgn" -> data.getString("code_cmpgn"), "caption_cmpgn" -> data.getString("caption_cmpgn"))
                        )

                        dataSet.insertP(scr_ScenarioData)

                    case Some(transactionNum) =>
                        transaction(dataSet.dataSource) {
                            connection =>
                                val values: Seq[Scr_Scenario] =
                                    requestData.transaction.get.operations.map {
                                        case operation: DSRequest => {
                                            val data = operation.data
                                            logger debug (s"data: ${newLine + data.toPrettyString}")

                                            val scr_ScenarioData =
                                                Scr_Scenario(id_scenario = Sequences(oraclePool).nextLong1(dataSet.fromBO.fromTable.databaseTablename),
                                                    version = data.getLocalDateTimeOpt("version"),
                                                    code_scenario = data.getString("code_scenario"),
                                                    caption_scenario = data.getString("caption_scenario"),
                                                    description_scenario = data.getStringOpt("description_scenario"),
                                                    begin_scenario = data.getLocalDateTimeOpt("begin_scenario"),
                                                    end_scenario = data.getLocalDateTimeOpt("end_scenario"),
                                                    status = Some(StatusScenario.notReady.value.toLong),
                                                    debug_mode = Some(false),
                                                    id_cmpgn = data.getLongOpt("id_cmpgn"))

                                            listResponse append DSResponse(
                                                status = RPCResponse.statusSuccess,
                                                data =
                                                  obj("id_scenario" -> scr_ScenarioData.id_scenario, "version" -> scr_ScenarioData.version, "code_scenario" -> scr_ScenarioData.code_scenario, "caption_scenario" -> scr_ScenarioData.caption_scenario, "description_scenario" -> scr_ScenarioData.description_scenario, "begin_scenario" -> scr_ScenarioData.begin_scenario, "end_scenario" -> scr_ScenarioData.end_scenario, "status" -> scr_ScenarioData.status, "id_cmpgn" -> scr_ScenarioData.id_cmpgn, "code_cmpgn" -> data.getString("code_cmpgn"), "caption_cmpgn" -> data.getString("caption_cmpgn"))
                                            )

                                            SendMessage(Message(channels = s"ListElements_Add_$transactionNum"))

                                            scr_ScenarioData
                                        }
                                        case x =>
                                            throw new RuntimeException(s"Bad branch: $x")
                                    }
                                dataSet.insertPWithoutCommit(connection = connection, values: _*)
                        }
                }

                val out = insert result match {
                    case Success(res) => {
                        res foreach (x => logger debug (s"Inserted: ${x} line(s)."))
                        DSResponse(
                            data = arr(listResponse.map(_.asJson).toSeq: _*),
                            status = RPCResponse.statusSuccess
                        )
                    }
                    case Failure(_) =>
                        DSResponseFailureEx(insert.printException.get.message, insert.printException.get.stackTrace)
                }

                Out(out = out)

                if (transactionNum.isDefined) SendMessage(Message(channels = s"ListElements_EndAdd_${transactionNum.get}"))

                selfStop()
            }
            case x =>
                throw new RuntimeException(s"Bad branch $x")
        }
    )

    def wrapperBlobGetter(blob: Blob): String = inputStream2Sting(blob)
}
