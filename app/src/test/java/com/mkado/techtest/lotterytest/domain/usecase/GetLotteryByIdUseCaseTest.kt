package com.mkado.techtest.lotterytest.domain.usecase

import com.mkado.techtest.lotterytest.domain.model.Lottery
import com.mkado.techtest.lotterytest.domain.repository.LotteryRepository
import com.mkado.techtest.lotterytest.domain.uscase.GetLotteryByIdUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class GetLotteryByIdUseCaseTest {

    private lateinit var useCase: GetLotteryByIdUseCase
    private val repository: LotteryRepository = mockk()

    @Before
    fun setUp() {
        useCase = GetLotteryByIdUseCase(repository)
    }

    @Test
    fun `get lottery by id should return lottery when repository returns data`() = runTest {
        // Given
        val lotteryId = "draw-88"
        val expectedLottery = Lottery(
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
        coEvery { repository.getLotteryById(lotteryId) } returns flowOf(expectedLottery)

        // When
        val result = useCase.getLotteryById(lotteryId).firstOrNull()

        // Then
        assertNotNull(result)
        assertEquals(expectedLottery, result)
    }

    @Test
    fun `get lottery by id should return null when repository returns null`() = runTest {
        // Given
        val lotteryId = "draw-99"
        coEvery { repository.getLotteryById(lotteryId) } returns flowOf(null)

        // When
        val result = useCase.getLotteryById(lotteryId).firstOrNull()

        // Then
        assertNull(result)
    }
}
