package com.example.balticbytebuccaneers.service.receipt

data class Receipt(
    val branchId: String?,
    val date: String?,
    val description: String?,
    val entries: List<Entry>?,
    val id: String?,
    val metaData: ReceiptMetaData?,
    val retailerId: String?
)
