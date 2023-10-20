package com.example.balticbytebuccaneers.service.receipt

import java.math.BigDecimal
import java.util.Locale.Category

data class ReceiptEntry(
    val name: String,
    val amount: BigDecimal,
    val category: String,
    val producer: String
)