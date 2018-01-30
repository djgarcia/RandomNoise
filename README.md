# RandomNoise

This package introduces class noise randomly into an RDD.

## Example


```scala
import org.apache.spark.mllib.feature._

val noise = 20 (in percentage)

// Data must be cached in order to improve the performance

val noisyData = new RandomNoise(trainingData, noise).runNoise()

```
