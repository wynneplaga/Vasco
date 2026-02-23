package com.wynneplaga.vasco

import java.math.BigDecimal
import java.math.MathContext.UNLIMITED

class MeasureOfDistance(distanceInMeters: BigDecimal): Measure<MeasureOfDistance, UnitOfDistance>(distanceInMeters) {

    constructor(distanceInMeters: Double): this(distanceInMeters.toBigDecimal(UNLIMITED))
    constructor(distance: Double, unit: UnitOfDistance): this(distance.toBigDecimal(UNLIMITED) * unit.ratioToBase)

    override fun newMeasure(measureInBaseUnit: BigDecimal) = MeasureOfDistance(measureInBaseUnit)

}