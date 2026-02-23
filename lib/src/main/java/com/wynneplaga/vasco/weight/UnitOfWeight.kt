package com.wynneplaga.vasco.weight

import com.wynneplaga.vasco.Unit
import java.math.BigDecimal
import java.math.MathContext.UNLIMITED

enum class UnitOfWeight(override val ratioToBase: BigDecimal): Unit {
    KILOGRAM(1.0),
    GRAM(0.001),
    MILLIGRAM(0.000001),
    MICROGRAM("0.000000001"),
    METRIC_TONNE(1000.0),
    POUND(0.453592),
    OUNCE(0.0283495)
    ;

    constructor(value: Double): this(value.toBigDecimal(UNLIMITED))
    constructor(value: String): this(value.toBigDecimal(UNLIMITED))

    override val defaultLengthAfterDecimal: Int = 0

}
