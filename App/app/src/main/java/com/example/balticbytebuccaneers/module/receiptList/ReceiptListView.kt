package com.example.balticbytebuccaneers.module.receiptList

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ReceiptListView() {
    LazyColumn {
        // Add 5 items
        items(5) { index ->
            ReceiptListViewItem(
                label = "Wasserflasche",
                amount = "$index,40 €"
            )
            Spacer(modifier= Modifier.size(50.dp, 5.dp))
        }
    }
}

@Preview
@Composable
fun ReceiptListViewPreview() {
    ReceiptListView()
}
