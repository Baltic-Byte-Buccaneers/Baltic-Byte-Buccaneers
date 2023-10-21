package com.example.balticbytebuccaneers.module.receiptList

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.PieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnalyticsCarousel() {
    val pieChartData = PieChartData(
        slices = listOf(
            PieChartData.Slice("Käse", 65f, Color.DarkGray),
            PieChartData.Slice("Mineralwasser", 35f, Color(0xFF666a86)),
            PieChartData.Slice("Schnaps", 10f, Color(0xFF95B8D1)),
            PieChartData.Slice("Rotwein", 40f, Color(0xFFF53844))
        ), plotType = PlotType.Pie
    )
    val vendorChartData = PieChartData(
        slices = listOf(
            PieChartData.Slice("Rewe", 15f, Color.DarkGray),
            PieChartData.Slice("Edeka", 35f, Color.Green),
            PieChartData.Slice("Tankstelle", 120f, Color.Yellow),
            PieChartData.Slice("Netto", 20f, Color.Magenta)
        ), plotType = PlotType.Pie
    )
    val pieChartConfig = PieChartConfig(
        isAnimationEnable = false,
        showSliceLabels = false,
        backgroundColor = MaterialTheme.colorScheme.surface,
        animationDuration = 15,
        labelVisible = true,
    )
    val pagerState = rememberPagerState(pageCount = {
        2
    })
    Column() {
        HorizontalPager(
            pagerState, modifier = Modifier
                .fillMaxWidth()
                .height(164.dp)
        ) { page ->
            if (page == 0) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    PieChart(
                        modifier = Modifier
                            .size(164.dp),
                        pieChartData,
                        pieChartConfig
                    )
                }
            } else {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    PieChart(
                        modifier = Modifier
                            .size(164.dp),
                        vendorChartData,
                        pieChartConfig
                    )
                }
            }
        }
        Row(
            Modifier
                .height(50.dp)
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