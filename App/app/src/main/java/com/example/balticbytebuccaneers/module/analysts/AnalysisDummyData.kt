package com.example.balticbytebuccaneers.module.analysts

import android.content.res.Resources
import com.example.balticbytebuccaneers.R


data class AnalysisData (
    val headline: String,
    val title: String,
    val description: String,
    val chart_data: String?
)
class AnalysisDummyData {
    val data = listOf(
        AnalysisData(
            Resources.getSystem().getString(R.string.analysis_receipt_category_composition_headline),
            Resources.getSystem().getString(R.string.analysis_receipt_category_composition_title),
            Resources.getSystem().getString(R.string.analysis_receipt_category_composition_description),
            null
            )
    )
}