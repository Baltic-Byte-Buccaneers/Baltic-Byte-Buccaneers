package com.example.balticbytebuccaneers.service.receipt

import com.example.balticbytebuccaneers.service.common.BalticByteBuccaneersService

class ReceiptService {
    suspend fun fetchAllReceipts(): List<Receipt> {
        return BalticByteBuccaneersService.instance.fetchAllReceipts()
    }

    suspend fun fetchReceiptById(id: String): Receipt {
        return BalticByteBuccaneersService.instance.fetchReceiptById(id)
    }
}