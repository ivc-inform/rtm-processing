package com.simplesys.rmi

import java.util.Properties
import java.util.concurrent.TimeUnit

import com.simplesys.actors.AppConfig._
import com.simplesys.app.ComDIConversion._
import com.simplesys.common.Strings._
import com.simplesys.config.Config
import com.simplesys.log.Logging
import com.simplesys.rtm.common.MessageMFMD
import com.simplesys.rtm.ScenarioCompanion._
import com.simplesys.scopt.SbProcessingTets._
import com.simplesys.scopt.{ProcessingSbtTestConfig, SbProcessingTets, noAction}
import com.telinform.comfmdi.connector.impl.mfmdoutmessage.ComfmdiConnectorMfmdOutMessageServiceImpl
import doobie.imports._
import org.apache.log4j.{Logger, PropertyConfigurator}

import scala.concurrent.duration.Duration

//import com.simplesys.doobie._  // ДОЛЖНО БЫТЬ !!!
import com.simplesys.doobie._

//import scalaz.Scalaz._

object Tests extends App with Config with Logging {

    val cat = Logger.getLogger(this.getClass)

    logger debug ("App EmulatorQueueMessages ...")
    val props = new Properties()

    props.put("log4j.rootLogger", "DEBUG, dummy")
    props.put("log4j.appender.dummy", "org.apache.log4j.ConsoleAppender")
    props.put("log4j.appender.dummy.layout", "org.apache.log4j.PatternLayout")

    PropertyConfigurator configure props

    def start(config: ProcessingSbtTestConfig) {
        try {

            val service = new ComfmdiConnectorMfmdOutMessageServiceImpl(
                cat,
                config.host,
                config.port,
                config.serviceName
            )

            logger debug "Starting services"
            service.open()

            logger debug "Services started"

            val whereClause = config.testIDs.map(_.toString).mkString("(", ",", ")")
            logger debug s"whereClause: $whereClause"

            logger debug "Sending messages"
            val sql =
                config.mode match {
                    case com.simplesys.scopt.runTest =>
                        //runTest -v:true -ids:38

                        s"""select RTM_TEST.id,
                            |DTTIMESTAMP as dTimestamp,
                            |'sb' as sacnCode,
                            |'Abstract-connector' as scnrCode,
                            |'DEBUG' as sSubject,
                            |'russia-beeline-beeline-moskva' as sOurCode,
                            |sAddress,
                            |0 as  nPriority,
                            |sText,
                            |null dStartTime,
                            |null dStopTime,
                            |null sComment,
                            |'('||RTM_TESTGROUP.scodegroup||')' || decode (RTM_TEST.SCODETEST, null, null, '('|| RTM_TEST.SCODETEST || ') ') sTot,
                            |'mfmdTest=(true) mfmdSend=(false)' sOpt,
                            |'debug' sType
                            | from RTM_TEST inner join RTM_TESTGROUP on (RTM_TESTGROUP.ID = RTM_TEST.IDGROUP) where RTM_TESTGROUP.ID in $whereClause and RTM_TEST.bactive = 1 order by DTTIMESTAMP""".stripMargin

                    case com.simplesys.scopt.runTestQueue =>
                        //runTestQueue -v:true -acn:raiff -sd:17.07.2016_12:01:05 -ed:18.07.2016_01:05:55

                        s"""select * from
                            |(select
                            | c.ID,
                            | c.TIMESTAMP  AS dTimestamp,
                            | acn.code AS sacnCode,
                            | cnr.code as  scnrCode,
                            | c.subject AS sSubject,
                            | ort.code AS sOurCode,
                            | c.ADDRESS AS sAddress,
                            | 0 as  nPriority,
                            |    c.TEXT       AS sText,
                            |    null dStartTime,
                            |    null dStopTime,
                            |    null sComment,
                            |    null sTot,
                            |    'mfmdTest=(true) mfmdSend=(false)' AS sOpt,
                            |    cnr.TYPE AS sType
                            |
                          |from
                            |com_data_subrange c,
                            |CONNECTORS_TAB cnr,
                            |ACCOUNTS_TAB  acn,
                            |opu_regions_tab ort
                            |where
                            | acn.code = '${config.acnCode}'
                            | and cnr.acn_id = acn.id
                            | and c.cnr_id = cnr.id
                            | and c.our_id=ort.id
                            | and c.TIMESTAMP between to_date('${localDateTime2Str(config.startDate)}','dd.mm.yyyy hh24:mi:ss') and to_date('${localDateTime2Str(config.endDate)}','dd.mm.yyyy hh24:mi:ss')
                            |
                            | order by c.TIMESTAMP)
                            | ${
                            if (config.lengthQueue > 0)
                                s"where rownum < ${config.lengthQueue}"
                            else ""
                        }""".stripMargin

                    case x ⇒ throw new RuntimeException(s"Bad branch: $x")
                }

            if (config.verbose)
                logger debug sql

            val proc = HC.process[MessageMFMD](sql, HPS.setFetchSize(dsLogger.settings.fetchSize), dsLogger.settings.fetchSize)

            val queue: Vector[MessageMFMD] = proc.transact(dsLoggerTransactor).runLog.run

            var qty = 0
            queue.zipWithIndex.foreach {
                case (message, index) =>
                    if (index != 0) {
                        val t0 = queue(index).timestamp.getMillis - queue(index - 1).timestamp.getMillis
                        val t = Duration(t0, TimeUnit.MILLISECONDS)
                        logger debug s"Time : ${DT(t0)}"
                        Thread sleep t0
                    }
                    //logger debug s"testInfo: ${testInfo.toPrettyString}"
                    logger debug s"${newLine.newLine} ////////////////////////////////////////////////////////////////////////////////////// $newLine Sended message: ${message.toString.newLine}"
                    service processMfmdOutMessage message
                    qty += 1
            }

            logger debug s"($qty) Messages sent"
            sys.exit(0)

        } catch {
            case e: Throwable =>
                e.printStackTrace()
                sys.exit(0)
        }

    }

    SbProcessingTets.parser.parse(args, ProcessingSbtTestConfig()) match {
        case Some(config) =>
            if (config.mode == noAction)
                SbProcessingTets.parser.showUsage()
            else
                start(config)

        case None =>
            SbProcessingTets.parser.showUsage()
    }

}
