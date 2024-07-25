package com.mkado.techtest.lotterytest.ui.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkado.techtest.lotterytest.domain.uscase.GenerateQRCodeUseCase
import com.mkado.techtest.lotterytest.ui.view.QRCodeGenerationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QRCodeGeneratorViewModel @Inject constructor(
    private val generateQRCodeUseCase: GenerateQRCodeUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<QRCodeGenerationState>(QRCodeGenerationState.Loading)
    val state: StateFlow<QRCodeGenerationState> = _state

    fun generateQRCode(data: String) {
        viewModelScope.launch {
            _state.value = QRCodeGenerationState.Loading
            try {
                // Generate QR code and update state to Loaded with the bitmap
                val bitmap = generateQRCodeUseCase.execute(data)
                _state.value = QRCodeGenerationState.Loaded(bitmap)
            } catch (e: Exception) {
                // Update state to Error with the error message
                _state.value = QRCodeGenerationState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
