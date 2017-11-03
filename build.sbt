organization := "com.bk"
scalaVersion := "2.11.11"
name := "spark-entity-extraction"

resolvers ++= Seq[Resolver](
  s3resolver.value("Snapshots resolver", s3("com.ee.bdec.coderepo"))
)

publishMavenStyle := false

publishTo := {
  Some(s3resolver.value("Snapshots bucket", s3("com.ee.bdec.coderepo")) withIvyPatterns)
}

libraryDependencies ++= Seq(
  "com.bk" %% "entity-extraction" % "0.1-SNAPSHOT",
  "org.apache.spark" % "spark-sql_2.11" % "2.2.0" % "provided"
)

lazy val app = (project in file("app")).
  settings(
    mainClass in assembly := Some("com.bk.nlp.entity.spark.SparkDriver"),
    assemblyJarName in assembly := "spark-entity-extraction.jar"
  )

artifact in (Compile, assembly) := {
  val art = (artifact in (Compile, assembly)).value
  art.withClassifier(Some("assembly"))
}

addArtifact(artifact in (Compile, assembly), assembly)

