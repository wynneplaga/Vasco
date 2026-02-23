package com.wynneplaga.vasco.distance

import com.wynneplaga.vasco.MeasureOfDistance
import com.wynneplaga.vasco.UnitOfDistance
import kotlin.math.roundToInt

object FeetAndInchesHelper {

    data class FeetAndInches(val feet: Int, val inches: Int) {
        override fun toString(): String {
            return "$feet' $inches\""
        }
    }

    fun convert(measureOfDistance: MeasureOfDistance): FeetAndInches {
        val totalInches = measureOfDistance.inUnit(UnitOfDistance.INCH).toDouble().roundToInt()
        val feet = (totalInches / 12)
        val inches = (totalInches % 12)
        return FeetAndInches(feet, inches)
    }

}