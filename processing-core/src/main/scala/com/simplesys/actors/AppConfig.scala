package com.simplesys.actors

import java.rmi.RemoteException
import java.util.Properties
import java.util.concurrent.atomic.AtomicBoolean

import akka.actor.ActorSystem
import akka.routing.{Broadcast, FromConfig}
import com.simplesys.advisa.SimilarTerminalCache
import com.simplesys.bonecp.BoneCPDataSource
import com.simplesys.config.Config
import com.simplesys.connectionStack.BoneCPStack
import com.simplesys.log.Logging
import com.simplesys.logging.LogScenarioActor
import com.telinform.comfmdi.connector.impl.mfmdoutmessage.ComfmdiConnectorMfmdOutMessageServiceImpl
import org.apache.log4j.{Logger, PropertyConfigurator}
import ru.simplesys.dmprocess.classifier.{ClassifierModel, TextType}
import ru.simplesys.dmprocess.templates.{FixedImmutableTemplateHolder, WordSwissTool}

import scala.collection.Traversable
import scala.concurrent.Future

//q8jC3CnOE3Zak
object AppConfig extends Config with Logging {

    def buildSimplePath(actorName: String): String = s"/${commonPrefix}/${actorName}"

    val commonPrefix: String = "user"

    logger trace ("AppConfig starting ...")

    val configPrefix = "rtm-processing"
    val scenariosConfigPrefix = s"${AppConfig.configPrefix}.scenarios"

    val fakeSMSSendMode = getBoolean(s"$configPrefix.fakeSMSSendMode")
    val fakeSMSSendModePhone = getString(s"$configPrefix.fakeSMSSendModePhone")

    val actorSystemName = getString(s"$configPrefix.common.actor-system-name")
    implicit val system: ActorSystem = ActorSystem(actorSystemName)

    val cpStack: BoneCPStack = BoneCPStack()

    val dsAdvisa: BoneCPDataSource = getString("dbPool.defaultAdvisa") match {
        case x@"oracleMFMSAdvisa" => cpStack OracleDataSource x
        case any => throw new RuntimeException(s"Bad: ${any}")
    }

    val dsLogger: BoneCPDataSource = getString("dbPool.defaultLogging") match {
        case x@"oracleMFMSLogging" => cpStack OracleDataSource x
        case any => throw new RuntimeException(s"Bad: ${any}")
    }

    val dsScenario: BoneCPDataSource = getString("dbPool.defaultScenario") match {
        case x@"oracleMFMSScenario" => cpStack OracleDataSource x
        case any => throw new RuntimeException(s"Bad: ${any}")
    }

    logger debug s"readTimeout: ${dsLogger.Config.ReadTimeout}"

    val dsAdvisaTransactor = dsAdvisa.DoobieDataSourceTransactor
    val dsLoggerTransactor = dsLogger.DoobieDataSourceTransactor
    val dsScenarioTransactor = dsScenario.DoobieDataSourceTransactor

    val actorNameSMSSender = "smsSender"
    val actorNamePhoneHolder = "phoneHolder"
    val actorNameTestPhoneHolder = "testPhoneHolder"
    val actorNameScenarioTrace = "scenarioTrace"
    val actorNameRunnings = "runnings"
    val actorNameTestRunnings = "testRunnings"
    val actorNameScenarioHolder = "scenarioHolder"
    val actorNameScenarioTesterHolder = "scenarioTesterHolder"
    val actorNameScenarioProdTesterHolder = "scenarioProdTesterHolder"

    val actorPathSmsSender = buildSimplePath(actorNameSMSSender)
    val actorPathPhoneHolder = buildSimplePath(actorNamePhoneHolder)
    val actorPathTestPhoneHolder = buildSimplePath(actorNameTestPhoneHolder)
    val actorPathScenarioTrace = buildSimplePath(actorNameScenarioTrace)
    val actorPathRunnings = buildSimplePath(actorNameRunnings)
    val actorPathTestRunnings = buildSimplePath(actorNameTestRunnings)
    val actorScenario = buildSimplePath(actorNameScenarioHolder)
    val actorScenarioTester = buildSimplePath(actorNameScenarioTesterHolder)
    val actorScenarioProdTester = buildSimplePath(actorNameScenarioProdTesterHolder)

    val actorSelSmsSender = system.actorSelection(actorPathSmsSender)
    val actorSelBusinessLogger = system.actorSelection(AppConfig.actorPathScenarioTrace)
    val actorSelRunnings = system.actorSelection(AppConfig.actorPathRunnings)
    val actorSelTestRunnings = system.actorSelection(AppConfig.actorPathTestRunnings)
    val actorScenarioHolder = system.actorSelection(AppConfig.actorScenario)
    val actorScenarioTesterHolder = system.actorSelection(AppConfig.actorScenarioTester)
    val actorScenarioProdTesterHolder = system.actorSelection(AppConfig.actorScenarioProdTester)

    val isUp = new AtomicBoolean(false)

    implicit val controlBus: ControlBusImpl = new ControlBusImpl()

    def initSingletonActors(
                             templateHolder: FixedImmutableTemplateHolder,
                             wordSwissTool: WordSwissTool,
                             classifierTool: ClassifierModel[Traversable[String], TextType],
                             advisaPOSCache: SimilarTerminalCache)(implicit system: ActorSystem): Unit = {

        system.actorOf(SmsSenderActor.props, actorNameSMSSender)
        system.actorOf(LogScenarioActor.props, actorNameScenarioTrace)

        val phoneRouter = system.actorOf(FromConfig.props(
            PhoneHolderActor.props(
                templateHolder,
                wordSwissTool,
                classifierTool,
                advisaPOSCache,
                ScenarioLoadActor
            )),
            actorNamePhoneHolder)

        system.actorOf(RunningsHolderActor.props(phoneRouter, false), actorNameRunnings)

        val testPhoneRouter = system.actorOf(FromConfig.props(
            PhoneHolderActor.props(
                templateHolder,
                wordSwissTool,
                classifierTool,
                advisaPOSCache,
                TestScenarioLoadActor
            )),
            actorNameTestPhoneHolder)

        system.actorOf(RunningsHolderActor.props(testPhoneRouter, true), actorNameTestRunnings)

        system.actorOf(ScenarioLoadActor.props, actorNameScenarioHolder)
    }

    def initSingletonActorsWeb()(implicit system: ActorSystem): (ComfmdiConnectorMfmdOutMessageServiceImpl, ComfmdiConnectorMfmdOutMessageServiceImpl) = {
        val cat = Logger.getLogger(this.getClass)

        val props = new Properties()

        props.put("log4j.rootLogger", "DEBUG, dummy")
        props.put("log4j.appender.dummy", "org.apache.log4j.ConsoleAppender")
        props.put("log4j.appender.dummy.layout", "org.apache.log4j.PatternLayout")

        PropertyConfigurator configure props

        val service = try {
            val port = getInt(s"$configPrefix.test.port")

            val service = new ComfmdiConnectorMfmdOutMessageServiceImpl(
                cat,
                getString(s"$configPrefix.test.host"),
                port,
                getString(s"$configPrefix.test.serviceName")
            )

            logger info s"Connector TEST RMI registry on $port created"
            service.open()
            logger info "TEST RMI services impl started"

            system.actorOf(ScenarioTesterMainActor.props(service), actorNameScenarioTesterHolder)
            service
        } catch {
            case e: RemoteException =>
                logger error e
                throw e
        }

        val serviceProd = try {
            val port = getInt(s"$configPrefix.prod.port")

            val service = new ComfmdiConnectorMfmdOutMessageServiceImpl(
                cat,
                getString(s"$configPrefix.prod.host"),
                port,
                getString(s"$configPrefix.prod.serviceName")
            )

            logger info s"Connector TEST PROD RMI registry on $port created"
            service.open()
            logger info "TEST PROD RMI services impl started"

            system.actorOf(ScenarioTesterMainActor.props(service), actorNameScenarioProdTesterHolder)
            service
        } catch {
            case e: RemoteException =>
                logger error e
                throw e
        }

        (service, serviceProd)
    }

    def initSingletonActorsDebug(): Unit = {
        system.actorOf(SmsSenderActor.props, actorNameSMSSender)
        system.actorOf(LogScenarioActor.props, actorNameScenarioTrace)
    }

    def shutdownActors(): Future[Boolean] = {
        import akka.pattern.gracefulStop
        import system.dispatcher

        import scala.concurrent.duration._

        val shutdownTimer = 2 minute
        val acPhFut = system.actorSelection(AppConfig.actorPathPhoneHolder).resolveOne(shutdownTimer)
        val acTestPhFut = system.actorSelection(AppConfig.actorPathTestPhoneHolder).resolveOne(shutdownTimer)
        val scLogFut = system.actorSelection(AppConfig.actorPathScenarioTrace).resolveOne(shutdownTimer)
        val smsSendFut = system.actorSelection(AppConfig.actorPathSmsSender).resolveOne(shutdownTimer)
        val runningsFut = system.actorSelection(AppConfig.actorPathRunnings).resolveOne(shutdownTimer)
        val testRunningsFut = system.actorSelection(AppConfig.actorPathTestRunnings).resolveOne(shutdownTimer)
        val scenarioFut = system.actorSelection(AppConfig.actorScenario).resolveOne(shutdownTimer)

        val stopResult = for (acPh <- acPhFut;
                              acTestPh <- acTestPhFut;
                              scLog <- scLogFut;
                              smsSend <- smsSendFut;
                              runnings <- runningsFut;
                              testRunnings <- testRunningsFut;
                              scenarios <- scenarioFut;
                              phStop <- gracefulStop(acPh, shutdownTimer, Broadcast(Reaper.ShutdownIt));
                              testPhStop <- gracefulStop(acTestPh, shutdownTimer, Broadcast(Reaper.ShutdownIt));
                              depsStop <- {
                                  val x = gracefulStop(scLog, shutdownTimer, Reaper.ShutdownIt)
                                  val y = gracefulStop(smsSend, shutdownTimer, Reaper.ShutdownIt)
                                  val z = gracefulStop(runnings, shutdownTimer, Reaper.ShutdownIt)
                                  val zt = gracefulStop(testRunnings, shutdownTimer, Reaper.ShutdownIt)
                                  val k = gracefulStop(scenarios, shutdownTimer, Reaper.ShutdownIt)
                                  for (xx <- x; yy <- y; zz <- z; zzt <- zt; kk ← k)
                                      yield xx && yy && zz && zzt && kk
                              }) yield phStop && testPhStop && depsStop

        stopResult
    }

    def shutdownWebActors(): Future[(Boolean, Boolean)] = {
        import akka.pattern.gracefulStop
        import system.dispatcher

        import scala.concurrent.duration._

        val shutdownTimer = 2 minute
        val scenarioTesterFut = system.actorSelection(AppConfig.actorScenarioTester).resolveOne(shutdownTimer)
        val scenarioProdTesterFut = system.actorSelection(AppConfig.actorScenarioProdTester).resolveOne(shutdownTimer)

        val stopResult: Future[(Boolean, Boolean)] = for (
            scenariosTester <- scenarioTesterFut;
            scenariosProdTester <- scenarioProdTesterFut;
            depsStop <- {
                val k1 = gracefulStop(scenariosTester, shutdownTimer, Reaper.ShutdownIt)
                val k2 = gracefulStop(scenariosProdTester, shutdownTimer, Reaper.ShutdownIt)
                for (kk1 ← k1; kk2 ← k2)
                    yield (kk1, kk2)
            }) yield depsStop

        stopResult
    }

    //logger trace ("AppConfig started ...")
}
