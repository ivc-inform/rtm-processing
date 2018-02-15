// This file is generated automatically (at 03.08.2016 14:48:32), do not spend any changes here, because they will be lost. Generator: "GenBOContainer, stage: #765"

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
import com.simplesys.js.components.StatusScenario
import com.simplesys.json.JsonObject
import com.simplesys.messages.Message
import com.simplesys.servlet.GetData
import ru.simplesys.defs.bo.scenarios.{TestGroup, _}

import scalaz.{Failure, Success}


trait scenarios_TestGroup_SemiHandTrait_Add extends SessionContextSupport with ServletActorDyn {

    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!! DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////
    val requestData = new DSRequestDyn(request)

    logger debug s"Request for Add: ${newLine + requestData.toPrettyString}"

    val dataSet = TestGroupDS(ds)
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

                        val testGroupData =
                            TestGroup(id = Sequences(ds).nextLong1(dataSet.fromBO.fromTable.databaseTablename),
                                codeGroup = data.getString("codeGroup"),
                                captionGroup = data.getStringOpt("captionGroup"),
                                descriptionGroup = data.getStringOpt("descriptionGroup"),
                                active = data.getBoolean("active"),
                                status = Some(StatusScenario.stoped.value.toLong),
                                parent = data.getLongOpt("parent"))

                        listResponse append
                          new DSResponseDyn {
                              Status = RPCResponseDyn.statusSuccess
                              Data =
                                RecordDyn("id" -> testGroupData.id, "codeGroup" -> testGroupData.codeGroup, "captionGroup" -> testGroupData.captionGroup, "descriptionGroup" -> testGroupData.descriptionGroup, "active" -> testGroupData.active, "status" -> testGroupData.status, "parent" -> testGroupData.parent)
                          }

                        dataSet.insertP(testGroupData)
                    }
                    case transactionNum =>
                        transaction(dataSet.dataSource) {
                            connection =>
                                _transactionNum = transactionNum
                                val values: Seq[TestGroup] =
                                    requestData.Transaction.Operations.map {
                                        case operation: JsonObject => {
                                            val data = operation.getJsonObjectOpt("data")
                                            logger debug (s"data: ${newLine + data.toPrettyString}")

                                            val testGroupData =
                                                TestGroup(id = Sequences(ds).nextLong1(dataSet.fromBO.fromTable.databaseTablename),
                                                    codeGroup = data.getString("codeGroup"),
                                                    captionGroup = data.getStringOpt("captionGroup"),
                                                    descriptionGroup = data.getStringOpt("descriptionGroup"),
                                                    active = data.getBoolean("active"),
                                                    status = Some(StatusScenario.stoped.value.toLong),
                                                    parent = data.getLongOpt("parent"))

                                            listResponse append
                                              new DSResponseDyn {
                                                  Status = RPCResponseDyn.statusSuccess
                                                  Data =
                                                    RecordDyn("id" -> testGroupData.id, "codeGroup" -> testGroupData.codeGroup, "captionGroup" -> testGroupData.captionGroup, "descriptionGroup" -> testGroupData.descriptionGroup, "active" -> testGroupData.active, "status" -> testGroupData.status, "parent" -> testGroupData.parent)
                                              }

                                            SendMessage(Message(channels = s"ListElements_Add_$transactionNum"))

                                            testGroupData
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
}
