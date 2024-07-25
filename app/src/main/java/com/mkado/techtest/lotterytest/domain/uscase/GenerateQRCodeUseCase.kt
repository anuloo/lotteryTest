package com.mkado.techtest.lotterytest.domain.uscase

import android.graphics.Bitmap
import com.mkado.techtest.lotterytest.domain.repository.LotteryRepository
import javax.inject.Inject

interface QRCodeUseCase{
    fun execute(data:String): Bitmap?
}

class GenerateQRCodeUseCase @Inject constructor (private val repository: LotteryRepository):QRCodeUseCase {
    override fun execute(data:String): Bitmap? {
        return repository.generateQRCodeBitmap(data)
    }
}