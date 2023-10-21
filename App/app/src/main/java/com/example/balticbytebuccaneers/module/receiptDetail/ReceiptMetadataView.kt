package com.example.balticbytebuccaneers.module.receiptDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.balticbytebuccaneers.service.receipt.domain.MetadataEntry
import com.example.balticbytebuccaneers.ui.theme.BalticByteBuccaneersTheme

@Composable
fun ReceiptMetadataView(entries: List<MetadataEntry>) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Additional receipt information",
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.height(16.dp))

            entries.forEachIndexed { index, metadataEntry ->
                MetadataEntry(entry = MetadataEntry(metadataEntry.key, metadataEntry.value))

                if (index < entries.size-1) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Divider(
                        thickness = 0.5.dp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}

@Composable
private fun MetadataEntry(entry: MetadataEntry) {
    Row {
        Text(
            text = entry.key,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(1f),
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = entry.value,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.End,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview
@Composable
fun ReceiptMetadataViewPreview() {
    BalticByteBuccaneersTheme {
        ReceiptMetadataView(listOf(
            MetadataEntry("Key", "Value"),
            MetadataEntry("Key", "Value"),
            MetadataEntry("Key", "Value"),
            MetadataEntry("Key", "Value"),
            MetadataEntry("Key", "Value"),
        ))
    }
}