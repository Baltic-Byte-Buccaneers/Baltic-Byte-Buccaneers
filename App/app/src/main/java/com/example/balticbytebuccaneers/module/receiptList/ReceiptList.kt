package com.example.balticbytebuccaneers.module.receiptList

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.math.BigDecimal

@Composable
fun ReceiptList(receipts: List<Receipt>, onListItemClick: (String) -> Unit) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            AnalyticsCarousel()
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(receipts) {receipt ->
            ReceiptListItem(receipt, onListItemClick)
        }
    }
}

@Preview
@Composable
fun ReceiptListPreview() {
    val receipts: List<Receipt> =
        listOf(
            Receipt(
                "ID",
                "Einkauf Rewe",
                BigDecimal(100)
            ),
            Receipt(
                "ID",
                "Einkauf Kaufland",
                BigDecimal(100)
            ),
            Receipt(
                "ID",
                "Raststätte Neuland",
                BigDecimal(100)
            )
        )
    ReceiptList(receipts) { }
}
