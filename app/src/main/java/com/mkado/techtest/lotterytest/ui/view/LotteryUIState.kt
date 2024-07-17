package com.mkado.techtest.lotterytest.ui.view

import com.mkado.techtest.lotterytest.domain.model.Lottery

sealed class LotteryUIState {
    object Loading : LotteryUIState()
    data class Loaded(val lotteryData: List<Lottery>) : LotteryUIState()
    data class Error(val message: String) : LotteryUIState()
}