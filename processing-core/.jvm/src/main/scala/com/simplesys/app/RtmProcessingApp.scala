package com.simplesys.rtm.app

import java.io.BufferedInputStream
import java.rmi.RemoteException
import java.rmi.registry.LocateRegistry

import akka.actor.ActorSystem
import com.simplesys.actors.AppConfig
import com.simplesys.advisa.SimilarTerminalCache
import com.simplesys.app.RtmProcessingConsumer
import com.simplesys.config.Config
import com.simplesys.log.Logging
import com.simplesys.rtm.common.Helpers
import com.telinform.comfmdi.connector.impl.mfmdoutmessage.ComfmdiConnectorMfmdOutMessageServiceImpl
import com.telinform.comfmdi.mfmd.impl.outmessage.ComfmdiMfmdMfmdOutMessageServiceImpl
import ru.mfms.mfmd.integration.common.SmsMessage
import ru.simplesys.dmprocess.classifier.RootClassifier
import ru.simplesys.dmprocess.templates.{FixedMutableTemplateHolder, FixedTemplate}

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.util.control.Exception._

case class InitData(rmiService: ComfmdiMfmdMfmdOutMessageServiceImpl, testRmiService: ComfmdiMfmdMfmdOutMessageServiceImpl, actorSystem: ActorSystem)
case class InitDataWeb(rmiService: ComfmdiConnectorMfmdOutMessageServiceImpl, prodRmiService: ComfmdiConnectorMfmdOutMessageServiceImpl)

object RtmProcessingApp extends Logging with Config {
    implicit val actorSystem = AppConfig.system
    val configPrefix = "rtm-processing"

    def init(): InitData = {
        logger.info("loading classifier")

        val currentClassifier = RootClassifier("word2vec", 300, 20)
        logger.info("classifier is loaded")

        logger.info("loading dictionaries")
        val swissTool = Helpers.getSwissTool
        logger.info("dictionaries are loaded")

        logger.info("loading mfmd templates")
        val templatesPath = getString(s"$configPrefix.templates.templatesPath")
        logger info s"templatesPath: $templatesPath"
        val exampleCountToStore = getInt(s"$configPrefix.templates.exampleRecordCount")

        val templates = {
            implicit val fixed = FixedTemplate
            val resourceTemplate = getClass.getClassLoader.getResourceAsStream(templatesPath)
            val is = new BufferedInputStream(resourceTemplate)

            ultimately(is.close()) {
                val mfmdTemplater = new FixedMutableTemplateHolder(exampleCountToStore)
                mfmdTemplater.fromXMLInputStream(is, SmsMessage.zFromXML)
                mfmdTemplater.toImmutable(swissTool, None, None)
            }
        }

        logger.info("mfmd templates are loaded")

        logger.info("loading advisa POS cache")
        val advisaTerminalCache = new SimilarTerminalCache()
        logger.info("advisa POS cache is loaded")

        logger.info(s"starting processing actor system ${AppConfig.actorSystemName}")

        // running actors
        AppConfig.initSingletonActors(
            templates,
            swissTool,
            currentClassifier,
            advisaTerminalCache
        )

        // can be switched just to returning ActorRefs from initSingletonActors

        implicit val actorSystemTimeout = 1 minute

        val rmiService: ComfmdiMfmdMfmdOutMessageServiceImpl = try {
            val phoneHolderFuture = actorSystem.actorSelection(AppConfig.actorPathPhoneHolder).resolveOne(actorSystemTimeout)
            val phoneHolder = Await.result(phoneHolderFuture, actorSystemTimeout)

            // can be switched just to returning ActorRefs from initSingletonActors
            logger.info(s"actor system ${AppConfig.actorSystemName} started")

            // running RMI services

            val host = getString(s"$configPrefix.prod.host")
            val port = getInt(s"$configPrefix.prod.port")

            logger info s"Starting RMI registry on $host:$port"
            logger info s"Creating connector RMI registry on :$port"

            LocateRegistry createRegistry port

            logger info s"Connector RMI registry on $port created"

            val service = new ComfmdiMfmdMfmdOutMessageServiceImpl(
                RtmProcessingConsumer(phoneHolder),
                host,
                port,
                getString(s"$configPrefix.prod.serviceName")
            )

            logger info "Starting RMI services impl"
            //AppConfig.isUp.set(true)
            service.open()

            logger info "RMI services impl started"

            service

        } catch {
            case e: RemoteException =>
                logger error e
                throw e
        }

        val testRmiService: ComfmdiMfmdMfmdOutMessageServiceImpl = try {
            val phoneHolderFuture = actorSystem.actorSelection(AppConfig.actorPathTestPhoneHolder).resolveOne(actorSystemTimeout)
            val phoneHolder = Await.result(phoneHolderFuture, actorSystemTimeout)

            val host = getString(s"$configPrefix.test.host")
            val port = getInt(s"$configPrefix.test.port")

            logger info s"Starting RMI registry on $host:$port"
            logger info s"Creating connector RMI registry on :$port"

            LocateRegistry createRegistry port

            logger info s"Connector Test RMI registry on $port created"

            val service = new ComfmdiMfmdMfmdOutMessageServiceImpl(
                RtmProcessingConsumer(phoneHolder),
                host,
                port,
                getString(s"$configPrefix.test.serviceName")
            )

            logger info "Starting Test RMI services impl"
            //AppConfig.isUp.set(true)
            service.open()

            logger info "Test RMI services impl started"

            service

        } catch {
            case e: RemoteException =>
                logger error e
                throw e
        }

        InitData(rmiService, testRmiService, actorSystem)
    }

    def init4Web(): InitDataWeb = {
        val res = AppConfig.initSingletonActorsWeb()
        InitDataWeb(res._1, res._2)
    }

    def destroy(initData: InitData): Unit = {
        sys.addShutdownHook {
            import scala.concurrent.duration._
            logger.info(s"shutting down application")

            AppConfig.isUp.set(false)

            logger.info(s"closing RMI service")
            initData.rmiService.close()
            logger.info(s"closed RMI service")

            logger.info(s"closing Test RMI service")
            initData.testRmiService.close()
            logger.info(s"closed Test RMI service")

            logger.info(s"shutting down actor system ${AppConfig.actorSystemName}")
            val stopResult = AppConfig.shutdownActors()
            Await.result(stopResult, 3 minute)

            initData.actorSystem.terminate()

            Await.result(initData.actorSystem.whenTerminated, 5 minute)
            logger.info(s"actor system ${AppConfig.actorSystemName} is down")

            logger.info(s"closing connection pools")
            logger.info(s"connection pools are closed")
        }
    }

    def destroyWeb(initDataWeb: InitDataWeb): Unit = {
        import scala.concurrent.duration._

        sys.addShutdownHook {
            logger.info(s"closing Test RMI service")
            initDataWeb.rmiService.close()
            logger.info(s"closed Test RMI service")

            logger.info(s"closing Test Prod RMI service")
            initDataWeb.prodRmiService.close()
            logger.info(s"closed Test Prod RMI service")

            val stopResult = AppConfig.shutdownWebActors()
            Await.result(stopResult, 5 minute)
        }
    }

    def main(args: Array[String]): Unit = {
        // running actorSystem
        destroy(init())
        logger.info("App started ...")
    }
}
