package com.mkado.techtest.lotterytest.data.service

import com.mkado.techtest.lotterytest.data.remote.api.LotteryApi
import javax.inject.Inject

class LotteryService @Inject constructor(private val api: LotteryApi) {
    suspend fun fetchLotteryData() = api.getLotteries()
}
