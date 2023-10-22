package com.example.balticbytebuccaneers.module.receiptList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.balticbytebuccaneers.module.receiptDetail.ReceiptDetailViewModel
import com.example.balticbytebuccaneers.service.producer.Producer
import com.example.balticbytebuccaneers.service.producer.ProducerService
import com.example.balticbytebuccaneers.service.receipt.ReceiptService
import com.example.balticbytebuccaneers.service.retailer.Retailer
import com.example.balticbytebuccaneers.service.retailer.RetailerService
import java.math.BigDecimal

class ReceiptListViewModel: ViewModel() {
    private val receiptService = ReceiptService()
    private val retailerService = RetailerService()

    var receipts = MutableLiveData<List<Receipt>>()

    val state = MutableLiveData<ViewState>()

    enum class ViewState {
        LOADING, DATA, ERROR
    }

    suspend fun fetchAllReceiptsOfUser() {
        state.value = ViewState.LOADING

        val retailers = retailerService.fetchAllRetailers()
        receiptService.fetchReceiptsByUserId("6533d29058066cb1f76c59e2").also { apiReceipts ->
            receipts.value = apiReceipts.map { receipt ->
                mapServiceReceiptToDomainReceipt(receipt, retailers)
            }
        }
        state.value = ViewState.DATA
    }

    private fun mapServiceReceiptToDomainReceipt(receipt: com.example.balticbytebuccaneers.service.receipt.Receipt, retailers: List<Retailer>): Receipt {
        var amount = BigDecimal(0)
        receipt.entries?.forEach {
            amount += it.amount ?: BigDecimal(0)
        }

        val description = retailers.firstOrNull { (it.id ?: "") == (receipt.retailerId ?: "") }

        return Receipt(receipt.id ?: "", description?.name ?: "--", amount)
    }
}