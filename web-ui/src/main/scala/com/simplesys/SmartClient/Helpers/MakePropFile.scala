package com.simplesys.SmartClient.Helpers

import com.simplesys.io._

import scala.io.Codec._
import scala.io.Source

object MakePropFile extends App {
    val f = "web-ui/src/main/resources/Fasade.rc".toFile
    val fres = "web-ui/src/main/resources/Props.rc".toFile

    val rExp = """(?:\w+)(?:\s+)(\w+)\s*(?:\:)\s*(.*)""".r("name", "type")

    fres <=| {
        out =>
            Source.fromFile(f)(UTF8) getLines() foreach {
                line =>
                    for (x <- rExp.findAllMatchIn(line)) {
                        val name = x.group("name")
                        val `type` = x.group("type").replace("with AutoChild", "")

                        val res = s"var $name: ScOption[${`type`}] = ScNone"

                        out(res)
                    }

            }
    }
}
