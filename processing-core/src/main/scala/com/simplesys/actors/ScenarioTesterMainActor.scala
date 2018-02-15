package com.simplesys.actors

import akka.actor.{Actor, Props}
import com.simplesys.akka.event.Logging
import com.simplesys.akka.http.sse._
import com.simplesys.appCommon
import com.simplesys.common.Strings._
import com.simplesys.js.components.StatusScenario
import com.simplesys.json.JsonObject
import com.simplesys.messages.ActorConfig._
import com.simplesys.messages.Message
import com.simplesys.rtm.ScenarioCompanion.DT
import com.simplesys.rtm.common.MessageMFMD
import com.telinform.comfmdi.connector.impl.mfmdoutmessage.ComfmdiConnectorMfmdOutMessageServiceImpl
import doobie.imports._
import org.joda.time.{DateTime, LocalDateTime}

import scala.collection.mutable.ArrayBuffer
import scalaz.{-\/, NonEmptyList, \/-}

//import com.simplesys.app.ComDIConversion._
import com.simplesys.app.ComDIConversion._

//import com.simplesys.doobie._
import com.simplesys.doobie._

case class TestStart(idsGroupTest: NonEmptyList[Long])
case class TestStop(idsGroupTest: NonEmptyList[Long])

case class GroupItem(idGroupTest: Long, var qty: Int, var break: Boolean = false)

case class Test(
                 id: Long,
                 timestamp: LocalDateTime,
                 createDate: Option[LocalDateTime],
                 codeTest: Option[String],
                 captionTest: Option[String],
                 descriptionTest: Option[String],
                 address: String,
                 text: String,
                 active: Boolean,
                 group: Long)

case class TestGroup(
                      id: Long,
                      codeGroup: String,
                      captionGroup: Option[String],
                      descriptionGroup: Option[String],
                      active: Boolean,
                      status: Long,
                      parent: Option[Long])

object ScenarioTesterMainActor {
    def props(service: ComfmdiConnectorMfmdOutMessageServiceImpl) = Props(new ScenarioTesterMainActor(service))

}

class ScenarioTesterMainActor(val service: ComfmdiConnectorMfmdOutMessageServiceImpl) extends Actor with Logging {
    def updateStatus(status: Int, idGroupTest: Long): Update0 = sql"""UPDATE RTM_TESTGROUP SET NSTATUS = $status WHERE ID = $idGroupTest""".update

    case class GroupItem(idGroupTest: Long, var qty: Int, var break: Boolean = false)

    val groups = ArrayBuffer.empty[GroupItem]

    def selectTestGroup(idGroupTest: Long): Query0[TestGroup] = sql"""SELECT ID, SCODEGROUP, SCAPTIONGROUP, SDESCRIPTIONGROUP, BACTIVE, NSTATUS, IDPARENT  FROM RTM_TESTGROUP WHERE ID = $idGroupTest""".query[TestGroup]

    override def receive: Receive = {
        case Reaper.ShutdownIt =>
            context stop self

        case TestStart(idsGroupTest) ⇒
            //println(s"children: ${context.children.toSeq.length}")
            context.actorOf(ScenarioTesterActor.props(service)) ! TestStart(idsGroupTest)

        case TestStop(idsGroupTest) ⇒
            context.children.foreach(_ ! TestStop(idsGroupTest))
            idsGroupTest.foreach {
                idGroupTest ⇒
                    updateStatus(StatusScenario.stoped.value, idGroupTest).run.transact(AppConfig.dsScenarioTransactor).runAsync {
                        case -\/(e) =>
                            throw e
                        case \/-(a) =>
                            SendMessage(Message(data = JsonObject("idGroupTest" → idGroupTest, "value" → StatusScenario.stoped.value), channels = appCommon.refteshTestGroupGridMessage))
                    }
            }
    }
}

object ScenarioTesterActor {
    def props(service: ComfmdiConnectorMfmdOutMessageServiceImpl) = Props(new ScenarioTesterActor(service))
}

class ScenarioTesterActor(val service: ComfmdiConnectorMfmdOutMessageServiceImpl) extends Actor with Logging {

    val groups = ArrayBuffer.empty[GroupItem]

    def selectTestGroup(idGroupTest: Long): Query0[TestGroup] = sql"""SELECT ID, SCODEGROUP, SCAPTIONGROUP, SDESCRIPTIONGROUP, BACTIVE, NSTATUS, IDPARENT  FROM RTM_TESTGROUP WHERE ID = $idGroupTest""".query[TestGroup]

    def updateStatus(status: Int, idGroupTest: Long): Update0 = sql"""UPDATE RTM_TESTGROUP SET NSTATUS = $status WHERE ID = $idGroupTest""".update

    override def receive: Receive = {
        case TestStart(idsGroupTest) ⇒
            def select: Query0[Test] = {
                val q = fr"""SELECT RTM_TEST.ID,
                                                RTM_TEST.DTTIMESTAMP,
                                                RTM_TEST.DTCREATEDATE,
                                                RTM_TEST.SCODETEST,
                                                RTM_TEST.SCAPTIONTEST,
                                                RTM_TEST.SDESCRIPTIONTEST,
                                                RTM_TEST.SADDRESS,
                                                RTM_TEST.STEXT,
                                                RTM_TEST.BACTIVE,
                                                RTM_TEST.IDGROUP
                                            FROM RTM_TEST
                                            INNER JOIN RTM_TESTGROUP ON (RTM_TESTGROUP.ID = RTM_TEST.IDGROUP)
                                            WHERE RTM_TEST.BACTIVE = '1'
                                             AND """  ++ Fragments.in(fr"RTM_TESTGROUP.ID", idsGroupTest) ++ Fragments.and(fr"ORDER BY RTM_TEST.DTTIMESTAMP ASC")
                  q.query[Test]
            }

            select.list.transact(AppConfig.dsScenarioTransactor).runAsync {
                case -\/(e) =>
                    throw e
                case \/-(list) ⇒
                    try {
                        val queue: Seq[MessageMFMD] = list.map {
                            test ⇒ MessageMFMD(
                                connectorOutMessageId = test.id,
                                timestamp = test.timestamp,
                                accountCode = test.codeTest.getOrElse("None"),
                                connectorCode = test.captionTest.getOrElse("None"),
                                subject = test.descriptionTest.getOrElse("None"),
                                operatorUnitRegionCode = test.active.toString,
                                address = test.address,
                                priority = 0,
                                text = test.text,
                                startTime = Some(DateTime.now().toLocalDateTime),
                                stopTime = None,
                                comment = None,
                                tot = None,
                                opt = None,
                                `type` = "test",
                                idGroupTest = test.group
                            )
                        }

                        queue.zipWithIndex.foreach {
                            case (message, index) =>
                                //logger debug s"message: $message"
                                groups.find(_.idGroupTest == message.idGroupTest) match {
                                    case None ⇒
                                        groups append GroupItem(message.idGroupTest, 1)
                                        updateStatus(StatusScenario.play.value, message.idGroupTest).run.transact(AppConfig.dsScenarioTransactor).runAsync {
                                            case -\/(e) =>
                                                throw e

                                            case \/-(a) =>
                                                SendMessage(Message(data = JsonObject("idGroupTest" → message.idGroupTest, "value" → StatusScenario.play.value), channels = appCommon.refteshTestGroupGridMessage))

                                                selectTestGroup(message.idGroupTest).unique.transact(AppConfig.dsScenarioTransactor).runAsync {
                                                    case -\/(e) =>
                                                        throw e

                                                    case \/-(groupTestRecord) =>
                                                        SendMessage(Message(data = JsonObject(
                                                            "idGroupTest" → message.idGroupTest,
                                                            "value" → StatusScenario.play.value,
                                                            "codeGroup" → groupTestRecord.codeGroup,
                                                            "captionGroup" → groupTestRecord.captionGroup
                                                        ), channels = appCommon.openLogWindowMessage))

                                                        makeStep(index, 1)
                                                }
                                        }
                                    case Some(item) if !item.break ⇒
                                        item.qty += 1
                                        makeStep(index, item.qty)
                                }

                                def makeStep(index: Int, groupIndex: Int): Unit = {
                                    logger info s"!!!!! GroupIndex: $groupIndex !!!!!!!!".append2Channel(appCommon.updateContentLogWindowMessage(message.idGroupTest))
                                    if (index != 0) {
                                        val t0: Long = queue(index).timestamp.toDateTime.getMillis - queue(index - 1).timestamp.toDateTime.getMillis
                                        //val t = Duration(t0, TimeUnit.MILLISECONDS)
                                        logger warning s"Time 0 : ${DT(t0)}".append2Channel(appCommon.updateContentLogWindowMessage(message.idGroupTest))
                                        Thread sleep t0
                                    }
                                    logger debug s"${newLine.newLine} ////////////////////////////////////////////// $newLine Sended message: ${message.toString.newLine}".append2Channel(appCommon.updateContentLogWindowMessage(message.idGroupTest))

                                    service processMfmdOutMessage message
                                    logger info s"!!!!! End GroupIndex: $groupIndex !!!!!!!!".newLine.append2Channel(appCommon.updateContentLogWindowMessage(message.idGroupTest))
                                }
                        }

                        groups.foreach {
                            group ⇒
                                logger info s"(${group.qty}) Messages sent !!!!!!!!".append2Channel(appCommon.updateContentLogWindowMessage(group.idGroupTest))
                                updateStatus(StatusScenario.stoped.value, group.idGroupTest).run.transact(AppConfig.dsScenarioTransactor).runAsync {
                                    case -\/(e) =>
                                        throw e

                                    case \/-(a) =>
                                        SendMessage(Message(data = JsonObject("idGroupTest" → group.idGroupTest, "value" → StatusScenario.stoped.value), channels = appCommon.refteshTestGroupGridMessage))

                                }
                        }

                        context stop self
                    }
                    catch {
                        case e: Throwable ⇒
                            SendMessage(Message(data = JsonObject(
                                "message" → e.getMessage,
                                "stackTrace" → e.getStackTrace().mkString("", newLine, newLine)
                            ), channels = appCommon.errorMessage))

                            context stop self
                    }
            }


        case TestStop(idsGroupTest) ⇒
            idsGroupTest.foreach(id ⇒ groups.map(_.idGroupTest).toSeq.intersect(Seq(idsGroupTest)).foreach(idGroupTest ⇒ groups.find(_.idGroupTest == id).foreach(_.break = true)))
    }
}

