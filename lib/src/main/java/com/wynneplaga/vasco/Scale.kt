package com.wynneplaga.vasco

interface Scale<U: Unit> {
    val metricUnit: U
    val imperialUnit: U
}