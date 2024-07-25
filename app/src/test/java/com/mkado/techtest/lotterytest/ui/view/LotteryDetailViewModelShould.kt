package com.mkado.techtest.lotterytest.ui.view

import com.mkado.techtest.lotterytest.domain.model.Lottery
import com.mkado.techtest.lotterytest.domain.uscase.LotteryByIdUseCase
import com.mkado.techtest.lotterytest.ui.view.viewmodel.LotteryDetailViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test


class LotteryDetailViewModelShould {

    private lateinit var sut: LotteryDetailViewModel
    private val mockGetLotteryByIdUseCase: LotteryByIdUseCase = mockk(relaxed = true)
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)
    private val lottery = Lottery(
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

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
        unmockkAll()
    }


    @Test
    fun emitLotteryDetail_when_getLotteryById_succeeds() = testScope.runTest {
        // Given
        coEvery { mockGetLotteryByIdUseCase.getLotteryById("draw-88") } returns flowOf(lottery)

        sut = LotteryDetailViewModel(mockGetLotteryByIdUseCase)

        // When
        sut.getLotteryById("draw-88")
        advanceUntilIdle()

        // Then
        assertEquals(lottery, sut.state.value)
    }
}
