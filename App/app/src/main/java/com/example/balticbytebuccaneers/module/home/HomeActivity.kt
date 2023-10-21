package com.example.balticbytebuccaneers.module.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.balticbytebuccaneers.component.bottomNavigation.AppNavigationBar
import com.example.balticbytebuccaneers.component.bottomNavigation.NavigationItem
import com.example.balticbytebuccaneers.module.transactionList.TransactionView
import com.example.balticbytebuccaneers.ui.theme.BalticByteBuccaneersTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BalticByteBuccaneersTheme(dynamicColor = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainNavigationView()
                }
            }
        }
    }
}

@Composable
fun MainNavigationView() {
    var navDestination by remember { mutableStateOf(NavigationItem.TRANSACTIONS) }

    Column(Modifier.fillMaxSize()) {
        Column(Modifier.weight(1f)) {
            when (navDestination) {
                NavigationItem.RECEIPTS -> Text(text = "RECEIPTS")
                NavigationItem.TRANSACTIONS -> TransactionView(transactions = arrayOf())

                NavigationItem.ANALYSIS -> Text(text = "ANALYSIS")
            }
        }
        AppNavigationBar { newSelectedNavigationItem ->
            navDestination = newSelectedNavigationItem
        }
    }
}