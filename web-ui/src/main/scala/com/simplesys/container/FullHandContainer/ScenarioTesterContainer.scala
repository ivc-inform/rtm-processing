package com.simplesys.container.FullHandContainer

import com.simplesys.actors.{AppConfig, TestStart, TestStop}
import com.simplesys.annotation.RSTransfer
import com.simplesys.app.SessionContextSupport
import com.simplesys.common.Strings._
import com.simplesys.isc.dataBinging.DSRequestDyn
import com.simplesys.isc.system.ServletActorDyn
import com.simplesys.js.components.{StatusScenario, StatusTestScenario}
import com.simplesys.json.JsonBigDecimal
import com.simplesys.scalaZ._
import com.simplesys.servlet.http.{HttpServletRequest, HttpServletResponse}
import com.simplesys.servlet.{GetData, ServletContext}

import scala.util.{Failure, Success, Try}
import scalaz.NonEmptyList

object ScenarioTesterContainer {
    val scenarios_Scr_Scenario_ScenarioTest = "logic/scenarios_Scr_Scenario/ScenarioTest"
}

@RSTransfer(urlPattern = "/logic/scenarios_Scr_Scenario/ScenarioTest")
class ScenarioTesterContainer(val request: HttpServletRequest, val response: HttpServletResponse, val servletContext: ServletContext) extends SessionContextSupport with ServletActorDyn {

    val requestData = new DSRequestDyn(request)

    def receive = {
        case GetData => {

            logger debug s"request: ${newLine + requestData.toPrettyString}"

            requestData.Transaction.TransactionNum match {
                case null =>
                    Try {
                        val data = requestData
                        val idsGroupTest: NonEmptyList[Long] = data.getJsonList("idsGroupTest").map {
                            case JsonBigDecimal(bigDecimal) ⇒ bigDecimal.toLong
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
