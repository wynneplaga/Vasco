package com.wynneplaga.vasco

import java.math.BigDecimal
import java.math.MathContext.UNLIMITED
import java.math.RoundingMode

class MeasureOfDistance(private var distanceInMeters: BigDecimal) {

    constructor(distanceInMeters: Double): this(distanceInMeters.toBigDecimal(UNLIMITED))
    constructor(distance: Double, unit: UnitOfDistance): this(distance.toBigDecimal(UNLIMITED) * unit.ratioToMeter)

    fun asDistanceInUnit(unit: UnitOfDistance, scale: Int = distanceInMeters.scale()): BigDecimal = distanceInMeters.setScale(scale + 1, RoundingMode.HALF_UP) / unit.ratioToMeter

    operator fun plus(other: MeasureOfDistance): MeasureOfDistance = MeasureOfDistance(distanceInMeters + other.distanceInMeters)
    operator fun minus(other: MeasureOfDistance): MeasureOfDistance = MeasureOfDistance(distanceInMeters - other.distanceInMeters)

    operator fun plusAssign(other: MeasureOfDistance) {
        distanceInMeters += other.distanceInMeters
    }
    operator fun minusAssign(other: MeasureOfDistance) {
        distanceInMeters += other.distanceInMeters
    }

    operator fun plus(other: Number): MeasureOfDistance = MeasureOfDistance(distanceInMeters + other.toDouble().toBigDecimal(UNLIMITED))
    operator fun minus(other: Number): MeasureOfDistance = MeasureOfDistance(distanceInMeters - other.toDouble().toBigDecimal(UNLIMITED))
    operator fun times(other: Number): MeasureOfDistance = MeasureOfDistance(distanceInMeters * other.toDouble().toBigDecimal(UNLIMITED))
    operator fun div(other: Number): MeasureOfDistance = MeasureOfDistance(distanceInMeters / other.toDouble().toBigDecimal(UNLIMITED))

    operator fun plusAssign(other: Number) {
        distanceInMeters += other.toDouble().toBigDecimal(UNLIMITED)
    }
    operator fun minusAssign(other: Number) {
        distanceInMeters -= other.toDouble().toBigDecimal(UNLIMITED)
    }
    operator fun timesAssign(other: Number) {
        distanceInMeters *= other.toDouble().toBigDecimal(UNLIMITED)
    }
    operator fun divAssign(other: Number) {
        distanceInMeters /= other.toDouble().toBigDecimal(UNLIMITED)
    }

    operator fun unaryMinus(): MeasureOfDistance = MeasureOfDistance(-distanceInMeters)

}