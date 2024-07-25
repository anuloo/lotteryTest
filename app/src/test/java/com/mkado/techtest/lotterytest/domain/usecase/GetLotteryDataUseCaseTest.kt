package com.mkado.techtest.lotterytest.domain.usecase

import com.mkado.techtest.lotterytest.domain.uscase.GetLotteryDataUseCase
import com.mkado.techtest.lotterytest.common.DataResult
import com.mkado.techtest.lotterytest.domain.uscase.LotteryUsecase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import com.mkado.techtest.lotterytest.domain.model.Lottery
import com.mkado.techtest.lotterytest.domain.repository.LotteryRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetLotteryDataUseCaseTest {

    private val lotteryRepository: LotteryRepository = mockk(relaxed = true)
    private lateinit var sut: LotteryUsecase

    @Before
    fun setUp() {
        sut = GetLotteryDataUseCase(lotteryRepository)
    }

    @Test
    fun `test invoke returns data from repository`() = runTest {
        val lotteryData = listOf(
            Lottery(
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

        every { lotteryRepository.getLotteryData() }.returns(flowOf(lotteryData))

        val result = sut.invoke().first()

        assert(result == lotteryData)
    }

    @Test
    fun `test refresh fetches and returns data from repository`() = runTest {
        val lotteryData = listOf(
            Lottery(
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

        coEvery { lotteryRepository.refreshLotteryData() }.returns(DataResult.Success(Unit))
        every {lotteryRepository.getLotteryData()  }returns (flowOf(lotteryData))

        val result = sut.refresh()

        assert(result == DataResult.Success(lotteryData))
    }
}
