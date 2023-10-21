package com.example.balticbytebuccaneers.module.receiptDetail

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleDown
import androidx.compose.material.icons.filled.ArrowCircleRight
import androidx.compose.material.icons.filled.ArrowCircleUp
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.TrendingDown
import androidx.compose.material.icons.filled.TrendingFlat
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.outlined.ArrowCircleDown
import androidx.compose.material.icons.outlined.ArrowCircleRight
import androidx.compose.material.icons.outlined.ArrowCircleUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.balticbytebuccaneers.service.receipt.ReceiptEntry
import com.example.balticbytebuccaneers.ui.theme.BalticByteBuccaneersTheme
import java.math.BigDecimal

@Composable
fun ReceiptEntryCard(receiptEntry: ReceiptEntry, onProducerTapped: () -> Unit) {
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
                AmountLabel(amount = "${receiptEntry.amount} €")
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = getIconFromTrend(receiptEntry.amountTrend),
                    contentDescription = "",
                    modifier = Modifier
                        .size(24.dp)
                        .align(CenterVertically),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Text(
                    text = receiptEntry.category,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = receiptEntry.producer,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.clickable { onProducerTapped() },
                    textDecoration = TextDecoration.Underline,
                )
            }
        }
    }
}

@Composable
private fun getIconFromTrend(trend: ReceiptEntry.AmountTrend): ImageVector {
    return when(trend) {
        ReceiptEntry.AmountTrend.ASCENDING -> Icons.Outlined.ArrowCircleUp
        ReceiptEntry.AmountTrend.STAGNATING -> Icons.Outlined.ArrowCircleRight
        ReceiptEntry.AmountTrend.DESCENDING -> Icons.Outlined.ArrowCircleDown
    }
}

@Composable
private fun AmountLabel(amount: String, modifier: Modifier = Modifier, enabled: Boolean = true) {
    val containerColor = with(MaterialTheme.colorScheme) {
        if (enabled) inverseSurface else inverseOnSurface
    }
    val contentColor = with(MaterialTheme.colorScheme) {
        if (enabled) inverseOnSurface else inverseSurface
    }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
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
                "Frankfurter Mühlen",
                ReceiptEntry.AmountTrend.ASCENDING
            )
        ) {}
    }
}