package com.example.balticbytebuccaneers.module.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.NavigationBarItemDefaults.colors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.balticbytebuccaneers.ui.theme.BalticByteBuccaneersTheme

@Composable
fun HomeView() {
    var selectedNavigationItem by remember { mutableStateOf(0) }
    val navigationBarItems = getNavigationBarItems()

    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
        NavigationBar {
                navigationBarItems.forEachIndexed { index, navigationBarItem ->
                    NavigationBarItem(
                        selected = index == selectedNavigationItem,
                        onClick = { selectedNavigationItem = index },
                        icon = { Icon(
                            imageVector = navigationBarItem.icon,
                            contentDescription = "",
                            modifier = Modifier.size(24.dp)
                        ) },
                        label = { Text(navigationBarItem.displayText) },
                        colors = colors(
                            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurface)
                    )
                }
        }
    }
}

@Preview
@Composable
fun HomeViewPreview() {
    BalticByteBuccaneersTheme {
        HomeView()
    }
}

private fun getNavigationBarItems(): List<NavigationItem> {
    return listOf(
        NavigationItem("Transactions", Icons.Filled.AccountBalance),
        NavigationItem("Receipts", Icons.Filled.ReceiptLong),
        NavigationItem("Shopping List", Icons.Filled.ShoppingCart),
        NavigationItem("Analysis", Icons.Filled.ShowChart))
}

private data class NavigationItem(val displayText: String, val icon: ImageVector)