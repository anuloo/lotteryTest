package com.mkado.techtest.lotterytest.ui.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkado.techtest.lotterytest.domain.uscase.GetLotteryDataUseCase
import com.mkado.techtest.lotterytest.ui.view.LotteryUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LotteryViewModel @Inject constructor(
    private val getLotteryDataUseCase: GetLotteryDataUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<LotteryUIState>(LotteryUIState.Loading)
    val state: StateFlow<LotteryUIState> = _state

    init {
        fetchLotteryData()
    }

    private fun fetchLotteryData() {
        viewModelScope.launch {
            delay(2000)
            getLotteryDataUseCase().collect { lotteryData ->
                _state.value = LotteryUIState.Loaded(lotteryData)
            }
        }
    }

    fun refreshData() {
        viewModelScope.launch {
            _state.value = LotteryUIState.Loading
            try {
                val data = getLotteryDataUseCase.refresh()
                _state.value = LotteryUIState.Loaded(data)
            } catch (e: Exception) {
                _state.value = LotteryUIState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}