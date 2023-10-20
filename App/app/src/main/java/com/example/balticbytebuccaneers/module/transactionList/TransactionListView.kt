package com.example.balticbytebuccaneers.module.transactionList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.balticbytebuccaneers.R
import com.example.balticbytebuccaneers.ui.theme.BalticByteBuccaneersTheme
import java.math.BigDecimal
import java.util.Date


@Composable
fun TransactionView() {

}

@Composable
fun TransactionCard(retailerName: String?, use: String?, amount: BigDecimal?, date: Date?, receipt: Boolean?) {
    val paddingModifier = Modifier.padding(16.dp)
    OutlinedCard(modifier = paddingModifier) {
        Row(modifier = paddingModifier) {
            Column(modifier = Modifier.weight(1.0F).align(Alignment.CenterVertically), verticalArrangement = Arrangement.Center) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null,
                )
            }
            Column(modifier = Modifier
                .weight(3F)
                .padding(horizontal = 16.dp)) {
                if (retailerName != null) {
                    Text(retailerName,
                        style = MaterialTheme.typography.headlineSmall)
                }
                if (use != null) {
                    Text(
                        use,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimary
                        )
                }
                if (date != null){
                    //Text(date)
                }
            }
            Column(modifier = Modifier.weight(weight = 1.0F)) {
                if (amount != null) {
                    ColoredAmount(amount)
                }
                if (receipt != null) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_launcher_foreground),
                            contentDescription = null,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ColoredAmount(amount: BigDecimal){
    return if (amount > BigDecimal.ZERO) {
        Text(amount.toString() + " €", color = Color.Green)
    }
    else if (amount < BigDecimal.ZERO) {
        Text(amount.toString() + " €", color = Color.Red)
    }
    else {
        Text(amount.toString() + " €", color = Color.Gray)
    }
}


@Preview(showBackground = true)
@Composable
fun TransactionPreview() {
    BalticByteBuccaneersTheme {
        TransactionCard(
            retailerName = "Edeka",
            use = "Ihr Einkauf vom 23.08.2023",
            amount = BigDecimal("20.19"),
            date = null,
            receipt = false
        )
        TransactionView()
    }
}