package com.example.balticbytebuccaneers.service.common

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BalticByteBuccaneersService
{
    val instance: BalticByteBuccaneersServiceInterface = Retrofit
        .Builder()
        .baseUrl("http://10.0.2.2:8080/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(BalticByteBuccaneersServiceInterface::class.java)
}