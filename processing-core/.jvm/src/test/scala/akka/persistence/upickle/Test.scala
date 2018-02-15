package akka.persistence.upickle

import akka.actor.{
  ActorSystem,
  ActorSystemImpl,
  ExtendedActorSystem,
  Extension,
  ExtensionId,
  ExtensionIdProvider
}
import akka.persistence.PersistentRepr
import akka.persistence.inner.JournalEntry
import akka.persistence.inner.extension.Journal
import com.simplesys.config.Config
import org.scalatest.FunSuite
import upickle.default._
import com.simplesys.common.Strings._

import scala.io.Codec._
import scala.io.Source
import scalax.file.ImplicitConversions._
import scalax.file.Path

class AnyExtensionImpl extends Extension

object AnyExtension
    extends ExtensionId[AnyExtensionImpl]
    with ExtensionIdProvider {

  override def lookup = AnyExtension
  override def createExtension(system: ExtendedActorSystem) =
    new AnyExtensionImpl

  override def get(system: ActorSystem): AnyExtensionImpl = super.get(system)
}

class Test extends FunSuite with Config {

  implicit val extendedSystem = new ActorSystemImpl(name = "mySystem",
                                                    applicationConfig = config,
                                                    classLoader =
                                                      getClass.getClassLoader,
                                                    None,
                                                    None)

  test("load jourval") {
    /*val journal = new Journal[JournalEntry]()
    val path: Path = "processing-core/src/test/resources/journal.sorage"
    journal.load(Some(path))*/
  }

}
