package org.apache.spark.mllib.util

import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.rdd.RDD

import scala.util.Random

class RandomNoise (val data: RDD[LabeledPoint], val noise: Double) extends Serializable {

  def runNoise(): RDD[LabeledPoint] = {

    val labels = data.map(_.label).distinct().collect()

    val tam = data.count.toInt

    val num = math.round(tam * (noise.toDouble / 100))

    val range = util.Random.shuffle(0 to tam - 1)

    val indices = range.take(num.toInt)

    val broadcastInd = data.sparkContext.broadcast(indices)

    val noiseData = data.zipWithIndex.map {
      case (v, k) =>
        if (broadcastInd.value contains (k)) {
          val label = labels diff List(v.label)
          LabeledPoint(label(Random.nextInt(label.length)), v.features)
        } else {
          v
        }
    }
    noiseData
  }
}
