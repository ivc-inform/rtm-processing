package ru.simplesys.sbprocessing.sbtbuild

import sbt._
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

object CommonDepsScalaJS {
    val useFullSmartClientWrapper = false

    val macroJS = Def.setting("com.simplesys" %%% "macrojs" % CommonDeps.versions.scalaJSVersion)
    val smartClientWrapper = if (useFullSmartClientWrapper) Def.setting("com.simplesys" %%% "smartclient-wrapper" % CommonDeps.versions.scalaJSVersion) else Def.setting("com.simplesys" %%% "common-types" % CommonDeps.versions.scalaJSVersion)

    val uPickleJS = Def.setting("com.lihaoyi" %%% "upickle" % CommonDeps.versions.uPickleVersion)

    val scalaTags = Def.setting("com.lihaoyi" %%% "scalatags" % CommonDeps.versions.scalaTagsVersion)
}
