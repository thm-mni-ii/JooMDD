name := "jext2ejsl"

version := "1.0"

scalaVersion := "2.11.7"

enablePlugins(SbtTwirl)

libraryDependencies ++= Seq(
  "com.github.jsqlparser" % "jsqlparser" % "0.9",
  "org.scala-lang.modules" %% "scala-xml" % "1.0.3"
)

assemblyJarName in assembly := "jext2ejsl.jar"