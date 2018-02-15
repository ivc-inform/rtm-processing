package com.simplesys.scopt

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import scopt.OptionParser

abstract sealed trait ModeTestRun

case object noAction extends ModeTestRun
case object runTest extends ModeTestRun
case object runTestQueue extends ModeTestRun

case class ProcessingSbtTestConfig(
    mode: ModeTestRun = noAction,
    testIDs: Seq[Int] = Seq.empty,
    startDate: DateTime = DateTime.now(),
    endDate: DateTime = DateTime.now(),
    lengthQueue: Int = -1,
    verbose: Boolean = false,
    serviceName: String = "ComfmdiMfmdMfmdOutMessageService",
    host: String = "127.0.0.1",
    port: Int = 40000,
    acnCode: String = ""
)

object SbProcessingTets {
  val fmt = DateTimeFormat.forPattern("dd.MM.YYYY HH:mm:ss")
  val shortFmt = DateTimeFormat.forPattern("dd.MM.YYYY")

  def strToDate(s: String): DateTime = {
    try {
      fmt.parseDateTime(s)
    } catch {
      case e: IllegalArgumentException =>
        shortFmt.parseDateTime(s)
    }

  }

  def dateToStr(dt: DateTime): String = dt.toString(fmt)

  implicit val dateRead: scopt.Read[DateTime] = scopt.Read.reads(strToDate)

  val parser = new OptionParser[ProcessingSbtTestConfig]("sb-procesing-test") {
    head("sb-procesing-test", "1.2.x")

    override val showUsageOnError = true

    def verbose =
      opt[Boolean]('v', "verbose")
        .action((x, c) => c.copy(verbose = x))
        .text("Расширенный режим сообщений.")

    def host =
      opt[String]('h', "host")
        .action((x, c) => c.copy(host = x))
        .text("Host RMI [0.0.0.0]")

    def port =
      opt[Int]('p', "port")
        .action((x, c) => c.copy(port = x))
        .text("Port RMI [40000]")

    def serviceName =
      opt[String]("serviceName")
        .abbr("svnm")
        .action((x, c) => c.copy(serviceName = x))
        .text("Service Name RMI [ComfmdiMfmdMfmdOutMessageService]")

    cmd("runTest")
      .action((_, c) => c.copy(mode = runTest))
      .text("Запуск тестов из определенных групп.")
      .children(
        opt[Seq[Int]]("testIDs")
          .abbr("ids")
          .required
          .action((x, c) => c.copy(testIDs = x))
          .text("testIds: IDs: Групп тестов"),
        verbose,
        host,
        port,
        serviceName
      )

    val strFmt = "\"dd.MM.YYYY [HH:mm:ss]\" | dd.MM.YYYY"
    cmd("runTestQueue")
      .action((_, c) => c.copy(mode = runTestQueue))
      .text("Запуск эмулятора очереди сообщений.")
      .children(
        opt[Int]('l', "lengthQueue")
          .action((x, c) => c.copy(lengthQueue = x))
          .text("Длина очереди сообщений [Inf]"),
        opt[String]("acnCode")
          .required()
          .abbr("acn")
          .action((x, c) => c.copy(acnCode = x))
          .text("Код клиента"),
        opt[DateTime]("startDate")
          .abbr("sd")
          .action((x, c) => c.copy(startDate = x))
          .text(s"Дата начала выборки для очереди сообщений [Now] ($strFmt)"),
        opt[DateTime]("endDate")
          .abbr("ed")
          .action((x, c) => c.copy(endDate = x))
          .text(
            s"Дата окончания выборки для очереди сообщений [Now] ($strFmt)"),
        verbose,
        host,
        port,
        serviceName
      )
  }

}
