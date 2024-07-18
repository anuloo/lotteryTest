package com.mkado.techtest.lotterytest.ui.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkado.techtest.lotterytest.domain.uscase.DrawUseCase
import com.mkado.techtest.lotterytest.ui.view.CheckDrawUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckDrawViewModel @Inject constructor(
    private val generateDrawUseCase: DrawUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<CheckDrawUIState>(CheckDrawUIState.Loaded(emptyList()))
    val state: StateFlow<CheckDrawUIState> = _state

    fun generateNumbers() {
        viewModelScope.launch {
            _state.value = CheckDrawUIState.Loading
            delay(2000)
            try {
                val numbers = generateDrawUseCase.execute()
                _state.value = CheckDrawUIState.Loaded(numbers)
            } catch (e: Exception) {
                _state.value = CheckDrawUIState.Error("Failed to generate numbers")
            }
        }
    }
}
