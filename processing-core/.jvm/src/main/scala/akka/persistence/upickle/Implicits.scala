package akka.persistence.upickle

import java.time.LocalDateTime

import akka.actor.{ActorRef, ExtendedActorSystem}
import akka.persistence.fsm.PersistentFSM.StateChangeEvent
import akka.persistence.inmemory.JournalEntry
import akka.persistence.query.TimeBasedUUID
import akka.persistence.{PersistentImpl, PersistentRepr}
import akka.serialization.Serialization
import com.simplesys.actors.Reaper._
import com.simplesys.actors.SeqGenerator
import com.simplesys.actors.SeqGenerator._
import com.simplesys.common.Strings._
import com.simplesys.log.Logging
import com.simplesys.rtm.scenario._
import upickle.default._
import upickle.{Js, json}


//q8jC3CnOE3Zak
trait Implicits extends Logging {
    val extendedSystem: ExtendedActorSystem

    implicit val localDateTime2Writer: Writer[LocalDateTime] = Writer[LocalDateTime](ldt => Js.Str(ldt.toString))
    implicit val localDateTime2Reader: Reader[LocalDateTime] = Reader[LocalDateTime] { case Js.Str(date) => LocalDateTime.parse(date) }
    
    implicit val bigInt2Writer: Writer[BigInt] = Writer[BigInt](bigInt => Js.Num(bigInt.toDouble))
    implicit val bigInt2Reader: Reader[BigInt] = Reader[BigInt] { case Js.Num(bigInt) => BigInt(bigInt.toLong) }

    implicit val actorRef2Writer: Writer[ActorRef] =
        Writer[ActorRef] {
            case actorRef: ActorRef => Js.Str(Serialization serializedActorPath actorRef)
            case null => Js.Null
        }
    implicit val actorRef2Reader: Reader[ActorRef] =
        Reader[ActorRef] {
            case Js.Str(path) => extendedSystem.provider.resolveActorRef(path)
            case Js.Null => null
        }

    implicit val payLoad2WriterSuper: Writer[Any] =
        Writer[Any] {
            case x: GetNewLimit => writeJs[GetNewLimit](x)
            case x: SeqGenerator.TakeNewLimit => writeJs[TakeNewLimit](x)
            case x: StartedUp => writeJs[StartedUp](x)
            case x: ShuttedDown => writeJs[ShuttedDown](x)
            case x: StateChangeEvent => writeJs[StateChangeEvent](x)
            case x: InnerEventUnified => writeJs[InnerEventUnified](x)
            case x: TimerUnified => writeJs[TimerUnified](x)
            case x: SetDataUnified => writeJs[SetDataUnified](x)
            case x => throw new RuntimeException(s"Bad branch (payLoad2WriterSuper) : ${x.getClass.getCanonicalName}")
        }

    implicit val payLoad2ReaderSuper: Reader[Any] =
        Reader[Any] {
            case x: Js.Obj if x("$type").str == GetNewLimit.getClass.asCanonicalName => readJs[GetNewLimit](x)
            case x: Js.Obj if x("$type").str == SeqGenerator.TakeNewLimit.getClass.asCanonicalName => readJs[TakeNewLimit](x)
            case x: Js.Obj if x("$type").str == StartedUp.getClass.asCanonicalName => readJs[StartedUp](x)
            case x: Js.Obj if x("$type").str == ShuttedDown.getClass.asCanonicalName => readJs[ShuttedDown](x)
            case x: Js.Obj if x("$type").str == StateChangeEvent.getClass.asCanonicalName => readJs[StateChangeEvent](x)
            case x: Js.Obj if x("$type").str == InnerEventUnified.getClass.asCanonicalName => readJs[InnerEventUnified](x)
            case x: Js.Obj if x("$type").str == TimerUnified.getClass.asCanonicalName => readJs[TimerUnified](x)
            case x: Js.Obj if x("$type").str == SetDataUnified.getClass.asCanonicalName => readJs[SetDataUnified](x)
            case x => throw new RuntimeException(s"Bad branch (payLoad2ReaderSuper) : ${x("$type").str}")
        }

    implicit val byteArray2Writer: Writer[Array[Byte]] =
        Writer[Array[Byte]](x => json.read(x.map(_.toChar) mkString strEmpty))

    implicit val byteArray2Reader: Reader[Array[Byte]] =
        Reader[Array[Byte]] {
            case obj: Js.Obj if obj("$type").str == PersistentImpl.getClass.asCanonicalName =>
                json.write(obj).map(_.toByte).toArray
            case x => throw new RuntimeException(s"Bad branch (byteArray2Reader) : $x")
        }

    implicit val getNewLimit2Reader: Reader[GetNewLimit] =
        Reader[GetNewLimit] {
            case payLoad: Js.Obj =>
                com.simplesys.actors.SeqGenerator.GetNewLimit(
                    count = readJs[Int](payLoad("count")),
                    replyTo = actorRef2Reader.read(payLoad("replyTo"))
                )
        }

    implicit val persistentRepr2Writer: Writer[PersistentRepr] =
        Writer[PersistentRepr] {
            persistentRepr =>
                Js.Obj(
                    "$type" -> writeJs[String](persistentRepr.getClass.getCanonicalName),
                    "payload" -> payLoad2WriterSuper.write(persistentRepr.payload),
                    "manifest" -> writeJs[String](persistentRepr.manifest),
                    "persistenceId" -> writeJs[String](persistentRepr.persistenceId),
                    "sequenceNr" -> writeJs[Long](persistentRepr.sequenceNr),
                    "writerUuid" -> writeJs[String](persistentRepr.writerUuid),
                    "deleted" -> writeJs[Boolean](persistentRepr.deleted),
                    "sender" -> actorRef2Writer.write(persistentRepr.sender)
                )
        }

    implicit val persistentRepr2Reader: Reader[PersistentRepr] =
        Reader[PersistentRepr] {
            case persistentImpl: Js.Obj if persistentImpl("$type").str == PersistentImpl.getClass.asCanonicalName =>
                PersistentImpl(
                    payload = payLoad2ReaderSuper.read(persistentImpl("payload")),
                    sequenceNr = readJs[Long](persistentImpl("sequenceNr")),
                    persistenceId = readJs[String](persistentImpl("persistenceId")),
                    manifest = readJs[String](persistentImpl("manifest")),
                    deleted = readJs[Boolean](persistentImpl("deleted")),
                    sender = actorRef2Reader.read(persistentImpl("sender")),
                    writerUuid = readJs[String](persistentImpl("writerUuid"))
                )
            case x => throw new RuntimeException(s"Bad branch (persistentRepr2Reader): $x")
        }

    implicit val journalEntry2Writer: Writer[JournalEntry] =
        Writer[JournalEntry] {
            journalEntry =>
                Js.Obj(
                    "$type" -> writeJs[String](journalEntry.getClass.getCanonicalName),
                    "persistenceId" -> writeJs[String](journalEntry.persistenceId),
                    "sequenceNr" -> writeJs[Long](journalEntry.sequenceNr),
                    "serialized" -> byteArray2Writer.write(journalEntry.serialized),
                    "deleted" -> writeJs[Boolean](journalEntry.deleted),
                    "ordering" -> writeJs[Long](journalEntry.ordering),
                    "tags" -> writeJs[Set[String]](journalEntry.tags),
                    "timestamp" -> writeJs[TimeBasedUUID](journalEntry.timestamp),
                    "repr" -> persistentRepr2Writer.write(journalEntry.repr)
                )
        }

    implicit val journalEntry2Reader: Reader[JournalEntry] =
        Reader[JournalEntry] {
            case journalEntry: Js.Obj if journalEntry("$type").str == JournalEntry.getClass.asCanonicalName =>
                JournalEntry(
                    persistenceId = readJs[String](journalEntry("persistenceId")),
                    sequenceNr = readJs[Long](journalEntry("sequenceNr")),
                    serialized = byteArray2Reader.read(journalEntry("serialized")),
                    repr = persistentRepr2Reader.read(journalEntry("repr")),
                    tags = readJs[Set[String]](journalEntry("tags")),
                    deleted = readJs[Boolean](journalEntry("deleted")),
                    timestamp = readJs[TimeBasedUUID](journalEntry("timestamp")),
                    ordering = readJs[Long](journalEntry("ordering"))
                )
        }
}
