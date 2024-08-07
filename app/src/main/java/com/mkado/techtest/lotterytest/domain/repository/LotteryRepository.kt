package com.mkado.techtest.lotterytest.domain.repository

import android.graphics.Bitmap
import com.mkado.techtest.lotterytest.common.DataResult
import com.mkado.techtest.lotterytest.data.remote.network.NetworkError
import com.mkado.techtest.lotterytest.domain.model.Lottery
import kotlinx.coroutines.flow.Flow

interface LotteryRepository {
    fun getLotteryData(): Flow<List<Lottery>>
    fun getLotteryById(lotteryId: String): Flow<Lottery?>
    suspend fun refreshLotteryData(): DataResult<Unit, NetworkError>
    fun generateRandomNumbers(): List<Int>
    fun generateQRCodeBitmap(data:String): Bitmap?
}