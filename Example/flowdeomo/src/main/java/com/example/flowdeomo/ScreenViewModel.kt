package com.example.flowdeomo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class ScreenViewModel: ViewModel() {
    private var currentTime = 0
    val timer = flow {
        while (true) {
            delay(1000)
            println("Flow is Active")
            emit(currentTime++)
        }
    }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            0
        )
}