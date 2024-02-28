import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}
import ReleaseTransformations._

scalaVersion in ThisBuild       := "2.12.19"
crossScalaVersions in ThisBuild := Seq("2.11.12", scalaVersion.value, "2.13.0")
organization in ThisBuild       := "com.github.vertical-blank"

onChangedBuildSource in Global  := ReloadOnSourceChanges

lazy val root = project
  .in(file("."))
  .settings(moduleName := "root")
  .settings(publishingSettings)
  .settings(noPublishSettings)
  .aggregate(scala_sql_formatterJVM, scala_sql_formatterJS)
  .dependsOn(scala_sql_formatterJVM, scala_sql_formatterJS)


lazy val scala_sql_formatter = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("."))
  .settings(moduleName := "scala-sql-formatter", sharedSettings, publishingSettings)
  .jvmSettings(
    libraryDependencies += "com.github.vertical-blank" % "sql-formatter" % "1.0.1"
  )
  .jsSettings(
    jsDependencies += "org.webjars.npm" % "sql-formatter" % "2.3.3" / "2.3.3/dist/sql-formatter.js" commonJSName "sqlFormatter",
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
  )

lazy val scala_sql_formatterJVM = scala_sql_formatter.jvm
lazy val scala_sql_formatterJS = scala_sql_formatter.js


lazy val commonScalacOptions = Def.setting {
  Seq(
    "-deprecation",
    "-encoding",
    "UTF-8",
    "-feature",
    "-language:_",
    "-unchecked",
    "-Xlint",
    "-Xlint:-nullary-unit",
    "-Ywarn-numeric-widen",
    "-Ywarn-value-discard"
  ) ++ {
    CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((2, v)) if v >= 13 =>
        Seq(
          "-Ymacro-annotations"
        )
      case _ =>
        Seq(
          "-Xfatal-warnings",
          "-Yno-adapted-args",
          "-Ypartial-unification",
          "-Xfuture"
        )
    }
  }
}

lazy val sharedSettings = Seq(
  scalacOptions ++= commonScalacOptions.value,
  (scalacOptions in Test) ~= (_.filterNot(_ == "-Xfatal-warnings")),
  libraryDependencies ++= Seq(
    "org.scalatest" %%% "scalatest" % "3.2.0" % Test
  )
)

lazy val publishingSettings = Seq(
  pgpSecretRing := file("local.secring.gpg"),
  pgpPublicRing := file("local.pubring.gpg"),
  name          := "scala-sql-formatter",
  description   := "SQL Formatter for Scala",
  releasePublishArtifactsAction := PgpKeys.publishSigned.value,
  publishMavenStyle       := true,
  publishArtifact in Test := false,
  pomIncludeRepository := { _ =>
    false
  },
  publishTo := Some(
    if (isSnapshot.value)
      Opts.resolver.sonatypeSnapshots
    else
      Opts.resolver.sonatypeStaging
  ),
  licenses := Seq("MIT" -> url("http://opensource.org/licenses/MIT")),
  homepage := Some(url("https://github.com/vertical-blank/scala-sql-formatter")),
  scmInfo := Some(
    ScmInfo(
      url("https://github.com/vertical-blank/scala-sql-formatter"),
      "scm:git@github.com:vertical-blank/scala-sql-formatter.git"
    )
  ),
  developers := List(
    Developer(
      id = "vertical-blank",
      name = "Yohei Yamana",
      email = "fixeight@gmail.com",
      url = url("https://github.com/vertical-blank")
    )
  )
) ++ sharedReleaseProcess

lazy val sharedReleaseProcess = Seq(
  releaseProcess := Seq[ReleaseStep](
    checkSnapshotDependencies,
    inquireVersions,
    runClean,
    runTest,
    setReleaseVersion,
    commitReleaseVersion,
    tagRelease,
    releaseStepCommandAndRemaining("+publishSigned"),
    setNextVersion,
    commitNextVersion,
    releaseStepCommand("sonatypeReleaseAll"),
    pushChanges
  )
)

lazy val noPublishSettings = Seq(
  publish         := {},
  publishLocal    := {},
  publishArtifact := false
)
