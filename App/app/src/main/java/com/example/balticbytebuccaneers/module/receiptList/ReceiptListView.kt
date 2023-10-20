package com.example.balticbytebuccaneers.module.receiptList

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ReceiptListView() {
    LazyColumn {
        // Add a single item
        item {
            Text(text = "Kondome - 5€")
        }

        // Add 5 items
        items(5) { index ->
            Text(text = "Wasserflasche: $index - 1,40 €")
        }

        // Add another single item
        item {
            Text(text = "Klopapier - 45 €")
        }
    }
}