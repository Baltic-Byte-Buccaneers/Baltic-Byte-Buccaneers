package com.example.balticbytebuccaneers.module.home

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.balticbytebuccaneers.module.receiptDetail.ReceiptDetailView
import com.example.balticbytebuccaneers.module.receiptDetail.ReceiptDetailViewModel
import com.example.balticbytebuccaneers.ui.theme.BalticByteBuccaneersTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BalticByteBuccaneersTheme(dynamicColor = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    val viewModel = remember {
                        ReceiptDetailViewModel("6533d1f8c91e3a690d412e4a") {
                            Toast.makeText(context, "Back pressed", Toast.LENGTH_SHORT).show()
                        }
                    }
                    ReceiptDetailView(viewModel = viewModel)
                }
            }
        }
    }
}