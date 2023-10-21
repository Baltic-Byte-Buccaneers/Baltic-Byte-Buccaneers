package com.example.balticbytebuccaneers.service.stock

import com.example.balticbytebuccaneers.service.common.BalticByteBuccaneersService

class StockService {
    suspend fun fetchAllStocks(): List<Stock> {
        return BalticByteBuccaneersService.instance.fetchAllStocks()
    }

    suspend fun fetchStockById(id: String): Stock {
        return BalticByteBuccaneersService.instance.fetchStockById(id)
    }
}