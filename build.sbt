name := """HelloPlayWithJavaV2"""
organization := "com.infoworks.lab"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)
                                      .settings(PlayKeys.playDefaultPort := 8080)

scalaVersion := "2.13.16"

resolvers += "jitpack" at "https://jitpack.io"

libraryDependencies += guice
libraryDependencies += "com.github.itsoulltd" % "JSQLEditor" % "v1.1.5.6-RELEASE"
libraryDependencies += "com.github.itsoulltd.WebComponentKit" % "http-rest-client" % "v1.14.5-RELEASE"


