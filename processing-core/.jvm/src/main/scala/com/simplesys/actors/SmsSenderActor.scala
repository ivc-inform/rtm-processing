package com.simplesys.actors

import akka.actor.{Actor, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model._
import akka.stream.{ActorMaterializer, ActorMaterializerSettings}
import com.simplesys.akka.event.Logging
import com.simplesys.config.Config
import com.simplesys.http._
import com.simplesys.rtm.scenario.PhoneData

trait SMSBodyTrait {
    val protocol: httpProtocolAbstract
    val text: String
    val isToSend: Boolean
    val phone: PhoneData
}

case class SMSBodyHttp(phone: PhoneData, text: String, isToSend: Boolean)
  extends SMSBodyTrait {
    override val protocol = httpProtocol
}

case class SMSBodyHttps(phone: PhoneData, text: String, isToSend: Boolean)
  extends SMSBodyTrait {
    override val protocol = httpsProtocol
}

case class SMSBatch(msgs: Traversable[SMSBodyTrait])

object SmsSenderActor {
    def props(implicit controlBus: ControlBusImpl): Props = Props(new SmsSenderActor)
}

class SmsSenderActor(implicit val controlBus: ControlBusImpl) extends Actor with Config with Logging {
    final implicit val materializer: ActorMaterializer = ActorMaterializer(ActorMaterializerSettings(context.system))

    private val http = Http(context.system)

    val configPrefix = AppConfig.configPrefix

    val smsLogin = getString(s"$configPrefix.smsSender.login")
    val smsPassword = getString(s"$configPrefix.smsSender.password")
    val smsSubject = getString(s"$configPrefix.smsSender.subject")
    val smsAddress = getString(s"$configPrefix.smsSender.address")
    val passwordParamName = getString(s"$configPrefix.smsSender.passwordParamName")
    val responseTimeout = getDuration(s"$configPrefix.smsSender.responseTimeout")

    import context.dispatcher

    override def preStart(): Unit = {
        super.preStart()
        logger.info(s"${self.path} started")
    }

    override def postStop(): Unit = {
        super.postStop()
        logger.info(s"${self.path} stopped")
    }

    def processSMS(x: SMSBodyTrait): Unit = {

        val params = Seq(
            SenderParam0("login", smsLogin),
            SenderParam0(passwordParamName, smsPassword),
            SenderParam0("subject[0]", smsSubject),
            SenderParam0("address[0]", if (AppConfig.fakeSMSSendMode) AppConfig.fakeSMSSendModePhone else x.phone.address),
            SenderParam("text[0]", x.text)
        )

        val message = MessageURI(protocol = x.protocol, address = smsAddress, params = params)

        import akka.pattern.pipe
        import context.dispatcher

        val uri = Uri(message)
        logger debug s"sms: should we send it: ${x.isToSend.toString},  uri: ${uri.toString()}"

        if (x.isToSend)
            http.singleRequest(HttpRequest(GET, uri = uri)).pipeTo(self)
    }

    override def receive: Receive = {
        case Reaper.ShutdownIt =>
            logger.debug(s"${self.path.name}: shutting down")
            context stop self

        case sms@SMSBodyHttp(ph, messageText, isSimulated) =>
            processSMS(sms)

        case batch@SMSBatch(arr) =>
            arr.foreach(processSMS)

        case HttpResponse(status, headers, entity, protocol) =>
            entity
              .toStrict(responseTimeout)
              .map(_.data.decodeString("UTF-8"))
              .onComplete(res =>
                  logger debug s"sms: status: ${status.value} body: ${res.toString}")

    }
}
