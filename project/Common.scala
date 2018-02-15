import com.simplesys.json.{JsonList, JsonObject}
import com.simplesys.mergewebapp.MergeWebappPlugin
import com.simplesys.mergewebapp.MergeWebappPlugin._
import com.typesafe.sbt.coffeescript.Import.CoffeeScriptKeys
import com.typesafe.sbt.coffeescript.SbtCoffeeScript
import com.typesafe.sbt.packager.archetypes.JavaAppPackaging
import com.typesafe.sbt.web.Import.WebKeys.webTarget
import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import ru.simplesys.plugins.sourcegen.DevPlugin
import ru.simplesys.plugins.sourcegen.DevPlugin._
import ru.simplesys.sbprocessing.sbtbuild.{CommonDeps, CommonDepsScalaJS, CommonSettings, PluginDeps}
import ru.simplesys.scalafmt.ScalaFormatPlugin.autoImport.scalafmtConfig
import sbt.Keys._
import sbt._
import com.typesafe.sbt.web.Import._

object Common extends Build{
    lazy val common = (project in file("common")).enablePlugins(ScalaJSPlugin).settings(

        libraryDependencies ++= Seq(
            CommonDeps.akkaActor.value,
            CommonDeps.doobieCore.value,
            CommonDeps.doobieCoreCats.value,
            CommonDeps.ssysCoreUtils.value,
            CommonDeps.log4J.value,
            CommonDeps.scalazStream.value,
            CommonDeps.scalazCore.value,
            CommonDepsScalaJS.smartClientWrapper.value
        )
    ).settings(CommonSettings.defaultProjectSettings)

    lazy val dbObjects = (project in file("db-objects")).enablePlugins(DevPlugin).settings(

        libraryDependencies ++= Seq(
            CommonDeps.ssysBoneCPWrapper.value,
            CommonDeps.ssysCoreLibrary.value,
            CommonDeps.ssysJsonExtender.value,
            CommonDeps.ssysJDBCWrapper.value,
            CommonDeps.jodaTime.value,
            CommonDeps.jodaConvert.value,
            CommonDeps.jdbcOracle11.value,
            CommonDeps.poiOOxml.value,
            CommonDeps.scalaTest.value % Test
        )
    ).settings(DevPlugin.devPluginGeneratorSettings).settings({
        Seq(
            sourceSchemaDir in DevConfig := (resourceDirectory in Compile).value / "defs",
            startPackageName in DevConfig := "ru.simplesys.defs",
            contextPath in DevConfig := "rtm-processing",
            maxArity := 254,
            sourceGenerators in Compile <+= (generateBoScalaCode in DevConfig)
        )
    }).settings(CommonSettings.defaultProjectSettings)

    lazy val processingCore = (project in file("processing-core"))
      .dependsOn(common, dbObjects)
      .settings(

        unmanagedClasspath in Runtime ++= Seq(baseDirectory.value / "data", baseDirectory.value / "etc"),
        unmanagedClasspath in Test ++= Seq(baseDirectory.value / "data", baseDirectory.value / "etc"),

        /*ScenarionPlugin.generateScenario := true,
        ScenarionPlugin.testModeGenerate := true,

        ScenarionPlugin.templateDir := Some(sourceDirectory.value / "main" / "scala" / "com" / "simplesys" / "templates"),
        ScenarionPlugin.miscDir := Some(sourceDirectory.value / "main" / "scala" / "com" / "simplesys" / "templates" / "misc"),*/

        scalafmtConfig in ThisBuild := Some(file(".scalafmt.conf")),
        resolvers += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/maven-releases/",

        libraryDependencies ++= Seq(
            //CommonDeps.slick.value,
            CommonDeps.dmProcessingTokenizer.value,
            CommonDeps.dmProcessingDictionary.value,
            CommonDeps.dmProcessingTemplates.value,
            CommonDeps.dmProcessingClassifier.value,
            CommonDeps.dmProcessingDbObjects.value, // :((((
            CommonDeps.dmProcessingXmlHelper.value,
            CommonDeps.scenarioBuilder.value,
            CommonDeps.ssysCommonWebapp.value,
            CommonDeps.ssysConfigWrapper.value,
            CommonDeps.akkaActor.value,
            CommonDeps.akkaPersistence.value,
            CommonDeps.akkaHttp.value,
            //CommonDeps.akkaQuery.value,
            CommonDeps.akkaSLF4J.value,
            //CommonDeps.akkaPersistenceInMemory.value,
            CommonDeps.akkaPersistenceJDBC.value,
            CommonDeps.akkaPersistenceInMemory.value,
            CommonDeps.kryoSerialization.value,
            CommonDeps.uPickle.value,
            CommonDeps.lz4.value,
            CommonDeps.doobieCore.value,
            CommonDeps.doobieCoreCats.value,
            CommonDeps.ssysScalaIOExtender.value,
            CommonDeps.ssysCommon.value,
            CommonDeps.ssysAkkaExtender.value,
            CommonDeps.simmetricCore.value,
            CommonDeps.log4J.value,
            CommonDeps.csvReader.value,
            CommonDeps.apacheCommonLang.value,
            CommonDeps.kamonCore.value,
            CommonDeps.kamonScala.value,
            CommonDeps.kamonAkka.value,
            CommonDeps.kamonSystemMetrics.value,
            CommonDeps.kamonStatsD.value,
            CommonDeps.scopt.value,
            CommonDeps.utilEval.value,
            CommonDeps.scalaFmt.value,
            CommonDeps.scalaTest.value % Test
        )
    ).settings(
        //fork in run := true,
        //packAutoSettings
    ).settings(
        //        packJvmOpts := Map("rtm-processing-app" -> Seq(
        //            "-Xms32g", "-Xmx64g",
        //            "-verbose:gc -Xloggc:main-gc.log", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=0.0.0.0:6005",
        //            "-Dcom.sun.management.jmxremote", "-Dcom.sun.management.jmxremote.port=9010", "-Dcom.sun.management.jmxremote.local.only=false",
        //            "-Dcom.sun.management.jmxremote.authenticate=false", "-Dcom.sun.management.jmxremote.ssl=false", "-Djava.rmi.server.hostname=0.0.0.0"
        //        ))
    ).settings(CommonSettings.defaultProjectSettings)


    lazy val processingTest= (project in file("processing-test")).enablePlugins(JavaAppPackaging).dependsOn(processingCore).settings(

        libraryDependencies ++= Seq(
            CommonDeps.ssysCommon.value,
            CommonDeps.doobieCore.value,
            CommonDeps.doobieCoreCats.value,
            CommonDeps.jdbcOracle11.value,
            CommonDeps.log4J.value,
            CommonDeps.scopt.value,
            CommonDeps.ssysConfigWrapper.value,
            CommonDeps.scalaTest.value % Test
        )).settings(CommonSettings.defaultProjectSettings)

    lazy val webUI = (project in file("web-ui")).enablePlugins(
        DevPlugin, MergeWebappPlugin, SbtCoffeeScript, ScalaJSPlugin).
      dependsOn(
          dbObjects,
          processingCore
      ).aggregate(dbObjects).settings(

        libraryDependencies ++= Seq(
            CommonDeps.servletAPI.value % Provided,
            CommonDeps.ssysCommonWebapp.value,
            CommonDeps.ssysIscComponents.value,
            CommonDeps.ssysXMLExtender.value,
            CommonDeps.ssysJsonExtender.value,
            CommonDeps.ssysIscMisc.value,
            CommonDeps.poiOOxml.value,

            CommonDeps.smartclient.value,
            CommonDeps.uPickle.value,
            CommonDeps.utilEval.value,

            CommonDeps.jettyWebapp.value % "container",
            CommonDeps.jettyAnnotations.value % "container",
            CommonDeps.jettyPlus.value % "container",

            CommonDeps.scalaTest.value % Test,

            CommonDepsScalaJS.smartClientWrapper.value,
            CommonDepsScalaJS.macroJS.value,

            CommonDepsScalaJS.uPickleJS.value,
            CommonDepsScalaJS.scalaTags.value

        )

    ).settings(DevPlugin.devPluginGeneratorSettings).settings({
        Seq(
            //scala.js
            crossTarget in fastOptJS := (sourceDirectory in Compile).value / "webapp" / "javascript" / "generatedScalaJS",
            crossTarget in fullOptJS := (sourceDirectory in Compile).value / "webapp" / "javascript" / "generatedScalaJS",
            crossTarget in packageJSDependencies := (sourceDirectory in Compile).value / "webapp" / "javascript" / "generatedScalaJS",

            //coffeeScript
            CoffeeScriptKeys.sourceMap := false,
            CoffeeScriptKeys.bare := false,
            webTarget := (sourceDirectory in Compile).value / "webapp" / "javascript" / "generated" / "generatedComponents" / "coffeescript",
            sourceDirectory in Assets := (sourceDirectory in Compile).value / "webapp" / "coffeescript" / "developed" / "developedComponents",
            (managedResources in Compile) ++= CoffeeScriptKeys.coffeeScript.value,

            //dev plugin
            sourceSchemaDir in DevConfig := (resourceDirectory in(dbObjects, Compile)).value / "defs",
            startPackageName in DevConfig := "ru.simplesys.defs",
            contextPath in DevConfig := "rtm-processing",
            maxArity in DevConfig := 254,

            sourceGenerators in Compile <+= generateScalaCode in DevConfig,

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
                ("com.simplesys.core", "isc-misc") -> Seq(
                    Seq("javascript") -> Some(Seq("webapp", "managed", "javascript", "isc-misc"))
                ),
                ("com.simplesys", "smartclient-js") -> Seq(
                    Seq("isomorphic") -> Some(Seq("webapp", "isomorphic"))
                )
            ),
            currentProjectGenerationDirPath in MergeWebappConfig := (sourceDirectory in Compile).value / "webapp" / "javascript" / "generated" / "generatedComponents",
            currentProjectDevelopedDirPath in MergeWebappConfig := (sourceDirectory in Compile).value / "webapp" / "javascript" / "developed",
            currentProjectCoffeeDevelopedDirPath in MergeWebappConfig := (sourceDirectory in Compile).value / "webapp" / "coffeescript" / "developed",
            merge in MergeWebappConfig <<= (merge in MergeWebappConfig).dependsOn(SbtCoffeeScript.autoImport.CoffeeScriptKeys.coffeeScript in Assets),

            (resourceGenerators in Compile) += task[Seq[File]] {

                val aboutFile: File = (sourceDirectory in Compile).value / "webapp" / "javascript" / "generated" / "generatedComponents" / "MakeAboutData.js"

                val list = JsonList()

                import scala.reflect.ClassTag
                import scala.reflect.runtime.universe._
                import scala.reflect.runtime.{universe ⇒ ru}

                def makeVersionList[T: TypeTag : ClassTag](e: T): Unit = {

                    val classLoaderMirror = ru.runtimeMirror(this.getClass.getClassLoader)
                    val `type`: ru.Type = ru.typeOf[T]

                    val classSymbol = `type`.typeSymbol.asClass

                    val decls = `type`.declarations.sorted.filter(_.isMethod).filter(!_.name.toString.contains("<init>"))
                    val im = classLoaderMirror reflect e

                    decls.foreach {
                        item =>

                            val shippingTermSymb = `type`.declaration(ru.newTermName(item.name.toString)).asTerm
                            val shippingFieldMirror = im reflectField shippingTermSymb
                            val res = shippingFieldMirror.get.toString()

                            list += JsonObject("libName" -> item.name.toString, "libVersion" -> res)
                    }
                }

                list ++= Seq(
                    JsonObject("libName" -> "Разработчики :", "libVersion" -> "Юдин Андрей (uandrew1965@gmail.com)"),
                    JsonObject("libName" -> "Версия :", "libVersion" -> version.value)
                )

                makeVersionList(CommonDeps.versions)
                makeVersionList(PluginDeps.versions)

                IO.write(aboutFile, s"simpleSyS.aboutData = ${list.toPrettyString}")
                Seq()
            }
        )
    }).settings(CommonSettings.defaultProjectSettings)
}


