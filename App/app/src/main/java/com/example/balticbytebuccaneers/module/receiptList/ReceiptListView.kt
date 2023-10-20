package com.example.balticbytebuccaneers.module.receiptList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ReceiptListView(receipts: List<Receipt>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
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
            ),
            Receipt(
                "Raststätte Neuland",
                "232,18 €"
            )
        )
    ReceiptListView(receipts)
}
