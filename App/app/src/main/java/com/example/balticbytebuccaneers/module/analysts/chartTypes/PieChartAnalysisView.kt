package com.example.balticbytebuccaneers.module.analysts.chartTypes

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.balticbytebuccaneers.module.analysts.AnalysisData
import com.example.balticbytebuccaneers.module.analysts.AnalysisDummyData
import com.example.balticbytebuccaneers.ui.theme.BalticByteBuccaneersTheme

@Composable
fun PieChartAnalysisView (analysisData: AnalysisData) {
    AbstractChartView(chart = null, analysisData = analysisData)
}

@Composable
fun PieChart(){

}

@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PieChartViewPreview() {
    BalticByteBuccaneersTheme {
        AbstractChartView(chart = null, analysisData = AnalysisDummyData().data[0])
    }
}