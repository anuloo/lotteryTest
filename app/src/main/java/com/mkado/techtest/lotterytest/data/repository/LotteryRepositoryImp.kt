package com.mkado.techtest.lotterytest.data.repository

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import com.mkado.techtest.lotterytest.common.DataResult
import com.mkado.techtest.lotterytest.data.local.room.LotteryDao
import com.mkado.techtest.lotterytest.data.mappers.toDomain
import com.mkado.techtest.lotterytest.data.mappers.toEntity
import com.mkado.techtest.lotterytest.data.remote.network.NetworkError
import com.mkado.techtest.lotterytest.data.service.LotteryService
import com.mkado.techtest.lotterytest.domain.model.Lottery
import com.mkado.techtest.lotterytest.domain.repository.LotteryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LotteryRepositoryImp @Inject constructor(
    private val service: LotteryService,
    private val dao: LotteryDao
):LotteryRepository {
    override fun getLotteryData(): Flow<List<Lottery>> {
        return dao.getLotteries().distinctUntilChanged().map { list ->
            list.map { it.toDomain() }
        }

    }

    override fun getLotteryById(lotteryId: String): Flow<Lottery?> {
        return dao.getLotteryById(lotteryId).map { it?.toDomain() }
    }

    override suspend fun refreshLotteryData(): DataResult<Unit, NetworkError> {
        return when (val result = service.fetchLotteryData()) {
            is DataResult.Success -> {
                // Unwrap the data from the service result
                val data = result.data

                try {
                    // Insert the fetched data into the DAO
                    dao.insertLotteries(data.map { it.toEntity() })

                    // Return success with no additional data
                    DataResult.Success(Unit)
                } catch (e: Exception) {
                    // Handle any other exceptions during DAO operation
                    DataResult.Error(
                        message = e.message,
                        code = -1,
                        error = NetworkError.UnknownError(e.message, e)
                    )
                }
            }
            is DataResult.Error -> {
                // Forward the error from the service to the repository
                DataResult.Error(
                    message = result.message,
                    code = result.code,
                    error = result.error
                )
            }
        }
    }



    override fun generateRandomNumbers(): List<Int> {
        return (1..50).shuffled().take(6)
    }

    override fun generateQRCodeBitmap(data:String): Bitmap? {
        return try {
            val size = 256 // QR code image size
            val bits = QRCodeWriter().encode(data, BarcodeFormat.QR_CODE, size, size)
            val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565)
            for (x in 0 until size) {
                for (y in 0 until size) {
                    bitmap.setPixel(x, y, if (bits[x, y]) Color.Black.toArgb() else Color.White.toArgb())
                }
            }
            bitmap
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}