package com.example.balticbytebuccaneers.module.receiptDetail

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.delay
import java.math.BigDecimal

class ReceiptDetailViewModel(
    val receiptId: String
) {

    enum class ViewState {
        LOADING, DATA, ERROR
    }

    val state = MutableLiveData<ViewState>()

    val merchantName = MutableLiveData<String>()
    val receiptIssueDate = MutableLiveData<String>()
    val amount = MutableLiveData<BigDecimal>()

    suspend fun fetchReceiptInformation() {
        state.value = ViewState.LOADING

        delay(2000)
        merchantName.value = "Rewe"
        receiptIssueDate.value = "20.10.2023"
        amount.value = BigDecimal("12.34")

        state.value = ViewState.DATA
    }
}