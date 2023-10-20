package com.example.balticbytebuccaneers.module.receiptList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ReceiptListViewItem(receipt: Receipt) {
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment =Alignment.CenterVertically
    ) {
        Icon(
            imageVector= Icons.Filled.ReceiptLong,
            contentDescription = "",
            modifier =
            Modifier.size(64.dp).background(Color.LightGray)
        )
        Text(text=receipt.description, color= Color.White)
        Text(text=receipt.amount, color=Color.Gray)
    }
}

@Preview
@Composable
fun ReceiptListViewItemPreview() {
    ReceiptListViewItem(Receipt("Wasserflasche", "12.43 €"))
}
