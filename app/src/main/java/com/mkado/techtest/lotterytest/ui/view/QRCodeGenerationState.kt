package com.mkado.techtest.lotterytest.ui.view

import android.graphics.Bitmap

sealed class QRCodeGenerationState {
    data object Loading : QRCodeGenerationState()
    data class Loaded(val bitmap: Bitmap?) : QRCodeGenerationState()
    data class Error(val message: String) : QRCodeGenerationState()
}