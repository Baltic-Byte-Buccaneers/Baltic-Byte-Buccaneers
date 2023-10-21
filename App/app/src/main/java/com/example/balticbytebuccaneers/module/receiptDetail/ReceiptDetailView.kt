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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.balticbytebuccaneers.component.topNavigation.TopNavigationBar
import com.example.balticbytebuccaneers.module.receiptDetail.retailDialog.ProducerDetailsDialog
import com.example.balticbytebuccaneers.module.receiptDetail.retailDialog.ProducerDetailsDialogViewModel
import com.example.balticbytebuccaneers.ui.theme.BalticByteBuccaneersTheme
import kotlinx.coroutines.launch
import java.util.Locale

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
    val receiptEntries = viewModel.receiptEntries.observeAsState()
    val metadata = viewModel.metadata.observeAsState()

    var showBottomSheet by remember { mutableStateOf(false) }
    val bottomSheetViewModel = remember { ProducerDetailsDialogViewModel("") }

    Column {
        Spacer(modifier = Modifier.height(8.dp))
        TopNavigationBar(onBackClick = { viewModel.onBackClick() })
        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {

            HeadlineBox(viewModel = viewModel)
            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {

                Spacer(modifier = Modifier.height(16.dp))

                receiptEntries.value?.forEach {
                    ReceiptEntryCard(it) { showBottomSheet = true }
                    Spacer(modifier = Modifier.height(8.dp))
                }

                Spacer(modifier = Modifier.height(16.dp))

                metadata.value?.let {
                    ReceiptMetadataView(it)
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }

        ProducerDetailsDialog(viewModel = bottomSheetViewModel, showBottomSheet = showBottomSheet) {
            showBottomSheet = false
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
                text = "Your purchase at $merchantName",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
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
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = amount?.let {
                    "%,.2f".format(Locale.GERMAN, it) + " €"
                } ?: "--",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HeadlineBoxPreview() {
    BalticByteBuccaneersTheme {
        HeadlineBox(viewModel = ReceiptDetailViewModel("") {})
    }
}