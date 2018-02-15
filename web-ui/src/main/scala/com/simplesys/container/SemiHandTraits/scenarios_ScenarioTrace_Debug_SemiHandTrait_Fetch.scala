// This file is generated automatically (at 05.12.2016 18:32:57), do not spend any changes here, because they will be lost. Generator: "GenBOContainer, stage: #765"

package ru.simplesys.defs.app.scala.container

import akka.actor.Actor
import com.simplesys.app.SessionContextSupport
import com.simplesys.common.Strings._
import com.simplesys.export.{ExelExport, ExelExport_Debug}
import com.simplesys.file.Path
import com.simplesys.io._
import com.simplesys.isc.dataBinging.RPC.RPCResponseDyn
import com.simplesys.isc.dataBinging.dataSource.RecordDyn
import com.simplesys.isc.dataBinging.{DSRequestDyn, DSResponseDyn, DSResponseFailureExDyn}
import com.simplesys.isc.grids.RecordsDynList
import com.simplesys.isc.system.ServletActorDyn
import com.simplesys.jdbc._
import com.simplesys.jdbc.control.DSRequest
import com.simplesys.jdbc.control.clob._
import com.simplesys.servlet.GetData
import com.simplesys.tuple.TupleSS16
import org.joda.time.LocalDateTime
import ru.simplesys.defs.bo.scenarios._

import scala.util.Try
import scalaz.{Failure, Success}


trait scenarios_ScenarioTrace_Debug_SemiHandTrait_Fetch extends SessionContextSupport with ServletActorDyn {

    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!! DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////
    val requestData = new DSRequestDyn(request)

    logger debug s"Request for Fetch: ${newLine + requestData.toPrettyString}"

    val dataSet = ScenarioTrace_DebugDS(ds)
    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!! END DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////

    def receiveBase: Option[Actor.Receive] = Some(
        {
            case GetData => {
                logger debug s"request: ${newLine + requestData.toPrettyString}"
                Try {
                    val _data = RecordsDynList()
                    val qty: Int = requestData.EndRow.toInt - requestData.StartRow.toInt + 1
                    val exportToFilesystem = requestData.ExportToFilesystem.value.toString.toBoolean

                    val _urlExportFile = if (exportToFilesystem) {
                        val relativePath: Option[String] = servletContext RealPath ""

                        val dsRequest = DSRequest(
                            sqlDialect = sessionContext.getSQLDialect,
                            startRow = if (exportToFilesystem) 0 else requestData.StartRow,
                            endRow = if (exportToFilesystem) 0 else requestData.EndRow,
                            sortBy = requestData.SortBy,
                            data = requestData.Data,
                            textMatchStyle = requestData.TextMatchStyle.toString
                        )

                        val exelExportFilePath = Path(s"${relativePath.get}/tmp")
                        val exelExport = new ExelExport_Debug(dataSet, dsRequest, exelExportFilePath.createDirectory(failIfExists = false).toFile)
                        val fileRes = exelExport.execute()

                        Some(s"${servletContext.ContextPath}tmp/${fileRes.getName}")
                    } else
                        None

                    val select = dataSet.Fetch(
                        dsRequest = DSRequest(
                            sqlDialect = sessionContext.getSQLDialect,
                            startRow = requestData.StartRow,
                            endRow = requestData.EndRow,
                            sortBy = requestData.SortBy,
                            data = requestData.Data,
                            textMatchStyle = requestData.TextMatchStyle.toString
                        ))
                    Out(classDyn = select.result match {
                        case Success(list) => {
                            list foreach {
                                case TupleSS16(actionmessageScenarioTrace: Array[String], actionobjectScenarioTrace: Array[String], actiontypeScenarioTrace: Array[String], addressScenarioTrace: String, bonusbaseScenarioTrace: Array[Double], bonusmessageScenarioTrace: Array[String], eventScenarioTrace: String, idScenarioTrace: Long, idactionmessageScenarioTrace: Array[Long], idbonusmessageScenarioTrace: Array[Long], idmarketingmessageScenarioTrace: Array[Long], marketingmessageScenarioTrace: Array[String], parentstageScenarioTrace: Array[String], scenarioScenarioTrace: String, stageScenarioTrace: String, timestampScenarioTrace: LocalDateTime) =>
                                    _data += RecordDyn("id" -> idScenarioTrace, "timestamp" -> timestampScenarioTrace, "address" -> addressScenarioTrace, "event" -> eventScenarioTrace, "scenario" -> scenarioScenarioTrace, "stage" -> stageScenarioTrace, "parentstage" -> parentstageScenarioTrace, "bonusbase" -> bonusbaseScenarioTrace, "idbonusmessage" -> idbonusmessageScenarioTrace, "bonusmessage" -> bonusmessageScenarioTrace, "idmarketingmessage" -> idmarketingmessageScenarioTrace, "marketingmessage" -> marketingmessageScenarioTrace, "actiontype" -> actiontypeScenarioTrace, "idactionmessage" -> idactionmessageScenarioTrace, "actionmessage" -> actionmessageScenarioTrace, "actionobject" -> actionobjectScenarioTrace)
                            }

                            logger debug s"_data: ${newLine + _data.toPrettyString}"

                            new DSResponseDyn {
                                Status = RPCResponseDyn.statusSuccess
                                Data = _data
                                StartRow = requestData.StartRow
                                EndRow = requestData.StartRow.toInt + list.length
                                TotalRows = if (qty == list.length) qty * 2 else list.length
                                UrlExportFile = _urlExportFile
                            }
                        }
                        case Failure(_) =>
                            new DSResponseFailureExDyn(select)
                    })
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

    def wrapperBlobGetter(blob: Blob): String = blob.asString
}
