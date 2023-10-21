package com.example.balticbytebuccaneers.service.transaction

import java.math.BigDecimal

data class Transaction(
    val amount: BigDecimal?,
    val date: String?,
    val description: String?,
    val iban: String?,
    val id: String?,
    val purpose: String?,
    val receiptId: String?,
    val userId: String?,
    val valutaDate: String?
)