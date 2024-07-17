package com.mkado.techtest.lotterytest.domain.repository

import com.mkado.techtest.lotterytest.domain.model.Lottery
import kotlinx.coroutines.flow.Flow

interface LotteryRepository {
    fun getLotteryData(): Flow<List<Lottery>>
    suspend fun refreshLotteryData()
}