package com.example.balticbytebuccaneers.module.analysts

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.balticbytebuccaneers.R
import com.example.balticbytebuccaneers.module.analysts.chartTypes.PieChartAnalysisView
import com.example.balticbytebuccaneers.ui.theme.BalticByteBuccaneersTheme


@Composable
fun AnalystsView(viewModel:AnalystsViewModel?){
    Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        PaginationView(viewModel = viewModel)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PaginationView(viewModel:AnalystsViewModel?) {
    // Display 10 items
    val pageCount = 10
    val pagerState = rememberPagerState(pageCount = {
        10
    })
    Column (modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(
            text = "My Evaluations",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        HorizontalPager(
            state = pagerState,
            pageSize = PageSize.Fill,
            modifier = Modifier.weight(1.0F)) { page ->
            Page(pagerState.currentPage)
        }
        Spacer(modifier= Modifier.height(20.dp))
        Row(
            Modifier
                .height(50.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(20.dp)

                )
            }
        }
    }
}
@Composable
private fun Page(index: Int) {
    val pageList = listOf(
        PieChartAnalysisView(analysisData = AnalysisDummyData().data[0])
    )
    pageList[index]
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PaginationPreview() {
    BalticByteBuccaneersTheme {
        PaginationView(null)
    }
}