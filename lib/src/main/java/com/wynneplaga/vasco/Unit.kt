package com.wynneplaga.vasco

import java.math.BigDecimal

interface Unit {
    val ratioToBase: BigDecimal
    val defaultLengthAfterDecimal: Int
}