package com.simplesys.app

import com.github.tototoshi.csv.{CSVReader, DefaultCSVFormat, QUOTE_NONE, Quoting}
import com.simplesys.config.Config

import scala.io.Codec._
import scala.io.Source

case class Human(firstName: Option[String], secondName: Option[String]) {
    def greeting: Option[String] = firstName.map(fn => secondName.map(sn => s"$fn $sn").getOrElse(fn))

    override def toString: String = s"FirstName: $firstName SecondName: $secondName"
}

object ReaderPhoneDataFile extends Config {
    object readerFormat extends DefaultCSVFormat {
        override val delimiter = ';'
        override val quoting: Quoting = QUOTE_NONE
        override val lineTerminator: String = System.lineSeparator()
    }

    def humanMap(fileName: String): Map[String, Human] = {
        val reader = CSVReader.open(
            Source.fromInputStream(getClass.getClassLoader.getResourceAsStream(fileName))(UTF8).reader())(readerFormat)

        reader.iterator.map { seq =>
            val phone = seq.head.trim
            if (seq.length == 2)
                (phone, Human(Some(seq(1)), None))
            else if (seq.length == 3)
                (phone, Human(Some(seq(1)), Some(seq(2))))
            else
                (phone, Human(None, None))
        }.toMap
    }

    def readPhones(fileName: String): IndexedSeq[String] =
        Source
          .fromInputStream(getClass.getClassLoader.getResourceAsStream(fileName))(
              UTF8)
          .getLines()
          .map(_.trim)
          .filter(_.length > 0)
          .toSet
          .toIndexedSeq
}
