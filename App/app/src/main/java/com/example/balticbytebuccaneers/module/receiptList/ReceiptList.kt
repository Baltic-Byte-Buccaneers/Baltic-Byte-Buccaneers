package com.example.balticbytebuccaneers.module.receiptList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.math.BigDecimal

@Composable
fun ReceiptList(receipts: List<Receipt>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(receipts) {receipt ->
            ReceiptListItem(receipt)
        }
    }
}

@Preview
@Composable
fun ReceiptListPreview() {
    val receipts: List<Receipt> =
        listOf(
            Receipt(
                "Einkauf Rewe",
                BigDecimal(100)
            ),
            Receipt(
                "Einkauf Kaufland",
                BigDecimal(100)
            ),
            Receipt(
                "Raststätte Neuland",
                BigDecimal(100)
            )
        )
    ReceiptList(receipts)
}
