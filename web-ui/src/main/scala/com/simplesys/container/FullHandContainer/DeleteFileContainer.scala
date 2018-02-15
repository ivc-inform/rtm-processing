package com.simplesys.container.FullHandContainer

import java.io.File

import com.simplesys.annotation.RSTransfer
import com.simplesys.app.SessionContextSupport
import com.simplesys.common.Strings._
import com.simplesys.isc.dataBinging.DSRequestDyn
import com.simplesys.isc.system.ServletActorDyn
import com.simplesys.servlet.http.{HttpServletRequest, HttpServletResponse}
import com.simplesys.servlet.{GetData, ServletContext}

import scala.util.{Failure, Success, Try}

@RSTransfer(urlPattern = "/logic/DeleteFile")
class DeleteFileContainer(val request: HttpServletRequest, val response: HttpServletResponse, val servletContext: ServletContext) extends SessionContextSupport with ServletActorDyn {

    val requestData = new DSRequestDyn(request)

    def receive = {
        case GetData => {

            logger debug s"request: ${newLine + requestData.toPrettyString}"

            requestData.Transaction.TransactionNum match {
                case null =>
                    Try {
                        val relativePath: Option[String] = servletContext RealPath ""
                        //println(s"relativePath = $relativePath")
                        val file = new File(s"${relativePath.get}/${requestData.getString("fileName")}")
                        file.delete()

                        OutOk

                    } match {
                        case Success(out) ⇒

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
