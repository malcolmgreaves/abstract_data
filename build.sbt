name := "data-tc"
organization in ThisBuild := "io.malcolmgreaves"
version in ThisBuild := {
  val major: Int = 0
  val minor: Int = 0
  val patch: Int = 1
  s"$major.$minor.$patch"
}

import SharedBuild._

lazy val root = project
  .in(file("."))
  .aggregate(
    `data-tc-scala`,
    `data-tc-spark`,
    `data-tc-flink`,
    `data-tc-extra`
  )
  .settings {
    publishArtifact := false
    publishLocal := {}
    publish := {}
  }

lazy val `data-tc-scala` = project.in(file("data-tc-scala")).settings {
  publishArtifact := true
}

lazy val `data-tc-spark` =
  project.in(file("data-tc-spark")).dependsOn(`data-tc-scala`).settings {
    publishArtifact := true
  }

lazy val `data-tc-flink` =
  project.in(file("data-tc-flink")).dependsOn(`data-tc-scala`).settings {
    publishArtifact := true
  }

lazy val `data-tc-extra` =
  project.in(file("data-tc-extra")).dependsOn(`data-tc-scala`).settings {
    publishArtifact := true
  }

lazy val subprojects: Seq[ProjectReference] = root.aggregate
lazy val publishTasks = subprojects.map { publish.in }

resolvers in ThisBuild := Seq(
  // sonatype, maven central
  "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/",
  "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
  // bintray
  "Scalaz Bintray" at "http://dl.bintray.com/scalaz/releases",
  Resolver.bintrayRepo("mfglabs", "maven"),
  Resolver.bintrayRepo("dwhjames", "maven"),
  // etc.
  "Confluent" at "http://packages.confluent.io/maven/"
)

// runtime & compiliation

lazy val javaV = "1.8"
scalaVersion in ThisBuild := "2.11.8"
scalacOptions in ThisBuild := Seq(
  "-optimize",
  "-deprecation",
  "-feature",
  "-unchecked",
  s"-target:jvm-$javaV",
  "-encoding",
  "utf8",
  "-language:postfixOps",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-language:experimental.macros",
  "-language:reflectiveCalls",
  "-Yno-adapted-args",
  "-Ywarn-value-discard",
  "-Xlint",
  "-Xfuture",
  "-Ywarn-dead-code",
  "-Xfatal-warnings" // Every warning is esclated to an error.
)
javacOptions in ThisBuild := Seq("-source", javaV, "-target", javaV)
javaOptions in ThisBuild := Seq(
  "-server",
  "-XX:+AggressiveOpts",
  "-XX:+TieredCompilation",
  "-XX:CompileThreshold=100",
  "-Xmx3000M",
  "-XX:+UseG1GC"
)

publishArtifact := false
