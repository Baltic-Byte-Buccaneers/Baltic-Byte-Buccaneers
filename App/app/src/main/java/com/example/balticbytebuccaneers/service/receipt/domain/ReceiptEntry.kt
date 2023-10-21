package com.example.balticbytebuccaneers.service.receipt.domain

import java.math.BigDecimal

data class ReceiptEntry(
    val name: String,
    val amount: BigDecimal,
    val category: String,
    val producer: String,
    val amountTrend: AmountTrend
) {
    enum class AmountTrend {
        ASCENDING, STAGNATING, DESCENDING
    }
}