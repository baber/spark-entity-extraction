# spark-entity-extraction

Runs an NLP named entity recognizer over a set of input files in the test-data folder.  To build:

sbt clean assembly

To run via a local Spark installation:

spark-submit --class "com.bk.nlp.entity.spark.SparkDriver" --master local target/scala-2.11/spark-entity-extraction-assembly-0.1-SNAPSHOT.jar