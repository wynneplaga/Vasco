package com.wynneplaga.vasco

import android.icu.util.LocaleData
import android.icu.util.ULocale
import android.os.Build
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

class Vasco private constructor(private val measureOfDistance: MeasureOfDistance) {

    companion object {

        fun of(measureOfDistance: MeasureOfDistance): Vasco = Vasco(measureOfDistance)

        fun defaultSystemOfMeasure(): SystemOfMeasure {
            val useMetric = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                LocaleData.getMeasurementSystem(ULocale.getDefault()) == LocaleData.MeasurementSystem.SI
            } else {
                listOf("US", "GB", "MM", "LR").contains(Locale.getDefault().country.uppercase())
            }
            return if (useMetric) SystemOfMeasure.METRIC else SystemOfMeasure.IMPERIAL
        }

    }

    private var scaleOfDistance: ScaleOfDistance? = null

    private var smallestScale: ScaleOfDistance? = null

    private var unit: UnitOfDistance? = null

    private var systemOfMeasure: SystemOfMeasure? = null

    private var insertSeparators: Boolean = true

    private var lengthAfterDecimal: Int = 2

    /**
     * Converts the give measure of distance into a formatted string and unit
     */
    fun convert(): UnitizedDistance {
        val unit: UnitOfDistance = if (unit != null) {
            when {
                scaleOfDistance != null -> throw IllegalStateException("Must not specify unit and scaleOfDistance")
                smallestScale != null -> throw IllegalStateException("Must not specify unit and smallestScale")
                systemOfMeasure != null -> throw IllegalStateException("Must not specify unit and systemOfMeasure")
                else -> unit as UnitOfDistance
            }
        } else if (scaleOfDistance != null) {
            when {
                smallestScale != null -> throw IllegalStateException("Must not specify unit and smallestScale")
                else -> {
                    if ((systemOfMeasure ?: defaultSystemOfMeasure()) == SystemOfMeasure.METRIC) scaleOfDistance!!.metricUnit else scaleOfDistance!!.imperialUnit
                }
            }
        } else {
            if ((systemOfMeasure ?: defaultSystemOfMeasure()) == SystemOfMeasure.METRIC) {
                when {
                    measureOfDistance.asDistanceInUnit(UnitOfDistance.KILOMETER) >= BigDecimal(1) || smallestScale == ScaleOfDistance.LARGE -> UnitOfDistance.KILOMETER
                    measureOfDistance.asDistanceInUnit(UnitOfDistance.METER) >= BigDecimal(1) || smallestScale == ScaleOfDistance.MEDIUM -> UnitOfDistance.METER
                    else -> UnitOfDistance.CENTIMETER
                }
            } else {
                when {
                    measureOfDistance.asDistanceInUnit(UnitOfDistance.MILE) >= BigDecimal(1) || smallestScale == ScaleOfDistance.LARGE -> UnitOfDistance.MILE
                    measureOfDistance.asDistanceInUnit(UnitOfDistance.FOOT) >= BigDecimal(1) || smallestScale == ScaleOfDistance.MEDIUM -> UnitOfDistance.FOOT
                    else -> UnitOfDistance.INCH
                }
            }
        }
        val format = NumberFormat.getInstance().apply {
            isGroupingUsed = insertSeparators
            maximumFractionDigits = lengthAfterDecimal
        }
        return UnitizedDistance(format.format(measureOfDistance.asDistanceInUnit(unit, lengthAfterDecimal)), unit)
    }

    fun scaleOfDistance(scaleOfDistance: ScaleOfDistance): Vasco = apply {
        this.scaleOfDistance = scaleOfDistance
    }

    fun smallestScale(smallestScale: ScaleOfDistance): Vasco = apply {
        this.smallestScale = smallestScale
    }

    fun unit(unit: UnitOfDistance): Vasco = apply {
        this.unit = unit
    }

    fun insertSeparators(insertSeparators: Boolean): Vasco = apply {
        this.insertSeparators = insertSeparators
    }

    fun lengthAfterDecimal(lengthAfterDecimal: Int): Vasco = apply {
        this.lengthAfterDecimal = lengthAfterDecimal
    }

    fun systemOfMeasure(systemOfMeasure: SystemOfMeasure): Vasco = apply {
        this.systemOfMeasure = systemOfMeasure
    }

}