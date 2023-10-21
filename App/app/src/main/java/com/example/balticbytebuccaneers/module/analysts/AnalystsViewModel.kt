package com.example.balticbytebuccaneers.module.analysts

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.delay

class AnalystsViewModel() {

    enum class ViewState {
        LOADING,
        DATA,
        ERROR
    }

    val state = MutableLiveData<ViewState>()
    val headline = MutableLiveData<String>()
    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    suspend fun getAnalysisInformation(page_index: Int) {
        state.value = AnalystsViewModel.ViewState.LOADING

        delay(2000)
        headline.value = "Rewe"
        title.value = "20.10.2023"
        description.value = "12"

        state.value = AnalystsViewModel.ViewState.DATA

    }
}