package com.mkado.techtest.lotterytest.ui.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkado.techtest.lotterytest.common.DataResult
import com.mkado.techtest.lotterytest.domain.uscase.LotteryUsecase
import com.mkado.techtest.lotterytest.ui.view.LotteryUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LotteryListViewModel @Inject constructor(
    private val getLotteryDataUseCase: LotteryUsecase,
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
            when (val result = getLotteryDataUseCase.refresh()) {
                is DataResult.Success -> {
                    _state.value = LotteryUIState.Loaded(result.data)
                }
                is DataResult.Error -> {
                    _state.value = LotteryUIState.Error("Refresh failed")
                }
            }
        }
    }
}