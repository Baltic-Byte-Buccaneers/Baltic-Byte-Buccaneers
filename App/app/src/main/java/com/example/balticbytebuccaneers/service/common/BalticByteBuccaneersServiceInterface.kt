package com.example.balticbytebuccaneers.service.common

import com.example.balticbytebuccaneers.service.branch.Branch
import com.example.balticbytebuccaneers.service.producer.Producer
import com.example.balticbytebuccaneers.service.receipt.Receipt
import com.example.balticbytebuccaneers.service.retailer.Retailer
import com.example.balticbytebuccaneers.service.stock.Stock
import com.example.balticbytebuccaneers.service.transaction.Transaction
import retrofit2.http.GET
import retrofit2.http.Path

interface BalticByteBuccaneersServiceInterface {
    @GET("branches")
    suspend fun fetchAllBranches(): List<Branch>

    @GET("branches/{id}")
    suspend fun fetchBranchById(@Path("id") id: String): Branch


    @GET("producers")
    suspend fun fetchAllProducers(): List<Producer>
    @GET("producers/{id}")
    suspend fun fetchProducerById(@Path("id") id: String): Producer


    @GET("receipts")
    suspend fun fetchAllReceipts(): List<Receipt>
    @GET("receipts/{id}")
    suspend fun fetchReceiptById(@Path("id") id: String): Receipt

    @GET("receipts/user/{userId}")
    suspend fun fetchAllReceiptsOfUser(@Path("userId") userId: String): List<Receipt>


    @GET("retailers")
    suspend fun fetchAllRetailers(): List<Retailer>
    @GET("retailers/{id}")
    suspend fun fetchRetailerById(@Path("id") id: String): Retailer


    @GET("transactions")
    suspend fun fetchAllTransactions(): List<Transaction>
    @GET("transactions/{id}")
    suspend fun fetchTransactionById(@Path("id") id: String): Transaction

    @GET("transactions/user/{userId}")
    suspend fun fetchAllTransactionsOfUser(@Path("userId") userId: String): List<Transaction>

    @GET("stocks")
    suspend fun fetchAllStocks(): List<Stock>

    @GET("stocks/{id}")
    suspend fun fetchStockById(@Path("id") stockId: String): Stock
}