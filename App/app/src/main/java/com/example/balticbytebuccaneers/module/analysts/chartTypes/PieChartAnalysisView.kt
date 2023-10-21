package com.example.balticbytebuccaneers.module.analysts.chartTypes

import android.content.res.Configuration
import android.graphics.Typeface
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.common.components.Legends
import co.yml.charts.common.model.PlotType
import co.yml.charts.common.utils.DataUtils
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
    val context = LocalContext.current
    val colors = listOf(
        MaterialTheme.colorScheme.primaryContainer,
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.tertiaryContainer,
        MaterialTheme.colorScheme.tertiary,
        MaterialTheme.colorScheme.secondaryContainer,
        MaterialTheme.colorScheme.secondary
    )
    val slices = ArrayList<PieChartData.Slice>()
    dataPoints.forEachIndexed() { i, element ->
        slices.add(PieChartData.Slice(element.name, element.value, colors[i]))
    }
    val pieChartData = PieChartData(
        slices = slices,
        plotType = PlotType.Donut
    )
    val pieChartConfig = PieChartConfig(
        showSliceLabels = true,
        sliceLabelTypeface = Typeface.DEFAULT_BOLD,
        labelTypeface = Typeface.DEFAULT_BOLD,
        labelVisible = true,
        labelType = PieChartConfig.LabelType.PERCENTAGE,
        labelColor = MaterialTheme.colorScheme.primary,
        activeSliceAlpha = .9f,
        isAnimationEnable = true,
        animationDuration = 500,
        strokeWidth = 120f,
        backgroundColor = Color.Transparent,
        chartPadding = 16
    )
    Column () {
        DonutPieChart(
            modifier = Modifier.width(256.dp).align(Alignment.CenterHorizontally),
            pieChartData,
            pieChartConfig
        )
        Legends(
            legendsConfig = DataUtils.getLegendsConfigFromPieChartData(
                pieChartData = pieChartData,
                3
            )

        )
    }
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