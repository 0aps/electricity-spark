package weather

class Electricity(data: Array[String]) {
  var offset: Integer = 4;
  val Array(idClient,
  createdAt,
  domesticType,
  product,
  market
  ) = data.take(offset + 1);

  def isDomestic: Boolean = domesticType == "T1"

  def getActiveByHour(hour: Integer): Float = {
    return data.lift(offset + hour)
      .map(x => x.toFloat)
      .getOrElse[Float](0);
  }

  def getDayConsumption: Float = {
    return (1 to 24).toArray.map(x => this.getActiveByHour(x)).sum;
  }
}
