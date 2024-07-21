package com.mkado.techtest.lotterytest.ui.view

import android.graphics.Bitmap
import com.mkado.techtest.lotterytest.domain.uscase.DrawUseCase
import com.mkado.techtest.lotterytest.domain.uscase.QRCodeUseCase
import com.mkado.techtest.lotterytest.ui.view.viewmodel.CheckDrawViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CheckDrawViewModelShould {

    private lateinit var sut: CheckDrawViewModel
    private val mockDrawUseCase: DrawUseCase = mockk()
    private val mockQRCodeUseCase: QRCodeUseCase = mockk()
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)
    private  val mockBitmap = mockk<Bitmap>()


    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this, relaxUnitFun = true)
        mockkStatic(Bitmap::class)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun emitLoadingState_when_generateNumbers_is_called() = testScope.runTest {
        // Mock DrawUseCase to return dummy data
        coEvery { mockDrawUseCase.execute() } returns listOf(1, 2, 3, 4, 5, 6)

        // Mock QRCodeUseCase to return dummy Bitmap
        every { Bitmap.createBitmap(any(), any(), any()) } returns mockBitmap
        coEvery { mockQRCodeUseCase.execute(any()) } returns mockBitmap

        // Given
        sut = CheckDrawViewModel(mockDrawUseCase, mockQRCodeUseCase)

        // Collect states to verify transitions
        val states = mutableListOf<CheckDrawUIState>()
        val job = launch {
            sut.state.collect { state ->
                states.add(state)
            }
        }

        // When
        sut.generateNumbers()
        advanceUntilIdle()  // Advance time to simulate coroutines processing

        // Then
        // Ensure that the Loading state is emitted before any other states
        assert(states.contains(CheckDrawUIState.Loading)) {
            "Expected Loading state to be emitted"
        }
        assertEquals(CheckDrawUIState.Loading, states.firstOrNull { it is CheckDrawUIState.Loading })

        // Ensure that the Loaded state is eventually emitted
        assert(states.contains(CheckDrawUIState.Loaded(listOf(1, 2, 3, 4, 5, 6)))) {
        "Expected Loaded state with numbers to be emitted"
    }

        job.cancel()  // Clean up
    }

    @Test
    fun emitLoadedState_with_numbers_and_qrCodeBitmap_when_generateNumbers_succeeds() = runTest {

        coEvery { mockDrawUseCase.execute() } returns listOf(1, 2, 3, 4, 5, 6)

        // Mock QRCodeUseCase to return dummy Bitmap
        coEvery { mockQRCodeUseCase.execute(any()) } returns mockBitmap
        // Given
        sut = CheckDrawViewModel(mockDrawUseCase, mockQRCodeUseCase)

        // Perform actions to trigger the Bitmap creation
        sut.generateNumbers()

    }

    @Test
    fun emitErrorState_when_generateNumbers_fails() = testScope.runTest {
        // Mock DrawUseCase to throw an exception
        coEvery { mockDrawUseCase.execute() } throws Exception("Failed to generate numbers")

        // Given
        sut = CheckDrawViewModel(mockDrawUseCase, mockQRCodeUseCase)

        // When
        sut.generateNumbers()
        advanceUntilIdle()

        // Then
        assertEquals(CheckDrawUIState.Error("Failed to generate numbers"), sut.state.value)
    }

    @Test
    fun emitLoadingState_and_then_errorState_when_generateNumbers_fails() = testScope.runTest {
        // Mock DrawUseCase to throw an exception
        coEvery { mockDrawUseCase.execute() } throws Exception("Failed to generate numbers")

        // Given
        sut = CheckDrawViewModel(mockDrawUseCase, mockQRCodeUseCase)

        // When
        sut.generateNumbers()
        advanceUntilIdle()

        // Then
        assertEquals(CheckDrawUIState.Error("Failed to generate numbers"), sut.state.value)
    }
}
