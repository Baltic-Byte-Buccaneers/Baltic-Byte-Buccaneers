package com.example.balticbytebuccaneers.module.receiptDetail

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.balticbytebuccaneers.service.receipt.ReceiptEntry
import com.example.balticbytebuccaneers.ui.theme.BalticByteBuccaneersTheme
import kotlinx.coroutines.launch
import java.math.BigDecimal

@Composable
fun ReceiptDetailView(viewModel: ReceiptDetailViewModel) {
    val state by viewModel.state.observeAsState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = viewModel.receiptId) {
        scope.launch {
            viewModel.fetchReceiptInformation()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (state == ReceiptDetailViewModel.ViewState.LOADING) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (state == ReceiptDetailViewModel.ViewState.DATA) {
            ReceiptDetailViewContent(viewModel)
        }
    }
}

@Composable
private fun ReceiptDetailViewContent(viewModel: ReceiptDetailViewModel) {

    Column(
        modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)
    ) {
        HeadlineBox(
            viewModel = viewModel,
            modifier = Modifier
                .padding(top = 16.dp, bottom = 8.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(modifier = Modifier.height(32.dp))

            for ( i in 1 .. 10) {
                ReceiptEntryCard(
                    ReceiptEntry(
                        "Käse",
                        BigDecimal("12.34"),
                        "Lebensmittel",
                        "Frankfurter Mühlen"
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun HeadlineBox(viewModel: ReceiptDetailViewModel, modifier: Modifier = Modifier) {

    val merchantName by viewModel.merchantName.observeAsState()
    val receiptIssueDate by viewModel.receiptIssueDate.observeAsState()
    val amount by viewModel.amount.observeAsState()

    Column(modifier.fillMaxWidth()) {
        Row {
            Text(
                text = "Ihr Einkauf bei $merchantName",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .weight(3f)
                    .align(Alignment.CenterVertically)
            )
            Icon(
                imageVector = Icons.Filled.ReceiptLong,
                contentDescription = "",
                modifier = Modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.onBackground
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Text(
                text = "$receiptIssueDate",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "$amount",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HeadlineBoxPreview() {
    BalticByteBuccaneersTheme {
        HeadlineBox(viewModel = ReceiptDetailViewModel(""))
    }
}