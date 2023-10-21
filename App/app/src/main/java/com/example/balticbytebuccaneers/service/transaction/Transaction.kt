package com.example.balticbytebuccaneers.service.transaction

import java.math.BigDecimal
import java.util.Date

data class Transaction (
    val id: String?,
    val userId: String?,
    val iban: String?,
    val amount: BigDecimal?,
    val date: Date?,
    val valutaDate: Date?,
    val description: String?,
    val purpose: String?,
    val receiptId: String?)
