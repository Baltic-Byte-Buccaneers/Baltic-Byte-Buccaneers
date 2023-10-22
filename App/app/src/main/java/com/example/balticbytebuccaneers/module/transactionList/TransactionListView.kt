package com.example.balticbytebuccaneers.module.transactionList

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Paid
import androidx.compose.material.icons.outlined.Paid
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.balticbytebuccaneers.R
import com.example.balticbytebuccaneers.service.transaction.Transaction
import com.example.balticbytebuccaneers.ui.theme.BalticByteBuccaneersTheme
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.util.Date
import java.util.Locale


@Composable
fun TransactionListView(viewModel: TransactionListViewModel, onTransactionClicked: (receiptId: String) -> Unit) {
    val state by viewModel.state.observeAsState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = "") {
        scope.launch {
            viewModel.fetchTransactions()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text(
            text = "Your Transactions",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(16.dp)
        )
        if (state == TransactionListViewModel.ViewState.LOADING) {
            Box (
                modifier = Modifier
                    .fillMaxSize()
            ){
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else if (state == TransactionListViewModel.ViewState.DATA) {
                TransactionsView(viewModel, onTransactionClicked)
        }
    }
}

@Composable
fun TransactionsView(viewModel: TransactionListViewModel, onTransactionClicked: (receiptId: String) -> Unit){
    val transactions = viewModel.transactions.observeAsState()
    LazyColumn {
        transactions.value?.let {
            items(count = it.size) { index ->
                transactions.value?.let {
                    TransactionCard(transaction = it[index]) {
                        it[index].receiptId?.let { receiptId ->
                            onTransactionClicked(receiptId)
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}


@Composable
fun TransactionCard(transaction: Transaction, onTransactionClicked: () -> Unit) {
    val context = LocalContext.current
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer
        ),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .clickable(
                enabled = true,
                onClick = {  }
            )
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Column(
                modifier = Modifier.align(Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Outlined.Paid,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = MaterialTheme.colorScheme.onTertiaryContainer,
                )
            }
            Column(
                modifier = Modifier
                    .weight(3F)
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    transaction.title ?: "--",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                if (transaction.purpose != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        transaction.purpose,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                if (transaction.date != null) {
                    Text(
                        transaction.date.toString(),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.End
            ) {
                if (transaction.amount != null) {
                    AmountLabel(transaction.amount)
                }
                if (transaction.receiptId != null) {
                    IconButton(
                        onClick = onTransactionClicked
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ReceiptLong,
                            tint = MaterialTheme.colorScheme.onTertiaryContainer,
                            contentDescription = "",
                            modifier = Modifier.size(64.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun AmountLabel(amount: BigDecimal, modifier: Modifier = Modifier) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
        shape = MaterialTheme.shapes.large,
        modifier = modifier.wrapContentHeight()
    ) {
        ColoredAmount(amount)
    }
}

@Composable
fun ColoredAmount(amount: BigDecimal){
    val amountString = amount.let {
        "%,.2f".format(Locale.GERMAN, it) + " €"
    }


    val color =  if (amount > BigDecimal.ZERO) {
        MaterialTheme.colorScheme.tertiary
    } else if (amount < BigDecimal.ZERO) {
        MaterialTheme.colorScheme.error
    } else {
        MaterialTheme.colorScheme.onSurface
    }

    Text(
        text = amountString,
        color = color,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun TransactionItemPreview() {
    BalticByteBuccaneersTheme {
        TransactionCard(
            Transaction(
                id = null,
                userid = null,
                iban = "DE2440002345244402",
                amount = BigDecimal("-20.19"),
                date = Date(),
                valutaDate = null,
                description = "Edeka",
                purpose = "Your purchase at Edeka",
                receiptId = "2020",
                title = ""
            )
        ){}
    }
}