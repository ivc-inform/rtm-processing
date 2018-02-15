package com.simplesys.container.FullHandContainer

import com.simplesys.actors._
import com.simplesys.annotation.RSTransfer
import com.simplesys.app.SessionContextSupport
import com.simplesys.common.Strings._
import com.simplesys.isc.dataBinging.DSRequestDyn
import com.simplesys.isc.system.ServletActorDyn
import com.simplesys.js.components.StatusScenario
import com.simplesys.servlet.http.{HttpServletRequest, HttpServletResponse}
import com.simplesys.servlet.{GetData, ServletContext}

import scala.util.{Failure, Success, Try}
import scalaz.NonEmptyList

object ScenarioUpdateStatusContainer {
    val scenarios_Scr_Scenario_UpdateStatus = "logic/scenarios_Scr_Scenario/UpdateStatus"
}

@RSTransfer(urlPattern = "/logic/scenarios_Scr_Scenario/UpdateStatus")
class ScenarioUpdateStatusContainer(val request: HttpServletRequest, val response: HttpServletResponse, val servletContext: ServletContext) extends SessionContextSupport with ServletActorDyn {

    val requestData = new DSRequestDyn(request)

    def receive = {
        case GetData => {

            logger debug s"request: ${newLine + requestData.toPrettyString}"

            requestData.Transaction.TransactionNum match {
                case null => Try {
                    val data = requestData

                    val id_scenario = data.getLong("id_scenario")
                    val mode = data.getString("mode")
                    val scenarioTestMode = data.getBoolean("scenarioTestMode")
                    val scenarioProdTestMode = data.getBoolean("scenarioProdTestMode")

                    mode match {
                        case x if x == StatusScenario.ready.mode ⇒
                            AppConfig.actorScenarioHolder ! Prepare(NonEmptyList(id_scenario), scenarioTestMode, scenarioProdTestMode)
                        case x if x == StatusScenario.play.mode ⇒
                            AppConfig.actorScenarioHolder ! Play(NonEmptyList(id_scenario), scenarioTestMode)
                        case x if x == StatusScenario.stoped.mode ⇒
                            AppConfig.actorScenarioHolder ! Stop(NonEmptyList(id_scenario), scenarioTestMode)
                        case x if x == StatusScenario.stoping.mode ⇒
                            AppConfig.actorScenarioHolder ! SoftStop(NonEmptyList(id_scenario), scenarioTestMode)
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
