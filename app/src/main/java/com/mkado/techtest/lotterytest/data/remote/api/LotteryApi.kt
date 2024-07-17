package com.mkado.techtest.lotterytest.data.remote.api

import com.mkado.techtest.lotterytest.BuildConfig
import com.mkado.techtest.lotterytest.data.remote.responses.LotteryData
import com.mkado.techtest.lotterytest.data.remote.responses.LotteryResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject


interface LotteryApiService {
    suspend fun getLotteries(): List<LotteryResponse>
}

class LotteryApi @Inject constructor(private val client: HttpClient) : LotteryApiService {
    override suspend fun getLotteries(): List<LotteryResponse> {
        val response: LotteryData = client.get(BuildConfig.LOTTERY_API_ROOT_URL+"lotto-result-api").body()
        return response.draws
    }
}
