enablePlugins(ScalaJSPlugin)

name := "todo app in scalajs-react"

scalaVersion := "2.11.11"

scalaJSUseMainModuleInitializer := true

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.9.2",
  "com.github.japgolly.scalajs-react" %%% "core" % "1.0.1",
  "com.github.japgolly.scalacss" %%% "core" % "0.5.3",
  "com.github.japgolly.scalacss" %%% "ext-react" % "0.5.3",
  "io.github.cquiroz" %%% "scala-java-time" % "2.0.0-M12",

  "com.github.japgolly.scalajs-react" %%% "core" % "1.0.1",
  "com.github.japgolly.scalajs-react" %%% "extra" % "1.0.1",
  "com.github.japgolly.scalacss" %%% "ext-react" % "0.5.3",
  "io.suzaku" %%% "diode" % "1.1.2",
  "io.suzaku" %%% "diode-react" % "1.1.2",
  "com.lihaoyi" %%% "utest" % "0.4.7" % Test
)

jsDependencies ++= Seq(
  "org.webjars.bower" % "react" % "15.5.4"
    /        "react-with-addons.js"
    minified "react-with-addons.min.js"
    commonJSName "React",

  "org.webjars.bower" % "react" % "15.5.4"
    /         "react-dom.js"
    minified  "react-dom.min.js"
    dependsOn "react-with-addons.js"
    commonJSName "ReactDOM",

  "org.webjars.bower" % "react" % "15.5.4"
    /         "react-dom-server.js"
    minified  "react-dom-server.min.js"
    dependsOn "react-dom.js"
    commonJSName "ReactDOMServer",

  "org.webjars" % "jquery" % "3.2.1"
    / "jquery.js"
    minified "jquery.min.js",

  "org.webjars" % "bootstrap" % "3.3.7-1"
    / "bootstrap.js"
    minified "bootstrap.min.js"
    dependsOn "jquery.js",

  "org.webjars" % "chartjs" % "2.1.3"
    / "Chart.js"
    minified "Chart.min.js",

  "org.webjars" % "log4javascript" % "1.4.10"
    / "js/log4javascript_uncompressed.js"
    minified "js/log4javascript.js"
)

enablePlugins(WorkbenchPlugin)
