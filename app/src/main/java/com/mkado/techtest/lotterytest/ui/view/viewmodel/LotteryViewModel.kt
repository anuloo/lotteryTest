package com.mkado.techtest.lotterytest.ui.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkado.techtest.lotterytest.domain.model.Lottery
import com.mkado.techtest.lotterytest.domain.uscase.GetLotteryDataUseCase
import com.mkado.techtest.lotterytest.domain.uscase.LotteryUsecase
import com.mkado.techtest.lotterytest.ui.view.LotteryUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LotteryViewModel @Inject constructor(
    private val getLotteryDataUseCase: LotteryUsecase
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

    // Placeholder function to get a lottery object by its ID
    fun getLotteryById(lotteryId: String?): Lottery {
        // Replace with actual implementation to fetch lottery details
        return Lottery(
            id = lotteryId ?: "default_id",
            drawDate = "2023-05-15",
            number1 = "10",
            number2 = "23",
            number3 = "36",
            number4 = "47",
            number5 = "21",
            number6 = "52",
            bonusBall = "39",
            topPrize = 4000000
        )
    }
}