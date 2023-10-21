package com.example.balticbytebuccaneers.service.receipt

import java.math.BigDecimal

data class Entry(
    val amount: BigDecimal?,
    val producerId: String?,
    val quantity: Int?,
    val title: String?,
    val vatRate: BigDecimal?,
    val category: String,
    val priceTendency: String
)
