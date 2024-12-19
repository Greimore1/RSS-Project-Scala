
name := "RSSReaderBackend"

version := "0.1"

scalaVersion := "2.13.12"

libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-dsl" % "0.23.23",
  "org.http4s" %% "http4s-ember-server" % "0.23.23",
  "org.http4s" %% "http4s-circe" % "0.23.23",
  "io.circe" %% "circe-generic" % "0.14.6",
  "io.circe" %% "circe-parser" % "0.14.6",
  "org.scala-lang.modules" %% "scala-xml" % "2.0.1",
  "org.slf4j" % "slf4j-simple" % "2.0.9" // Add this dependency
)

