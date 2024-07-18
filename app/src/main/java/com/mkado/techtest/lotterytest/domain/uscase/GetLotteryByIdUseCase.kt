package com.mkado.techtest.lotterytest.domain.uscase

import com.mkado.techtest.lotterytest.domain.model.Lottery
import com.mkado.techtest.lotterytest.domain.repository.LotteryRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

interface LotteryByIdUseCase {
    suspend fun getLotteryById(lotteryId: String): Lottery?
}

class GetLotteryByIdUseCase @Inject constructor(
    private val repository: LotteryRepository
) : LotteryByIdUseCase {

    override suspend fun getLotteryById(lotteryId: String): Lottery? {
        return repository.getLotteryById(lotteryId).firstOrNull()
    }
}