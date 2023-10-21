package com.example.balticbytebuccaneers.module.receiptList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.balticbytebuccaneers.service.receipt.ReceiptService
import java.math.BigDecimal

class ReceiptListViewModel: ViewModel() {
    private val receiptService = ReceiptService()

    var receipts = MutableLiveData<List<Receipt>>()

    suspend fun fetchAllReceiptsOfUser() {
        receiptService.fetchReceiptsByUserId("6533d29058066cb1f76c59e2").also { apiReceipts ->
            receipts.value = apiReceipts.map(::mapServiceReceiptToDomainReceipt)
        }
    }

    private fun mapServiceReceiptToDomainReceipt(receipt: com.example.balticbytebuccaneers.service.receipt.Receipt): Receipt {
        var amount = BigDecimal(0)
        receipt.entries!!.forEach {
            amount += it.amount ?: BigDecimal(0)
        }

        return Receipt(receipt.retailerId ?: "--", amount)
    }
}