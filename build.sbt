import PlayKeys._

name := "monte-carlo"

version := "1.0.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

PlayKeys.externalizeResources := false

fork in run := true

javaOptions in run ++= Seq("-Xms1G", "-Xmx2G")