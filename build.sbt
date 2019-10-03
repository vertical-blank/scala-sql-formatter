import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

name := "scala-sql-formatter"

scalaVersion in ThisBuild := "2.12.8"

lazy val root = 
  crossProject(JSPlatform, JVMPlatform).
  crossType(CrossType.Full).in(file(".")).
  settings(
    name := "scala-sql-formatter",
    version := "0.1-SNAPSHOT"
  ).
  jvmSettings(
    libraryDependencies += "com.github.vertical-blank" % "sql-formatter" % "1.0"
  ).
  jsSettings(
    jsDependencies += "org.webjars.npm" % "sql-formatter" % "2.3.3" / "2.3.3/dist/sql-formatter.js" commonJSName "sqlFormatter",
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
  ).
  configs(IntegrationTest).
  settings(Defaults.itSettings: _*).
  settings(libraryDependencies += "org.scalatest" %%% "scalatest" % "3.0.8" % IntegrationTest).
  settings(
    unmanagedSourceDirectories in IntegrationTest ++=
      CrossType.Full.sharedSrcDir(baseDirectory.value, "it").toSeq
  ).
  jsSettings(inConfig(IntegrationTest)(ScalaJSPlugin.testConfigSettings): _*)

