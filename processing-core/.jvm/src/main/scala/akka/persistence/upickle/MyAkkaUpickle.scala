package akka.persistence.upickle

import java.nio.charset.StandardCharsets

import akka.actor.ExtendedActorSystem
import akka.persistence.{PersistentImpl, PersistentRepr}
import akka.serialization.Serializer
import com.simplesys.log.Logging
import upickle.default._
import upickle.{Js, json}

class MyAkkaUpickle(implicit val extendedSystem: ExtendedActorSystem)
    extends Serializer
    with Implicits
    with Logging {

  override def identifier: Int = 56916791

  override def toBinary(o: AnyRef): Array[Byte] = {
    o match {
      case persistentImpl: PersistentRepr =>
        write(persistentImpl).getBytes(StandardCharsets.UTF_8.name())
      case x =>
        throw new RuntimeException(
          s"Attempt serrialize: ${x.getClass.getCanonicalName}")
    }

  }
  override def includeManifest = true

  override def fromBinary(bytes: Array[Byte],
                          manifest: Option[Class[_]]): AnyRef = {
    manifest match {
      case None =>
        throw new RuntimeException(s"Bad branch (fromBinary)")

      case Some(x) =>
        json.read(new String(bytes)) match {
          case x: Js.Obj
              if x("$type").str == "akka.persistence.PersistentImpl" =>
            logger debug s"////////////// fromBinary for : PersistentImpl ////////////"
            readJs[PersistentRepr](x)
          case x: Js.Obj =>
            throw new RuntimeException(s"Bad branch for : ${x("$type").str}")
        }
    }
  }
}
