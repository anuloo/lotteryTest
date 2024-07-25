package com.mkado.techtest.lotterytest.ui.view

import com.mkado.techtest.lotterytest.domain.model.Lottery
import com.mkado.techtest.lotterytest.domain.uscase.LotteryUsecase
import com.mkado.techtest.lotterytest.common.DataResult
import com.mkado.techtest.lotterytest.ui.view.viewmodel.LotteryListViewModel
import io.mockk.*
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.*


class LotteryListViewModelShould {

    private lateinit var sut: LotteryListViewModel
    private val mockGetLotteryDataUseCase: LotteryUsecase = mockk()
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)
    private  val lotteryList = listOf(
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
    private val error =  DataResult.Error("error",-1, null)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun teardown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun emitLoadingState_and_then_loadedState_when_fetchLotteryData_succeeds() = testScope.runTest {
        // Given

        coEvery { mockGetLotteryDataUseCase.invoke() } returns flowOf(lotteryList)
        sut = LotteryListViewModel(mockGetLotteryDataUseCase)

        // Collect states to verify transitions
        val states = mutableListOf<LotteryUIState>()
        val job = launch {
            sut.state.collect { state ->
                states.add(state)
            }
        }

        // When
        advanceUntilIdle()  // Simulates the delay and coroutine processing

        // Then
        // Ensure that the Loading state is emitted first
        assert(states.contains(LotteryUIState.Loading)) {
            "Expected Loading state to be emitted"
        }
        assertEquals(LotteryUIState.Loading, states.firstOrNull { it is LotteryUIState.Loading })

        // Ensure that the Loaded state is eventually emitted
        assert(states.contains(LotteryUIState.Loaded(lotteryList))) {
            "Expected Loaded state with lottery data to be emitted"
        }

        job.cancel()  // Clean up
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun emitLoadingState_and_then_loadedState_when_refreshData_succeeds() = testScope.runTest {
        // Given

        coEvery { mockGetLotteryDataUseCase.refresh() } returns DataResult.Success(lotteryList)
        every { mockGetLotteryDataUseCase.invoke() } returns flowOf(lotteryList)
        sut = LotteryListViewModel(mockGetLotteryDataUseCase)

        // Collect states to verify transitions
        val states = mutableListOf<LotteryUIState>()
        val job = launch {
            sut.state.toList(states)
        }

        // When
        sut.refreshData()
        advanceUntilIdle()

        // Then
        assertEquals(LotteryUIState.Loading, states[0])
        assertEquals(LotteryUIState.Loaded(lotteryList), states[1])

        job.cancel()
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun emitErrorState_when_refreshData_fails() = testScope.runTest {
        // Given
        coEvery { mockGetLotteryDataUseCase.refresh() } returns error
        every { mockGetLotteryDataUseCase.invoke() } returns flowOf(lotteryList)
        sut = LotteryListViewModel(mockGetLotteryDataUseCase)

        // Collect states to verify transitions
        val states = mutableListOf<LotteryUIState>()
        val job = launch {
            sut.state.toList(states)
        }

        // When
        sut.refreshData()
        advanceUntilIdle()

        // Then
        assertEquals(LotteryUIState.Loading, states[0])
        assertEquals(LotteryUIState.Error("Refresh failed"), states[1])

        job.cancel()
    }


}
