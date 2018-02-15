package com.simplesys.misc.filter

import javax.servlet.annotation.WebFilter

import com.simplesys.akka.http.LoginedData1
import com.simplesys.akka.http.filter.AkkaPartialFilter
import com.simplesys.app.SessionContext
import com.simplesys.common.Strings._
import com.simplesys.common.equality.SimpleEquality._
import com.simplesys.filter.{FailureAuthentication, LoginRequiredChannel, LoginRequiredResponse, SuccesAuthentication}
import com.simplesys.isc.dataBinging.DSRequestDyn._
import com.simplesys.jdbc.control.classBO.Where
import com.simplesys.jdbc.exception.NoDataFoundException
import com.simplesys.messages.ActorConfig._
import com.simplesys.messages.MessageExt
import com.simplesys.servlet.{FilterChain, ServletRequest, ServletResponse}
import com.simplesys.tuple.TupleSS6
import ru.simplesys.defs.bo.admin.{User, UserDS}

import scalaz.{Failure, Success}

@WebFilter(urlPatterns = Array("/logic/*"), asyncSupported = true)
class ReLoginFilter extends AkkaPartialFilter {

    override protected def DoFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {

        val req = request.toHttp
        val resp = response.toHttp

        val session = req.Session
        val sessionContext = new SessionContext(session)

        def getAuth: Unit = {
            getAutentification match {
                case LoginedData1(str, login, id, captionUser, codeGroup) if str == "" =>
                    SuccesAuthentication(req, resp, login, id, captionUser, codeGroup)
                case LoginedData1(str, _, _, _, _) if str != "" =>
                    FailureAuthentication(req, resp, str)
            }
        }

        getKindOperation match {
            case "login" =>
                getAuth
            case "logout" =>
                chain.DoFilter(request, response)
            case anyOperation =>
                logger trace s"!!!!! Request URI: $anyOperation"

                if (!sessionContext.sessionIsValidate) {
                    LoginRequiredResponse(req, resp) //Работает только как заглушка 'транспорта' по requestу
                    SendMessage(MessageExt(channels = LoginRequiredChannel))
                }
                else
                    chain.DoFilter(request, response)
        }

        def getKindOperation: String = {
            val res = req.RequestURI.replaceFirst(req.ContextPath, strEmpty) match {
                case "logic/login" => "login"
                case "logic/logout" => "logout"
                case _ => req.RequestURI
            }
            logger.trace(s"kindOperation: ${res}")
            res
        }

        def getAutentification: LoginedData1 = {

            val requestData = req.toDSRequest
            val login = requestData.getString("login")
            val password = requestData.getString("password")

            logger trace (s"login: ${login}")
            logger trace (s"pasword: ${password}")

            val user = UserDS(sessionContext.getDS)

            user.selectOne(user.diUser ~ user.loginUser ~ user.captionUser ~ user.codeGroupUserGroup_Group ~ user.loginUser ~ user.passwordUser, where = Where(user.loginUser === login) And (user.passwordUser === password)) result match {
                case Success(TupleSS6(userID, loginedUser, captionUser, loginedGroup, _, _)) =>
                    for (_session <- session) {
                        _session.Attribute("userId", Some(userID))
                        _session.Attribute("loginedUser", Some(loginedUser))
                        _session.Attribute("captionUser", Some(captionUser))
                        _session.Attribute("loginedGroup", Some(loginedGroup))
                        _session.Attribute("logged", Some(true))
                    }
                    LoginedData1(strEmpty, login, userID, captionUser, loginedGroup)

                case Failure(e) => e match {
                    case e: NoDataFoundException =>
                        session match {
                            case Some(_session) =>
                                _session RemoveAttribute "userId"
                                _session RemoveAttribute "loginedUser"
                                _session RemoveAttribute "captionUser"
                                _session RemoveAttribute "loginedGroup"
                                _session RemoveAttribute "logged"

                                if (login === "root") {
                                    user.insertP(User(di = 0L, group = 0L, login = "root", firstName = None, secondName = None, lastName = "root", password = "qwerty", active = true)) result match {
                                        case Success(_) =>
                                            for (_session <- session) {
                                                _session.Attribute("userId", Some(0))
                                                _session.Attribute("loginedUser", Some("root"))
                                                _session.Attribute("captionUser", Some("root"))
                                                _session.Attribute("loginedGroup", Some(strEmpty))
                                                _session.Attribute("logged", Some(true))
                                            }
                                            LoginedData1(strEmpty, "root")
                                        case Failure(e) =>
                                            LoginedData1("Аутентификация не прошла :-(")
                                    }
                                } else
                                    user.selectOne(user.diUser ~ user.captionUser ~ user.loginUser, where = Where(user.loginUser === "root")) result match {
                                        case Success(_) =>
                                            LoginedData1("Аутентификация не прошла :-(")
                                        case Failure(e) => e match {
                                            case e: NoDataFoundException =>
                                                LoginedData1("Возможно введение ROOT пользователя. Введите root/xxxxx-password")
                                        }
                                    }
                            case None =>
                                LoginedData1("Not Session exists")
                        }
                    case e: Throwable =>
                        LoginedData1(e.getMessage)
                }
            }
        }
    }
}
