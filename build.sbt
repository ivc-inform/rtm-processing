import com.simplesys.jrebel.JRebelPlugin
import com.simplesys.jrebel.JRebelPlugin._
import com.simplesys.mergewebapp.MergeWebappPlugin._
import com.typesafe.sbt.coffeescript.SbtCoffeeScript.autoImport._
import com.typesafe.sbt.packager.docker.DockerPlugin._
import com.typesafe.sbt.web.Import.WebKeys._
import com.typesafe.sbt.web.SbtWeb.autoImport._
import ru.simplesys.plugins.sourcegen.DevPlugin._
import sbt.Keys.version
import sbtcrossproject.{CrossType, crossProject}

lazy val root = sbtcrossproject.crossProject(JSPlatform, JVMPlatform)
  .enablePlugins(GitVersioning)
  .settings(CommonSettings.noPublishSettings)
  .settings(inThisBuild(Seq(
      git.baseVersion := CommonSettings.settingValues.baseVersion,
      scalaVersion := CommonSettings.settingValues.scalaVersion,
      scalacOptions := CommonSettings.settingValues.scalacOptions,
      organization := CommonSettings.settingValues.organization,
      name := CommonSettings.settingValues.name
  )
    ++ CommonSettings.defaultSettings)
  )
  .aggregate(common, `db-objects`, `processing-core`, `processing-test`, `web-ui`)
  .dependsOn(common, `db-objects`, `processing-core`, `processing-test`, `web-ui`)


lazy val common = sbtcrossproject.crossProject(JSPlatform, JVMPlatform)
  .crossType(sbtcrossproject.CrossType.Pure)
  .enablePlugins(ScalaJSPlugin)
  .settings(CommonSettings.noPublishSettings)
  .settings(CommonSettings.defaultSettings)
  .jsSettings(
      libraryDependencies ++= Seq(
          CommonDepsScalaJS.commonTypesSCW.value,
          CommonDepsScalaJS.scalaTest.value
      ),
      scalacOptions ++= (if (scalaJSVersion.startsWith("0.6.")) Seq("-P:scalajs:sjsDefinedByDefault") else Nil)
  )
  .jvmSettings(
      libraryDependencies ++= Seq(
          CommonDeps.doobieCore,
          CommonDeps.doobieCoreCats,
          CommonDeps.akkaActor,
          CommonDeps.ssysCoreUtils,
          CommonDeps.log4J,
          CommonDeps.scalazStream,
          CommonDeps.scalazCore,
          CommonDeps.scalaTest
      )
  )

lazy val `common-js` = common.js
lazy val `common-jvm` = common.jvm

lazy val `db-objects` = sbtcrossproject.crossProject(JSPlatform, JVMPlatform)
  .crossType(sbtcrossproject.CrossType.Pure)
  .settings(CommonSettings.noPublishSettings)
  .settings(CommonSettings.defaultSettings)
  .dependsOn(common)
  .jsSettings(
      libraryDependencies ++= Seq(
          CommonDepsScalaJS.circeExtender.value
      )
  )
  .jvmConfigure(_ enablePlugins DevPlugin)
  .jvmSettings(
      DevPlugin.devPluginGeneratorSettings,
      libraryDependencies ++= Seq(
          CommonDeps.ssysCoreLibrary,
          CommonDeps.ssysJDBCWrapper,
          CommonDeps.jdbcOracle12,
          CommonDeps.poiOOxml,
          CommonDeps.scalaTest % Test
      ),
      sourceSchemaDir in DevConfig := (resourceDirectory in Compile).value / "defs",
      startPackageName in DevConfig := "ru.simplesys.defs",
      contextPath in DevConfig := "aps",
      maxArity := 254,
      quoted := true,
      sourceGenerators in Compile += (generateBoScalaCode in DevConfig)
  )

lazy val `db-objects-js` = `db-objects`.js
lazy val `db-objects-jvm` = `db-objects`.jvm

lazy val `processing-core` = sbtcrossproject.crossProject(JSPlatform, JVMPlatform)
  .crossType(sbtcrossproject.CrossType.Pure)
  .settings(CommonSettings.noPublishSettings)
  .settings(CommonSettings.defaultSettings)
  .dependsOn(`db-objects`)
  .jvmSettings(

      unmanagedClasspath in Runtime ++= Seq(baseDirectory.value / "data", baseDirectory.value / "etc"),
      unmanagedClasspath in Test ++= Seq(baseDirectory.value / "data", baseDirectory.value / "etc"),

      libraryDependencies ++= Seq(
          CommonDeps.dmProcessingTokenizer,
          CommonDeps.dmProcessingDictionary,
          CommonDeps.dmProcessingTemplates,
          CommonDeps.dmProcessingClassifier,
          CommonDeps.dmProcessingXmlHelper,
          CommonDeps.scenarioConfigurator,
          CommonDeps.ssysCommonWebapp,
          CommonDeps.ssysConfigWrapper,
          CommonDeps.akkaActor,
          CommonDeps.akkaPersistence,
          CommonDeps.akkaHttp,
          CommonDeps.akkaSLF4J,
          //CommonDeps.akkaPersistenceInMemory,
          CommonDeps.hikariPoolDataSources,
          CommonDeps.akkaPersistenceJDBC,
          CommonDeps.akkaPersistenceInMemory,
          CommonDeps.kryoSerialization,
          CommonDeps.uPickle,
          CommonDeps.lz4,
          CommonDeps.ssysScalaIOExtender,
          CommonDeps.ssysCommon,
          CommonDeps.ssysAkkaExtender,
          CommonDeps.simmetricCore,
          CommonDeps.log4J,
          CommonDeps.csvReader,
          CommonDeps.apacheCommonLang,
          CommonDeps.kamonCore,
          CommonDeps.kamonScala,
          CommonDeps.kamonAkka,
          CommonDeps.kamonSystemMetrics,
          CommonDeps.kamonStatsD,
          CommonDeps.scopt,
          CommonDeps.circeExtender,
          CommonDeps.utilEval,
          CommonDeps.scalaFmt,
          CommonDeps.scalaTest % Test
      )
  )

lazy val `processing-core-js` = `processing-core`.js
lazy val `processing-core-jvm` = `processing-core`.jvm

lazy val `processing-test` = sbtcrossproject.crossProject(JSPlatform, JVMPlatform)
  .crossType(sbtcrossproject.CrossType.Pure)
  .settings(CommonSettings.noPublishSettings)
  .settings(CommonSettings.defaultSettings)
  .dependsOn(`processing-core`)
  .jvmSettings(
      libraryDependencies ++= Seq(
          CommonDeps.ssysCommon,
          CommonDeps.jdbcOracle12,
          CommonDeps.log4J,
          CommonDeps.scopt,
          CommonDeps.ssysConfigWrapper,
          CommonDeps.scalaTest % Test
      )
  )

lazy val `processing-test-js` = `processing-test`.js
lazy val `processing-test-jvm` = `processing-test`.jvm

lazy val `web-ui` = sbtcrossproject.crossProject(JSPlatform, JVMPlatform)
  .crossType(sbtcrossproject.CrossType.Pure)
  .settings(CommonSettings.noPublishSettings)
  .dependsOn(`processing-core`)
  .aggregate(`processing-core`)
  .settings(CommonSettings.defaultSettings)
  .jvmConfigure(_ enablePlugins(DevPlugin, MergeWebappPlugin, SbtCoffeeScript, JettyPlugin, WarPlugin, WebappPlugin, JRebelPlugin, DockerPlugin, JavaAppPackaging))
  .settings(
      addCommandAlias("debug-restart", "; jetty:stop ; fastOptJS ; package ; jetty:start"),
      addCommandAlias("reset", "; clean ; compile ; fastOptJS "),
      addCommandAlias("full-reset", "; clean ; package ; fastOptJS "),
      addCommandAlias("buildDockerImage", "; clean ; fastOptJS ; package; docker:buildImage"),
      addCommandAlias("buildAndPublishDockerImage", "; clean ; fastOptJS ; package; docker:publishToCloud"),
      libraryDependencies ++= Seq(
          //CommonDeps.ssysJDBCWrapper,
          CommonDeps.scalaTest % Test
      )
  )
  .jvmSettings(

      JRebelPlugin.jrebelSettings,
      jrebel.webLinks += (sourceDirectory in Compile).value / "webapp",
      jrebel.enabled := true,

      javaOptions in Jetty ++= Seq(
          "-javaagent:jrebel/jrebel.jar",
          "-noverify",
          "-XX:+UseConcMarkSweepGC",
          "-XX:+CMSClassUnloadingEnabled"
      ),

      libraryDependencies ++= Seq(
          CommonDeps.servletAPI % Provided,
          CommonDeps.ssysIscComponents,
          CommonDeps.ssysScalaIOExtender,
          CommonDeps.ssysXMLExtender,
          CommonDeps.circeExtender,
          CommonDeps.ssysCommonWebapp,

          CommonDeps.smartclient,

          CommonDeps.scalaTest % Test,

          CommonDeps.scalaTags
      )
  )
  .jvmSettings(

      //coffeeScript
      CoffeeScriptKeys.sourceMap := false,
      CoffeeScriptKeys.bare := false,
      sourceDirectory in Assets := (sourceDirectory in Compile).value / "webapp" / "coffeescript" / "developed" / "developedComponents",
      webTarget := (sourceDirectory in Compile).value / "webapp" / "javascript" / "generated" / "generatedComponents" / "coffeescript",
      (managedResources in Compile) ++= CoffeeScriptKeys.coffeeScript.value,

      //dev plugin
      sourceSchemaDir in DevConfig := (resourceDirectory in(`db-objects-jvm`, Compile)).value / "defs",
      startPackageName in DevConfig := "ru.simplesys.defs",
      contextPath in DevConfig := "aps",
      maxArity in DevConfig := 254,
      quoted in DevConfig := true,
      sourceGenerators in Compile += (generateScalaCode in DevConfig),

      //merger
      mergeMapping in MergeWebappConfig := Seq(
          ("com.simplesys.core", "common-webapp") -> Seq(
              Seq("webapp", "javascript", "generated", "generatedComponents", "coffeescript") -> Some(Seq("webapp", "managed", "javascript", "common-webapp", "generated", "generatedComponents", "coffeescript")),
              Seq("webapp", "javascript", "developed") -> Some(Seq("webapp", "managed", "javascript", "common-webapp", "developed")),
              Seq("webapp", "coffeescript", "developed") -> Some(Seq("webapp", "managed", "coffeescript", "common-webapp", "developed")),
              Seq("webapp", "css") -> Some(Seq("webapp", "managed", "css", "common-webapp")),
              Seq("webapp", "html") -> Some(Seq("webapp", "managed", "html", "common-webapp")),
              Seq("webapp", "images") -> Some(Seq("webapp", "managed", "images", "common-webapp"))
          ),
          ("com.simplesys.core", "isc-components") -> Seq(
              Seq("webapp", "javascript", "generated", "generatedComponents") -> Some(Seq("webapp", "managed", "javascript", "isc-components", "generated", "generatedComponents")),
              Seq("webapp", "javascript", "generated", "generatedComponents", "coffeescript") -> Some(Seq("webapp", "managed", "javascript", "isc-components", "generated", "generatedComponents", "coffeescript")),
              Seq("javascript", "com", "simplesys") -> Some(Seq("webapp", "managed", "javascript", "isc-components", "developed", "developedComponents")),
              Seq("coffeescript") -> Some(Seq("webapp", "managed", "coffeescript", "isc-components", "developed", "developedComponents"))
          ),
          ("com.simplesys", "jsgantt-improved") -> Seq(
              Seq("javascript") -> Some(Seq("webapp", "managed", "javascript", "jsgantt-improved")),
              Seq("css") -> Some(Seq("webapp", "managed", "css", "jsgantt-improved"))
          ),
          ("com.simplesys", "smartclient-js") -> Seq(
              Seq("isomorphic") -> Some(Seq("webapp", "isomorphic"))
          )
      ),
      webAppDirPath in MergeWebappConfig := (sourceDirectory in Compile).value,
      merge in MergeWebappConfig := (merge in MergeWebappConfig).dependsOn(CoffeeScriptKeys.coffeeScript in Assets).value,

      //xsbtWeb
      containerPort := 8084,
      containerArgs := Seq("--path", "/aps"),
      containerLibs in Jetty := Seq(
          CommonDeps.jettyRuner intransitive()
      ),
      artifactName := { (v: ScalaVersion, m: ModuleID, a: Artifact) =>
          a.name + "." + a.extension
      },
      webappWebInfClasses := true,


      //      docker
      //      defaultLinuxInstallLocation in Docker := "",
      //      dockerBaseImage := "ivcinform/jetty:9.4.7.v20170914",
      //      daemonUser in Docker := "",
      //      daemonGroup in Docker := "",
      //      dockerDocfileCommands := Seq(),
      //      dockerEntrypoint := Seq(),
      //      dockerCmd := Seq(),
      //      dockerExposedPorts in Docker := Seq(8080),

      //      packageName in Docker := CommonSettings.settingValues.name,
      //      dockerUsername in Docker := None,
      //      dockerRepository in Docker := Some("hub.docker.com"),
      //      dockerRepository := Some("ivcinform"),
      //      dockerUpdateLatest := false,
      //      dockerAlias in Docker := DockerAlias(dockerRepository.value, (dockerUsername in Docker).value, CommonSettings.settingValues.name, Some(version.value)),
      //      dockerDocfileCommands := Seq(
      //          copy(s"webapp/", s"/var/lib/jetty/webapps/${CommonSettings.settingValues.name}"),
      //          entrypoint("/docker-entrypoint.sh"),
      //      ),
      (resourceGenerators in Compile) += task[Seq[File]] {

          val aboutFile: File = (sourceDirectory in Compile).value / "webapp" / "javascript" / "generated" / "generatedComponents" / "MakeAboutData.js"

          import scala.reflect.ClassTag
          import scala.reflect.runtime.universe._
          import scala.reflect.runtime.{universe ⇒ ru}

          def makeVersionList[T: TypeTag : ClassTag](e: T): Unit = {

              val classLoaderMirror = ru.runtimeMirror(this.getClass.getClassLoader)
              val `type`: ru.Type = ru.typeOf[T]

              val decls = `type`.declarations.sorted.filter(_.isMethod).filter(!_.name.toString.contains("<init>"))
              val im = classLoaderMirror reflect e

              Common.list append (decls.map {
                  item =>
                      val shippingTermSymb = `type`.declaration(ru.newTermName(item.name.toString)).asTerm
                      val shippingFieldMirror = im reflectField shippingTermSymb
                      val res = shippingFieldMirror.get.toString()

                      Info(item.name.toString, res)
              }: _ *)
          }

          Common.list append (Seq(
              Info("Разработка :", "АО ИВЦ \"Информ\" (info@ivc-inform.ru)"),
              Info("Версия :", version.value)
          ): _*)

          makeVersionList(CommonDeps.versions)
          makeVersionList(PluginDeps.versions)

          IO.write(aboutFile, s"simpleSyS.aboutData = ${Common.spaces2}")
          Seq()
      },

      skip in packageJSDependencies := false
  )
  .jsConfigure(_ enablePlugins (DevPlugin))
  .jsSettings(
      skip in packageJSDependencies := false,
      jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv(),

      //dev plugin
      sourceSchemaDir in DevConfig := (resourceDirectory in(`db-objects-jvm`, Compile)).value / "defs",
      startPackageName in DevConfig := "ru.simplesys.defs",
      contextPath in DevConfig := "aps",
      maxArity in DevConfig := 254,
      quoted in DevConfig := true,
      sourceGenerators in Compile += (generateScalaJSCode in DevConfig),

      //scala.js
      crossTarget in fastOptJS := (sourceDirectory in Compile).value / ".." / ".." / ".." / ".jvm" / "src" / "main" / "webapp" / "javascript" / "generated" / "generatedComponentsJS",
      crossTarget in fullOptJS := (sourceDirectory in Compile).value / ".." / ".." / ".." / ".jvm" / "src" / "main" / "webapp" / "javascript" / "generated" / "generatedComponentsJS",
      crossTarget in packageJSDependencies := (sourceDirectory in Compile).value / ".." / ".." / ".." / ".jvm" / "src" / "main" / "webapp" / "javascript" / "generated" / "generatedComponentsJS",

      libraryDependencies ++= Seq(
          CommonDeps.ssysJDBCWrapper,

          CommonDepsScalaJS.smartClientWrapper.value,
          CommonDepsScalaJS.scalaTags.value,
          CommonDepsScalaJS.jQuery.value,
          CommonDepsScalaJS.scalaDom.value,

          CommonDepsScalaJS.circeExtender.value,
          CommonDepsScalaJS.servletWrapper.value
      )
  )


lazy val `web-ui-js` = `web-ui`.js
lazy val `web-ui-jvm` = `web-ui`.jvm

