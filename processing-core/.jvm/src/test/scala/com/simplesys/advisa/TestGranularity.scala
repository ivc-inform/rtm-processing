package com.simplesys
package advisa

import com.simplesys.actors.AppConfig
import com.simplesys.log.Logging
import org.scalatest.{FunSuite, Matchers}

/**
  * Created by newf on 22.07.16.
  */
class TestGranularity extends FunSuite with Matchers with Logging {
  test("calcBounds") {
    import scala.math._
    val categorySimilarity = 0.8d //misc.Helper.minimumSimilarityCategoryMetricValue
    for (left <- 1 to 10; right <- left to 10) {
      val maximumPossibleSimilarity: Double = (left: Double) / (sqrt(left) * sqrt(
          right))
      if (maximumPossibleSimilarity >= categorySimilarity) {
        logger.info(
          s"left: $left, rigth: $right, maxSimilarity: $maximumPossibleSimilarity")
      }
    }
  }

}
