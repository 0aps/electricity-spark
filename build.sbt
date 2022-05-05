ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.11.8"

lazy val root = (project in file("."))
  .settings(
    name := "weather-spark",
    libraryDependencies += "org.apache.spark" %% "spark-core" % "2.3.0",
    libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.3.0"
  )
