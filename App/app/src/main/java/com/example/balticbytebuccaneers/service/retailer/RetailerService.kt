package com.example.balticbytebuccaneers.service.retailer

import com.example.balticbytebuccaneers.service.common.BalticByteBuccaneersService

class RetailerService {
    suspend fun fetchAllRetailers(): List<Retailer> {
        return BalticByteBuccaneersService.instance.fetchAllRetailers()
    }

    suspend fun fetchRetailerById(id: String): Retailer {
        return BalticByteBuccaneersService.instance.fetchRetailerById(id)
    }
}