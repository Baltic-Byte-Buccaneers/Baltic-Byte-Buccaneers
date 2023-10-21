package com.example.balticbytebuccaneers.module.analysts.chartTypes

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.DonutPieChart
import co.yml.charts.ui.piechart.charts.PieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import com.example.balticbytebuccaneers.module.analysts.AnalysisData
import com.example.balticbytebuccaneers.module.analysts.AnalysisDummyData
import com.example.balticbytebuccaneers.ui.theme.BalticByteBuccaneersTheme

@Composable
fun PieChartAnalysisView (analysisData: AnalysisData) {
    AbstractChartView(
        chart = { PieChartView(dataPoints = analysisData.chart_data) },
        analysisData = analysisData
    )
}

@Composable
fun PieChartView(dataPoints: ArrayList<ChartDataPoint>){
    val colors = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary,
        MaterialTheme.colorScheme.tertiary,
        MaterialTheme.colorScheme.primaryContainer,
        MaterialTheme.colorScheme.secondaryContainer,
        MaterialTheme.colorScheme.tertiaryContainer
    )
    val slices = ArrayList<PieChartData.Slice>()
    dataPoints.forEachIndexed() { i, element ->
        slices.add(PieChartData.Slice(element.name, element.value, colors[i]))
    }
    val pieChartData = PieChartData(
        slices = slices,
        plotType = PlotType.Pie
    )
    val pieChartConfig = PieChartConfig(
        activeSliceAlpha = .9f,
        isAnimationEnable = true,
        strokeWidth = 120f,
        backgroundColor = Color.Transparent,
        animationDuration = 15,
        labelVisible = true,
    )
    DonutPieChart(
        modifier = Modifier.fillMaxWidth(),
        pieChartData,
        pieChartConfig
    )
}

@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PieChartViewPreview() {
    BalticByteBuccaneersTheme {
        PieChartAnalysisView(
            analysisData = AnalysisDummyData().data[0])
    }
}