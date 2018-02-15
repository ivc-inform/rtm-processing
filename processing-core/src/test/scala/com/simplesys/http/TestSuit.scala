package com.simplesys.http

import java.net.URLEncoder

import com.simplesys.common.Strings._
import com.simplesys.log.Logging
import org.scalatest.FunSuite

class TestSuite extends FunSuite with Logging {
  test("MessageSender") {
    logger debug newLine + MessageURI(
      protocol = httpProtocol,
      address = "gate10.mfms.ru:XXXX/CLIENT/connectorX/send",
      Seq(SenderParam("login", "CLIENT"),
          SenderParam("password", "P@ssw0rd"),
          SenderParam("subject[0]", "SUBJECT"),
          SenderParam("address[0]", "79031111111"),
          SenderParam("text[0]", "message text"),
          SenderParam("subject[1]", "SUBJECT"),
          SenderParam("address[1]", "79031115551"),
          SenderParam("text[1]", "message text"))
    )
  }

  test("test URLEncoder") {
    logger debug newLine + URLEncoder.encode("message, text", "UTF-8");
  }

  test("MessageResponse") {
    //logger debug (MessageResponse.unapply("ok;1;1002").toString)
    //logger debug (MessageResponse.unapply("ok;1").toString)

    "ok;1;1002" match {
      case MessageResponse(message) =>
        logger.debug(
          s"${message.codeResponse} ${message.yourID} ${message.theirsID}")
    }
  }
}
