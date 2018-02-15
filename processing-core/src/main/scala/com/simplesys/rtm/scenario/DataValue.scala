package com.simplesys.rtm.scenario

import org.joda.time.LocalDateTime

object DataValue {
    implicit def str2DataValue(string: String): DataValue = StringValue(string)
    implicit def strOpt2DataValue(string: Option[String]): Option[DataValue] = string.map(StringValue(_))

    implicit def bool2DataValue(boolean: Boolean): DataValue = BooleanValue(boolean)
    implicit def boolOpt2DataValue(boolean: Option[Boolean]): Option[DataValue] = boolean.map(BooleanValue(_))

    implicit def int2DataValue(int: Int): DataValue = IntValue(int)
    implicit def intOpt2DataValue(int: Option[Int]): Option[DataValue] = int.map(IntValue(_))

    implicit def long2DataValue(long: Long): DataValue = LongValue(long)
    implicit def longOpt2DataValue(long: Option[Long]): Option[DataValue] = long.map(LongValue(_))

    implicit def double2DataValue(double: Double): DataValue = DoubleValue(double)
    implicit def doubleOpt2DataValue(double: Option[Double]): Option[DataValue] = double.map(DoubleValue(_))

    implicit def ldt2DataValue(ldt: LocalDateTime): DataValue = LocalDateTimeValue(ldt)
    implicit def ldtOpt2DataValue(ldt: Option[LocalDateTime]): Option[DataValue] = ldt.map(LocalDateTimeValue(_))
}

sealed abstract trait DataValue

case class IntValue(value: Int) extends DataValue

case class StringValue(value: String) extends DataValue

case class DoubleValue(value: Double) extends DataValue

case class LongValue(value: Long) extends DataValue

case class LocalDateTimeValue(value: LocalDateTime) extends DataValue

case class BooleanValue(value: Boolean) extends DataValue
