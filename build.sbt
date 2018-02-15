import Common._
import com.typesafe.sbt.SbtGit.git
import ru.simplesys.plugins.sourcegen.DevPlugin._
import ru.simplesys.sbprocessing.sbtbuild.CommonSettings

name := "rtm-processing"

lazy val root = (project in file(".")).
  enablePlugins(GitVersioning).
  aggregate(processingCore, processingTest, webUI, dbObjects).
  settings(inThisBuild(Seq(
      git.baseVersion := CommonSettings.settingValues.baseVersion,
      scalaVersion := CommonSettings.settingValues.scalaVersion,
      scalacOptions := CommonSettings.settingValues.scalacOptions,
      organization := CommonSettings.settingValues.organization,
      publishTo := {
          val corporateRepo = "http://toucan.simplesys.lan/"
          if (isSnapshot.value)
              Some("snapshots" at corporateRepo + "artifactory/libs-snapshot-local")
          else
              Some("releases" at corporateRepo + "artifactory/libs-release-local")
      },
      credentials += Credentials(Path.userHome / ".ivy2" / ".credentials"),
      liquibaseUsername in DevConfig := "mosk",
      liquibasePassword in DevConfig := "m125osk",
      liquibaseDriver in DevConfig := "oracle.jdbc.OracleDriver",
      liquibaseUrl in DevConfig := "jdbc:oracle:thin:@orapg.simplesys.lan:1521/test"
  )
    ++ CommonSettings.defaultSettings),
      publishArtifact in(Compile, packageBin) := false,
      publishArtifact in(Compile, packageDoc) := false,
      publishArtifact in(Compile, packageSrc) := false
  )
