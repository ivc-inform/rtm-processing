import CommonDeps.versions
import sbt._
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

object CommonDepsScalaJS {
    val smartClientWrapper = Def.setting("com.simplesys" %%% "smartclient-wrapper" % versions.scalaJSVersion)
    val commonTypesSCW = Def.setting("com.simplesys" %%% "common-types" % versions.scalaJSVersion)
    //val smartClientWrapper = ProjectRef(uri("../../JOB/scalajs"), "smartClientCrossProjJS")

    val scalaTags = Def.setting("com.lihaoyi" %%% "scalatags" % versions.scalaTagsVersion)
    val scalaTest = Def.setting("org.scalatest" %%% "scalatest" % versions.scalaTestVersion % Test)
    val scalaDom = Def.setting("org.scala-js" %%% "scalajs-dom" % versions.scalajsDOMVersion)
    val jQuery = Def.setting("io.udash" %%% "udash-jquery" % versions.udashJQueryVersion)

    val circeExtender = Def.setting("com.simplesys.cross" %%% "circe-extender" % versions.ssCrossVersion)
    val servletWrapper = Def.setting("com.simplesys.cross" %%% "servlet-wrapper" % versions.ssCrossVersion)
}
