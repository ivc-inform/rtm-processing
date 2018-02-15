// This file is generated automatically (at 03.08.2016 14:48:32), do not spend any changes here, because they will be lost. Generator: "GenBOContainer, stage: #765"

package ru.simplesys.defs.app.scala.container

import java.time.LocalDateTime

import akka.actor.Actor
import com.simplesys.app.SessionContextSupport
import com.simplesys.circe.Circe._
import com.simplesys.common.Strings._
import com.simplesys.export.ExelExport
import com.simplesys.file.Path
import com.simplesys.isc.dataBinging.DSResponse.DSResponseFailureEx
import com.simplesys.isc.dataBinging.{DSRequest, DSResponse, RPCResponse}
import com.simplesys.jdbc.control.DsRequest
import com.simplesys.servlet.isc.{GetData, ServletActor}
import com.simplesys.tuple.TupleSS16
import io.circe.Json._
import io.circe.generic.auto._
import io.circe.syntax._
import ru.simplesys.defs.bo.scenarios._

import scala.util.Try
import scalaz.{Failure, Success}


trait scenarios_ScenarioTrace_SemiHandTrait_Fetch extends SessionContextSupport with ServletActor {

    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!! DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////
    val requestData: DSRequest = request.JSONData.as[DSRequest].getOrElse(throw new RuntimeException("Dont parsed Request JSON"))

    logger debug s"Request for Update: ${newLine + requestData.asJson.toPrettyString}"

    val dataSet = ScenarioTraceDS(oraclePool)
    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!! END DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////

    def receiveBase: Option[Actor.Receive] = Some(
        {
            case GetData => {
                logger debug s"request: ${newLine + requestData.asJson.toPrettyString}"
                Try {
                    val qty: Int = requestData.endRow.getOrElse(0) - requestData.startRow.getOrElse(-1) + 1
                    val exportToFilesystem = requestData.asJson.getBooleanOpt("exportToFilesystem").getOrElse(false)

                    val _urlExportFile = if (exportToFilesystem) {
                        val relativePath: Option[String] = servletContext RealPath ""

                        val dsRequest = new DsRequest(
                            sqlDialect = sessionContext.getSQLDialect,
                            startRow = if (exportToFilesystem) 0 else requestData.startRow.getOrElse(0),
                            endRow = if (exportToFilesystem) 0 else requestData.endRow.getOrElse(0),
                            sortBy = requestData.sortBy.getOrElse(Vector.empty),
                            data = requestData.data.getOrElse(Null),
                            textMatchStyle = requestData.textMatchStyle.getOrElse(strEmpty)
                        )

                        val exelExportFilePath = Path(s"${relativePath.get}/tmp")
                        val exelExport = new ExelExport(dataSet, dsRequest, exelExportFilePath.createDirectory(failIfExists = false).toFile)
                        val fileRes = exelExport.execute()

                        Some(s"${servletContext.ContextPath}tmp/${fileRes.getName}")
                    } else
                        None

                    val select = dataSet.Fetch(
                        dsRequest = DsRequest(
                            sqlDialect = sessionContext.getSQLDialect,
                            startRow = requestData.startRow.getOrElse(0),
                            endRow = requestData.endRow.getOrElse(0),
                            sortBy = requestData.sortBy.getOrElse(Vector.empty),
                            data = requestData.data.getOrElse(Null),
                            textMatchStyle = requestData.textMatchStyle.getOrElse(strEmpty)
                        ))

                    val out = select.result match {
                        case Success(list) => {
                            val _data = arr(list map {
                                case TupleSS16(actionmessageScenarioTrace: Array[String], actionobjectScenarioTrace: Array[String], actiontypeScenarioTrace: Array[String], addressScenarioTrace: String, bonusbaseScenarioTrace: Array[Double], bonusmessageScenarioTrace: Array[String], eventScenarioTrace: String, idScenarioTrace: Long, idactionmessageScenarioTrace: Array[Long], idbonusmessageScenarioTrace: Array[Long], idmarketingmessageScenarioTrace: Array[Long], marketingmessageScenarioTrace: Array[String], parentstageScenarioTrace: Array[String], scenarioScenarioTrace: String, stageScenarioTrace: String, timestampScenarioTrace: LocalDateTime) =>
                                    obj("id" -> idScenarioTrace, "timestamp" -> timestampScenarioTrace, "address" -> addressScenarioTrace, "event" -> eventScenarioTrace, "scenario" -> scenarioScenarioTrace, "stage" -> stageScenarioTrace, "parentstage" -> parentstageScenarioTrace, "bonusbase" -> bonusbaseScenarioTrace, "idbonusmessage" -> idbonusmessageScenarioTrace, "bonusmessage" -> bonusmessageScenarioTrace, "idmarketingmessage" -> idmarketingmessageScenarioTrace, "marketingmessage" -> marketingmessageScenarioTrace, "actiontype" -> actiontypeScenarioTrace, "idactionmessage" -> idactionmessageScenarioTrace, "actionmessage" -> actionmessageScenarioTrace, "actionobject" -> actionobjectScenarioTrace)
                            }: _*)

                            logger debug s"_data: ${newLine + _data.toPrettyString}"

                            DSResponse(
                                status = RPCResponse.statusSuccess,
                                data = _data,
                                startRow = requestData.startRow,
                                endRow = Some(requestData.startRow.getOrElse(0) + list.length),
                                totalRows = Some(if (qty == list.length) qty * 2 else list.length),
                                urlExportFile = _urlExportFile,
                            )
                        }
                        case Failure(_) =>
                            DSResponseFailureEx(select.printException.get.message, select.printException.get.stackTrace)
                    }
                    Out(out = out)
                } match {
                    case scala.util.Success(_) ⇒

                    case scala.util.Failure(e) ⇒
                        OutFailure(e)
                }

                selfStop()
            }
            case x =>
                throw new RuntimeException(s"Bad branch $x")
        }
    )
}
