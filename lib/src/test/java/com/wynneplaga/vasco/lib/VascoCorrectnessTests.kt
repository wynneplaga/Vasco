package com.wynneplaga.vasco.lib

import com.wynneplaga.vasco.MeasureOfDistance
import com.wynneplaga.vasco.SystemOfMeasure
import com.wynneplaga.vasco.UnitOfDistance
import com.wynneplaga.vasco.Vasco
import org.junit.Test

import org.junit.Assert.*

class VascoCorrectnessTests {

    @Test
    fun `Uses separators correctly`() {
        val measureOfDistance = MeasureOfDistance(1.0, UnitOfDistance.KILOMETER)

        val commaResult = Vasco
            .of(measureOfDistance)
            .unit(UnitOfDistance.METER)
            .convert()
        assertEquals("1,000", commaResult.distance)
        assertEquals(UnitOfDistance.METER, commaResult.unit)

        val noCommaResult = Vasco
            .of(measureOfDistance)
            .unit(UnitOfDistance.METER)
            .insertSeparators(false)
            .convert()
        assertEquals("1000", noCommaResult.distance)
        assertEquals(UnitOfDistance.METER, noCommaResult.unit)
    }

    @Test
    fun `Accepts different units`() {
        val measureOfDistance = MeasureOfDistance(5.0, UnitOfDistance.YARD)

        val metricResult = Vasco
            .of(measureOfDistance)
            .systemOfMeasure(SystemOfMeasure.IMPERIAL)
            .convert()
        assertEquals("15", metricResult.distance)
        assertEquals(UnitOfDistance.FOOT, metricResult.unit)

        val imperialResult = Vasco
            .of(measureOfDistance)
            .unit(UnitOfDistance.KILOMETER)
            .lengthAfterDecimal(4)
            .convert()
        assertEquals("0.0046", imperialResult.distance)
        assertEquals(UnitOfDistance.KILOMETER, imperialResult.unit)
    }

    @Test
    fun `System of measure is correct`() {
        val measureOfDistance = MeasureOfDistance(2.0)

        val metricResult = Vasco
            .of(measureOfDistance)
            .systemOfMeasure(SystemOfMeasure.METRIC)
            .convert()
        assertEquals("2", metricResult.distance)
        assertEquals(UnitOfDistance.METER, metricResult.unit)

        val imperialResult = Vasco
            .of(measureOfDistance)
            .systemOfMeasure(SystemOfMeasure.IMPERIAL)
            .convert()
        assertEquals("6.56", imperialResult.distance)
        assertEquals(UnitOfDistance.FOOT, imperialResult.unit)
    }

    @Test
    fun `Amount after commas is correct`() {
        val measureOfDistance = MeasureOfDistance(5.0)

        val zero = Vasco
            .of(measureOfDistance)
            .systemOfMeasure(SystemOfMeasure.IMPERIAL)
            .lengthAfterDecimal(0)
            .convert()
        assertEquals("16", zero.distance)
        val one = Vasco
            .of(measureOfDistance)
            .systemOfMeasure(SystemOfMeasure.IMPERIAL)
            .lengthAfterDecimal(1)
            .convert()
        assertEquals("16.4", one.distance)
        val two = Vasco
            .of(measureOfDistance)
            .systemOfMeasure(SystemOfMeasure.IMPERIAL)
            .lengthAfterDecimal(2)
            .convert()
        assertEquals("16.4", two.distance)
        val three = Vasco
            .of(measureOfDistance)
            .systemOfMeasure(SystemOfMeasure.IMPERIAL)
            .lengthAfterDecimal(3)
            .convert()
        assertEquals("16.404", three.distance)
        val four = Vasco
            .of(measureOfDistance)
            .systemOfMeasure(SystemOfMeasure.IMPERIAL)
            .lengthAfterDecimal(4)
            .convert()
        assertEquals("16.4042", four.distance)
        val five = Vasco
            .of(measureOfDistance)
            .systemOfMeasure(SystemOfMeasure.IMPERIAL)
            .lengthAfterDecimal(5)
            .convert()
        assertEquals("16.4042", five.distance)
    }

}