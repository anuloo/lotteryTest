package com.mkado.techtest.lotterytest.domain.uscase

import com.mkado.techtest.lotterytest.domain.model.Lottery
import com.mkado.techtest.lotterytest.domain.repository.LotteryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

interface LotteryByIdUseCase {
    suspend fun getLotteryById(lotteryId: String): Flow<Lottery?>
}

class GetLotteryByIdUseCase @Inject constructor(
    private val repository: LotteryRepository
) : LotteryByIdUseCase {

    override suspend fun getLotteryById(lotteryId: String): Flow<Lottery?> {
        return repository.getLotteryById(lotteryId)
    }
}