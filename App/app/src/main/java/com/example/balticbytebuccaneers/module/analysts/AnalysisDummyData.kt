package com.example.balticbytebuccaneers.module.analysts

import android.content.res.Resources
import com.example.balticbytebuccaneers.R


data class AnalysisData (
    val headline: String,
    val title: String,
    val description: String
)
class AnalysisDummyData {
    private val data = listOf(
        AnalysisData(
            Resources.getSystem().getString(R.string.analysis_receipt_category_composition_headline),
            Resources.getSystem().getString(R.string.analysis_receipt_category_composition_title),
            Resources.getSystem().getString(R.string.analysis_receipt_category_composition_description)
            )
    )
    fun get_data_from_index(index: Int): AnalysisData {
        return data[index]
    }
}