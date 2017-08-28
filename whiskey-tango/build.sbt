name := """whiskey-tango"""
organization := "me.angellandros"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"


dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-core" % "2.8.9"
dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-databind" % "2.8.9"
dependencyOverrides += "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.8.9"

dependencyOverrides += "org.apache.hadoop" % "hadoop-mapreduce-client-core" % "2.7.2"
dependencyOverrides += "org.apache.hadoop" % "hadoop-common" % "2.7.2"
dependencyOverrides += "commons-io" % "commons-io" % "2.4"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.0" % Test
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.2.0"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.2.0"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "me.angellandros.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "me.angellandros.binders._"
