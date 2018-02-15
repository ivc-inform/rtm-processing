package com.simplesys.rtm

object ScenarioCompanion {
    object DT {
        def apply(d: Long): DT = {
            var seconds: Long = d / 1000
            var minits = 0L
            var hours = 0L
            var days = 0L

            if (seconds / 60 > 0) {
                minits = seconds / 60
                seconds -= minits * 60
            }

            if (minits / 60 > 0) {
                hours = minits / 60
                minits -= hours * 60
            }

            if (hours / 24 > 0) {
                days = hours / 24
                hours -= days * 24
            }

            DT(days, hours, minits, seconds)
        }
    }

    case class DT(days: Long, hours: Long, minits: Long, seconds: Long) {
        override def toString: String = s"Days: $days, Hours: $hours, Mins: $minits, Secs: $seconds"
    }
}
