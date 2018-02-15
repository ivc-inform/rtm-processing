import sbt._


lazy val devPlugin = uri("../../sbt-plugins/dev-plugin")
//lazy val circeExtender = uri("../../simplesys/circe-extender")
//lazy val sbtCoffeeScript = uri("../../sbt-plugins/sbt-coffeescript")
//lazy val sbtNativePackager = uri("../../sbt-plugins/sbt-native-packager")
//lazy val mergeJS = uri("../../sbt-plugins/merge-js")

lazy val root = Project(id = "buildPlugins", base = file(".")).dependsOn(
    RootProject(devPlugin),
    /*RootProject(circeExtender), */
    /*RootProject(sbtCoffeeScript), */
    /*RootProject(sbtNativePackager), */
    /*RootProject(mergeJS)*/
)

  .settings(sbt.inThisBuild(CommonSettings.defaultSettings))
  .settings(
      classpathTypes += "maven-plugin",
      //PluginDeps.devPlugin,
      PluginDeps.sbtCoffeeScript,
      PluginDeps.mergeJS,
      PluginDeps.xsbtWeb,
      PluginDeps.sbtNativePackager,
      PluginDeps.jrebelPlugin,
      PluginDeps.crossproject,
      PluginDeps.sbtCrossproject,
      libraryDependencies ++= Seq(
          CommonDeps.circeExtender
      )
  )
