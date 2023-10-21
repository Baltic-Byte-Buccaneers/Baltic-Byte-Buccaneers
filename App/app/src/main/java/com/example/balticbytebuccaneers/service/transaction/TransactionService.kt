package com.example.balticbytebuccaneers.service.transaction

import com.example.balticbytebuccaneers.service.common.BalticByteBuccaneersService

class TransactionService {
    suspend fun fetchAllTransactions(): List<Transaction> {
        return BalticByteBuccaneersService.instance.fetchAllTransactions()
    }

    suspend fun fetchTransactionById(id: String): Transaction {
        return BalticByteBuccaneersService.instance.fetchTransactionById(id)
    }

    suspend fun fetchTransactionsOfUser(userId: String): List<Transaction> {
        return BalticByteBuccaneersService.instance.fetchAllTransactionsOfUser(userId)
    }
}