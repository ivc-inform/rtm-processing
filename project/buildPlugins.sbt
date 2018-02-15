import com.typesafe.sbt.GitVersioning
import com.typesafe.sbt.SbtGit.git
import ru.simplesys.sbprocessing.sbtbuild.{CommonSettings, PluginDeps}
import sbt._


//lazy val scenarioPlugin = uri("../../build-plugins/scenario-plugin")
//lazy val devPlugin = uri("../../sbt-plugins/dev-plugin")
//lazy val scalaFmtPlugin = uri("../../build-plugins/scala-fmt")

lazy val root = Project(id = "buildPlugins", base = file("."))
  .enablePlugins(GitVersioning)
  .dependsOn(
      /*scenarioPlugin*/
      /*, scalaFmtPlugin*/
      /*devPlugin*/)
  .settings(inThisBuild(CommonSettings.defaultSettings ++ Seq(
      git.baseVersion := CommonSettings.settingValues.baseVersion
  ))).
  settings(
      //PluginDeps.scenarioPlugin,
      PluginDeps.scalaFmtPlugin,
      PluginDeps.sbtNativePackager,
      PluginDeps.devPlugin,
      PluginDeps.scalaFmtPlugin,
      PluginDeps.sbtCoffeeScript,
      PluginDeps.mergeJS,
      PluginDeps.xsbtWeb,
      PluginDeps.scalaJSPlugin
  )
