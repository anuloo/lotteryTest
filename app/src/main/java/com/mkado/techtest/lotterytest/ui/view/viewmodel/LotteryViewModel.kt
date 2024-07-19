package com.mkado.techtest.lotterytest.ui.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkado.techtest.lotterytest.domain.model.Lottery
import com.mkado.techtest.lotterytest.domain.uscase.GetLotteryByIdUseCase
import com.mkado.techtest.lotterytest.domain.uscase.GetLotteryDataUseCase
import com.mkado.techtest.lotterytest.domain.uscase.LotteryByIdUseCase
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
    private val getLotteryDataUseCase: LotteryUsecase,
    private val getLotteryByIdUseCase: LotteryByIdUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<LotteryUIState>(LotteryUIState.Loading)
    val state: StateFlow<LotteryUIState> = _state

    private val _lotteryDetail = MutableStateFlow<Lottery?>(null)
    val lotteryDetail: StateFlow<Lottery?> = _lotteryDetail

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

    fun getLotteryById(lotteryId: String) {
        viewModelScope.launch {
            getLotteryByIdUseCase.getLotteryById(lotteryId)
                .collect { lottery ->
                    _lotteryDetail.value = lottery
                }
        }
    }
}