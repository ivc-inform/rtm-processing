package com.simplesys.container.FullHandContainer

import com.simplesys.actors.{AppConfig, TestStart, TestStop}
import com.simplesys.annotation.RSTransfer
import com.simplesys.app.SessionContextSupport
import com.simplesys.circe.Circe._
import com.simplesys.common.Strings._
import com.simplesys.components.{StatusScenario, StatusTestScenario}
import com.simplesys.isc.dataBinging.DSRequest
import com.simplesys.servlet.ServletContext
import com.simplesys.servlet.http.{HttpServletRequest, HttpServletResponse}
import com.simplesys.servlet.isc.{GetData, ServletActor}
import io.circe.generic.auto._
import io.circe.syntax._

import scala.util.{Failure, Success, Try}
import scalaz.NonEmptyList
import com.simplesys.scalaZ._

object ScenarioTesterContainer {
    val scenarios_Scr_Scenario_ScenarioTest = "logic/scenarios_Scr_Scenario/ScenarioTest"
}

@RSTransfer(urlPattern = "/logic/scenarios_Scr_Scenario/ScenarioTest")
class ScenarioTesterContainer(val request: HttpServletRequest, val response: HttpServletResponse, val servletContext: ServletContext) extends SessionContextSupport with ServletActor {

    val requestData: DSRequest = request.JSONData.as[DSRequest].getOrElse(throw new RuntimeException("Dont parsed Request JSON"))

    def receive = {
        case GetData => {

            logger debug s"request: ${newLine + requestData.asJson.toPrettyString}"

            val transactionNum = if (requestData.transaction.isEmpty) None else requestData.transaction.get.transactionNum

            transactionNum match {
                case None =>
                    Try {
                        val data = requestData.data
                        val idsGroupTest: NonEmptyList[Long] = data.getJsonList("idsGroupTest").map {
                            case bigDecimal if bigDecimal.isNumber & bigDecimal.asNumber.isDefined & bigDecimal.asNumber.get.toLong.isDefined ⇒
                                bigDecimal.asNumber.get.toLong.get
                            case x ⇒ throw new RuntimeException(s"Bad branch: $x")
                        }.toNonEmptyList

                        val mode = data.getInt("mode")

                        mode match {
                            case x if x == StatusTestScenario.play.value ⇒
                                AppConfig.actorScenarioTesterHolder ! TestStart(idsGroupTest)
                            case x if x == StatusTestScenario.playAsProd.value ⇒
                                AppConfig.actorScenarioProdTesterHolder ! TestStart(idsGroupTest)
                            case x if x == StatusScenario.stoped.value ⇒
                                AppConfig.actorScenarioTesterHolder ! TestStop(idsGroupTest)
                                AppConfig.actorScenarioProdTesterHolder ! TestStop(idsGroupTest)
                            case x ⇒
                                logger error s"Не определенное состояние $x"
                        }
                    } match {
                        case Success(_) ⇒
                            OutOk
                        case Failure(e) ⇒
                            OutFailure(e)
                    }
                case transactionNum =>
                    OutFailure(new RuntimeException("Неопределенное состояние."))

            }
            selfStop()
        }
        case x =>
            throw new RuntimeException(s"Bad branch $x")
    }
}
