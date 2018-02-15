// This file is generated automatically (at 03.08.2016 14:48:32), do not spend any changes here, because they will be lost. Generator: "GenBOContainer, stage: #765"

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
import com.simplesys.messages.Message
import com.simplesys.servlet.isc.{GetData, ServletActor}
import io.circe.Json._
import io.circe.generic.auto._
import io.circe.syntax._
import ru.simplesys.defs.bo.scenarios._

import scala.collection.mutable.ArrayBuffer
import scalaz.{Failure, Success}


trait scenarios_TestGroup_SemiHandTrait_Add extends SessionContextSupport with ServletActor {

    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!! DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////
    val requestData: DSRequest = request.JSONData.as[DSRequest].getOrElse(throw new RuntimeException("Dont parsed Request JSON"))

    logger debug s"Request for Update: ${newLine + requestData.asJson.toPrettyString}"

    val dataSet = TestGroupDS(oraclePool)
    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!! END DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////

    def receiveBase: Option[Actor.Receive] = Some(
        {
            case GetData => {
                import com.simplesys.messages.ActorConfig._

                val listResponse = ArrayBuffer.empty[DSResponse]

                val transactionNum = if (requestData.transaction.isEmpty) None else requestData.transaction.get.transactionNum

                logger debug s"request: ${newLine + requestData.asJson.toPrettyString}"

                val insert: ValidationEx[Array[Int]] = transactionNum match {
                    case null => {
                        val data = requestData.data

                        logger debug s"data: ${newLine + data.toPrettyString}"

                        val testGroupData =
                            TestGroup(id = Sequences(oraclePool).nextLong1(dataSet.fromBO.fromTable.databaseTablename),
                                codeGroup = data.getString("codeGroup"),
                                captionGroup = data.getStringOpt("captionGroup"),
                                descriptionGroup = data.getStringOpt("descriptionGroup"),
                                active = data.getBoolean("active"),
                                status = Some(StatusScenario.stoped.value.toLong),
                                parent = data.getLongOpt("parent"))

                        listResponse append
                          DSResponse(
                              status = RPCResponse.statusSuccess,
                              data =
                                obj("id" -> testGroupData.id, "codeGroup" -> testGroupData.codeGroup, "captionGroup" -> testGroupData.captionGroup, "descriptionGroup" -> testGroupData.descriptionGroup, "active" -> testGroupData.active, "status" -> testGroupData.status, "parent" -> testGroupData.parent)
                          )

                        dataSet.insertP(testGroupData)
                    }
                    case _ =>
                        transaction(dataSet.dataSource) {
                            connection =>
                                val values: Seq[TestGroup] =
                                    requestData.transaction.get.operations.map {
                                        case operation: DSRequest => {
                                            val data = operation.data
                                            logger debug (s"data: ${newLine + data.toPrettyString}")

                                            val testGroupData =
                                                TestGroup(id = Sequences(oraclePool).nextLong1(dataSet.fromBO.fromTable.databaseTablename),
                                                    codeGroup = data.getString("codeGroup"),
                                                    captionGroup = data.getStringOpt("captionGroup"),
                                                    descriptionGroup = data.getStringOpt("descriptionGroup"),
                                                    active = data.getBoolean("active"),
                                                    status = Some(StatusScenario.stoped.value.toLong),
                                                    parent = data.getLongOpt("parent"))

                                            listResponse append
                                              DSResponse(
                                                  status = RPCResponse.statusSuccess,
                                                  data =
                                                    obj("id" -> testGroupData.id, "codeGroup" -> testGroupData.codeGroup, "captionGroup" -> testGroupData.captionGroup, "descriptionGroup" -> testGroupData.descriptionGroup, "active" -> testGroupData.active, "status" -> testGroupData.status, "parent" -> testGroupData.parent)
                                              )

                                            SendMessage(Message(channels = s"ListElements_Add_$transactionNum"))

                                            testGroupData
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
}
