package com.simplesys.misc

import java.time

import com.simplesys.actors.AppConfig
import com.simplesys.config.Config
import com.simplesys.log.Logging
import com.simplesys.misc.Helper._
import org.scalatest.FunSuite

import scala.concurrent.duration._

class TestSuite extends FunSuite with Logging with Config {
  test("countMatches") {
    val a = "123456/4444/".countMatches("/")
    logger debug s"Qty matches: ${a}"
  }

  test("Duration") {
    val configPrefix = s"${AppConfig.configPrefix}.scenarios.CafeToUber"
    logger debug s"${getDuration(s"$configPrefix.coolingTimeout")}"
    val a: time.Duration = config.getDuration(s"$configPrefix.coolingTimeout")
    val b: FiniteDuration = getDuration(s"$configPrefix.coolingTimeout")
    logger debug s"${config.getDuration(s"$configPrefix.coolingTimeout")}"
  }

}
