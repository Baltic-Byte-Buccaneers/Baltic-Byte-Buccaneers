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
fun ReceiptList(receipts: List<Receipt>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        receipts.forEach { receipt ->
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
    ReceiptList(receipts)
}
