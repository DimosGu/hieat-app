import _root_.sbt.Keys._
import _root_.sbt._
import com.thoughtworks.sbtApiMappings.ApiMappings
import play.routes.compiler.InjectedRoutesGenerator
import play.sbt.Play.autoImport._
import play.sbt.PlayScala
import play.sbt.routes.RoutesKeys
import play.twirl.sbt.Import.TwirlKeys

object Build extends Build {

  import BuildSettings._

  override lazy val settings = super.settings :+ {
    shellPrompt := (s => Project.extract(s).currentProject.id + " > ")
  }

  ///////////////////////////////////////////////////////////////
  // hellocode project
  ///////////////////////////////////////////////////////////////
  lazy val hieat = Project("hieat", file("."))
    .enablePlugins(PlayScala)
    .enablePlugins(ApiMappings)
    .aggregate(hiPlatform, hiCommon)
    .dependsOn(hiPlatform, hiCommon)
    .settings(basicSettings: _*)
    .settings(
      description := "hieat",
      TwirlKeys.templateImports ++= Seq("me.hieat.site.data.domain", "me.hieat.site.common.enums._" /*, "me.hieat.site.data.model._"*/),
      RoutesKeys.routesGenerator := InjectedRoutesGenerator,
      PlayKeys.playRunHooks <+= baseDirectory.map(base => AssetsRunHook(base)),
      libraryDependencies ++= (
        __compile(ws) ++
          __compile(_scalaModules) ++
          __compile(_scalaLogging) ++
          __compile(_commonsEmail) ++
          __test(_scalatestPlay)),
      libraryDependencies ~= {
        _ map {
          case m if m.organization == "com.typesafe.play" =>
            m.exclude("commons-logging", "commons-logging").
              exclude("com.typesafe.akka", "akka-actor").
              exclude("com.typesafe.akka", "akka-slf4j").
              exclude("org.scala-lang", "scala-library").
              exclude("org.scala-lang", "scala-compiler").
              exclude("org.scala-lang", "scala-reflect").
              exclude("org.scala-lang.modules", "scala-xml").
              exclude("org.scala-lang.modules", "scala-parser-combinators").
              excludeAll()
          case m => m
        }
      })

  ///////////////////////////////////////////////////////////////
  // projects
  ///////////////////////////////////////////////////////////////
  lazy val hiPlatform = Project("hi-platform", file("hi-platform"))
    .enablePlugins(ApiMappings)
    .dependsOn(hiCommon)
    .settings(basicSettings: _*)
    .settings(
      description := "hi-platform",
      libraryDependencies ++= (
        __provided(_play) ++
          __provided(_scalaLogging) ++
          __compile(_slick) ++
          __compile(_hikariCP) ++
          __compile(_slickPg) ++
          __compile(_postgresql) ++
          __compile(_patchca) ++
          __compile(_redis) ++
          __compile(_akkaActor) ++
          __compile(_akkaSlf4j)))

  lazy val hiCommon = Project("hi-common", file("hi-common"))
    .enablePlugins(ApiMappings)
    .settings(basicSettings: _*)
    .settings(
      description := "hi-common",
      libraryDependencies ++= (
        __compile(_bson) ++
          __compile(_commonsLang3) ++
          __compile(_bouncycastle) ++
          __compile(_commonsCodec) ++
          __provided(_playJson) ++
          __provided(_commonsEmail) ++
          __provided(_scalaLogging) ++
          __provided(_typesafeConfig)))

}

