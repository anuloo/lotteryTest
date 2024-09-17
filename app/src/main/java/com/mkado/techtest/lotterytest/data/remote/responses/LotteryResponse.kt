package com.mkado.techtest.lotterytest.data.remote.responses


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LotteryData(
  val draws: List<LotteryResponse>
)

@Serializable
data class LotteryResponse(
    @SerialName("bonus-ball")
    val bonusBall: String,
    @SerialName("drawDate")
    val drawDate: String,
    @SerialName("id")
    val id: String,
    @SerialName("number1")
    val number1: String,
    @SerialName("number2")
    val number2: String,
    @SerialName("number3")
    val number3: String,
    @SerialName("number4")
    val number4: String,
    @SerialName("number5")
    val number5: String,
    @SerialName("number6")
    val number6: String,
    @SerialName("topPrize")
    val topPrize: Long
)

