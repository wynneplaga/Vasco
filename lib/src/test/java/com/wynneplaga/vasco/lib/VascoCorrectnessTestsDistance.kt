package com.wynneplaga.vasco.lib

import com.wynneplaga.vasco.MeasureOfDistance
import com.wynneplaga.vasco.UnitOfDistance
import com.wynneplaga.vasco.Vasco
import org.junit.Assert.assertEquals
import org.junit.Test

class VascoCorrectnessTestsDistance {

    @Test
    fun `Uses separators correctly`() {
        val measureOfDistance = MeasureOfDistance(1.0, UnitOfDistance.KILOMETER)

        val commaResult = Vasco
            .of(measureOfDistance)
            .convert(UnitOfDistance.METER)
        assertEquals("1,000", commaResult.distance)
        assertEquals(UnitOfDistance.METER, commaResult.unit)

        val noCommaResult = Vasco
            .of(measureOfDistance)
            .convert(UnitOfDistance.METER) {
                insertSeparators = false
            }
        assertEquals("1000", noCommaResult.distance)
        assertEquals(UnitOfDistance.METER, noCommaResult.unit)
    }

    @Test
    fun `Accepts different units`() {
        val measureOfDistance = MeasureOfDistance(5.0, UnitOfDistance.YARD)

        val metricResult = Vasco
            .of(measureOfDistance)
            .convert(UnitOfDistance.FOOT)
        assertEquals("15", metricResult.distance)
        assertEquals(UnitOfDistance.FOOT, metricResult.unit)

        val imperialResult = Vasco
            .of(measureOfDistance)
            .convert(UnitOfDistance.KILOMETER) {
                lengthAfterDecimal = 4
            }
        assertEquals("0.0046", imperialResult.distance)
        assertEquals(UnitOfDistance.KILOMETER, imperialResult.unit)
    }

    @Test
    fun `System of measure is correct`() {
        val measureOfDistance = MeasureOfDistance(2.0)

        val metricResult = Vasco
            .of(measureOfDistance)
            .convert(UnitOfDistance.METER)
        assertEquals("2", metricResult.distance)
        assertEquals(UnitOfDistance.METER, metricResult.unit)

        val imperialResult = Vasco
            .of(measureOfDistance)
            .convert(UnitOfDistance.FOOT)
        assertEquals("6.56", imperialResult.distance)
        assertEquals(UnitOfDistance.FOOT, imperialResult.unit)
    }

    @Test
    fun `Amount after commas is correct`() {
        val measureOfDistance = MeasureOfDistance(5.0)

        val zero = Vasco
            .of(measureOfDistance)
            .convert(UnitOfDistance.FOOT) {
                lengthAfterDecimal = 0
            }
        assertEquals("16", zero.distance)
        val one = Vasco
            .of(measureOfDistance)
            .convert(UnitOfDistance.FOOT) {
                lengthAfterDecimal = 1
            }
        assertEquals("16.4", one.distance)
        val two = Vasco
            .of(measureOfDistance)
            .convert(UnitOfDistance.FOOT) {
                lengthAfterDecimal = 2
            }
        assertEquals("16.4", two.distance)
        val three = Vasco
            .of(measureOfDistance)
            .convert(UnitOfDistance.FOOT) {
                lengthAfterDecimal = 3
            }
        assertEquals("16.404", three.distance)
        val four = Vasco
            .of(measureOfDistance)
            .convert(UnitOfDistance.FOOT) {
                lengthAfterDecimal = 4
            }
        assertEquals("16.4042", four.distance)
        val five = Vasco
            .of(measureOfDistance)
            .convert(UnitOfDistance.FOOT) {
                lengthAfterDecimal = 5
            }
        assertEquals("16.4042", five.distance)
    }

}