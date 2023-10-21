package com.example.balticbytebuccaneers.service.producer

import com.example.balticbytebuccaneers.service.common.BalticByteBuccaneersService

class ProducerService {
    suspend fun fetchAllProducers(): List<Producer> {
        return BalticByteBuccaneersService.instance.fetchAllProducers()
    }

    suspend fun fetchProducerById(id: String): Producer {
        return BalticByteBuccaneersService.instance.fetchProducerById(id)
    }
}