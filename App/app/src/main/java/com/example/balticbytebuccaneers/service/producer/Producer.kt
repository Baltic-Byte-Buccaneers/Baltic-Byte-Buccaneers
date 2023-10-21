package com.example.balticbytebuccaneers.service.producer

data class Producer(
    val description: String?,
    val iconId: String?,
    val id: String?,
    val isin: String?,
    val lastPrice: Int?,
    val name: String?,
    val priceData: List<PriceData>?,
    val symbol: String?,
    val tendency: String?,
    val wkn: String?
)
