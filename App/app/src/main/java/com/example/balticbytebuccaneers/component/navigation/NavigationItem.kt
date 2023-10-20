package com.example.balticbytebuccaneers.component.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.ui.graphics.vector.ImageVector

enum class NavigationItem(val displayText: String, val icon: ImageVector) {
    TRANSACTIONS("Transactions", Icons.Filled.AccountBalance),
    RECEIPTS("Receipts", Icons.Filled.ReceiptLong),
    SHOPPING_LIST("Shopping List", Icons.Filled.ShoppingCart),
    ANALYSIS("Analysis", Icons.Filled.ShowChart)
}