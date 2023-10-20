package com.example.balticbytebuccaneers.module.receiptList

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ReceiptListView(viewModel: ReceiptListViewModel) {
    ReceiptList(receipts = viewModel.receipts)
}

@Preview
@Composable
fun ReceiptListViewPreview() {
    val model = ReceiptListViewModel()

    ReceiptListView(viewModel = model)
}
