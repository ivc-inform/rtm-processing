import sbt._

object PluginDeps {
    object versions {
        val sbtNativePackagerVersion = "1.3.3"

        val devPluginVersion = "1.3.18"
        val sbtCoffeScriptVersion = "1.1.6"
        val mergeJSVersion = "1.0.15"
        val xsbtWebVersion = "4.0.2"

        val scalaJSPluginVersion = "0.6.22"
        val scalaCrossProjectPluginVersion = "0.3.4"
        val jrabelPluginVersion = "0.11.1"
    }

    val sbtCrossproject = addSbtPlugin("org.scala-native" % "sbt-scalajs-crossproject" % versions.scalaCrossProjectPluginVersion)
    val crossproject = addSbtPlugin("org.scala-native" % "sbt-crossproject" % versions.scalaCrossProjectPluginVersion)
    val sbtNativePackager = addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % versions.sbtNativePackagerVersion)
    val devPlugin = addSbtPlugin("ru.simplesys" % "dev-plugin" % versions.devPluginVersion)
    val sbtCoffeeScript = addSbtPlugin("com.typesafe.sbt" % "sbt-coffeescript" % versions.sbtCoffeScriptVersion)
    val mergeJS = addSbtPlugin("ru.simplesys" % "merge-js" % versions.mergeJSVersion)
    val xsbtWeb = addSbtPlugin("com.earldouglas" % "xsbt-web-plugin" % versions.xsbtWebVersion)
    val scalaJSPlugin = addSbtPlugin("org.scala-js" % "sbt-scalajs" % versions.scalaJSPluginVersion)
    val jrebelPlugin = addSbtPlugin("com.simplesys" % "jrebel-plugin" % versions.jrabelPluginVersion)
}

object CommonDeps {
    object versions {

        val doobieVersion = "0.4.1"

        val ssysCoreVersion = "1.5.0.1"

        val poiVersion = "3.17"
        val scalaTagsVersion = "0.6.7"

        val dmProcessingVersion = "1.4.5.1"

        val scenarioBuilderVersion = "1.5.2"

        val ssysDictionariesDataVersion = "1.5.0.1"

        val akkaVersion = "2.5.9"
        val akkaHttpVersion = "10.0.11"

        val akkaPersistenceJDBCVersion = "3.2.0"
        val akkaPersistenceInMemoryVersion = "2.5.1.1"

        val lz4Version = "1.3.0"
        val kryoSerializationVersion = "0.5.2"

        val scalazVersion = "7.2.19"
        val scalazStreamVersion = "0.8.6"

        val log4jVersion = "1.2.17"
        val apacheCommonLangVersion = "2.6"
        val simmetricNameCore = "4.1.1"
        val oracle11DriverVersion = "11.2.0.4"
        val jdbcOracle12DriverVersion = "12.2.0.1"

        val scalaTestVersion = "3.0.4"
        val csvReaderVersion = "1.3.5"
        val scoptVersion = "3.7.0"
        val scalajsDOMVersion = "0.9.3"

        val udashJQueryVersion = "1.1.0"

        val ssCrossVersion = "1.0.0.6"

        val scalaFmtVersion = "0.6.8"

        val servletAPIVersion = "4.0.0"
        val smartclientVersion = "11.1-v20170703.2"
        val jettyVersion = "9.4.8.v20171121"

        val scalaJSVersion = "1.5.0.1"
    }

    val scalazCore = "org.scalaz" %% "scalaz-core" % versions.scalazVersion
    val scalazStream = "org.scalaz.stream" %% "scalaz-stream" % versions.scalazStreamVersion

    val akkaActor = "com.typesafe.akka" %% "akka-actor" % versions.akkaVersion
    val akkaSLF4J = "com.typesafe.akka" %% "akka-slf4j" % versions.akkaVersion
    val akkaPersistence = "com.typesafe.akka" %% "akka-persistence" % versions.akkaVersion
    val akkaHttp = "com.typesafe.akka" %% "akka-http" % versions.akkaHttpVersion

    val log4J = "log4j" % "log4j" % versions.log4jVersion

    val apacheCommonLang = "commons-lang" % "commons-lang" % versions.apacheCommonLangVersion
    val simmetricCore = "com.github.mpkorstanje" % "simmetrics-core" % versions.simmetricNameCore


    val ssysConfigWrapper = "com.simplesys.core" %% "config-wrapper" % versions.ssysCoreVersion
    val ssysCommon = "com.simplesys" %% "common" % "1.5.0.1"
    val ssysScalaIOExtender = "com.simplesys" %% "scala-io-extender" % "1.5.0.1"
    val ssysLogBackWrapper = "com.simplesys" %% "logback-wrapper" % "1.5.0.1"
    val ssysAkkaExtender = "com.simplesys.core" %% "akka-extender" % versions.ssysCoreVersion
    val utilEval = "com.simplesys.core" %% "util-eval-extender" % versions.ssysCoreVersion
    val scalaFmt = "com.geirsson" %% "scalafmt-core" % versions.scalaFmtVersion

    val akkaPersistenceJDBC = "com.github.dnvriend" %% "akka-persistence-jdbc" % versions.akkaPersistenceJDBCVersion
    val akkaPersistenceInMemory = "com.github.dnvriend" %% "akka-persistence-inmemory" % versions.akkaPersistenceInMemoryVersion
    val kryoSerialization = "com.github.romix.akka" %% "akka-kryo-serialization" % versions.kryoSerializationVersion
    val lz4 = "net.jpountz.lz4" % "lz4" % versions.lz4Version

    val dmProcessingTokenizer = "ru.mfms.mfmd.text" %% "tokenizer" % versions.dmProcessingVersion
    val dmProcessingDictionary = "ru.mfms.mfmd.text" %% "dictionary" % versions.dmProcessingVersion
    val dmProcessingTemplates = "ru.mfms.mfmd.text" %% "templates" % versions.dmProcessingVersion
    val dmProcessingClassifier = "ru.mfms.mfmd.text" %% "classifier" % versions.dmProcessingVersion
    val dmProcessingXmlHelper = "ru.mfms.mfmd.text" %% "xml-helper" % versions.dmProcessingVersion

    val jdbcOracle12 = "com.oracle.jdbc" % "ojdbc8" % versions.jdbcOracle12DriverVersion

    val doobieCore = "org.tpolecat" %% "doobie-core" % versions.doobieVersion
    val doobieCoreCats = "org.tpolecat" %% "doobie-core-cats" % versions.doobieVersion

    val kamonCore = "io.kamon" %% "kamon-core" % "1.0.1"
    val kamonScala = "io.kamon" %% "kamon-scala" % "0.6.7"
    val kamonAkka = "io.kamon" %% "kamon-akka-2.4" % "0.6.6"
    val kamonSystemMetrics = "io.kamon" %% "kamon-system-metrics" % "1.0.0"
    val kamonStatsD = "io.kamon" %% "kamon-statsd" % "0.6.7"

    val scalaTest = "org.scalatest" %% "scalatest" % versions.scalaTestVersion

    val csvReader = "com.github.tototoshi" %% "scala-csv" % versions.csvReaderVersion

    val scopt = "com.github.scopt" %% "scopt" % versions.scoptVersion

    val scenarioConfigurator = "com.simplesys.scenarioBuilder" %% "scenario-configurator" % versions.scenarioBuilderVersion
    val uPickle = "com.simplesys.scenarioBuilder" %% "scenario-upickle" % versions.scenarioBuilderVersion

    val hikariPoolDataSources = "com.simplesys.core" %% "hikari-cp" % versions.ssysCoreVersion

    val servletAPI = "javax.servlet" % "javax.servlet-api" % versions.servletAPIVersion
    val ssysCommonWebapp = "com.simplesys.core" %% "common-webapp" % versions.ssysCoreVersion
    val ssysIscComponents = "com.simplesys.core" %% "isc-components" % versions.ssysCoreVersion
    val ssysXMLExtender = "com.simplesys.core" %% "xml-extender" % versions.ssysCoreVersion
    val ssysIscMisc = "com.simplesys.core" %% "isc-misc" % versions.ssysCoreVersion
    val ssysCoreLibrary = "com.simplesys.core" %% "core-library" % versions.ssysCoreVersion
    val ssysCoreUtils = "com.simplesys.core" %% "core-utils" % versions.ssysCoreVersion
    val ssysJDBCWrapper = "com.simplesys.core" %% "jdbc-wrapper" % versions.ssysCoreVersion

    val smartclient = "com.simplesys" %% "smartclient-js" % versions.smartclientVersion

    val jettyRuner = "org.eclipse.jetty" % "jetty-runner" % versions.jettyVersion

    val poiOOxml = "org.apache.poi" % "poi-ooxml" % versions.poiVersion
    val scalaTags = "com.lihaoyi" %% "scalatags" % versions.scalaTagsVersion

    val circeExtender = "com.simplesys.cross" %% "circe-extender" % versions.ssCrossVersion
}

