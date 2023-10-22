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
                ChartDataPoint("Mobility", 2.0F),
                ChartDataPoint("Beverages", 5.0F),
                ChartDataPoint("Food", 17.0F),
                ChartDataPoint("Household items", 12.0F),
                ChartDataPoint("Hygiene", 8.0F),
            )
        ),
        AnalysisData(
            R.string.analysis_receipt_food_composition_headline,
            R.string.analysis_receipt_food_composition_title,
            R.string.analysis_receipt_food_composition_description,
            arrayListOf(
                ChartDataPoint("Meat", 18.0F),
                ChartDataPoint("Dairy", 5.0F),
                ChartDataPoint("Vegetables", 7.0F),
                ChartDataPoint("Fruit", 3.0F),
                ChartDataPoint("Pastries", 8.0F),
            )
        ),
        AnalysisData(
            R.string.analysis_receipt_mobility_headline,
            R.string.analysis_receipt_mobility_title,
            R.string.analysis_receipt_mobility_description,
            arrayListOf(

                ChartDataPoint("Train", 60.0F),
                ChartDataPoint("Car", 45.0F),
                ChartDataPoint("Car Sharing", 10.0F),
                ChartDataPoint("E-Scooter", 4.0F)
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