package com.example.balticbytebuccaneers.service.receipt

import java.util.Date

data class Receipt(
    val branchId: String?,
    val date: Date?,
    val description: String?,
    val entries: List<Entry>?,
    val id: String?,
    val metadata: ReceiptMetaData?,
    val retailerId: String?
)
