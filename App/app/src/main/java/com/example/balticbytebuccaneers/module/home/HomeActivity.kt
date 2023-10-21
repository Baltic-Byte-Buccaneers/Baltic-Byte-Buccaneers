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
import com.example.balticbytebuccaneers.module.receiptDetail.ReceiptDetailView
import com.example.balticbytebuccaneers.module.receiptDetail.ReceiptDetailViewModel
import com.example.balticbytebuccaneers.module.transactionList.TransactionListView
import com.example.balticbytebuccaneers.module.transactionList.TransactionListViewModel
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
                    AnalystsView()
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
                NavigationItem.RECEIPTS -> ReceiptsView("6533d1f8c91e3a690d412e4a")
                NavigationItem.TRANSACTIONS -> TransactionListViewWrapper {}
                NavigationItem.ANALYSIS -> Text(text = "ANALYSIS")
            }
        }
        AppNavigationBar { newSelectedNavigationItem ->
            navDestination = newSelectedNavigationItem
        }
    }
}

@Composable
private fun TransactionListViewWrapper(onTransactionClicked: (receiptId: String) -> Unit) {
    val viewModel = remember { TransactionListViewModel() }
    TransactionListView(viewModel, onTransactionClicked)
}
@Composable
private fun ReceiptsView(receiptId: String) {
    val viewModel = remember { ReceiptDetailViewModel(receiptId = receiptId) {} }
    ReceiptDetailView(viewModel = viewModel)
}