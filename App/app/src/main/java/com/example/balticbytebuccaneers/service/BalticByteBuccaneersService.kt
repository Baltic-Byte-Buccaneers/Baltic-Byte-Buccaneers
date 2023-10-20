package com.example.balticbytebuccaneers.service

import retrofit2.http.GET
import retrofit2.http.Headers

interface BalticByteBuccaneersService {
    @GET("branches")
    @Headers("accept: application/json")
    suspend fun fetchAllBranches(): List<Branch>
}