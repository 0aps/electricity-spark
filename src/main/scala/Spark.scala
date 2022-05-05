package weather

import org.apache.log4j.{Level, Logger}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

class Spark(var config: SparkConf,
            var sc: SparkContext,
            var spark: SparkSession) {

  var rawDataCSV: RDD[Electricity] = null;

  def this() {
    this(null, null, null);
    Logger.getRootLogger.setLevel(Level.ERROR);
    Logger.getLogger("org").setLevel(Level.OFF);
    Logger.getLogger("akka").setLevel(Level.OFF);

    this.config = new SparkConf()
      .setAppName("ElectricityAnalysis")
      .setMaster("local[*]")
      .set("spark.hadoop.validateOutputSpecs", "false");

    this.sc = new SparkContext(config);
    this.spark = SparkSession.builder.config(sc.getConf).getOrCreate();
  }

  ...

  /**
   * Print the max/min consumption. (global and by client)
   */
  def output(): Any = {
    var globalConsumption = this.rawDataCSV.map(x => x.getDayConsumption);
    println(s"Global ${globalConsumption.max} ${globalConsumption.min}");

    var consumptionsGroup = this.rawDataCSV
      .map(x => (x.idClient, x.getDayConsumption));

    var localMaxConsumption = consumptionsGroup.reduceByKey((x, y) => Math.max(x, y));
    var localMinConsumption = consumptionsGroup.reduceByKey((x, y) => Math.min(x, y));

    localMaxConsumption.foreach(println);
    localMinConsumption.foreach(println);
  }

}
