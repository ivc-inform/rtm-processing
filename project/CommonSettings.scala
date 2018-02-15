package ru.simplesys.sbprocessing.sbtbuild

import sbt.Keys._
import sbt.Setting

object CommonSettings {
    object settingValues {
        val baseVersion = "1.3"

        val scalaVersion = "2.12.4"
        val organization = "com.simplesys.rtmProcessing"

        val scalacOptions = Seq(
            "-feature",
            "-language:higherKinds",
            "-language:implicitConversions",
            "-language:existentials",
            "-language:postfixOps",
            "-deprecation",
            "-unchecked")
    }

    val defaultSettings = {
        Seq(
            scalacOptions := settingValues.scalacOptions,
            organization := settingValues.organization
        )
    }

    val defaultProjectSettings: Seq[Setting[_]] = {
        aether.AetherPlugin.autoImport.overridePublishSettings
    }
}
