package com.example.balticbytebuccaneers.module.transactionList

import androidx.lifecycle.MutableLiveData
import com.example.balticbytebuccaneers.service.receipt.domain.MetadataEntry
import com.example.balticbytebuccaneers.service.receipt.domain.ReceiptEntry
import com.example.balticbytebuccaneers.service.transaction.Transaction
import com.example.balticbytebuccaneers.service.transaction.TransactionService
import kotlinx.coroutines.delay
import java.math.BigDecimal

class TransactionListViewModel() {

    enum class ViewState {
        LOADING, DATA, ERROR
    }

    val state = MutableLiveData<ViewState>()

    val transactions = MutableLiveData<List<Transaction>>()
    private val transactionService = TransactionService()

    suspend fun fetchTransactions() {
        state.value = ViewState.LOADING
        transactions.value = transactionService.fetchAllTransactions()
        state.value = ViewState.DATA
    }
}