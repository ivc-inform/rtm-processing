package com.simplesys

import java.sql.Timestamp
import java.time.LocalDateTime

import com.simplesys.logging.LevelLoggingA
import _root_.doobie.util.meta.Meta

package object doobie {
    implicit val localDateTimeMeta: Meta[LocalDateTime] = Meta[java.sql.Timestamp].nxmap(
            (timestamp) => timestamp.toLocalDateTime,
            (localDateTime) => Timestamp.valueOf(localDateTime)
        )

        implicit val levelLoginMeta: Meta[LevelLoggingA] = Meta[Int].xmap(
            (levelInt) => LevelLoggingA.getObject(levelInt),
            (level) => level.value
        )
}
