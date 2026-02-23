package com.wynneplaga.vasco.weight

import com.wynneplaga.vasco.Scale

enum class ScaleOfWeight(override val metricUnit: UnitOfWeight, override val imperialUnit: UnitOfWeight):
    Scale<UnitOfWeight> {
    SMALL(UnitOfWeight.GRAM, UnitOfWeight.OUNCE),
    MEDIUM(UnitOfWeight.KILOGRAM, UnitOfWeight.POUND)
}