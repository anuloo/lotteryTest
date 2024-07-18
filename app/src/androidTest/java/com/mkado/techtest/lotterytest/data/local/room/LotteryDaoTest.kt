package com.mkado.techtest.lotterytest.data.local.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@HiltAndroidTest
class LotteryDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: LotteryDatabase
    private lateinit var dao: LotteryDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.lotteryDao()
    }

    @After
    fun teardown() {
        database.close()
    }

   @Test
    fun insertLotteryEntity() = runTest {
        // Creating a list with a single lottery entity
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

        // Inserting the lottery entity
        dao.insertLotteries(lotteryEntities)

        // Retrieving all lotteries
        val allLotteries = dao.getLotteries().first()

        // Asserting that the inserted entity is present
        assertThat(allLotteries).containsExactlyElementsIn(lotteryEntities)
    }

    @Test
    fun loadLotteries() = runTest {

        val allLotteries = dao.getLotteries().first()

        // Asserting that the list is empty or matches expected empty state
        assertThat(allLotteries).isEmpty() // Adjust as needed based on initial state
    }

}