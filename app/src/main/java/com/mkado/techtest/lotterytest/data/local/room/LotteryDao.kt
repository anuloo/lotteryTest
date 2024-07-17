package com.mkado.techtest.lotterytest.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LotteryDao {
    @Query("SELECT * FROM lottery_data")
    fun getLotteries(): Flow<List<LotteryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLotteries(lotteries: List<LotteryEntity>)
}