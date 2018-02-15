package com.simplesys

import com.simplesys.logging.LevelLoggingA
import org.joda.time.{DateTime, LocalDateTime}
import _root_.doobie.util.meta.Meta

package object doobie {
    implicit val localDateTimeMeta: Meta[LocalDateTime] = Meta[java.sql.Timestamp].nxmap(
        (timestamp) => new LocalDateTime(timestamp.getTime),
        (localDateTime) => new java.sql.Timestamp(localDateTime.toDateTime.getMillis)
    )

    implicit val dateTimeMeta: Meta[DateTime] = Meta[java.sql.Timestamp].nxmap(
        (timestamp) => new DateTime(timestamp.getTime),
        (dateTime) => new java.sql.Timestamp(dateTime.getMillis)
    )

    implicit val levelLoginMeta: Meta[LevelLoggingA] = Meta[Int].xmap(
        (levelInt) => LevelLoggingA.getObject(levelInt),
        (level) => level.value
    )
}
