package com.example.balticbytebuccaneers.module.receiptList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ReceiptListViewItem(receipt: Receipt) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.ReceiptLong,
                contentDescription = "",
                modifier =
                Modifier.size(64.dp)
            )

            Text(text = receipt.description, color = MaterialTheme.colorScheme.onSecondaryContainer)
            Text(text = receipt.amount, color = MaterialTheme.colorScheme.onSecondaryContainer)
        }
    }
}

@Preview
@Composable
fun ReceiptListViewItemPreview() {
    ReceiptListViewItem(Receipt("Edeka Sunwold", "12.43 €"))
}
