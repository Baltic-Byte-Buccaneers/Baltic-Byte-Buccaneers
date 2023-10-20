package com.example.balticbytebuccaneers.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpClientHolder {
    val balticByteBuccaneerService = Retrofit
        .Builder()
        .baseUrl("http://10.0.2.2:8080/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(BalticByteBuccaneersService::class.java)
}