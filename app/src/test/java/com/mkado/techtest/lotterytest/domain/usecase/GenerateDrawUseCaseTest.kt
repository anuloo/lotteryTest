package com.mkado.techtest.lotterytest.domain.usecase

import com.mkado.techtest.lotterytest.domain.repository.LotteryRepository
import com.mkado.techtest.lotterytest.domain.uscase.GenerateDrawUseCase
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GenerateDrawUseCaseTest {

    private lateinit var useCase: GenerateDrawUseCase
    private val repository: LotteryRepository = mockk()

    @Before
    fun setUp() {
        useCase = GenerateDrawUseCase(repository)
    }

    @Test
    fun `execute should return a list of random numbers`() {
        // Given
        val expectedNumbers = listOf(1, 2, 3, 4, 5, 6)
        every { repository.generateRandomNumbers() } returns expectedNumbers

        // When
        val result = useCase.execute()

        // Then
        assertEquals(expectedNumbers, result)
    }
}
