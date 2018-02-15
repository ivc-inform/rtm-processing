package com.simplesys.misc

import java.lang.Math._
import java.util.regex.Pattern

import com.simplesys.config.Config

object Helper extends Config {

    val replaceToWhitespacePattern = Pattern.compile(getString("rtm-processing.similarTerminalCache.replaceToWhitespaceRegexp"))
    val tokenizePattern = Pattern.compile(getString("rtm-processing.similarTerminalCache.tokenizeRegexp"))
    val minimumTerminalNameLength = getInt("rtm-processing.similarTerminalCache.minimumTerminalNameLength")
    val minimumSimilarityCategoryMetricValue = getDouble("rtm-processing.similarTerminalCache.minimumSimilarityCategoryMetricValue")
    val minimumSimilarityMerchantUnitMetricValue = getDouble("rtm-processing.similarTerminalCache.minimumSimilarityMerchantUnitMetricValue")
    val forkJoinThreadCount = getInt("rtm-processing.similarTerminalCache.forkJoinThreadCount")

    def isNull(string: String) = string match {
        case null => true
        case _ => false
    }

    def isNotNul(string: String) = !isNull(string)

    implicit class StringOpts(string: String) {
        def countMatches(sub: String) = org.apache.commons.lang.StringUtils.countMatches(string, sub)
        def substringAfterLast(separator: String) = org.apache.commons.lang.StringUtils.substringAfterLast(string, separator)
        def upperCase = org.apache.commons.lang.StringUtils upperCase string
        def isBlank = org.apache.commons.lang.StringUtils isBlank string

        //def simplify: String = Pattern.compile(replaceToWhitespaceRegexp).matcher(string).replaceAll("#").toUpperCase
        def simplify: String =
        replaceToWhitespacePattern
          .matcher(string)
          .replaceAll("#")
          .replaceAll("#+", "#")
          .toUpperCase
        def tokenize: Set[String] =
            if (isNull(string) || string.isEmpty)
                Set.empty[String]
            else
                tokenizePattern.split(string).toSet

        def isWeirdNameForTerminal: Boolean = {
            val merchantName = string.simplify
            (merchantName.replaceAll("[#\\s]", "").length == 0) || (merchantName
              .countMatches("/") > 1 && merchantName.substringAfterLast("/").isBlank)
        }
    }

    implicit class OptStringUtils(string: Option[String]) {
        def upperCase: Option[String] = string match {
            case None => None
            case Some(string) => Some(string.upperCase)
        }

        def countMatches(sub: String): Option[Int] = string match {
            case None => None
            case Some(string) => Some(string countMatches sub)
        }

        def substringAfterLast(separator: String): Option[String] = string match {
            case None => None
            case Some(string) => Some(string substringAfterLast separator)
        }
    }

    implicit class SetOpts[T](setA: Set[T]) {
        def compare(setB: Set[T]): Double = {
            if (setA.isEmpty && setB.isEmpty)
                1.0
            else if (setA.isEmpty || setB.isEmpty)
                0.0
            else {
                var dotProduct = 0.0
                var magnitudeA = 0.0
                var magnitudeB = 0.0

                val _setA = if (setA.size < setB.size) setB else setA
                val _setB = if (setA.size < setB.size) setA else setB

                _setA.union(_setB).foreach { entry =>
                    val aCount = _setA.count(_ == entry)
                    val bCount = _setB.count(_ == entry)

                    dotProduct += aCount * bCount
                    magnitudeA += aCount * aCount
                    magnitudeB += bCount * bCount
                }

                dotProduct / (sqrt(magnitudeA) * sqrt(magnitudeB))
            }
        }
    }
}
