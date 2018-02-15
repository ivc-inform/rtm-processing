package com.simplesys.actors.serialization.kryo

import com.esotericsoftware.kryo.io.{Input, Output}
import com.esotericsoftware.kryo.{Kryo, Serializer}
import enumeratum.EnumEntry.Uppercase
import enumeratum._
import org.joda.time._
import org.joda.time.chrono._

import scala.collection.immutable

sealed trait JodaChronologyExt extends EnumEntry with Uppercase {
  def chronology: Chronology
}

object JodaChronologyExt extends Enum[JodaChronologyExt] {
  val values = findValues
  //private val values = SealedEnumRuntime.values[JodaChronologyExt]
  private val mappedKeys = namesToValuesMap // values.map(x => (x.key, x))(collection.breakOut)
  private val mappedObjs: immutable.Map[Chronology, String] =
    values.map(x => (x.chronology, x.entryName))(collection.breakOut)
  def getValue(code: String): Chronology = mappedKeys(code).chronology
  def getKey(chrono: Chronology): String = mappedObjs(chrono)

  case object ISO_Chronology extends JodaChronologyExt {
    override def entryName: String = "ISO"
    val chronology = ISOChronology.getInstance()
  }
  case object COPTIC_Chronology extends JodaChronologyExt {
    override def entryName: String = "COPTIC"
    val chronology = CopticChronology.getInstance()
  }
  case object ETHIOPIC_Chronology extends JodaChronologyExt {
    override def entryName: String = "ETHIOPIC"
    val chronology = EthiopicChronology.getInstance()
  }
  case object GREGORIAN_Chronology extends JodaChronologyExt {
    override def entryName: String = "GREGORIAN"
    val chronology = GregorianChronology.getInstance()
  }
  case object JULIAN_Chronology extends JodaChronologyExt {
    override def entryName: String = "JULIAN"
    val chronology = JulianChronology.getInstance()
  }
  case object ISLAMIC_Chronology extends JodaChronologyExt {
    override def entryName: String = "ISLAMIC"
    val chronology = IslamicChronology.getInstance()
  }
  case object BUDDHIST_Chronology extends JodaChronologyExt {
    override def entryName: String = "BUDDHIST"
    val chronology = BuddhistChronology.getInstance()
  }
  case object GJ_Chronology extends JodaChronologyExt {
    override def entryName: String = "GJ"
    val chronology = GJChronology.getInstance()
  }

}

class JodaDateTimeKryoSerializer extends Serializer[DateTime] {
  override def write(kryo: Kryo, output: Output, t: DateTime): Unit = {
    output.writeLong(t.getMillis, true)
    output.writeString(t.getZone.getID)
    output.writeString(JodaChronologyExt.getKey(t.getChronology))
  }

  override def read(kryo: Kryo, input: Input, cl: Class[DateTime]): DateTime = {
    val millis = input.readLong(true)
    val tz = input.readString()
    val chrono = input.readString()
    val chronology = JodaChronologyExt.getValue(chrono)
    new DateTime(millis, chronology.withZone(DateTimeZone.forID(tz)))
  }
}

class JodaLocalDateTimeKryoSerializer extends Serializer[LocalDateTime] {
  override def write(kryo: Kryo, output: Output, t: LocalDateTime): Unit = {
    //millis are in local timezone
    val millis = t.toDateTime.getMillis
    output.writeLong(millis, true)
  }

  override def read(kryo: Kryo,
                    input: Input,
                    cl: Class[LocalDateTime]): LocalDateTime = {
    val millis = input.readLong(true)
    // millis in ISO chronology relating to local timezone
    new LocalDateTime(millis)
  }
}

class JodaLocalTimeKryoSerializer extends Serializer[LocalTime] {
  override def write(kryo: Kryo, output: Output, t: LocalTime): Unit = {
    //millis are in local timezone
    val millis: Long = t.getMillisOfDay
    output.writeLong(millis, true)
  }

  override def read(kryo: Kryo,
                    input: Input,
                    cl: Class[LocalTime]): LocalTime = {
    val millis = input.readLong(true)
    // millis in ISO chronology relating to local timezone
    new LocalTime(millis)
  }
}
