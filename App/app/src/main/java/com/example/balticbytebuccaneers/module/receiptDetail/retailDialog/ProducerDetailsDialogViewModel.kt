package com.example.balticbytebuccaneers.module.receiptDetail.retailDialog

import androidx.lifecycle.MutableLiveData
import com.example.balticbytebuccaneers.service.producer.ProducerService
import com.example.balticbytebuccaneers.service.receipt.domain.ReceiptEntry

class ProducerDetailsDialogViewModel(
    val producerId: String
) {
    enum class ViewState {
        LOADING, DATA, ERROR
    }

    enum class Trend {
        ASCENDING, DESCENDING, NEUTRAL
    }

    val viewState = MutableLiveData<ViewState>()

    val lastWeekTrend = MutableLiveData<Trend>()
    val lastWeekTrendValue = MutableLiveData<String>()

    val lastYearTrend = MutableLiveData<Trend>()
    val lastYearTrendValue = MutableLiveData<String>()

    val producerName = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    private val producerService = ProducerService()

    suspend fun fetchProducerInformation() {
        viewState.value = ViewState.LOADING

        val producer = producerService.fetchProducerById(producerId)
        producer.stockId?.let {

        }

        lastWeekTrend.value = Trend.DESCENDING
        lastWeekTrendValue.value = "123,34 €"
        lastYearTrend.value = Trend.ASCENDING
        lastYearTrendValue.value = "167,89 €"

        producerName.value = producer.name ?: "--"
        description.value = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum."

        viewState.value = ViewState.DATA
    }

    private fun getTrendForTendency(tendency: String): ReceiptEntry.AmountTrend? {
        return when(tendency) {
            "up" -> ReceiptEntry.AmountTrend.ASCENDING
            "down" -> ReceiptEntry.AmountTrend.DESCENDING
            "neutral" -> ReceiptEntry.AmountTrend.STAGNATING
            else -> null
        }
    }
}