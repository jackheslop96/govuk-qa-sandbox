ThisBuild / scalaVersion := "2.13.14"
ThisBuild / version      := "0.1.0"

lazy val app = (project in file("app"))
  .enablePlugins(PlayScala)
  .settings(
    name := "govuk-journey-frontend",
    libraryDependencies ++= Seq(
      guice,
      "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.1" % Test
    )
  )

lazy val tests = (project in file("tests"))
  .settings(
    name := "govuk-journey-selenium-sandbox",
    libraryDependencies ++= Seq(
      "org.scalatest"           %% "scalatest"       % "3.2.18" % Test,
      "org.seleniumhq.selenium" % "selenium-java"    % "4.21.0",
      "io.github.bonigarcia"    % "webdrivermanager" % "5.9.2"
    ),
    Test / parallelExecution := true
  )
