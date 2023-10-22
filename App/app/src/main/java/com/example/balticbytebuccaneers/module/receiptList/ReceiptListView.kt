package com.example.balticbytebuccaneers.module.receiptList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun ReceiptListScreen(onClick: (String) -> Unit) {
    val viewModel = remember {ReceiptListViewModel() }

    ReceiptListView(viewModel = viewModel, onClick = onClick)
}

@Composable
fun ReceiptListView(viewModel: ReceiptListViewModel, onClick: (String) -> Unit) {
    val composableCoroutineScope  = rememberCoroutineScope()
    val receipts = viewModel.receipts.observeAsState()
    val state by viewModel.state.observeAsState()

    LaunchedEffect(key1 = "fetchAllOfUserReceipts") {
        composableCoroutineScope.launch {
            viewModel.fetchAllReceiptsOfUser()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (state == ReceiptListViewModel.ViewState.LOADING) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (state == ReceiptListViewModel.ViewState.DATA) {
            Column(modifier = Modifier.fillMaxSize()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Your Receipts",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                ReceiptList(receipts = receipts.value ?: listOf(), onClick)
            }
        }
    }
}

@Preview
@Composable
fun ReceiptListViewPreview() {
    val model = ReceiptListViewModel()
    MaterialTheme {
        ReceiptListView(viewModel = model, onClick = {})
    }
}
