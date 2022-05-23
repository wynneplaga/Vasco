package com.wynneplaga.vasco

enum class ScaleOfDistance(val metricUnit: UnitOfDistance, val imperialUnit: UnitOfDistance) {
    SMALL(UnitOfDistance.CENTIMETER, UnitOfDistance.INCH),
    MEDIUM(UnitOfDistance.METER, UnitOfDistance.FOOT),
    LARGE(UnitOfDistance.KILOMETER, UnitOfDistance.MILE)
}