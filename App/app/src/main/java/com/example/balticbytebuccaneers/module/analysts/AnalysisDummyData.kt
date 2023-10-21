package com.example.balticbytebuccaneers.module.analysts

import android.content.res.Resources
import com.example.balticbytebuccaneers.R


data class AnalysisData (
    val headline: Int,
    val title: Int,
    val description: Int,
    val chart_data: String?
)
class AnalysisDummyData {
    val data = listOf(
        AnalysisData(
            R.string.analysis_receipt_category_composition_headline,
            R.string.analysis_receipt_category_composition_title,
            R.string.analysis_receipt_category_composition_description,
            null
            )
    )
}