import sbt.Keys._
import sbt._
import sbt.{Credentials, Path}

object CommonSettings {
    object settingValues {
        val baseVersion = "1.3"
        val name = "rtm-processing"

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

    val publishSettings = inThisBuild(Seq(
        publishMavenStyle := true,
        publishTo := {
            val corporateRepo = "http://maven-repo.mfms/"
            if (isSnapshot.value)
                Some("snapshots" at corporateRepo + "nexus/content/repositories/mfmd-snapshot/")
            else
                Some("releases" at corporateRepo + "nexus/content/repositories/mfmd-release/")
        },
        credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")
    ))

    val noPublishSettings = inThisBuild(Seq(
        publishArtifact := false,
        packagedArtifacts := Map.empty,
        publish := {},
        publishLocal := {}
    ))
}
