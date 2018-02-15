package com.simplesys.common

import java.time.{LocalDateTime, ZoneId}

object Common {
    implicit def date2LocalDateTime(input: java.util.Date): LocalDateTime = input.toInstant.atZone(ZoneId.systemDefault).toLocalDateTime

    implicit def localDateTime2Date(input: LocalDateTime): java.util.Date = java.util.Date.from(input.atZone(ZoneId.systemDefault).toInstant)
    implicit def optLocalDateTime2Date(input: Option[LocalDateTime]): java.util.Date = input.map(ldt ⇒ java.util.Date.from(ldt.atZone(ZoneId.systemDefault).toInstant)).orNull

    implicit def date2OptLocalDateTime(input: java.util.Date): Option[LocalDateTime] = Option(input).map(_.toInstant.atZone(ZoneId.systemDefault).toLocalDateTime)

    implicit def str2OptString(input: String): Option[String] = Option(input).filter(_.length > 0)
    implicit def strOpt2String(input: Option[String]): String = input.orNull

    implicit def long2OptLong(input: Long): Option[Long] = Option(input)
    implicit def optLong2Long(input: Option[Long]): Long = input getOrElse -1L

    implicit def int2OptInt(input: Int): Option[Int] = Option(input)
    implicit def optInt2Int(input: Option[Int]): Int = input getOrElse -1

    implicit def bool2OptBool(input: Boolean): Option[Boolean] = Option(input)
    implicit def optBool2Bool(input: Option[Boolean]): Boolean = input getOrElse false
}
