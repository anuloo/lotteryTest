package com.mkado.techtest.lotterytest.domain.model

data class Lottery(
    val bonusBall: String,
    val drawDate: String,
    val id: String,
    val number1: String,
    val number2: String,
    val number3: String,
    val number4: String,
    val number5: String,
    val number6: String,
    val topPrize: Long
)

