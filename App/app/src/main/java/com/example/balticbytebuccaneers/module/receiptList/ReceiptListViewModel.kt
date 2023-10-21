package com.example.balticbytebuccaneers.module.receiptList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.balticbytebuccaneers.service.producer.Producer
import com.example.balticbytebuccaneers.service.producer.ProducerService
import com.example.balticbytebuccaneers.service.receipt.ReceiptService
import com.example.balticbytebuccaneers.service.retailer.Retailer
import com.example.balticbytebuccaneers.service.retailer.RetailerService
import java.math.BigDecimal

class ReceiptListViewModel: ViewModel() {
    private val receiptService = ReceiptService()
    private val producerService = RetailerService()

    var receipts = MutableLiveData<List<Receipt>>()

    suspend fun fetchAllReceiptsOfUser() {
        receiptService.fetchReceiptsByUserId("6533d29058066cb1f76c59e2").also { apiReceipts ->
            receipts.value = apiReceipts.map { receipt ->
                val retailer = producerService.fetchRetailerById(receipt.retailerId ?: "")
                mapServiceReceiptToDomainReceipt(receipt, retailer)
            }
        }
    }

    private fun mapServiceReceiptToDomainReceipt(receipt: com.example.balticbytebuccaneers.service.receipt.Receipt, retailer: Retailer): Receipt {
        var amount = BigDecimal(0)
        receipt.entries!!.forEach {
            amount += it.amount ?: BigDecimal(0)
        }

        return Receipt(receipt.id ?: "", retailer.name ?: "--", amount)
    }
}