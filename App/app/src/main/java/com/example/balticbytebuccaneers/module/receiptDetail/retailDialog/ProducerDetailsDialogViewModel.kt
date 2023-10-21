package com.example.balticbytebuccaneers.module.receiptDetail.retailDialog

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.delay

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
    suspend fun fetchProducerInformation() {
        viewState.value = ViewState.LOADING
        delay(1000)

        lastWeekTrend.value = Trend.DESCENDING
        lastWeekTrendValue.value = "123,34 €"
        lastYearTrend.value = Trend.ASCENDING
        lastYearTrendValue.value = "167,89 €"

        producerName.value = "Unilever"
        description.value = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum."

        viewState.value = ViewState.DATA
    }
}