# Vasco

[![Apache 2.0](https://img.shields.io/hexpm/l/plug.svg)](http://www.apache.org/licenses/LICENSE-2.0) [![](https://jitpack.io/v/wynneplaga/vasco.svg)](https://jitpack.io/#wynneplaga/vasco) [![Version](https://img.shields.io/badge/API-1%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=21)

A library for converting between different units of distance and displaying them as strings.

## Example use

The API provides a variety of ways to specify what unit you want as your result. If you do not specify, Vasco will decide for you based on things like the magnitude of the measurement and the locale of the device.

```kotlin
val measureOfDistance = MeasureOfDistance(5.0, UnitOfDistance.YARD)

val imperialResult = Vasco
    .of(measureOfDistance)
    .systemOfMeasure(SystemOfMeasure.IMPERIAL)
    .convert()

// Is equal to
UnitizedDistance("15", UnitOfDistance.FOOT)
```
```kotlin
val measureOfDistance = MeasureOfDistance(5.0, UnitOfDistance.YARD)
val metricResult = Vasco
    .of(measureOfDistance)
    .unit(UnitOfDistance.KILOMETER)
    .lengthAfterDecimal(4)
    .convert()
    
// Is equal to
UnitizedDistance("0.0046", UnitOfDistance.KILOMETER)
```
```kotlin
val measureOfDistance = MeasureOfDistance(5.0, UnitOfDistance.YARD)
val automaticResult = Vasco
    .of(measureOfDistance)
    .convert()
    
// Is equal to either
UnitizedDistance("4.57", UnitOfDistance.METER)
// or
UnitizedDistance("15", UnitOfDistance.FOOT)
// depending on the device locale
```

## Get Started

```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
```gradle
dependencies {
    implementation 'com.github.wynneplaga:Vasco:1.0.0'
}
```
