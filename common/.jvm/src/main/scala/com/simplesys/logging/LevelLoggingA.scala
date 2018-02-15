package com.simplesys.logging

import ru.simplesys.coreutil.SealedEnumRuntime

sealed abstract trait LevelLoggingA {
    def value: Int
}

case object TraceLevel extends LevelLoggingA {
    override def value = 0
}

case object DebugLevel extends LevelLoggingA {
    override def value = 1
}

case object InfoLevel extends LevelLoggingA {
    override def value = 2
}

case object WarningLevel extends LevelLoggingA {
    override def value = 3
}

case object ErrorLevel extends LevelLoggingA {
    override def value = 4
}

case object FatalErrorLevel extends LevelLoggingA {
    override def value = 5
}

object LevelLoggingA {
    private val values = SealedEnumRuntime.values[LevelLoggingA]
    private val mappedKeys: Map[Int, LevelLoggingA] = values.map(x => (x.toString.toInt, x))(collection.breakOut)
    def getObject(level: Int): LevelLoggingA = mappedKeys(level)
}
