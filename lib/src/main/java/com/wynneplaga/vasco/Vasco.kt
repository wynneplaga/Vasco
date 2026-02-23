package com.wynneplaga.vasco

import android.icu.util.LocaleData
import android.icu.util.ULocale
import android.os.Build
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

class Vasco <M: Measure<*, U>, U: Unit> private constructor(private val measure: M) {

    companion object {

        fun <M: Measure<*, U>, U: Unit> of(measure: M): Vasco<M, U> = Vasco(measure)

        fun defaultSystemOfMeasure(): SystemOfMeasure {
            val useMetric = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                LocaleData.getMeasurementSystem(ULocale.getDefault()) == LocaleData.MeasurementSystem.SI
            } else {
                listOf("US", "GB", "MM", "LR").contains(Locale.getDefault().country.uppercase())
            }
            return if (useMetric) SystemOfMeasure.METRIC else SystemOfMeasure.IMPERIAL
        }

    }

    sealed interface DesiredUnit<U: Unit> {
        val unit: U

        data class Exact<U: Unit>(override val unit: U): DesiredUnit<U>
        data class Auto<U: Unit, S: Scale<U>>(val scale: S, val systemOfMeasure: SystemOfMeasure = defaultSystemOfMeasure()): DesiredUnit<U> {
            override val unit: U
                get() = when (systemOfMeasure) {
                    SystemOfMeasure.METRIC -> scale.metricUnit
                    SystemOfMeasure.IMPERIAL -> scale.imperialUnit
                }
        }
    }

    var insertSeparators: Boolean = true
    var lengthAfterDecimal: Int = -1

    fun convert(intoUnit: U, block: Vasco<M, U>.() -> kotlin.Unit = {}): UnitizedValue<U> = convert(DesiredUnit.Exact(intoUnit), block)
    fun <S: Scale<U>> convert(scale: S, systemOfMeasure: SystemOfMeasure, block: Vasco<M, U>.() -> kotlin.Unit = {}) = convert(
        DesiredUnit.Auto(scale, systemOfMeasure), block)

    /**
     * Converts the give measure of distance into a formatted string and unit
     */
    fun convert(intoUnit: DesiredUnit<U>, block: Vasco<M, U>.() -> kotlin.Unit = {}): UnitizedValue<U> {
        lengthAfterDecimal = intoUnit.unit.defaultLengthAfterDecimal
        block(this)
        val format = NumberFormat.getInstance().apply {
            isGroupingUsed = insertSeparators
            maximumFractionDigits = lengthAfterDecimal
        }
        return UnitizedValue(format.format(measure.inUnit(intoUnit.unit, lengthAfterDecimal)), intoUnit.unit)
    }

}