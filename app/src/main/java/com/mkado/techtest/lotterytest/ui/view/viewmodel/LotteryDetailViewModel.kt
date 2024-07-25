package com.mkado.techtest.lotterytest.ui.view.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkado.techtest.lotterytest.domain.model.Lottery
import com.mkado.techtest.lotterytest.domain.uscase.LotteryByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LotteryDetailViewModel @Inject constructor(
    private val getLotteryByIdUseCase: LotteryByIdUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<Lottery?>(null)
    val state: StateFlow<Lottery?> = _state

    fun getLotteryById(lotteryId: String) {
        viewModelScope.launch {
            getLotteryByIdUseCase.getLotteryById(lotteryId)
                .collect { lottery ->
                    _state.value = lottery
                }
        }
    }
}
