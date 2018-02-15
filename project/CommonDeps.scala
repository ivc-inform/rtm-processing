package ru.simplesys.sbprocessing.sbtbuild

import sbt._

object PluginDeps {
    object versions {
        val scalaFmtPlaginVersion = "1.0.1"
        val sbtNativePackagerVersion = "1.3.3"

        val devPluginVersion = "1.0.37"

        val sbtCoffeScriptVersion = "1.0.3.1"

        val mergeJSVersion = "1.0.9"

        val sbtAspectJVersion = "0.10.2"
        val xsbtWebVersion = "0.9.1"
        
        val scalaJSPluginVersion = "0.6.19"
        val scalaFixVersion = "0.3.2"
    }

    val sbtNativePackager = addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % versions.sbtNativePackagerVersion)
    val scalaFmtPlugin = addSbtPlugin("ru.simplesys" % "scala-fmt" % versions.scalaFmtPlaginVersion)
    val devPlugin = addSbtPlugin("ru.simplesys" % "dev-plugin" % versions.devPluginVersion)
    val sbtCoffeeScript = addSbtPlugin("com.typesafe.sbt" % "sbt-coffeescript" % versions.sbtCoffeScriptVersion)
    val mergeJS = addSbtPlugin("ru.simplesys" % "merge-js" % versions.mergeJSVersion)
    val xsbtWeb = addSbtPlugin("com.earldouglas" % "xsbt-web-plugin" % versions.xsbtWebVersion)
    val scalaJSPlugin = addSbtPlugin("org.scala-js" % "sbt-scalajs" % versions.scalaJSPluginVersion)
}

object CommonDeps {
    object versions {

        val jodaVersion = "2.9.4"
        val jodaConvertVersion = "1.8.1"

        val doobieVersion = "0.4.1"

        val ssysCoreVersion = "1.3.1"
        //val ssysCoreVersion = "1.3-SNAPSHOT"

        val poiVersion = "3.15"
        val scalaTagsVersion = "0.6.3"

        //val dmProcessingVersion = "1.3-SNAPSHOT"
        val dmProcessingVersion = "1.3.1"

        val scenarioBuilderVersion = "1.3.1"
        //val scenarioBuilderVersion = "1.3-SNAPSHOT"


        val ssysDictionariesDataVersion = "1.1.2"
        val dictionaryMitEduInterfaceVersion = "2.3.3"

        //val akkaVersion = "2.5.1"
        val akkaVersion = "2.5.9"
        val akkaHttpVersion = "10.0.11"

        val akkaPersistenceJDBCVersion = "2.4.17.1"
        val akkaPersistenceInMemoryVersion = "2.4.17.3"

        val h2Version = "1.4.192"

        val uPickleVersion = "0.4.4"
        val lz4Version = "1.3.0"
        val kryoSerializationVersion = "0.5.2"

        val scalazVersion = "7.2.8"
        val scalazStreamVersion = "0.8.6"

        val log4jVersion = "1.2.17"
        val apacheCommonLangVersion = "2.6"
        val simmetricNameCore = "4.0.1"
        val oracle11DriverVersion = "11.2.0.4"

        val scalaTestVersion = "3.0.1"
        val sbtPackVersion = "0.8.0"
        val csvReaderVersion = "1.3.4"
        val scoptVersion = "3.5.0"

        val kamonVersion = "0.6.6"
        val slickVersion = "3.2.0"
        val scalaFmtVersion = "0.6.3"

        val servletAPIVersion = "3.1.0"
        val smartclientVersion = "11.0-v20160805.11"
        //val jettyVersion = "9.4.5.v20170502"
        val jettyVersion = "9.4.8.v20171121"

        //val scalaJSVersion = "1.3-SNAPSHOT"
        val scalaJSVersion = "1.3.6"
    }

    val jodaTime = Def.setting("joda-time" % "joda-time" % versions.jodaVersion)
    val jodaConvert = Def.setting("org.joda" % "joda-convert" % versions.jodaConvertVersion)

    val scalazCore = Def.setting("org.scalaz" %% "scalaz-core" % versions.scalazVersion)
    val scalazStream = Def.setting("org.scalaz.stream" %% "scalaz-stream" % versions.scalazStreamVersion)

    val akkaActor = Def.setting("com.typesafe.akka" %% "akka-actor" % versions.akkaVersion)
    val akkaSLF4J = Def.setting("com.typesafe.akka" %% "akka-slf4j" % versions.akkaVersion)
    val akkaPersistence = Def.setting("com.typesafe.akka" %% "akka-persistence" % versions.akkaVersion)
    val akkaHttp = Def.setting("com.typesafe.akka" %% "akka-http" % versions.akkaHttpVersion)

    val log4J = Def.setting("log4j" % "log4j" % versions.log4jVersion)

    val apacheCommonLang = Def.setting("commons-lang" % "commons-lang" % versions.apacheCommonLangVersion)
    val simmetricCore = Def.setting("com.github.mpkorstanje" % "simmetrics-core" % versions.simmetricNameCore)

    val ssysConfigWrapper = Def.setting("com.simplesys.core" %% "config-wrapper" % versions.ssysCoreVersion)
    val ssysCommon = Def.setting("com.simplesys.core" %% "common" % versions.ssysCoreVersion)
    val ssysScalaIOExtender = Def.setting("com.simplesys.core" %% "scala-io-extender" % versions.ssysCoreVersion)
    val ssysBoneCPWrapper = Def.setting("com.simplesys.core" %% "bonecp-wrapper" % versions.ssysCoreVersion)
    val ssysLogBackWrapper = Def.setting("com.simplesys.core" %% "logback-wrapper" % versions.ssysCoreVersion)
    val ssysAkkaExtender = Def.setting("com.simplesys.core" %% "akka-extender" % versions.ssysCoreVersion)
    val utilEval = Def.setting("com.simplesys.core" %% "util-eval-extender" % versions.ssysCoreVersion)
    val scalaFmt = Def.setting("com.geirsson" %% "scalafmt-core" % versions.scalaFmtVersion)

    val akkaPersistenceJDBC = Def.setting("com.github.dnvriend" %% "akka-persistence-jdbc" % versions.akkaPersistenceJDBCVersion)
    val akkaPersistenceInMemory = Def.setting("com.github.dnvriend" %% "akka-persistence-inmemory" % versions.akkaPersistenceInMemoryVersion)
    val kryoSerialization = Def.setting("com.github.romix.akka" %% "akka-kryo-serialization" % versions.kryoSerializationVersion)
    val lz4 = Def.setting("net.jpountz.lz4" % "lz4" % versions.lz4Version)

    val dmProcessingTokenizer = Def.setting("com.simplesys.dmprocessing" %% "tokenizer" % versions.dmProcessingVersion)
    val dmProcessingDictionary = Def.setting("com.simplesys.dmprocessing" %% "dictionary" % versions.dmProcessingVersion)
    val dmProcessingTemplates = Def.setting("com.simplesys.dmprocessing" %% "templates" % versions.dmProcessingVersion)
    val dmProcessingClassifier = Def.setting("com.simplesys.dmprocessing" %% "classifier" % versions.dmProcessingVersion)
    val dmProcessingXmlHelper = Def.setting("com.simplesys.dmprocessing" %% "xml-helper" % versions.dmProcessingVersion)
    val dmProcessingDbObjects = Def.setting("com.simplesys.dmprocessing" %% "db-objects" % versions.dmProcessingVersion)

    val jdbcOracle11 = Def.setting("com.simplesys.jdbc.drivers" % "oracle" % versions.oracle11DriverVersion)

    val doobieCore = Def.setting("org.tpolecat" %% "doobie-core" % versions.doobieVersion)
    val doobieCoreCats = Def.setting("org.tpolecat" %% "doobie-core-cats" % versions.doobieVersion)

    val kamonCore = Def.setting("io.kamon" %% "kamon-core" % versions.kamonVersion)
    val kamonScala = Def.setting("io.kamon" %% "kamon-scala" % versions.kamonVersion)
    val kamonAkka = Def.setting("io.kamon" %% "kamon-akka-2.4" % versions.kamonVersion)
    val kamonSystemMetrics = Def.setting("io.kamon" %% "kamon-system-metrics" % versions.kamonVersion)
    val kamonStatsD = Def.setting("io.kamon" %% "kamon-statsd" % versions.kamonVersion)

    val scalaTest = Def.setting("org.scalatest" %% "scalatest" % versions.scalaTestVersion)

    val sbtPack = addSbtPlugin("org.xerial.sbt" % "sbt-pack" % versions.sbtPackVersion)
    val csvReader = Def.setting("com.github.tototoshi" %% "scala-csv" % versions.csvReaderVersion)

    val scopt = Def.setting("com.github.scopt" %% "scopt" % versions.scoptVersion)

    val scenarioBuilder = Def.setting("com.simplesys.scenarioBuilder" %% "scenario-configurator" % versions.scenarioBuilderVersion)
    val uPickle = Def.setting("com.simplesys.scenarioBuilder" %% "scenario-upickle" % versions.scenarioBuilderVersion)

    val slick = Def.setting("com.typesafe.slick" %% "slick" % versions.slickVersion)

    val servletAPI = Def.setting("javax.servlet" % "javax.servlet-api" % versions.servletAPIVersion)
    val ssysCommonWebapp = Def.setting("com.simplesys.core" %% "common-webapp" % versions.ssysCoreVersion)
    val ssysIscComponents = Def.setting("com.simplesys.core" %% "isc-components" % versions.ssysCoreVersion)
    val ssysXMLExtender = Def.setting("com.simplesys.core" %% "xml-extender" % versions.ssysCoreVersion)
    val ssysJsonExtender = Def.setting("com.simplesys.core" %% "json-extender-typesafe" % versions.ssysCoreVersion)
    val ssysIscMisc = Def.setting("com.simplesys.core" %% "isc-misc" % versions.ssysCoreVersion)
    val ssysCoreLibrary = Def.setting("com.simplesys.core" %% "core-library" % versions.ssysCoreVersion)
    val ssysCoreUtils = Def.setting("com.simplesys.core" %% "core-utils" % versions.ssysCoreVersion)
    val ssysJDBCWrapper = Def.setting("com.simplesys.core" %% "jdbc-wrapper" % versions.ssysCoreVersion)

    val smartclient = Def.setting("com.simplesys" % "smartclient-js" % versions.smartclientVersion)

    val jettyWebapp = Def.setting("org.eclipse.jetty" % "jetty-webapp" % versions.jettyVersion)
    val jettyAnnotations = Def.setting("org.eclipse.jetty" % "jetty-annotations" % versions.jettyVersion)
    val jettyPlus = Def.setting("org.eclipse.jetty" % "jetty-plus" % versions.jettyVersion)

    val poiOOxml = Def.setting("org.apache.poi" % "poi-ooxml" % versions.poiVersion)
    val scalaTags = Def.setting("com.lihaoyi" %% "scalatags" % versions.scalaTagsVersion)
}

object DepsHelper {
    def moduleId(scalaVer: String,
                 moduleId_2_11: Option[ModuleID],
                 moduleId_2_10: Option[ModuleID]): Option[ModuleID] =
        CrossVersion.partialVersion(scalaVer) match {
            case Some((2, scalaMajor)) if scalaMajor >= 11 => moduleId_2_11
            case _ => moduleId_2_10
        }
}
