package com.example.balticbytebuccaneers.module.receiptDetail

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.balticbytebuccaneers.service.receipt.ReceiptEntry
import com.example.balticbytebuccaneers.ui.theme.BalticByteBuccaneersTheme
import java.math.BigDecimal

@Composable
fun ReceiptEntryCard(receiptEntry: ReceiptEntry) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row {
                Text(
                    text = receiptEntry.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                AmountLabel(amount = receiptEntry.amount.toString())
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Text(
                    text = receiptEntry.category,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = receiptEntry.producer
                )
            }
        }
    }
}

@Composable
private fun AmountLabel(amount: String, modifier: Modifier = Modifier, enabled: Boolean = true) {
    val containterColor = with(MaterialTheme.colorScheme) {
        if (enabled) inverseSurface else inverseOnSurface
    }
    val contentColor = with(MaterialTheme.colorScheme) {
        if (enabled) inverseOnSurface else inverseSurface
    }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = containterColor,
            contentColor = contentColor,
        ),
        shape = MaterialTheme.shapes.large,
        modifier = modifier.wrapContentHeight()
    ) {
        Text(
            text = amount,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ReceiptEntryCardPreview() {
    BalticByteBuccaneersTheme {
        ReceiptEntryCard(
            ReceiptEntry(
                "Käse",
                BigDecimal("12.34"),
                "Lebensmittel",
                "Frankfurter Mühlen"
            )
        )
    }
}