package com.mkado.techtest.lotterytest.data.repository

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import com.mkado.techtest.lotterytest.data.local.room.LotteryDao
import com.mkado.techtest.lotterytest.data.mappers.toDomain
import com.mkado.techtest.lotterytest.data.mappers.toEntity
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

    override suspend fun refreshLotteryData() {
        val data = service.fetchLotteryData()
        dao.insertLotteries(data.map { it.toEntity() })
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