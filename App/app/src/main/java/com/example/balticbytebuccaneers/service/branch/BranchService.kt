package com.example.balticbytebuccaneers.service.branch

import com.example.balticbytebuccaneers.service.common.BalticByteBuccaneersService

class BranchService {
    suspend fun fetchAllBranches(): List<Branch> {
        return BalticByteBuccaneersService.instance.fetchAllBranches()
    }
}