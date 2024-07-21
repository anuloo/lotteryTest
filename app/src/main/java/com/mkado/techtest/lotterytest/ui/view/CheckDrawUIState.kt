package com.mkado.techtest.lotterytest.ui.view

sealed class CheckDrawUIState {
    data object Loading : CheckDrawUIState()
    data class Loaded(val numbers: List<Int>) : CheckDrawUIState()
    data class Error(val message: String) : CheckDrawUIState()
}
