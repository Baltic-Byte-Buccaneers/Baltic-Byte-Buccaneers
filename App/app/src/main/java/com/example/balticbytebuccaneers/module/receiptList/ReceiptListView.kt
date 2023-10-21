package com.example.balticbytebuccaneers.module.receiptList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun ReceiptListScreen(onClick: () -> Unit) {
    ReceiptListView(viewModel = ReceiptListViewModel(), onClick = onClick)
}

@Composable
fun ReceiptListView(viewModel: ReceiptListViewModel, onClick: () -> Unit) {
    val composableCoroutineScope  = rememberCoroutineScope()
    val receipts = viewModel.receipts.observeAsState()

    LaunchedEffect(key1 = "fetchAllOfUserReceipts") {
        composableCoroutineScope.launch {
            viewModel.fetchAllReceiptsOfUser()
        }
    }

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { onClick() }, shape = CircleShape) {
            Icon(Icons.Filled.ExpandLess, "Scroll to top")
        }
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            AnalyticsCarousel()
            ReceiptList(receipts = receipts.value ?: listOf())
        }
    }

}

@Preview
@Composable
fun ReceiptListViewPreview() {
    val model = ReceiptListViewModel()
    MaterialTheme {
        ReceiptListView(viewModel = model, onClick = {})
    }
}
