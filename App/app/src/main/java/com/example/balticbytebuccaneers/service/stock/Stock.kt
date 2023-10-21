package com.example.balticbytebuccaneers.service.stock

import java.math.BigDecimal

data class Stock(
    val description: String?,
    val id: String?,
    val isin: String?,
    val lastPrice: String?,
    val name: String?,
    val priceData: List<PriceData>?,
    val symbol: String?,
    val tendencyWeek: String?,
    val tendencyYear: String?,
    val wkn: String?
)
