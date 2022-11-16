package com.example.oplevappprojekt.ViewModel

import androidx.lifecycle.ViewModel
import com.example.oplevappprojekt.model.Journey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class JourneyViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(Journey())
    val uiState = _uiState.asStateFlow()

    fun newJourney(){

    }

}

