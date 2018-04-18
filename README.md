# RandomNoise

This package introduces class noise randomly into an RDD.

## Example


```scala
import org.apache.spark.mllib.util._

val noise = 20 //(in percentage)

// Data must be cached in order to improve the performance

val noisyModel = new RandomNoise(trainingData, // RDD[LabeledPoint]
                                noise) // Percentage of noise
                                
val noisyData = noisyModel.runNoise()

```
