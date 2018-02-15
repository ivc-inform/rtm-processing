import io.circe.generic.auto._
import io.circe.syntax._

import scala.collection.mutable.ArrayBuffer

case class Info(libName: String, libVersion: String) {
    def toJson = this.asJson
    override def toString: String = toJson.spaces2
}

object Common {
    var list = ArrayBuffer.empty[Info]

    def spaces2: String = list.toSet.asJson.spaces2
}


