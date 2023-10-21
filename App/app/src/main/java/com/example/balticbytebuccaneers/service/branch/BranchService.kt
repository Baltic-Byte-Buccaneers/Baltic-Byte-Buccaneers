package com.example.balticbytebuccaneers.service.branch

import com.example.balticbytebuccaneers.service.common.BalticByteBuccaneersService

class BranchService {
    suspend fun fetchAllBranches(): List<Branch> {
        return BalticByteBuccaneersService.instance.fetchAllBranches()
    }

    suspend fun fetchBranchById(id: String): Branch {
        return BalticByteBuccaneersService.instance.fetchBranchById(id)
    }
}