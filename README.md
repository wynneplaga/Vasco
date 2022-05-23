# Vasco

A library for converting between different units of distance and displaying them as strings.

## Example use

```kotlin
val measureOfDistance = MeasureOfDistance(5.0, UnitOfDistance.YARD)

val imperialResult = Vasco
    .of(measureOfDistance)
    .systemOfMeasure(SystemOfMeasure.IMPERIAL)
    .convert()

// Is equal to
UnitizedDistance("15", UnitOfDistance.FOOT)

val metricResult = Vasco
    .of(measureOfDistance)
    .unit(UnitOfDistance.KILOMETER)
    .lengthAfterDecimal(4)
    .convert()
    
// Is equal to
UnitizedDistance("0.0046", UnitOfDistance.KILOMETER)

val automaticResult = Vasco
    .of(measureOfDistance)
    .convert()
    
// Is equal to either
UnitizedDistance("4.57", UnitOfDistance.METER)
// or
UnitizedDistance("15", UnitOfDistance.FOOT)
// depnding on the device locale
```
