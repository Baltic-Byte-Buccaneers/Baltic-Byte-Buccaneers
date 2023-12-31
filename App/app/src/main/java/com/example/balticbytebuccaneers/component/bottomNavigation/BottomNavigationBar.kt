package com.example.balticbytebuccaneers.component.bottomNavigation

import androidx.compose.foundation.layout.size
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
fun AppNavigationBar(selectedItem: NavigationItem, onItemSelected: (NavigationItem) -> Unit) {

    NavigationBar {
        NavigationItem.values().forEachIndexed { index, navigationBarItem ->
            NavigationBarItem(
                selected = navigationBarItem == selectedItem,
                onClick = {
                    onItemSelected(navigationBarItem)
                          },
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