package akka.persistence.rtm

import com.simplesys.config.Config
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.duration.Duration
import scala.reflect.runtime.{universe => ru}

class TestSuit extends FunSuite with Config {

  implicit class str_opr(string: String) {
    def like(list: String*): Boolean =
      list.exists(item => string.indexOf(item) != -1)
    def notLike(list: String*): Boolean =
      list.forall(item => string.indexOf(item) == -1)
  }

  test("2") {
    println(
      "V R-Connect prinyata k ispolneniu zayavka na konvertaciyu na 500.00 EUR. Spravka po tel 8-800-7000072 Raiffeisenbank".toLowerCase
        .like("zayavka"))
    println(
      "V R-Connect prinyata k ispolneniu zayavka na konvertaciyu na 500.00 EUR. Spravka po tel 8-800-7000072 Raiffeisenbank".toLowerCase
        .like("konvertaciyu"))
  }

  test("jodatime") {
    println(
      new DateTime()
        .withTimeAtStartOfDay()
        .plusHours(24)
        .plus(Duration("0 d").toMillis))
    println(Duration("2 min"))
  }
}
