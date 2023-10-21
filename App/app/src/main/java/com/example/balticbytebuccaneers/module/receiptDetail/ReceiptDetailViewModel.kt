package com.example.balticbytebuccaneers.module.receiptDetail

import androidx.compose.runtime.Composable
import androidx.lifecycle.MutableLiveData
import com.example.balticbytebuccaneers.service.producer.Producer
import com.example.balticbytebuccaneers.service.producer.ProducerService
import com.example.balticbytebuccaneers.service.receipt.Receipt
import com.example.balticbytebuccaneers.service.receipt.ReceiptMetaData
import com.example.balticbytebuccaneers.service.receipt.ReceiptService
import com.example.balticbytebuccaneers.service.receipt.domain.MetadataEntry
import com.example.balticbytebuccaneers.service.receipt.domain.ReceiptEntry
import com.example.balticbytebuccaneers.service.retailer.Retailer
import com.example.balticbytebuccaneers.service.retailer.RetailerService
import kotlinx.coroutines.delay
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.Locale

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

    private val receiptService = ReceiptService()
    private val retailerService = RetailerService()
    private val producerService = ProducerService()

    suspend fun fetchReceiptInformation() {
        state.value = ViewState.LOADING

        val receipt = receiptService.fetchReceiptById(receiptId)
        receipt.retailerId?.let {
            val retailer = retailerService.fetchRetailerById(receipt.retailerId)
            val producers = producerService.fetchAllProducers()
            println(retailer)
            setData(receipt, retailer, producers)
            return
        }

    }

    private fun setData(receipt: Receipt, retailer: Retailer, producers: List<Producer>) {
        merchantName.value = retailer.name ?: "--"
        val s = SimpleDateFormat("dd.mm.yyyy", Locale.GERMANY)
        receiptIssueDate.value = receipt.date?.let { s.format(it) } ?: "--"
        amount.value = receipt.entries?.map { it.amount ?: BigDecimal.ZERO }
            ?.reduce { first, second -> first + second }

        receiptEntries.value = receipt.entries?.map {
            ReceiptEntry(
                it.title ?: "--",
                it.amount,
                it.category,
                producers.firstOrNull { producer ->
                    producer.id == it.producerId
                }?.name ?: "--",
                it.producerId,
                getTrendForTendency(it.priceTendency)
            )
        }

        metadata.value = getMetadataEntriesFromMetadata(receipt.metadata)

        state.value = ViewState.DATA
    }

    private fun getTrendForTendency(tendency: String): ReceiptEntry.AmountTrend? {
        return when(tendency) {
            "up" -> ReceiptEntry.AmountTrend.ASCENDING
            "down" -> ReceiptEntry.AmountTrend.DESCENDING
            "neutral" -> ReceiptEntry.AmountTrend.STAGNATING
            else -> null
        }
    }

    private fun getMetadataEntriesFromMetadata(metadata: ReceiptMetaData?): List<MetadataEntry> {
        return listOf(
            MetadataEntry("Checksum", metadata?.checkSum ?: "--"),
            MetadataEntry("Serial Number", metadata?.serialNumber ?: "--"),
            MetadataEntry("Signature Count", metadata?.signatureCount ?: "--"),
            MetadataEntry("Terminal Id", metadata?.terminalId ?: "--"),
            MetadataEntry("Transaction Id", metadata?.transactionId ?: "--"),
        )
    }
}