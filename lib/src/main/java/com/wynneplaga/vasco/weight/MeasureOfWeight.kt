package com.wynneplaga.vasco

import com.wynneplaga.vasco.weight.UnitOfWeight
import java.math.BigDecimal
import java.math.MathContext.UNLIMITED

class MeasureOfWeight(weightInKilograms: BigDecimal): Measure<MeasureOfWeight, UnitOfWeight>(weightInKilograms) {

    constructor(weightInKilograms: Double): this(weightInKilograms.toBigDecimal(UNLIMITED))
    constructor(weight: Double, unit: UnitOfWeight): this(weight.toBigDecimal(UNLIMITED) * unit.ratioToBase)

    override fun newMeasure(measureInBaseUnit: BigDecimal) = MeasureOfWeight(measureInBaseUnit)

}