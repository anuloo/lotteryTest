package com.mkado.techtest.lotterytest.data.repository

import android.graphics.Bitmap
import com.mkado.techtest.lotterytest.data.local.room.LotteryDao
import com.mkado.techtest.lotterytest.data.local.room.LotteryEntity
import com.mkado.techtest.lotterytest.data.mappers.toDomain
import com.mkado.techtest.lotterytest.data.mappers.toEntity
import com.mkado.techtest.lotterytest.data.remote.responses.LotteryResponse
import com.mkado.techtest.lotterytest.data.service.LotteryService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class LotteryRepositoryImpTest {

    private val lotteryService: LotteryService = mockk(relaxed = true)
    private val lotteryDao: LotteryDao = mockk(relaxed = true)
    private lateinit var sut: LotteryRepositoryImp

    @Before
    fun setUp() {
        sut = LotteryRepositoryImp(lotteryService, lotteryDao)
    }

    @Test
    fun `test get lottery data returns data from dao`() = runTest {
        val lotteryEntities = listOf(
            LotteryEntity(
                id = "draw-88",
                drawDate = "2024-05-15",
                number1 = "10",
                number2 = "23",
                number3 = "36",
                number4 = "47",
                number5 = "21",
                number6 = "52",
                bonusBall = "39",
                topPrize = 7000000
            )
        )
        val lotteries = lotteryEntities.map { it.toDomain() }

        // Mock the behavior of getLotteries to return a MutableStateFlow
        val mockStateFlow = MutableStateFlow(lotteryEntities)  // Initialize with desired initial state
        every { lotteryDao.getLotteries() } returns mockStateFlow

        // Call the repository function and get the result
        val result = sut.getLotteryData().first()

        // Assert that the result matches the expected list of lotteries
        assert(result == lotteries)
    }

    @Test
    fun `test refresh lottery data fetches from service and inserts into dao`() = runTest {
        val lotteryResponses = listOf(
            LotteryResponse(
                id = "draw-88",
                drawDate = "2024-05-15",
                number1 = "10",
                number2 = "23",
                number3 = "36",
                number4 = "47",
                number5 = "21",
                number6 = "52",
                bonusBall = "39",
                topPrize = 7000000
            )
        )
        val lotteryEntities = lotteryResponses.map { it.toEntity() }

        // Mock the behavior of fetchLotteryData() to return the lotteryResponses list
        coEvery { lotteryService.fetchLotteryData() } returns lotteryResponses

        // Call the refresh method on the repository
        sut.refreshLotteryData()

        // Verify that insertLotteries was called with the expected entities
        coVerify { lotteryDao.insertLotteries(lotteryEntities) }
    }

    @Test
    fun `test get lottery by id returns data from dao`() = runTest {
        val lotteryEntity = LotteryEntity(
            id = "draw-88",
            drawDate = "2024-05-15",
            number1 = "10",
            number2 = "23",
            number3 = "36",
            number4 = "47",
            number5 = "21",
            number6 = "52",
            bonusBall = "39",
            topPrize = 7000000
        )
        val lottery = lotteryEntity.toDomain()

        // Mock the behavior of getLotteryById to return a MutableStateFlow
        val mockStateFlow = MutableStateFlow(lotteryEntity)
        every { lotteryDao.getLotteryById("draw-88") } returns mockStateFlow

        // Call the repository function and get the result
        val result = sut.getLotteryById("draw-88").first()

        // Assert that the result matches the expected lottery
        assertEquals(lottery, result)
    }

    @Test
    fun `test generate random numbers returns six unique numbers`() {
        val numbers = sut.generateRandomNumbers()
        assertEquals(6, numbers.size)
        assertEquals(numbers.toSet().size, numbers.size) // Ensure all numbers are unique
        assert(numbers.all { it in 1..50 }) // Ensure all numbers are between 1 and 50
    }

    @Test
    fun `test generate qr code bitmap returns not a null bitmap`() {
        mockkStatic(Bitmap::class)
        val mockBitmap = mockk<Bitmap>(relaxed = true)
        every { Bitmap.createBitmap(any(), any(), any()) } returns mockBitmap

        val data = "Sample QR Code"
        val bitmap = sut.generateQRCodeBitmap(data)

        assertTrue(bitmap != null)
        // Verify that createBitmap was called
        coVerify { Bitmap.createBitmap(any(), any(), any()) }
    }
}

