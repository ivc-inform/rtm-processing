package com.simplesys.container.FullHandContainer

import com.simplesys.annotation.RSTransfer
import com.simplesys.app.SessionContextSupport
import com.simplesys.circe.Circe._
import com.simplesys.common.Strings._
import com.simplesys.isc.dataBinging.DSRequest
import com.simplesys.jdbc.control.classBO.Where
import com.simplesys.servlet.ServletContext
import com.simplesys.servlet.http.{HttpServletRequest, HttpServletResponse}
import com.simplesys.servlet.isc.{GetData, ServletActor}
import io.circe.generic.auto._
import io.circe.syntax._
import ru.simplesys.defs.bo.admin.{User, UserDS}
import com.simplesys.jdbc.JDBC._


//import com.simplesys.jdbc._  //!!Должен быть
import com.simplesys.jdbc._  //!!Должен быть
import scala.util.{Failure, Success, Try}


@RSTransfer(urlPattern = "/logic/ChangePassword")
class ChangePasswordContainer(val request: HttpServletRequest, val response: HttpServletResponse, val servletContext: ServletContext) extends SessionContextSupport with ServletActor {

    val requestData: DSRequest = request.JSONData.as[DSRequest].getOrElse(throw new RuntimeException("Dont parsed Request JSON"))
    val userDS = UserDS(oraclePool)

    def receive = {
        case GetData => {

            logger debug s"request: ${newLine + requestData.asJson.toPrettyString}"
            val transactionNum = if (requestData.transaction.isEmpty) None else requestData.transaction.get.transactionNum

            transactionNum match {
                case None =>
                    Try {
                        val oldPassword = requestData.asJson.getString("oldPassword")
                        val password = requestData.asJson.getString("password")
                        val confPassword = requestData.asJson.getString("confPassword")
                        val userId = requestData.asJson.getLong("userId")

                        userDS.selectPOne(where = Where(userDS.diUser === userId)).result match {
                            case scalaz.Success(user) ⇒
                                if (user.passwordUser != oldPassword)
                                    throw new RuntimeException("Несовпадение старого пароля.")

                                if (password != confPassword)
                                    throw new RuntimeException("Несовпадение нового пароля и подтверждения этого пароля.")

                                userDS.updateP(values =
                                  new User(di = user.diUser,
                                      login = user.loginUser,
                                      firstName = user.firstNameUser,
                                      secondName = user.secondNameUser,
                                      lastName = user.lastNameUser,
                                      password = password,
                                      active = user.activeUser,
                                      group = user.groupUser.headOption), where = Where(userDS.diUser === userId)).result match {
                                    case scalaz.Success(qty) ⇒
                                        qty
                                    case scalaz.Failure(e) ⇒
                                        throw e
                                }
                            case scalaz.Failure(e) ⇒
                                throw e
                        }
                    } match {
                        case Success(_) ⇒
                            OutOk

                        case Failure(e) ⇒
                            OutFailure(e)

                    }
                case _ =>
                    OutFailure(new RuntimeException("Неопределенное состояние."))

            }
            selfStop()
        }
        case x =>
            throw new RuntimeException(s"Bad branch $x")
    }
}
