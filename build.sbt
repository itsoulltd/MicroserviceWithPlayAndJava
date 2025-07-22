name := """HelloPlayWithJavaV2"""
organization := "com.infoworks.lab"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)
                                      .settings(PlayKeys.playDefaultPort := 8080)

scalaVersion := "2.13.16"

resolvers += "jitpack" at "https://jitpack.io"

libraryDependencies += guice
libraryDependencies += "com.github.itsoulltd" % "JSQLEditor" % "v1.1.5.7-RELEASE"
libraryDependencies += "com.github.itsoulltd.WebComponentKit" % "http-rest-client" % "v1.14.5-RELEASE"
libraryDependencies += "javax.validation" % "validation-api" % "2.0.1.Final"
libraryDependencies += "javax.el" % "javax.el-api" % "3.0.0"
libraryDependencies += "org.glassfish" % "jakarta.el" % "3.0.3"
libraryDependencies += "org.hibernate.validator" % "hibernate-validator" % "6.0.13.Final"


