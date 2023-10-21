package com.example.balticbytebuccaneers.module.receiptList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.PieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData

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
    val pieChartConfig = PieChartConfig(
        isAnimationEnable = false,
        showSliceLabels = false,
        backgroundColor = MaterialTheme.colorScheme.surface,
        animationDuration = 15,
        labelVisible = true,
    )

    Column(
        horizontalAlignment = CenterHorizontally,
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
}

@Preview
@Composable
fun AnalyticsCarouselPreview() {
    AnalyticsCarousel()
}