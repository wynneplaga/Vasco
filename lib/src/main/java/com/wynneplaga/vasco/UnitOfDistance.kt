package com.wynneplaga.vasco

import java.math.BigDecimal
import java.math.MathContext.UNLIMITED

enum class UnitOfDistance(val ratioToMeter: BigDecimal) {
    METER(1.0),
    DECIMETER(0.1),
    CENTIMETER(0.01),
    MILLIMETER(0.001),
    MICROMETER("0.000001"),
    NANOMETER("0.000000001"),
    DECAMETER(10.0),
    HECTOMETER(100.0),
    KILOMETER(1000.0),
    INCH(0.0254),
    MILE(1609.34),
    FOOT(0.3048),
    YARD(0.9144),
    NAUTICAL_MILE(1852.0),
    LIGHT_SECOND("299792458")
    ;

    constructor(value: Double): this(value.toBigDecimal(UNLIMITED))
    constructor(value: String): this(value.toBigDecimal(UNLIMITED))

}
