name := """HelloPlayWithJavaV2"""
organization := "com.infoworks.lab"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)
                                      .settings(PlayKeys.playDefaultPort := 8080)

scalaVersion := "2.13.16"

resolvers += "jitpack" at "https://jitpack.io"

libraryDependencies += guice
libraryDependencies += "com.github.itsoulltd" % "JFoundationKit" % "v1.0.4"
libraryDependencies += "com.github.itsoulltd" % "JSqlKit" % "v1.0.1"
libraryDependencies += "javax.validation" % "validation-api" % "2.0.1.Final"
libraryDependencies += "javax.el" % "javax.el-api" % "3.0.0"
libraryDependencies += "org.glassfish" % "jakarta.el" % "3.0.3"
libraryDependencies += "org.hibernate.validator" % "hibernate-validator" % "6.0.13.Final"

libraryDependencies ++= Seq(
  javaJdbc,
  "org.postgresql" % "postgresql" % "42.2.1",
  "com.h2database" % "h2" % "1.4.200"
)


