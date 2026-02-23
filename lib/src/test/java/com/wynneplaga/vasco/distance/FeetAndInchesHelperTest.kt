package com.wynneplaga.vasco.distance

import com.wynneplaga.vasco.MeasureOfDistance
import com.wynneplaga.vasco.UnitOfDistance
import org.junit.Assert
import org.junit.Test

class FeetAndInchesHelperTest {

    @Test
    fun test() {
        val height = MeasureOfDistance(5.0, UnitOfDistance.FOOT) + MeasureOfDistance(11.0, UnitOfDistance.INCH)
        val result = FeetAndInchesHelper.convert(height)
        Assert.assertEquals(result.toString(), "5' 11\"")
    }

    @Test
    fun testFromMetric() {
        val height = MeasureOfDistance(180.0, UnitOfDistance.CENTIMETER)
        val result = FeetAndInchesHelper.convert(height)
        Assert.assertEquals(result.toString(), "5' 11\"")
    }

}