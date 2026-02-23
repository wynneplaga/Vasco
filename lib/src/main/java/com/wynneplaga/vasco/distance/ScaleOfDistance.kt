package com.wynneplaga.vasco

enum class ScaleOfDistance(override val metricUnit: UnitOfDistance, override val imperialUnit: UnitOfDistance): Scale<UnitOfDistance> {
    SMALL(UnitOfDistance.CENTIMETER, UnitOfDistance.INCH),
    MEDIUM(UnitOfDistance.METER, UnitOfDistance.FOOT),
    LARGE(UnitOfDistance.KILOMETER, UnitOfDistance.MILE)
}