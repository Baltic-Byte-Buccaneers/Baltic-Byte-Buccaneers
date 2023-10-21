package com.example.balticbytebuccaneers.module.transactionList

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ReceiptLong
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
fun TransactionListView(viewModel: TransactionListViewModel) {
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
                TransactionsView(viewModel)
        }
    }
}

@Composable
fun TransactionsView(viewModel: TransactionListViewModel){
    val transactions = viewModel.transactions.observeAsState()
    LazyColumn {
        transactions.value?.let {
            items(count = it.size) { index ->
                transactions.value?.let {
                    TransactionCard(transaction = it[index])
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}


@Composable
fun TransactionCard(transaction: Transaction) {
    val context = LocalContext.current
    val paddingModifier = Modifier.padding(16.dp)
    OutlinedCard(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .clickable(
                enabled = true,
                onClick = {
                    Toast
                        .makeText(
                            context,
                            "TransactionDetailsView goes here",
                            Toast.LENGTH_SHORT
                        )
                        .show()
                }
            )
    ) {
        Row(modifier = paddingModifier) {
            Column(
                modifier = Modifier
                    .weight(1.0F)
                    .align(Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null,
                    modifier = Modifier.size(64.dp)
                )
            }
            Column(
                modifier = Modifier
                    .weight(3F)
                    .padding(horizontal = 16.dp)
            ) {
                if (transaction.description != null) {
                    Text(
                        transaction.description,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
                if (transaction.purpose != null) {
                    Text(
                        transaction.purpose,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                if (transaction.date != null) {
                    Text(
                        transaction.date.toString(),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
            Column(
                modifier = Modifier.weight(weight = 1.0F),
                horizontalAlignment = Alignment.End
            ) {
                if (transaction.amount != null) {
                    ColoredAmount(transaction.amount)
                }
                if (transaction.receiptId != null) {
                    IconButton(
                        onClick = {
                            Toast.makeText(context, "Receipt Available", Toast.LENGTH_SHORT).show()
                        }) {
                        Icon(
                            imageVector = Icons.Outlined.ReceiptLong,
                            tint = Color.Gray,
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
fun ColoredAmount(amount: BigDecimal){
    val amountString = amount.let {
        "%,.2f".format(Locale.GERMAN, it) + " €"
    } ?: "--"
    return if (amount > BigDecimal.ZERO) {
        Text(
            text = amountString,
            color = Color.Green,
            fontWeight = FontWeight.Bold
        )
    } else if (amount < BigDecimal.ZERO) {
        Text(
            text = amountString,
            color = Color.Red,
            fontWeight = FontWeight.Bold
        )
    } else {
        Text(
            text = amountString,
            color = Color.Gray,
            fontWeight = FontWeight.Bold
        )
    }
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
                receiptId = "2020"
            )
        )
    }
}

/*
@Preview(showBackground = true)
@Composable
fun TransactionListPreview() {
    BalticByteBuccaneersTheme {
        TransactionView(
            arrayOf(
                Transaction(
                    id = null,
                    userid = null,
                    iban = "DE2440002345244402",
                    amount = BigDecimal("20.19"),
                    date = Date(),
                    valutaDate = null,
                    description = "Edeka",
                    purpose = "Your purchase at Edeka",
                    receiptId = "2020"
                ),
                Transaction(
                    id = null,
                    userid = null,
                    iban = "DE2440002345244402",
                    amount = BigDecimal("-20.19"),
                    date = Date(),
                    valutaDate = null,
                    description = "REWE",
                    purpose = "Your purchase at REWE",
                    receiptId = "2020"
                ),
                Transaction(
                    id = null,
                    userid = null,
                    iban = "DE2440002345244402",
                    amount = BigDecimal("0.00"),
                    date = Date(),
                    valutaDate = null,
                    description = "comdirect",
                    purpose = "account maintenance charge",
                    receiptId = null
                )
            )
        )
    }

}

*/