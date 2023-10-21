@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.balticbytebuccaneers.module.receiptDetail.retailDialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TrendingDown
import androidx.compose.material.icons.filled.TrendingFlat
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.balticbytebuccaneers.module.receiptDetail.retailDialog.ProducerDetailsDialogViewModel.Trend
import kotlinx.coroutines.launch

@Composable
fun ProducerDetailsDialog(producerID: String?, showBottomSheet: Boolean, onDismissSheet: () -> Unit) {

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    if (showBottomSheet && producerID != null) {
        val bottomSheetViewModel = remember { ProducerDetailsDialogViewModel(producerID) }
        ModalBottomSheet(
            onDismissRequest = onDismissSheet,
            sheetState = sheetState
        ) {
            // Sheet content
            ProducerDetailsDialogContent(bottomSheetViewModel) {
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        onDismissSheet()
                    }
                }
            }
        }
    }
}

@Composable
private fun ProducerDetailsDialogContent(viewModel: ProducerDetailsDialogViewModel, onClick: () -> Unit) {

    val viewState = viewModel.viewState.observeAsState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = "") {
        scope.launch {
            viewModel.fetchProducerInformation()
        }
    }

    if (viewState.value == ProducerDetailsDialogViewModel.ViewState.LOADING) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 64.dp)) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    } else if (viewState.value == ProducerDetailsDialogViewModel.ViewState.DATA) {
        ProducerDetailsDialogDataContent(viewModel, onClick)
    }
}

@Composable
private fun ProducerDetailsDialogDataContent(viewModel: ProducerDetailsDialogViewModel, onClick: () -> Unit) {
    val producerName = viewModel.producerName.observeAsState("--")
    val description = viewModel.description.observeAsState("--")
    val lastYearTrend = viewModel.lastYearTrend.observeAsState()
    val lastYearTrendValue = viewModel.lastYearTrendValue.observeAsState("--")
    val lastWeekTrend = viewModel.lastWeekTrend.observeAsState()
    val lastWeekTrendValue = viewModel.lastWeekTrendValue.observeAsState("--")

    Column(modifier = Modifier
        .padding(horizontal = 16.dp)
        .padding(bottom = 32.dp)
        .fillMaxWidth()) {
        Text(
            text = producerName.value,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Text(
            text = description.value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Spacer(modifier = Modifier.width(16.dp))
            TrendCard(
                "Last Year",
                lastYearTrendValue.value,
                lastYearTrend.value,
                modifier = Modifier.weight(2f)
            )
            Spacer(modifier = Modifier.weight(0.5f))
            TrendCard(
                "Last Week",
                lastWeekTrendValue.value,
                lastWeekTrend.value,
                modifier = Modifier.weight(2f)
            )
            Spacer(modifier = Modifier.width(16.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onClick, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text("Trade Stock now")
        }
    }
}

@Composable
private fun TrendCard(
    headline: String,
    value: String,
    trend: Trend?,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer
        ),
        modifier = modifier,
    ) {
        Row(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()) {
            Column {
                Text(
                    text = headline,
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = value,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            trend?.let {
                Icon(
                    imageVector = getIconForTrend(trend),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

@Composable
private fun getIconForTrend(trend: Trend): ImageVector {
    return when(trend) {
        Trend.ASCENDING -> Icons.Filled.TrendingUp
        Trend.DESCENDING -> Icons.Filled.TrendingDown
        Trend.NEUTRAL -> Icons.Filled.TrendingFlat
    }
}

@Preview
@Composable
private fun TrendCardPreview() {
    TrendCard("Last Year", "123,45 €", Trend.ASCENDING)
}

@Preview
@Composable
private fun ProducerDetailsDialogContent() {
    ProducerDetailsDialogContent(ProducerDetailsDialogViewModel("")) {}
}