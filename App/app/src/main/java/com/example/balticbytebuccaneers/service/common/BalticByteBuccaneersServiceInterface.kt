package com.example.balticbytebuccaneers.service.common

import com.example.balticbytebuccaneers.service.branch.Branch
import retrofit2.http.GET
import retrofit2.http.Headers

interface BalticByteBuccaneersServiceInterface {
    @GET("branches")
    @Headers("accept: application/json")
    suspend fun fetchAllBranches(): List<Branch>

    suspend fun fetchAllRetailers()
}