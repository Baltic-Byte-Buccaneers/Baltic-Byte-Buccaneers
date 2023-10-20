package com.example.balticbytebuccaneers.module.receiptList

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ReceiptListView(receipts: List<Receipt>) {
    Column {
        // Add 5 items
        receipts.forEach { receipt ->
            ReceiptListViewItem(receipt)
        }
    }
}

@Preview
@Composable
fun ReceiptListViewPreview() {
    val receipts: List<Receipt> =
        listOf(
            Receipt(
                "Einkauf Rewe",
                "12,40 €"
            ),
            Receipt(
                "Einkauf Kaufland",
                "153,30 €"
            )
        )
    ReceiptListView(receipts)
}
