package com.mkado.techtest.lotterytest.domain.uscase

import com.mkado.techtest.lotterytest.domain.model.Lottery
import com.mkado.techtest.lotterytest.domain.repository.LotteryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

interface LotteryUsecase {
    operator fun invoke(): Flow<List<Lottery>>
    suspend fun refresh():List<Lottery>
}

class GetLotteryDataUseCase @Inject constructor(
    private val repository: LotteryRepository
):LotteryUsecase {
    override operator fun invoke(): Flow<List<Lottery>> {
        return repository.getLotteryData()
    }

    override suspend fun refresh():List<Lottery> {
        repository.refreshLotteryData()
        return repository.getLotteryData().first()
    }
}