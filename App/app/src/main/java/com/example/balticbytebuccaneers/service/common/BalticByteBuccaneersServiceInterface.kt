package com.example.balticbytebuccaneers.service.common

import com.example.balticbytebuccaneers.service.branch.Branch
import retrofit2.http.GET

interface BalticByteBuccaneersServiceInterface {
    @GET("branches")
    suspend fun fetchAllBranches(): List<Branch>
}