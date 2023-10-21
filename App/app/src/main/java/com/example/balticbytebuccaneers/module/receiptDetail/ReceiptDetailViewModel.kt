package com.example.balticbytebuccaneers.module.receiptDetail

import androidx.lifecycle.MutableLiveData
import com.example.balticbytebuccaneers.service.receipt.domain.MetadataEntry
import com.example.balticbytebuccaneers.service.receipt.domain.ReceiptEntry
import kotlinx.coroutines.delay
import java.math.BigDecimal

class ReceiptDetailViewModel(
    val receiptId: String,
    val onBackClick: () -> Unit
) {

    enum class ViewState {
        LOADING, DATA, ERROR
    }

    val state = MutableLiveData<ViewState>()

    val merchantName = MutableLiveData<String>()
    val receiptIssueDate = MutableLiveData<String>()
    val amount = MutableLiveData<BigDecimal>()
    val receiptEntries = MutableLiveData<List<ReceiptEntry>>()
    val metadata = MutableLiveData<List<MetadataEntry>>()

    suspend fun fetchReceiptInformation() {
        state.value = ViewState.LOADING

        delay(2000)
        merchantName.value = "Rewe"
        receiptIssueDate.value = "20.10.2023"
        amount.value = BigDecimal("12.34")
        receiptEntries.value = generateSequence {
            ReceiptEntry(
                "Käse",
                BigDecimal("12.34"),
                "Lebensmittel",
                "Frankfurter Mühlen",
                ReceiptEntry.AmountTrend.values().random()
            )
        }.take(10).toList()

        metadata.value = generateSequence { MetadataEntry("Key", "Value") }
            .take(5)
            .toList()

        state.value = ViewState.DATA
    }
}