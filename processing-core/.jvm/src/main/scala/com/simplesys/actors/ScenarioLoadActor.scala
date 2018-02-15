package com.simplesys.actors

import java.io.File

import akka.actor.{Actor, Props}
import akka.routing.Broadcast
import com.simplesys.actors.Reaper.ShutdownScenario
import com.simplesys.akka.event.Logging
import com.simplesys.appCommon
import com.simplesys.common.Strings._
import com.simplesys.config.Config
import com.simplesys.exception.GenerateException
import com.simplesys.file.{Path, PathSet}
import com.simplesys.io._
import com.simplesys.components._
import com.simplesys.messages.ActorConfig._
import com.simplesys.messages.Message
import com.simplesys.rtm.scenario.{ScenarioCompanion, ScenarioRegistry}
import com.simplesys.scenario4Prod.{AppDef4Prod, ScenarioTempl}
import com.simplesys.scenario4Template.{AppDef4Template, ScenarioTableRecord}
import com.simplesys.util.EvalExt
import doobie.imports._
import io.circe.Json._
import org.scalafmt.Scalafmt
import org.scalafmt.config.ScalafmtConfig
import com.simplesys.appCommon.common._

import scala.collection.mutable
import scala.concurrent.duration._
import scala.util.{Try, _}
import scalaz.{-\/, \/-}

//import com.simplesys.doobie._ // Может понадобиться при нестандартных типах запроса !!!
import com.simplesys.doobie._ // Может понадобиться при нестандартных типах запроса !!!
import scalaz.NonEmptyList

case class Prepare(idsScenario: NonEmptyList[Long], testModeGenerate: Boolean = false, prodTestModeGenerate: Boolean = false)
case class Play(idsScenario: NonEmptyList[Long], testModeGenerate: Boolean = false)
case class SoftStop(idsScenario: NonEmptyList[Long], testModeGenerate: Boolean = false)
case class Stop(idsScenario: NonEmptyList[Long], testModeGenerate: Boolean = false)

//case object LoadReadyScenarios

case class ScenarioTableRecordEx(codeGroup: String, codeScenario: String, json: Option[String], idScenario: Int, status: Int)

object Loader {
    val fmtConfig = ScalafmtConfig.intellij.copy(
        maxColumn = 250,
        danglingParentheses = true,
        bestEffortInDeeplyNestedCode = true
    )
}

trait Loader extends ScenarioRegistry with Config {

    private[this] val eval = new EvalExt()

    def updateStatus(status: Int, idScenario: Long): Update0 = sql"""UPDATE RTM_SCR_SCENARIO SET NSTATUS = $status WHERE IDID_SCENARIO = $idScenario""".update

    val enable4LoadedStatuses: NonEmptyList[Int] = if (testMode) NonEmptyList(StatusScenario.playTest.value) else NonEmptyList(StatusScenario.play.value, StatusScenario.playTestAsProd.value)

    def loadDebugScenarios(): Unit = {

        val debugFilesPath: Path = Path((new File(".")).getAbsolutePath.delLastChar + "processing-core/src/main/scala/com/simplesys/rtm/scenimpl/")

        val debugFiles4Del: PathSet[Path] = debugFilesPath * "*.scala"

        debugFiles4Del.foreach(_.delete(true))

        val pathMode = mutable.HashMap.empty[Path, Int]

        def select: Query0[ScenarioTableRecordEx] = {
            val q =
                fr"""SELECT  RTM_SCR_CMPGN.SCODE_CMPGN,
                              RTM_SCR_SCENARIO.SCODE_SCENARIO,
                              RTM_SCR_SCENARIO.CLBEXECODE,
                              RTM_SCR_SCENARIO.IDID_SCENARIO,
                              RTM_SCR_SCENARIO.NSTATUS
                      FROM RTM_SCR_CMPGN
                      INNER JOIN RTM_SCR_SCENARIO
                      ON (RTM_SCR_CMPGN.IDID_CMPGN = RTM_SCR_SCENARIO.IDID_CMPGN)
                      WHERE RTM_SCR_SCENARIO.BDEBUG_MODE = 1
                       AND """ ++ Fragments.in(fr"RTM_SCR_SCENARIO.NSTATUS", enable4LoadedStatuses)
            q.query[ScenarioTableRecordEx]
        }

        select.list.transact(AppConfig.dsScenarioTransactor).run.foreach {
            record ⇒
                val fileName = s"${ScenarioTempl.getCompanionScenarioName(record.codeScenario.capitalize)}.scala"
                val path: Path = debugFilesPath / fileName
                pathMode += path → record.status
                val file = path.createFile(failIfExists = false).toFile
                record.json.foreach(code ⇒ file <== (_ (code.unQuoted)))
        }

        val debugFiles: PathSet[Path] = debugFilesPath * "*.scala"

        debugFiles.foreach {
            path ⇒
                loadScenarioCompanion(path.toFile) match {
                    case Success(scenarioCompanion) ⇒

                        addScenarioCompanion(scenarioCompanion, " //////////////// DEBUG MODE !!!!!!!!!!!!!!!!!!!!!! /////////////////////////////////////")
                        updateStatus(pathMode(path), scenarioCompanion.scenarioData.scenarioVersion.idScenario).run.transact(AppConfig.dsScenarioTransactor).run

                    case Failure(e) ⇒
                        eval.getErrorsString.foreach(logger.error(_))
                }
        }
    }

    def loadReadyScenarios(): Unit = {

        def select: Query0[ScenarioTableRecordEx] = {
            val q =
                fr"""SELECT  RTM_SCR_CMPGN.SCODE_CMPGN,
                              RTM_SCR_SCENARIO.SCODE_SCENARIO,
                              RTM_SCR_SCENARIO.CLBEXECODE,
                              RTM_SCR_SCENARIO.IDID_SCENARIO,
                              RTM_SCR_SCENARIO.NSTATUS
                      FROM RTM_SCR_CMPGN
                      INNER JOIN RTM_SCR_SCENARIO
                      ON (RTM_SCR_CMPGN.IDID_CMPGN = RTM_SCR_SCENARIO.IDID_CMPGN)
                      WHERE RTM_SCR_SCENARIO.BDEBUG_MODE != 1
                      AND   """ ++ Fragments.in(fr"RTM_SCR_SCENARIO.NSTATUS", enable4LoadedStatuses)
            q.query[ScenarioTableRecordEx]
        }

        select.list.transact(AppConfig.dsScenarioTransactor).run.foreach {
            record ⇒
                record.json.foreach {
                    json ⇒
                        findScenarioCompanion(record.idScenario) match {
                            case None ⇒
                                val code = json.unQuoted
                                //logger debug code
                                val scenarioCompanionName = ScenarioTempl.getCompanionScenarioName(record.codeScenario.capitalize)

                                import scala.util._
                                loadScenarioCompanion(scenarioCompanionName, code) match {
                                    case Success(scenarioCompanion) ⇒
                                        addScenarioCompanion(scenarioCompanion, s"${if (testMode) "in testMode" else ""}")
                                        updateStatus(new com.simplesys.components.Play(StatusScenario.getStatus(record.status).test, StatusScenario.getStatus(record.status).prod).value, record.idScenario).run.transact(AppConfig.dsScenarioTransactor).run

                                    case Failure(e) ⇒
                                        eval.getErrorsString.foreach(logger.error(_))
                                        updateStatus(if (record.status == StatusScenario.play.value) StatusScenario.stoped.value else StatusScenario.stopedTest.value, record.idScenario).run.transact(AppConfig.dsScenarioTransactor).run
                                }

                            case Some(scenarioCompanion) ⇒
                                logger warn s" !!!!!!!!!!!!!!!!!!!!!!! Scenario: ${scenarioCompanion.scenarioData.toString}, already loaded. !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
                        }
                }
        }
    }

    if (getBooleanDefault("app.debugScenario", false))
        loadDebugScenarios()
    loadReadyScenarios()

    def loadScenarioCompanion(scenarioCompanionName: String, code: String): Try[ScenarioCompanion] = {
        Try {
            eval compile code
            val scenarioCompanion: ScenarioCompanion = eval.inPlace[ScenarioCompanion](scenarioCompanionName)
            scenarioCompanion
        }
    }

    def loadScenarioCompanion(scenarioCompanionFile: File): Try[ScenarioCompanion] = {
        Try {
            eval compileFromFiles scenarioCompanionFile
            val scenarioCompanion: ScenarioCompanion = eval.inPlace[ScenarioCompanion](scenarioCompanionFile.onlyFileName)
            scenarioCompanion
        }
    }
}

object ScenarioLoadActor extends Loader {

    override protected lazy val testMode: Boolean = false

    def props = Props(new ScenarioLoadActor {
        override protected val testMode: Boolean = ScenarioLoadActor.testMode
    })
}

object TestScenarioLoadActor extends Loader {

    override protected lazy val testMode: Boolean = true

    def props = Props(new ScenarioLoadActor {
        override protected val testMode: Boolean = TestScenarioLoadActor.testMode
    })
}

abstract class ScenarioLoadActor extends Actor with Config with Logging {

    protected val testMode: Boolean

    def loadScenarioCompanion(scenarioCompanionName: String, code: String)(implicit eval: EvalExt): Try[ScenarioCompanion] = {
        Try {
            eval compile code
            val scenarioCompanion: ScenarioCompanion = eval.inPlace[ScenarioCompanion](scenarioCompanionName)
            scenarioCompanion
        }
    }

    def updateStatus(status: Int, idScenario: Long): Update0 = sql"""UPDATE RTM_SCR_SCENARIO SET NSTATUS = $status WHERE IDID_SCENARIO = $idScenario""".update

    def getScenariosList(idsScenario: NonEmptyList[Long], columnName: String = "JSNJSONSTORAGE_SCENARIO"): List[ScenarioTableRecordEx] = {

        def select: Query0[ScenarioTableRecordEx] = {
            val q = (columnName match {
                case "JSNJSONSTORAGE_SCENARIO" ⇒
                    fr"""SELECT  RTM_SCR_CMPGN.SCODE_CMPGN,
                                  RTM_SCR_SCENARIO.SCODE_SCENARIO,
                                  RTM_SCR_SCENARIO.JSNJSONSTORAGE_SCENARIO,
                                  RTM_SCR_SCENARIO.IDID_SCENARIO,
                                  RTM_SCR_SCENARIO.NSTATUS
                          FROM RTM_SCR_CMPGN
                          INNER JOIN RTM_SCR_SCENARIO
                          ON (RTM_SCR_CMPGN.IDID_CMPGN = RTM_SCR_SCENARIO.IDID_CMPGN)
                          WHERE """

                case "CLBEXECODE" ⇒
                    fr"""SELECT  RTM_SCR_CMPGN.SCODE_CMPGN,
                                  RTM_SCR_SCENARIO.SCODE_SCENARIO,
                                  RTM_SCR_SCENARIO.CLBEXECODE,
                                  RTM_SCR_SCENARIO.IDID_SCENARIO,
                                  RTM_SCR_SCENARIO.NSTATUS
                          FROM RTM_SCR_CMPGN
                          INNER JOIN RTM_SCR_SCENARIO
                          ON (RTM_SCR_CMPGN.IDID_CMPGN = RTM_SCR_SCENARIO.IDID_CMPGN)
                          WHERE """
            }) ++ Fragments.in(fr"RTM_SCR_SCENARIO.IDID_SCENARIO", idsScenario)

            q.query[ScenarioTableRecordEx]
        }

        select.list.transact(AppConfig.dsScenarioTransactor).run
    }

    def prepare(idsScenario: NonEmptyList[Long], testModeGenerate: Boolean = false, prodTestModeGenerate: Boolean = false)(implicit eval: EvalExt): Unit = {

        def updateClobConfig(clobConfigCode: Option[String], idScenario: Int): Update0 = {
            if (clobConfigCode.isDefined)
                fr0"""UPDATE RTM_SCR_SCENARIO SET CLBCLOBCONFIG = ${clobConfigCode.get.dblQuoted} WHERE IDID_SCENARIO = $idScenario""".update
            else
                fr0"""UPDATE RTM_SCR_SCENARIO SET CLBCLOBCONFIG = NULL WHERE IDID_SCENARIO = $idScenario""".update
        }

        def updateClobExe(clobExeCode: Option[String], idScenario: Int): Update0 = {
            if (clobExeCode.isDefined)
                fr0"""UPDATE RTM_SCR_SCENARIO SET CLBEXECODE = ${clobExeCode.get.dblQuoted} WHERE IDID_SCENARIO = $idScenario""".update
            else
                fr0"""UPDATE RTM_SCR_SCENARIO SET CLBEXECODE = NULL WHERE IDID_SCENARIO = $idScenario""".update
        }

        def updateClobConfigError(clobConfigError: Option[String], idScenario: Int): Update0 = sql"""UPDATE RTM_SCR_SCENARIO SET CLBCLOBCONFIGERROR = ${clobConfigError} WHERE IDID_SCENARIO = $idScenario""".update

        def updateClobExeError(clobExeError: Option[String], idScenario: Int): Update0 = sql"""UPDATE RTM_SCR_SCENARIO SET CLBEXECODEERROR = ${clobExeError} WHERE IDID_SCENARIO = $idScenario""".update

        val scenarioTableRecords: List[ScenarioTableRecordEx] = getScenariosList(idsScenario)

        scenarioTableRecords.foreach {
            scenarioTableRecord ⇒
                val scenarioTempls: Seq[Option[ScenarioTempl]] = AppDef4Template.makeScenarioTemplate(List(ScenarioTableRecord(
                    codeGroup = scenarioTableRecord.codeGroup,
                    codeScenario = scenarioTableRecord.codeScenario,
                    json = scenarioTableRecord.json,
                    idScenario = scenarioTableRecord.idScenario
                )), Path("")).map {
                    _ match {
                        case Success(generatedCodeItem) ⇒
                            val code = Scalafmt.format(generatedCodeItem.code, Loader.fmtConfig).get
                            try {
                                eval compile code
                                val scenarioTempl: ScenarioTempl = eval.inPlace[ScenarioTempl](s"new ${generatedCodeItem.codeScenario}")

                                updateClobConfig(Some(code), generatedCodeItem.idScenario).run.transact(AppConfig.dsScenarioTransactor).run
                                updateClobConfigError(None, generatedCodeItem.idScenario).run.transact(AppConfig.dsScenarioTransactor).run
                                Some(scenarioTempl)
                            }
                            catch {
                                case e: Throwable ⇒
                                    updateClobConfig(None, generatedCodeItem.idScenario).run.transact(AppConfig.dsScenarioTransactor).run
                                    updateClobConfigError(eval.getErrorsString, generatedCodeItem.idScenario).run.transact(AppConfig.dsScenarioTransactor).run
                                    updateStatus(StatusScenario.notReady.value, generatedCodeItem.idScenario).run.transact(AppConfig.dsScenarioTransactor).run
                                    None
                            }
                        case Failure(e: GenerateException) ⇒
                            updateStatus(StatusScenario.notReady.value, scenarioTableRecord.idScenario).run.transact(AppConfig.dsScenarioTransactor).runAsync {
                                case -\/(e) =>
                                    throw e
                                case \/-(a) =>
                                    SendMessage(Message(data = obj("error" → fromString(e.message)), channels = refteshScenarioGridMessage))
                            }
                            None

                        case Failure(e) ⇒
                            updateStatus(StatusScenario.notReady.value, scenarioTableRecord.idScenario).run.transact(AppConfig.dsScenarioTransactor).runAsync {
                                case -\/(e) =>
                                    throw e
                                case \/-(a) =>
                                    SendMessage(Message(data = obj("error" → fromString(e.getMessage)), channels = refteshScenarioGridMessage))
                            }
                            None

                    }
                }

                val validScenarioTempls = scenarioTempls.filter(_.isDefined).map(_.get)

                val journalPluginId = getString("akka.persistence.journal-test.plugin")
                logger debug s"journalPluginId: $journalPluginId"

                val snapshotPluginId = getString("akka.persistence.snapshot-store-test.plugin")
                logger debug s"snapshotPluginId: $snapshotPluginId"

                AppDef4Prod.generateScalaScenarioCode(Path(""), validScenarioTempls, "genScenariosImpl", testModeGenerate, prodTestModeGenerate, journalPluginId, snapshotPluginId).foreach {
                    _ match {
                        case Success(generatedCodeItem) ⇒
                            val code = Scalafmt.format(generatedCodeItem.code, Loader.fmtConfig).get

                            loadScenarioCompanion(generatedCodeItem.codeScenario, code) match {
                                case Success(scenarioCompanion) ⇒
                                    updateStatus(new Ready(testModeGenerate, prodTestModeGenerate).value, generatedCodeItem.idScenario).run.transact(AppConfig.dsScenarioTransactor).run
                                    updateClobExe(Some(code), generatedCodeItem.idScenario).run.transact(AppConfig.dsScenarioTransactor).runAsync {
                                        case -\/(e) =>
                                            updateClobExeError(Some(e.getMessage), generatedCodeItem.idScenario).run.transact(AppConfig.dsScenarioTransactor).run
                                            throw e
                                        case \/-(a) =>
                                            updateClobExeError(None, generatedCodeItem.idScenario).run.transact(AppConfig.dsScenarioTransactor).run
                                            SendMessage(Message(refteshScenarioGridMessage))
                                    }

                                case Failure(e: Throwable) ⇒
                                    updateClobExeError(eval.getErrorsString, generatedCodeItem.idScenario).run.transact(AppConfig.dsScenarioTransactor).run
                                    updateClobExe(None, generatedCodeItem.idScenario).run.transact(AppConfig.dsScenarioTransactor).run
                                    updateStatus(StatusScenario.notReady.value, generatedCodeItem.idScenario).run.transact(AppConfig.dsScenarioTransactor).runAsync {
                                        case -\/(e) =>
                                            updateClobExeError(Some(e.getMessage), generatedCodeItem.idScenario).run.transact(AppConfig.dsScenarioTransactor).run
                                            throw e
                                        case \/-(a) =>
                                            eval.getErrorsString.foreach(errorStr ⇒ SendMessage(Message(data = obj("error" → fromString(errorStr)), channels = refteshScenarioGridMessage)))
                                    }
                            }
                        case Failure(e: GenerateException) ⇒
                            updateStatus(StatusScenario.notReady.value, scenarioTableRecord.idScenario).run.transact(AppConfig.dsScenarioTransactor).runAsync {
                                case -\/(e) =>
                                    throw e
                                case \/-(a) =>
                                    SendMessage(Message(data = obj("error" → fromString(e.message)), channels = refteshScenarioGridMessage))
                            }

                        case Failure(e) ⇒
                            updateStatus(StatusScenario.notReady.value, scenarioTableRecord.idScenario).run.transact(AppConfig.dsScenarioTransactor).runAsync {
                                case -\/(e) =>
                                    throw e
                                case \/-(a) =>
                                    SendMessage(Message(data = obj("error" → fromString(e.getMessage)), channels = refteshScenarioGridMessage))
                            }
                    }
                }
        }
        SendMessage(Message(makeOperationMessage))
    }

    def play(idsScenario: NonEmptyList[Long])(implicit eval: EvalExt): Unit = {
        getScenariosList(idsScenario, "CLBEXECODE").foreach {
            record ⇒
                record.json.foreach {
                    json ⇒
                        val code = json.unQuoted
                        val scenarioCompanionName = ScenarioTempl.getCompanionScenarioName(record.codeScenario.capitalize)

                        loadScenarioCompanion(scenarioCompanionName, code) match {
                            case Success(scenarioCompanion) ⇒
                                (StatusScenario isTest record.status.toDouble) match {
                                    case true ⇒
                                        TestScenarioLoadActor addScenarioCompanion(scenarioCompanion, "in testMode")
                                    case false ⇒
                                        ScenarioLoadActor addScenarioCompanion scenarioCompanion
                                }
                                updateStatus(new com.simplesys.components.Play(StatusScenario.getStatus(record.status).test, StatusScenario.getStatus(record.status).prod).value, record.idScenario).run.transact(AppConfig.dsScenarioTransactor).runAsync {
                                    case -\/(e) =>
                                        throw e
                                    case \/-(a) =>
                                        SendMessage(Message(channels = refteshScenarioGridMessage))
                                }
                            case Failure(e) ⇒
                                updateStatus(new NotReady(StatusScenario.getStatus(record.status).test, StatusScenario.getStatus(record.status).prod).value, record.idScenario).run.transact(AppConfig.dsScenarioTransactor).run
                                eval.getErrorsString.foreach(errorStr ⇒ SendMessage(Message(data = obj("error" → fromString(errorStr)), channels = refteshScenarioGridMessage)))
                        }
                }
        }

        SendMessage(Message(makeOperationMessage))
    }

    def softStop(idsScenario: NonEmptyList[Long]): Unit = {
        getScenariosList(idsScenario, "CLBEXECODE").foreach {
            record ⇒
                if ((StatusScenario isTest record.status.toDouble) match {
                    case true ⇒
                        TestScenarioLoadActor removeScenarioCompanion record.idScenario
                    case false ⇒
                        ScenarioLoadActor removeScenarioCompanion record.idScenario
                }) {
                    updateStatus(new Stoping(StatusScenario.getStatus(record.status).test, StatusScenario.getStatus(record.status).prod).value, record.idScenario).run.transact(AppConfig.dsScenarioTransactor).runAsync {
                        case -\/(e) =>
                            throw e
                        case \/-(a) =>
                            SendMessage(Message(channels = refteshScenarioGridMessage))
                    }
                }
        }
        SendMessage(Message(makeOperationMessage))
    }

    def hardStop(idsScenario: NonEmptyList[Long]): Unit = {
        implicit val actorSystemTimeout = 1 minute

        val phoneHolder = AppConfig.system.actorSelection(AppConfig.actorPathPhoneHolder)
        val testPhoneHolder = AppConfig.system.actorSelection(AppConfig.actorPathTestPhoneHolder)

        getScenariosList(idsScenario, "CLBEXECODE").foreach {
            record ⇒
                (StatusScenario isTest record.status.toDouble) match {
                    case true ⇒
                        if (TestScenarioLoadActor removeScenarioCompanion record.idScenario)
                            testPhoneHolder ! Broadcast(ShutdownScenario(record.idScenario, record.status))

                    case false ⇒
                        if (ScenarioLoadActor removeScenarioCompanion record.idScenario)
                            phoneHolder ! Broadcast(ShutdownScenario(record.idScenario, record.status))

                }
        }
        SendMessage(Message(makeOperationMessage))
    }

    override def receive: Receive = {
        case Reaper.ShutdownIt =>
            context stop self

        case Prepare(idsScenario, testModeGenerate, prodTestModeGenerate) ⇒
            implicit val eval = new EvalExt()
            prepare(idsScenario, testModeGenerate, prodTestModeGenerate)

        case Play(idsScenario, _) ⇒
            implicit val eval = new EvalExt()
            play(idsScenario)

        case SoftStop(idsScenario, _) ⇒
            softStop(idsScenario)

        case Stop(idsScenario, _) ⇒
            hardStop(idsScenario)

        //case LoadReadyScenarios ⇒

        case ShutdownScenario(idScenario, status) ⇒
            updateStatus(new Stoped(StatusScenario.getStatus(status).test, StatusScenario.getStatus(status).prod).value, idScenario).run.transact(AppConfig.dsScenarioTransactor).runAsync {
                case -\/(e) =>
                    throw e
                case \/-(a) =>
                    SendMessage(Message(channels = refteshScenarioGridMessage))
            }

        case x ⇒
            logger error "Unknown: $x"

    }

}
