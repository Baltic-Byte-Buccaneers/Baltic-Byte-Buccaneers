package com.example.balticbytebuccaneers.component.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppNavigationBar(navigationBarItems: List<NavigationItem>, onItemSelected: (Int) -> Unit) {
    var selectedNavigationItemIndex by remember { mutableStateOf(0) }

    NavigationBar {
        navigationBarItems.forEachIndexed { index, navigationBarItem ->
            NavigationBarItem(
                selected = index == selectedNavigationItemIndex,
                onClick = { selectedNavigationItemIndex = index },
                icon = { Icon(
                    imageVector = navigationBarItem.icon,
                    contentDescription = "",
                    modifier = Modifier.size(24.dp)
                ) },
                label = { Text(navigationBarItem.displayText) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurface)
            )
        }
    }
}

fun getNavigationBarItems(): List<NavigationItem> {
    return listOf(
        NavigationItem("Transactions", Icons.Filled.AccountBalance),
        NavigationItem("Receipts", Icons.Filled.ReceiptLong),
        NavigationItem("Shopping List", Icons.Filled.ShoppingCart),
        NavigationItem("Analysis", Icons.Filled.ShowChart))
}