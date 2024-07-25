package com.mkado.techtest.lotterytest.domain.uscase

import com.mkado.techtest.lotterytest.common.DataResult
import com.mkado.techtest.lotterytest.data.remote.network.NetworkError
import com.mkado.techtest.lotterytest.domain.model.Lottery
import com.mkado.techtest.lotterytest.domain.repository.LotteryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

interface LotteryUsecase {
    operator fun invoke(): Flow<List<Lottery>>
    suspend fun refresh():DataResult<List<Lottery>, NetworkError>
}

class GetLotteryDataUseCase @Inject constructor(
    private val repository: LotteryRepository
) : LotteryUsecase {

    override operator fun invoke(): Flow<List<Lottery>> {
        return repository.getLotteryData()
    }

    override suspend fun refresh(): DataResult<List<Lottery>, NetworkError> {
        // Refresh the data
        return when (val result = repository.refreshLotteryData()) {
            is DataResult.Success -> {
                // Fetch the updated data and return it
                val updatedData = repository.getLotteryData().first()
                DataResult.Success(updatedData)
            }
            is DataResult.Error -> {
                // Forward the error from the repository
                DataResult.Error(
                    message = result.message,
                    code = result.code,
                    error = result.error
                )
            }
        }
    }
}
