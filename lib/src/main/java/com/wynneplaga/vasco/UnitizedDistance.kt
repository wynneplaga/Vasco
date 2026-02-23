package com.wynneplaga.vasco

@ConsistentCopyVisibility
data class UnitizedValue<U: Unit> internal constructor(val distance: String, val unit: U)