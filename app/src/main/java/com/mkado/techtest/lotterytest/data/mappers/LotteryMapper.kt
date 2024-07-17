package com.mkado.techtest.lotterytest.data.mappers

import com.mkado.techtest.lotterytest.data.local.room.LotteryEntity
import com.mkado.techtest.lotterytest.data.remote.responses.LotteryResponse
import com.mkado.techtest.lotterytest.domain.model.Lottery

fun LotteryResponse.toEntity(): LotteryEntity {
    return LotteryEntity(
        id = id,
        bonusBall = bonusBall,
        drawDate = drawDate,
        number1 = number1,
        number2 = number2,
        number3 = number3,
        number4 = number4,
        number5 = number5,
        number6 = number6,
        topPrize = topPrize
    )
}

fun LotteryEntity.toDomain(): Lottery {
    return Lottery(
        id = id,
        bonusBall = bonusBall,
        drawDate = drawDate,
        number1 = number1,
        number2 = number2,
        number3 = number3,
        number4 = number4,
        number5 = number5,
        number6 = number6,
        topPrize = topPrize
    )
}
      