package com.simplesys.container.FullHandContainer

import java.io.File

import com.simplesys.annotation.RSTransfer
import com.simplesys.app.SessionContextSupport
import com.simplesys.circe.Circe._
import com.simplesys.common.Strings._
import com.simplesys.isc.dataBinging.DSRequest
import com.simplesys.servlet.ServletContext
import com.simplesys.servlet.http.{HttpServletRequest, HttpServletResponse}
import com.simplesys.servlet.isc.{GetData, ServletActor}
import io.circe.generic.auto._
import io.circe.syntax._

import scala.util.{Failure, Success, Try}

@RSTransfer(urlPattern = "/logic/DeleteFile")
class DeleteFileContainer(val request: HttpServletRequest, val response: HttpServletResponse, val servletContext: ServletContext) extends SessionContextSupport with ServletActor {

    val requestData: DSRequest = request.JSONData.as[DSRequest].getOrElse(throw new RuntimeException("Dont parsed Request JSON"))

    def receive = {
        case GetData => {

            logger debug s"request: ${newLine + requestData.asJson.toPrettyString}"

            val transactionNum = if (requestData.transaction.isEmpty) None else requestData.transaction.get.transactionNum

            transactionNum match {
                case None =>
                    Try {
                        val relativePath: Option[String] = servletContext RealPath ""
                        //println(s"relativePath = $relativePath")
                        val file = new File(s"${relativePath.get}/${requestData.asJson.getString("fileName")}")
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
