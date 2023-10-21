package com.example.balticbytebuccaneers.module.analysts

import android.content.res.Resources
import com.example.balticbytebuccaneers.R
import com.example.balticbytebuccaneers.module.analysts.chartTypes.ChartDataPoint


data class AnalysisData (
    val headline: Int,
    val title: Int,
    val description: Int,
    val chart_data: ArrayList<ChartDataPoint>
)
class AnalysisDummyData {
    val data = listOf(
        AnalysisData(
            R.string.analysis_receipt_category_composition_headline,
            R.string.analysis_receipt_category_composition_title,
            R.string.analysis_receipt_category_composition_description,
            arrayListOf(
                ChartDataPoint("A", 2.0F),
                ChartDataPoint("B", 5.0F),
                ChartDataPoint("C", 7.0F),
                ChartDataPoint("D", 12.0F),
                ChartDataPoint("E", 8.0F),
            )
        ),
        AnalysisData(
            R.string.analysis_receipt_category_composition_headline,
            R.string.analysis_receipt_category_composition_title,
            R.string.analysis_receipt_category_composition_description,
            arrayListOf(
                ChartDataPoint("A", 2.0F),
                ChartDataPoint("B", 5.0F),
                ChartDataPoint("C", 7.0F),
                ChartDataPoint("D", 12.0F),
                ChartDataPoint("E", 8.0F),
            )
        ),
        AnalysisData(
            R.string.analysis_receipt_category_composition_headline,
            R.string.analysis_receipt_category_composition_title,
            R.string.analysis_receipt_category_composition_description,
            arrayListOf(
                ChartDataPoint("A", 2.0F),
                ChartDataPoint("B", 5.0F),
                ChartDataPoint("C", 7.0F),
                ChartDataPoint("D", 12.0F),
                ChartDataPoint("E", 8.0F),
            )
        ),
        AnalysisData(
            R.string.analysis_receipt_category_composition_headline,
            R.string.analysis_receipt_category_composition_title,
            R.string.analysis_receipt_category_composition_description,
            arrayListOf(
                ChartDataPoint("A", 2.0F),
                ChartDataPoint("B", 5.0F),
                ChartDataPoint("C", 7.0F),
                ChartDataPoint("D", 12.0F),
                ChartDataPoint("E", 8.0F),
            )
        ),
        AnalysisData(
            R.string.analysis_receipt_category_composition_headline,
            R.string.analysis_receipt_category_composition_title,
            R.string.analysis_receipt_category_composition_description,
            arrayListOf(
                ChartDataPoint("A", 2.0F),
                ChartDataPoint("B", 5.0F),
                ChartDataPoint("C", 7.0F),
                ChartDataPoint("D", 12.0F),
                ChartDataPoint("E", 8.0F),
            )
        ),

    )
}