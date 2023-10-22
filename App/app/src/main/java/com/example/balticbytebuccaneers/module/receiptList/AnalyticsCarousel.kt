package com.example.balticbytebuccaneers.module.receiptList

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.PieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import com.example.balticbytebuccaneers.module.analysts.chartTypes.ChartDataPoint
import com.example.balticbytebuccaneers.module.analysts.chartTypes.PieChartView

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnalyticsCarousel() {
    val colors = listOf(
        MaterialTheme.colorScheme.primaryContainer,
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.tertiaryContainer,
        MaterialTheme.colorScheme.tertiary,
        MaterialTheme.colorScheme.secondaryContainer,
        MaterialTheme.colorScheme.secondary
    )

    val pieChartData = arrayListOf(
        ChartDataPoint("Cheese", 65f),
        ChartDataPoint("Sparkling Water", 35f),
        ChartDataPoint("Alcoholic Beverages", 10f),
        ChartDataPoint("Pastries", 40f)
    )
    val vendorChartData = arrayListOf(
        ChartDataPoint("Rewe", 15f),
        ChartDataPoint("Edeka", 35f),
        ChartDataPoint("Petrol Station", 120f),
        ChartDataPoint("Aldi", 20f)
    )
    val pieChartConfig = PieChartConfig(
        isAnimationEnable = false,
        showSliceLabels = false,

        backgroundColor = Color.Transparent,
        animationDuration = 15,
        labelVisible = true,
    )
    val pagerState = rememberPagerState(pageCount = {
        2
    })
    Column() {
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Your Last Purchases",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        HorizontalPager(
            pagerState, modifier = Modifier
                .fillMaxWidth()
                //.height(164.dp)
        ) { page ->
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                if (page == 0) {
                    PieChartView(pieChartData, 164, 80f)
                } else {
                    PieChartView(vendorChartData ,164, 80f)
                }
            }
        }
        Row(
            Modifier
                .height(12.dp)
                .fillMaxWidth()
                .align(CenterHorizontally),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(8.dp)

                )
            }
        }
    }
}

@Preview
@Composable
fun AnalyticsCarouselPreview() {
    AnalyticsCarousel()
}