package com.wynneplaga.vasco.lib

import com.wynneplaga.vasco.MeasureOfWeight
import com.wynneplaga.vasco.weight.UnitOfWeight
import com.wynneplaga.vasco.Vasco
import org.junit.Assert.assertEquals
import org.junit.Test

class VascoCorrectnessTestsWeight {

    @Test
    fun `Converts correctly`() {
        val measureOfWeight = MeasureOfWeight(180.0, UnitOfWeight.POUND)

        val commaResult = Vasco
            .of(measureOfWeight)
            .convert(UnitOfWeight.KILOGRAM)
        assertEquals("82", commaResult.distance)
        assertEquals(UnitOfWeight.KILOGRAM, commaResult.unit)
    }

}