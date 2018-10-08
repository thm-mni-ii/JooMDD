name := "jext2ejsl"

version := "1.0"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  "com.github.jsqlparser" % "jsqlparser" % "1.2",
  "org.scala-lang.modules" %% "scala-xml" % "1.0.6"
)

assemblyJarName in assembly := "jext2ejsl.jar"