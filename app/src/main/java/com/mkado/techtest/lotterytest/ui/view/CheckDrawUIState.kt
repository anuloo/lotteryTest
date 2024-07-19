package com.mkado.techtest.lotterytest.ui.view

import android.graphics.Bitmap

sealed class CheckDrawUIState {
    data object Loading : CheckDrawUIState()
    data class Loaded(val numbers: List<Int>) : CheckDrawUIState()
    data class Error(val message: String) : CheckDrawUIState()
}
