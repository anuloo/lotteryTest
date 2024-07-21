package com.mkado.techtest.lotterytest.domain.usecase

import android.graphics.Bitmap
import com.mkado.techtest.lotterytest.domain.repository.LotteryRepository
import com.mkado.techtest.lotterytest.domain.uscase.GenerateQRCodeUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GenerateQRCodeUseCaseTest {

    private lateinit var useCase: GenerateQRCodeUseCase
    private val repository: LotteryRepository = mockk()

    @Before
    fun setUp() {
        useCase = GenerateQRCodeUseCase(repository)
    }

    @Test
    fun `execute should return a QR code bitmap`() {
        // Given
        val data = "Sample QR Code"
        val expectedBitmap = mockk<Bitmap>()
        every { repository.generateQRCodeBitmap(data) } returns expectedBitmap

        // When
        val result = useCase.execute(data)

        // Then
        assertEquals(expectedBitmap, result)
        verify { repository.generateQRCodeBitmap(data) }
    }
}
