package com.example.balticbytebuccaneers.service.receipt

data class ReceiptMetaData (
    val checkSum: String?,
    val serialNumber: String?,
    val signatureCount: String?,
    val terminalId: String?,
    val transactionId: String?
)