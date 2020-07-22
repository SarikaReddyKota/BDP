name := "spark1"

version := "0.1"

scalaVersion := "2.12.10"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.2.0",
  "org.apache.spark" %% "spark-sql" % "2.2.0",
  "org.apache.spark" %% "spark-graphx" % "2.2.0",
  "graphframes" % "graphframes" % "0.8.0-spark3.0-s_2.12"
)
resolvers += "SparkPackages" at "https://dl.bintray.com/spark-packages/maven"
// https://mvnrepository.com/artifact/graphframes/graphframes
libraryDependencies += "graphframes" % "graphframes" % "0.8.0-spark3.0-s_2.12"