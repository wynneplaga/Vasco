package com.wynneplaga.vasco

import java.math.BigDecimal
import java.math.MathContext.UNLIMITED
import java.math.RoundingMode

abstract class Measure<M: Measure<M, U>, U: Unit>(private var measureInBaseUnit: BigDecimal) {

    protected abstract fun newMeasure(measureInBaseUnit: BigDecimal): M

    fun inUnit(unit: U, scale: Int = measureInBaseUnit.scale()): BigDecimal = measureInBaseUnit.setScale(scale + 1, RoundingMode.HALF_UP) / unit.ratioToBase

    operator fun plus(other: M): M = newMeasure(measureInBaseUnit + other.measureInBaseUnit)
    operator fun minus(other: M): M = newMeasure(measureInBaseUnit - other.measureInBaseUnit)

    operator fun plusAssign(other: M) {
        measureInBaseUnit += other.measureInBaseUnit
    }
    operator fun minusAssign(other: M) {
        measureInBaseUnit += other.measureInBaseUnit
    }

    operator fun plus(other: Number): M = newMeasure(measureInBaseUnit + other.toDouble().toBigDecimal(UNLIMITED))
    operator fun minus(other: Number): M = newMeasure(measureInBaseUnit - other.toDouble().toBigDecimal(UNLIMITED))
    operator fun times(other: Number): M = newMeasure(measureInBaseUnit * other.toDouble().toBigDecimal(UNLIMITED))
    operator fun div(other: Number): M = newMeasure(measureInBaseUnit / other.toDouble().toBigDecimal(UNLIMITED))

    operator fun plusAssign(other: Number) {
        measureInBaseUnit += other.toDouble().toBigDecimal(UNLIMITED)
    }
    operator fun minusAssign(other: Number) {
        measureInBaseUnit -= other.toDouble().toBigDecimal(UNLIMITED)
    }
    operator fun timesAssign(other: Number) {
        measureInBaseUnit *= other.toDouble().toBigDecimal(UNLIMITED)
    }
    operator fun divAssign(other: Number) {
        measureInBaseUnit /= other.toDouble().toBigDecimal(UNLIMITED)
    }

    operator fun unaryMinus(): M = newMeasure(-measureInBaseUnit)

}