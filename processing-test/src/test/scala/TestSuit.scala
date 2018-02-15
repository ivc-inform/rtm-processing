import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.scalatest.FunSuite

class TestSuit extends FunSuite {
    test("LocalDate") {
        val fmt = DateTimeFormat.forPattern("dd.MM.YYYY HH:mm:ss")
        val shortFmt = DateTimeFormat.forPattern("dd.MM.YYYY")

        def strToDate(s: String): DateTime = {
            try {
                fmt.parseDateTime(s)
            } catch {
                case e: IllegalArgumentException =>
                    shortFmt.parseDateTime(s)
            }

        }

        val dt = new DateTime()
        val dtStr = fmt.print(dt)
        println(dtStr)
        println(strToDate(dtStr).toString(fmt))
        println(strToDate("19.07.2016 21:24:13").toString(fmt))
        println(strToDate("19.07.2016").toString(fmt))
    }
}
