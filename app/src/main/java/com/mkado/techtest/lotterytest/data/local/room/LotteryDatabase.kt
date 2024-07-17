package com.mkado.techtest.lotterytest.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LotteryEntity::class], version = 1)
abstract class LotteryDatabase : RoomDatabase() {
    abstract fun lotteryDao(): LotteryDao
}