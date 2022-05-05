import weather.Spark

object ElectricityAnalysis {
  def main(args: Array[String]): Unit = {
    val spark: Spark = new Spark();
    spark.load(null);

    spark.output();
  }
}