package com.mkado.techtest.lotterytest.data.repository

import com.mkado.techtest.lotterytest.data.local.room.LotteryDao
import com.mkado.techtest.lotterytest.data.mappers.toDomain
import com.mkado.techtest.lotterytest.data.mappers.toEntity
import com.mkado.techtest.lotterytest.data.remote.api.LotteryApi
import com.mkado.techtest.lotterytest.data.service.LotteryService
import com.mkado.techtest.lotterytest.domain.model.Lottery
import com.mkado.techtest.lotterytest.domain.repository.LotteryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LotteryRepositoryImp @Inject constructor(
    private val service: LotteryService,
    private val dao: LotteryDao
):LotteryRepository {
    override fun getLotteryData(): Flow<List<Lottery>> {
        return dao.getLotteries().distinctUntilChanged().map { list ->
            list.map { it.toDomain() }
        }

    }

    override suspend fun refreshLotteryData() {
        val data = service.fetchLotteryData()
        dao.insertLotteries(data.map { it.toEntity() })
    }
}