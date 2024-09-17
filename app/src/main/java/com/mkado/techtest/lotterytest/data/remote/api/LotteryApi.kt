package com.mkado.techtest.lotterytest.data.remote.api

import com.mkado.techtest.lotterytest.BuildConfig
import com.mkado.techtest.lotterytest.common.DataResult
import com.mkado.techtest.lotterytest.data.remote.network.NetworkError
import com.mkado.techtest.lotterytest.data.remote.responses.LotteryData
import com.mkado.techtest.lotterytest.data.remote.responses.LotteryResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

interface LotteryApiService {
    suspend fun getLotteries(): DataResult<List<LotteryResponse>, NetworkError>
}

class LotteryApi @Inject constructor(private val client: HttpClient) : LotteryApiService {
    override suspend fun getLotteries(): DataResult<List<LotteryResponse>, NetworkError> {
        return try {
            val response: LotteryData = client.get(BuildConfig.LOTTERY_API_ROOT_URL + "db").body()
            DataResult.Success(response.draws)
        } catch (e: io.ktor.serialization.ContentConvertException) {
            DataResult.Error(message = e.message, code = -1, error = NetworkError.SerializationError(e.message, e))
        } catch (e: Exception) {
            DataResult.Error(message = e.message, code = -1, error = NetworkError.UnknownError(e.message, e))
        }
    }
}