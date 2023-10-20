package com.example.balticbytebuccaneers.module.receiptList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ReceiptListView(viewModel: ReceiptListViewModel, onClick: () -> Unit) {
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
            ReceiptList(receipts = viewModel.receipts)
        }
    }

}

@Preview
@Composable
fun ReceiptListViewPreview() {
    val model = ReceiptListViewModel()

    ReceiptListView(viewModel = model, onClick = {})

}
