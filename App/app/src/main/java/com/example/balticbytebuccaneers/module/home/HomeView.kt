package com.example.balticbytebuccaneers.module.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.balticbytebuccaneers.component.navigation.AppNavigationBar
import com.example.balticbytebuccaneers.component.navigation.getNavigationBarItems
import com.example.balticbytebuccaneers.ui.theme.BalticByteBuccaneersTheme

@Composable
fun HomeView() {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
        AppNavigationBar(navigationBarItems = getNavigationBarItems()) { newSelectedItemIndex ->
        }
    }
}

@Preview
@Composable
fun HomeViewPreview() {
    BalticByteBuccaneersTheme {
        HomeView()
    }
}

