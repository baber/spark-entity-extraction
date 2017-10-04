package com.bk.nlp.entity.spark

import com.bk.nlp.EntityRecognition
import com.bk.nlp.model.NamedEntity
import org.apache.spark.rdd.RDD

object SparkDriver {

  import org.apache.spark.sql.SparkSession

  def main(args: Array[String]) {
    val filesDir = args.head
    val sparkSession = SparkSession.builder.appName("Named Entity Extractor").getOrCreate()
    val textData = sparkSession.sparkContext.wholeTextFiles(filesDir).cache()
    val entityData = textData.map { fileTuple => (fileTuple._1,  EntityRecognition.extractEntities(fileTuple._2))  }

    entityData.saveAsTextFile(s"${filesDir}entities")
    sparkSession.stop()
  }


  def printEntities(entityData: RDD[(String, List[NamedEntity])]) = {
    entityData foreach(x => { println(x._1); println(x._2) })
  }

}
