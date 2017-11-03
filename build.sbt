organization := "com.bk"
scalaVersion := "2.11.11"
name := "spark-entity-extraction"

resolvers ++= Seq[Resolver](
  s3resolver.value("Snapshots resolver", s3("com.ee.bdec.coderepo")) withIvyPatterns
)

libraryDependencies ++= Seq(
  "com.bk" %% "entity-extraction" % "0.1-SNAPSHOT",
  "org.apache.spark" % "spark-sql_2.11" % "2.2.0" % "provided"
)

lazy val root = (project in file("."))
  .settings(
    assemblyOutputPath in assembly := file(s"./target/${(assemblyDefaultJarName in assembly).value}")
  )



