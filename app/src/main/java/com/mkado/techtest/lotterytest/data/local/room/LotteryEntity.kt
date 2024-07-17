package com.mkado.techtest.lotterytest.data.local.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lottery_data")
data class LotteryEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "bonus_ball") val bonusBall: String,
    @ColumnInfo(name = "draw_date") val drawDate: String,
    @ColumnInfo(name = "number_1") val number1: String,
    @ColumnInfo(name = "number_2") val number2: String,
    @ColumnInfo(name = "number_3") val number3: String,
    @ColumnInfo(name = "number_4") val number4: String,
    @ColumnInfo(name = "number_5") val number5: String,
    @ColumnInfo(name = "number_6") val number6: String,
    @ColumnInfo(name = "top_prize") val topPrize: Long
)
