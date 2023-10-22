package com.example.balticbytebuccaneers.module.receiptDetail.retailDialog

import androidx.lifecycle.MutableLiveData
import com.example.balticbytebuccaneers.service.producer.Producer
import com.example.balticbytebuccaneers.service.producer.ProducerService
import com.example.balticbytebuccaneers.service.receipt.domain.ReceiptEntry
import com.example.balticbytebuccaneers.service.stock.PriceData
import com.example.balticbytebuccaneers.service.stock.Stock
import com.example.balticbytebuccaneers.service.stock.StockService
import com.example.balticbytebuccaneers.util.onNonNull
import java.math.BigDecimal
import java.util.Locale

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

    val lastWeekTrend = MutableLiveData<Trend?>()
    val lastWeekTrendValue = MutableLiveData<String>()

    val lastYearTrend = MutableLiveData<Trend?>()
    val lastYearTrendValue = MutableLiveData<String>()

    val producerName = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    private val producerService = ProducerService()
    private val stockService = StockService()

    suspend fun fetchProducerInformation() {
        viewState.value = ViewState.LOADING

        val result = kotlin.runCatching {
            val producer = producerService.fetchProducerById(producerId)
            producer.stockId?.let {
                val stock = stockService.fetchStockById(it)
                return@runCatching Pair(producer, stock)
            }
        }

        if (result.isSuccess) {
            result.getOrNull()?.let {
                setData(it.first, it.second)
            }
            viewState.value = ViewState.DATA
        } else {
            viewState.value = ViewState.ERROR
        }
    }

    private fun setData(producer: Producer, stock: Stock) {
        lastWeekTrend.value = getTrendOverTime(stock.lastPrice, stock.priceData?.getOrNull(5))
        lastWeekTrendValue.value = getPriceOverTimeDifference(stock.lastPrice, stock.priceData?.getOrNull(5))
        lastYearTrend.value = getTrendOverTime(stock.lastPrice, stock.priceData?.last())
        lastYearTrendValue.value = getPriceOverTimeDifference(stock.lastPrice, stock.priceData?.last())


        producerName.value = producer.name ?: "--"
        description.value = stock.description ?: "--"
    }
    private fun getPriceOverTimeDifference(lastPriceString: String?, priceData: PriceData?): String {
        onNonNull(lastPriceString, priceData) { lastPriceNonNull, priceDataNonNull ->
            val lastPrice = BigDecimal(lastPriceNonNull)
            return priceDataNonNull.low?.let {
                val difference = BigDecimal(it) - lastPrice
                return "%,.2f".format(Locale.GERMAN, difference) + " €"
            } ?: "--"
        }
        return "--"
    }

    private fun getTrendOverTime(lastPriceString: String?, priceData: PriceData?): Trend? {
        onNonNull(lastPriceString, priceData) { lastPriceNonNull, priceDataNonNull ->
            val lastPrice = BigDecimal(lastPriceNonNull)
            return priceDataNonNull.low?.let {
                val difference = BigDecimal(it) - lastPrice
                return if (difference > BigDecimal.ZERO) {
                    Trend.ASCENDING
                } else if (difference < BigDecimal.ZERO) {
                    Trend.DESCENDING
                } else {
                    Trend.NEUTRAL
                }
            }
        }
        return null
    }
}