package com.simplesys.container.FullHandContainer

import com.simplesys.annotation.RSTransfer
import com.simplesys.app.SessionContextSupport
import com.simplesys.common.Strings._
import com.simplesys.isc.dataBinging.DSRequestDyn
import com.simplesys.isc.system.ServletActorDyn
import com.simplesys.jdbc.control.classBO.Where
import com.simplesys.servlet.http.{HttpServletRequest, HttpServletResponse}
import com.simplesys.servlet.{GetData, ServletContext}
import ru.simplesys.defs.bo.admin.{User, UserDS}

//import com.simplesys.jdbc._  //!!Должен быть
import com.simplesys.jdbc._  //!!Должен быть
import scala.util.{Failure, Success, Try}


@RSTransfer(urlPattern = "/logic/ChangePassword")
class ChangePasswordContainer(val request: HttpServletRequest, val response: HttpServletResponse, val servletContext: ServletContext) extends SessionContextSupport with ServletActorDyn {

    val requestData = new DSRequestDyn(request)
    val userDS = UserDS(ds)

    def receive = {
        case GetData => {

            logger debug s"request: ${newLine + requestData.toPrettyString}"

            requestData.Transaction.TransactionNum match {
                case null =>
                    Try {
                        val oldPassword = requestData.getString("oldPassword")
                        val password = requestData.getString("password")
                        val confPassword = requestData.getString("confPassword")
                        val userId = requestData.getLong("userId")

                        userDS.selectPOne(where = Where(userDS.diUser === userId)).result match {
                            case scalaz.Success(user) ⇒
                                if (user.passwordUser != oldPassword)
                                    throw new RuntimeException("Несовпадение старого пароля.")

                                if (password != confPassword)
                                    throw new RuntimeException("Несовпадение нового пароля и подтверждения этого пароля.")

                                userDS.updateP(values =
                                  User(di = user.diUser,
                                      login = user.loginUser,
                                      firstName = user.firstNameUser,
                                      secondName = user.secondNameUser,
                                      lastName = user.lastNameUser,
                                      password = password,
                                      active = user.activeUser,
                                      group = user.groupUser), where = Where(userDS.diUser === userId)).result match {
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
                case transactionNum =>
                    OutFailure(new RuntimeException("Неопределенное состояние."))

            }
            selfStop()
        }
        case x =>
            throw new RuntimeException(s"Bad branch $x")
    }
}
