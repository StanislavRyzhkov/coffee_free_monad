ThisBuild / scalaVersion := "2.12.12"

ThisBuild / version := "0.0.1"

ThisBuild / organization := "company.ryzhkov"

ThisBuild / scalafixDependencies += "com.github.liancheng" %% "organize-imports" % "0.4.4"

lazy val common = (project in file("."))
  .enablePlugins(ScalafmtPlugin)
  .enablePlugins(AssemblyPlugin)
  .settings(
    name := "http-dsl",
    semanticdbEnabled := true,
    semanticdbVersion := "0.9.24",
    addCompilerPlugin(scalafixSemanticdb),
    libraryDependencies ++= Seq(
      "org.postgresql" % "postgresql" % "42.2.14",
      "com.typesafe.slick" %% "slick" % "3.3.3",
      "org.slf4j" % "slf4j-nop" % "1.6.4",
      "com.typesafe.slick" %% "slick-hikaricp" % "3.3.3",
      "com.typesafe" % "config" % "1.4.1",
      "org.typelevel" %% "cats-core" % "2.1.1",
      "org.typelevel" %% "cats-free" % "2.1.1",
      "org.scalatest" %% "scalatest" % "3.2.0" % Test,
      "org.scalatest" %% "scalatest-core" % "3.2.0",
      "org.scalatest" %% "scalatest-funsuite" % "3.2.0" % Test
    ),
    scalacOptions ++= Seq(
      "-feature",
      "-language:implicitConversions",
      "-language:higherKinds",
      "-language:postfixOps",
      "-Yrangepos",
      "-Ywarn-unused-import"
    ),
    mainClass in (Compile, run) := Some("company.ryzhkov.Application"),
    mainClass in (assembly) := Some("company.ryzhkov.Application"),
    assemblyJarName in assembly := "http.jar"
  )
